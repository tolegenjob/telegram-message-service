package com.example.telegram_message_service.Exception;

public class NotLinkedException extends RuntimeException {
    public NotLinkedException(String message) {
        super(message);
    }
}
