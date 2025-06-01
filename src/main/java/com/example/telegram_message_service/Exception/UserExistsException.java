package com.example.telegram_message_service.Exception;

public class UserExistsException extends RuntimeException {
  public UserExistsException(String message) {
    super(message);
  }
}
