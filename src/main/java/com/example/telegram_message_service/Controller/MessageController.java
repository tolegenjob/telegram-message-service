package com.example.telegram_message_service.Controller;

import com.example.telegram_message_service.Dto.Request.MessageRequest;
import com.example.telegram_message_service.Dto.Response.MessageResponse;
import com.example.telegram_message_service.Service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(
            @RequestBody @Valid MessageRequest request,
            @AuthenticationPrincipal String username
    ) {
        MessageResponse response = MessageResponse.toDto(messageService.sendMessage(username, request));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<MessageResponse>> getAllMessages(
            @AuthenticationPrincipal String username,
            Pageable pageable
    ) {
        Page<MessageResponse> responses = messageService.getMessagesByUsername(username, pageable)
                .map(MessageResponse::toDto);
        return ResponseEntity.ok(responses);
    }

}
