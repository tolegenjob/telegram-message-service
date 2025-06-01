package com.example.telegram_message_service.Telegram;

import com.example.telegram_message_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final UserRepository userRepository;

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String usernameFromUser = update.getMessage().getText();

            userRepository.findByTelegramToken(chatId.toString()).ifPresentOrElse(
                    user -> log.info("Пользователь уже привязан: {}", user.getUsername()),
                    () -> userRepository.findByUsername(usernameFromUser).ifPresentOrElse(
                            user -> {
                                user.setTelegramToken(chatId.toString());
                                userRepository.save(user);
                                sendMessage(chatId, "Привязка прошла успешно для пользователя: " + user.getUsername());
                            },
                            () -> sendMessage(chatId, "Пользователь с логином '" + usernameFromUser + "' не найден.")
                    )
            );
        }
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения в Telegram: {}", e.getMessage());
        }
    }

}
