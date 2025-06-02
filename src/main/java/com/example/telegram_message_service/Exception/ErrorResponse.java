package com.example.telegram_message_service.Exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse (
        HttpStatus status,
        String message,
        String error,
        LocalDateTime timestamp ) {
}
