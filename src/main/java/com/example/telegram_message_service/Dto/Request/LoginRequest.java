package com.example.telegram_message_service.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotBlank(message = "Username must not be blank")
        @Size(min = 4, max = 20, message = "Username must be between 4 and 20 symbols")
        String username,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 8, max = 100, message = "Password must be at least 8 symbols")
        String password
) {
}
