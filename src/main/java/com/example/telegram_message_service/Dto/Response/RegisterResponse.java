package com.example.telegram_message_service.Dto.Response;

import com.example.telegram_message_service.Entity.User;

import java.time.LocalDateTime;

public record RegisterResponse(
        String username,
        String name,
        LocalDateTime createdAt
) { public static RegisterResponse toDto(User user) {
        return new RegisterResponse(user.getUsername(), user.getName(), user.getCreatedAt());
    }
}
