package com.example.telegram_message_service.Telegram;

import com.example.telegram_message_service.Entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramService {

    private final TelegramBot telegramBot;

    public void sendMessageToUser(User user, String content) {
        String telegramToken = user.getTelegramToken();
        if (telegramToken == null) {
            log.warn("Пользователь {} не привязал Telegram", user.getUsername());
            return;
        }

        Long chatId;
        try {
            chatId = Long.parseLong(telegramToken);
        } catch (NumberFormatException e) {
            log.error("Неверный формат telegramToken: {}", telegramToken);
            return;
        }

        String message = String.format(
                "%s, я получил от тебя сообщение:\n%s",
                user.getName(), content
        );

        telegramBot.sendMessage(chatId, message);
    }

}
