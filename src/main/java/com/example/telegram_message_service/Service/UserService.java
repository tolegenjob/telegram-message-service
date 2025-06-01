package com.example.telegram_message_service.Service;

import com.example.telegram_message_service.Entity.User;
import com.example.telegram_message_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void setTelegramTokenForUser(String username, String token) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setTelegramToken(token);
        userRepository.save(user);
        log.info("Telegram token for user {} has been set", user.getUsername());
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        log.info("Getting user by username {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

}
