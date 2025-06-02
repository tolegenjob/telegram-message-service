package com.example.telegram_message_service.Dto.Response;

import com.example.telegram_message_service.Entity.User;

import java.time.LocalDateTime;

public record UserUpdateResponse(
        String username,
        String name,
        LocalDateTime updatedAt
) { public static UserUpdateResponse toDTO(User user) {
    return new UserUpdateResponse(
            user.getUsername(),
            user.getName(),
            user.getUpdatedAt()
    );
}
}
