package com.example.telegram_message_service.Dto.Response;

import com.example.telegram_message_service.Entity.Message;

import java.time.LocalDateTime;

public record MessageResponse(
        LocalDateTime sentAt,
        String content
) { public static MessageResponse toDto(Message msg) {
        return new MessageResponse(msg.getSentAt(), msg.getContent());
    }
}
