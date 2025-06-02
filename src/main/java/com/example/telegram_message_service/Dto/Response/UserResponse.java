package com.example.telegram_message_service.Dto.Response;

import com.example.telegram_message_service.Entity.User;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String username,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { public static UserResponse toDTO(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
