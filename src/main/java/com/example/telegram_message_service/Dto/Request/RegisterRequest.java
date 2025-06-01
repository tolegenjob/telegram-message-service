package com.example.telegram_message_service.Dto.Request;

public record RegisterRequest(
        String username,
        String password,
        String name
) {
}
