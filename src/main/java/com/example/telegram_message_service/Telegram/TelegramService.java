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
        Long chatId = user.getTelegramChatId();
        if (chatId == null) {
            log.error("User {} had not linked Telegram", user.getUsername());
            return;
        }
        String fullMessage = "%s, я получил от тебя сообщение:\n%s".formatted(user.getName(), content);
        telegramBot.sendMessage(chatId, fullMessage);
    }

}
