package com.example.telegram_message_service.Controller;

import com.example.telegram_message_service.Dto.Request.UserUpdateRequest;
import com.example.telegram_message_service.Dto.Response.TelegramTokenResponse;
import com.example.telegram_message_service.Dto.Response.UserResponse;
import com.example.telegram_message_service.Dto.Response.UserUpdateResponse;
import com.example.telegram_message_service.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal String username) {
        UserResponse userResponse = UserResponse.toDTO(userService.getUserByUsername(username));
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping
    public ResponseEntity<UserUpdateResponse> updateUser(
            @AuthenticationPrincipal String username,
            @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        UserUpdateResponse userUpdateResponse = UserUpdateResponse.toDTO(userService.updateUserByUsername(username, userUpdateRequest));
        return ResponseEntity.ok(userUpdateResponse);
    }

    @PostMapping("/telegram-token")
    public ResponseEntity<TelegramTokenResponse> generateTelegramToken(@AuthenticationPrincipal String username) {
        TelegramTokenResponse telegramTokenResponse = new TelegramTokenResponse(userService.generateTelegramToken(username));
        return ResponseEntity.ok(telegramTokenResponse);
    }

    @DeleteMapping("/telegram-token")
    public ResponseEntity<Void> unlinkTelegram(@AuthenticationPrincipal String username) {
        userService.unlinkTelegram(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

}
