package com.example.telegram_message_service.Dto.Request;

public record LoginRequest(
        String username,
        String password
) {
}
