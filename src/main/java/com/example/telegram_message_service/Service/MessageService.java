package com.example.telegram_message_service.Service;

import com.example.telegram_message_service.Dto.Request.MessageRequest;
import com.example.telegram_message_service.Entity.Message;
import com.example.telegram_message_service.Entity.User;
import com.example.telegram_message_service.Exception.NotLinkedException;
import com.example.telegram_message_service.Repository.MessageRepository;
import com.example.telegram_message_service.Telegram.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final UserService userService;
    private final MessageRepository messageRepository;
    private final TelegramService telegramService;

    @Transactional
    public Message sendMessage(String username, MessageRequest request) {
        User user = userService.getUserByUsername(username);
        if (user.getTelegramChatId() == null) {
            throw new NotLinkedException("The user is not linked to Telegram. Please link first.");
        }
        telegramService.sendMessageToUser(user, request.content());
        Message message = new Message();
        message.setContent(request.content());
        message.setSentAt(LocalDateTime.now());
        message.setUser(user);
        log.info("Message {} has been sent to user {}", request.content(), username);
        return messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public Page<Message> getMessagesByUsername(String username, Pageable pageable) {
        User user = userService.getUserByUsername(username);
        log.info("Getting messages for user {}", user.getUsername());
        return messageRepository.findAllByUserOrderBySentAtDesc(user, pageable);
    }

}
