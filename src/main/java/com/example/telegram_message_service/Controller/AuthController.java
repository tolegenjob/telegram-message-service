package com.example.telegram_message_service.Controller;

import com.example.telegram_message_service.Dto.Request.LoginRequest;
import com.example.telegram_message_service.Dto.Request.RegisterRequest;
import com.example.telegram_message_service.Dto.Response.LoginResponse;
import com.example.telegram_message_service.Dto.Response.RegisterResponse;
import com.example.telegram_message_service.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        RegisterResponse response = RegisterResponse.toDto(authService.register(request));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        String token = authService.login(request);
        LoginResponse response = new LoginResponse(request.username(), token);
        return ResponseEntity.ok(response);
    }

}
