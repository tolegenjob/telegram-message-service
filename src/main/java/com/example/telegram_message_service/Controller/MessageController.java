package com.example.telegram_message_service.Controller;

import com.example.telegram_message_service.Dto.Request.MessageRequest;
import com.example.telegram_message_service.Dto.Response.MessageResponse;
import com.example.telegram_message_service.Service.MessageService;
import com.example.telegram_message_service.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody MessageRequest request,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        messageService.sendMessage(userDetails.getUsername(), request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MessageResponse>> getAllMessages(@AuthenticationPrincipal UserDetails userDetails) {
        List<MessageResponse> messages = messageService.getMessagesByUsername(userDetails.getUsername())
                .stream()
                .map(MessageResponse::toDto)
                .toList();
        return ResponseEntity.ok(messages);
    }

}
