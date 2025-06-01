package com.example.telegram_message_service.Exception;

public class ApiException extends RuntimeException {
  public ApiException(String message) {
    super(message);
  }
}
