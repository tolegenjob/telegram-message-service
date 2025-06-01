package com.example.telegram_message_service.Controller;

import com.example.telegram_message_service.Dto.Request.MessageRequest;
import com.example.telegram_message_service.Dto.Response.MessageResponse;
import com.example.telegram_message_service.Service.MessageService;
import com.example.telegram_message_service.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody MessageRequest request,
                                            @AuthenticationPrincipal String username) {
        messageService.sendMessage(username, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MessageResponse>> getAllMessages(@AuthenticationPrincipal String username) {
        List<MessageResponse> messages = messageService.getMessagesByUsername(username)
                .stream()
                .map(MessageResponse::toDto)
                .toList();
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/telegram-token")
    public ResponseEntity<String> setTelegramToken(@RequestBody String token,
                                                   @AuthenticationPrincipal String username) {
        userService.setTelegramTokenForUser(username, token.trim());
        return ResponseEntity.ok("Telegram token successfully set");
    }

}
