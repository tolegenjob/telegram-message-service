package com.example.telegram_message_service.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Username must not be blank")
        @Size(min = 4, max = 20, message = "Username must be between 4 and 20 symbols")
        String username,

        @NotBlank(message = "Password must not be blank")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,100}$",
                message = "Password must be at least 8 symbols, including uppercase and lowercase letters, numbers and special characters [@$!%*?&]"
        )
        String password,

        @NotBlank(message = "Name must not be blank")
        @Size(min = 2, max = 50, message = "Username must be between 2 and 50 symbols")
        String name
) {
}
