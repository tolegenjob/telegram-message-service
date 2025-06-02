package com.example.telegram_message_service.Service;

import com.example.telegram_message_service.Dto.Request.LoginRequest;
import com.example.telegram_message_service.Dto.Request.RegisterRequest;
import com.example.telegram_message_service.Entity.User;
import com.example.telegram_message_service.Exception.AlreadyExistsException;
import com.example.telegram_message_service.Repository.UserRepository;
import com.example.telegram_message_service.Security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;

    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new AlreadyExistsException("User with this username already exists");
        }
        User user = userService.createUser(request);
        log.info("User {} has been registered", user.getUsername());
        return user;
    }

    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        log.info("User {} logged in", authentication.getName());
        return jwtService.generateToken(authentication.getName());
    }

}
