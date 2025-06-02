package com.example.telegram_message_service.Telegram;

import com.example.telegram_message_service.Config.TelegramBotProperties;
import com.example.telegram_message_service.Entity.User;
import com.example.telegram_message_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final UserRepository userRepository;
    private final TelegramBotProperties telegramBotProperties;

    @Override
    public String getBotUsername() {
        return telegramBotProperties.username();
    }

    @Override
    public String getBotToken() {
        return telegramBotProperties.token();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String usernameFromUser = update.getMessage().getChat().getUserName();
            String tokenFromMessage = update.getMessage().getText();

            if (userRepository.findByTelegramChatId(chatId).isPresent()) {
                sendMessage(chatId, "Вы уже авторизованы.");
                log.info("User {} is already authorized", usernameFromUser);
                return;
            }

            if (tokenFromMessage.equalsIgnoreCase("/start")) {
                sendMessage(chatId, """
                Добро пожаловать!
                
                Чтобы привязать ваш аккаунт, выполните вход в веб-приложение telegram-message-service.
                Затем перейдите в свой профиль и сгенерируйте Telegram-токен.
                
                После этого отправьте сюда токен одним сообщением.
                """);
                return;
            }

            Optional<User> candidate = userRepository.findByTelegramToken(tokenFromMessage);
            if (candidate.isPresent()) {
                User user = candidate.get();
                user.setTelegramChatId(chatId);
                user.setTelegramToken(null);
                userRepository.save(user);
                sendMessage(chatId, "Успешная привязка аккаунта!");
                log.info("User {} has been authorized", usernameFromUser);
            } else {
                sendMessage(chatId, "Неверный токен. Убедитесь, что вы скопировали его правильно.");
                log.error("User {} has provided wrong token", usernameFromUser);
            }
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
            log.error("Error sending message to Telegram: {}", e.getMessage());
        }
    }

}
