package com.example.telegram_message_service.Service;

import com.example.telegram_message_service.Dto.Request.MessageRequest;
import com.example.telegram_message_service.Entity.Message;
import com.example.telegram_message_service.Entity.User;
import com.example.telegram_message_service.Repository.MessageRepository;
import com.example.telegram_message_service.Telegram.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final UserService userService;
    private final MessageRepository messageRepository;
    private final TelegramService telegramService;

    @Transactional
    public void sendMessage(String username, MessageRequest request) {
        User user = userService.getUserByUsername(username);

        String fullMessage = "%s, я получил от тебя сообщение:\n%s".formatted(user.getName(), request.content());

        telegramService.sendMessageToUser(user, fullMessage);

        Message message = new Message();
        message.setContent(request.content());
        message.setSentAt(LocalDateTime.now());
        message.setUser(user);

        messageRepository.save(message);
        log.info("Message {} has been sent to user {}", request.content(), username);
    }

    @Transactional(readOnly = true)
    public List<Message> getMessagesByUsername(String username) {
        User user = userService.getUserByUsername(username);
        log.info("Getting messages for user {}", user.getUsername());
        return messageRepository.findAllByUserOrderBySentAtDesc(user);
    }

}
