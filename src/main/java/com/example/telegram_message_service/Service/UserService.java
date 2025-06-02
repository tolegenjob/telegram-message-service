package com.example.telegram_message_service.Service;

import com.example.telegram_message_service.Dto.Request.RegisterRequest;
import com.example.telegram_message_service.Dto.Request.UserUpdateRequest;
import com.example.telegram_message_service.Entity.User;
import com.example.telegram_message_service.Exception.AlreadyExistsException;
import com.example.telegram_message_service.Exception.NotLinkedException;
import com.example.telegram_message_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.telegram_message_service.Util.UserUtil.findUserOrThrow;
import static com.example.telegram_message_service.Util.TokenGenerator.generateToken;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setName(request.name());
        User saved = userRepository.save(user);
        log.info("User {} has been created", saved.getUsername());
        return userRepository.save(user);
    }

    @Transactional
    public User updateUserByUsername(String username, UserUpdateRequest request){
        User user = findUserOrThrow(userRepository, username);
        if (request.name() != null && !request.name().isBlank()) {
            user.setName(request.name());
        }
        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }
        User saved = userRepository.save(user);
        log.info("User {} has been updated", saved.getUsername());
        return saved;
    }

    @Transactional
    public String generateTelegramToken(String username) {
        User user = findUserOrThrow(userRepository, username);
        String telegramToken;
        if (user.getTelegramChatId() == null) {
            telegramToken = generateToken();
            user.setTelegramToken(telegramToken);
            userRepository.save(user);
        } else {
            throw new AlreadyExistsException("User %s is already linked with telegram".formatted(username));
        }
        log.info("Generated telegram token for user {}", username);
        return telegramToken;

    }

    @Transactional
    public void unlinkTelegram(String username) {
        User user = findUserOrThrow(userRepository, username);
        if (user.getTelegramChatId() != null) {
            user.setTelegramChatId(null);
            userRepository.save(user);
        } else {
            throw new NotLinkedException("User %s is not linked with telegram".formatted(username));
        }
        log.info("Deleted telegram token for user {}", username);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        User user = findUserOrThrow(userRepository, username);
        log.info("Got user {}", username);
        return user;
    }

    @Transactional
    public void deleteUserByUsername(String username) {
        User user = findUserOrThrow(userRepository, username);
        userRepository.delete(user);
        log.info("User {} has been deleted", username);
    }

}
