package com.example.telegram_message_service.Dto.Request;

import jakarta.validation.constraints.NotBlank;

public record MessageRequest(

        @NotBlank(message = "Content must not be blank")
        String content
) {
}
