package com.example.telegram_message_service.Repository;

import com.example.telegram_message_service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<User> findByTelegramToken(String token);
    Optional<User> findByTelegramChatId(Long telegramChatId);

}
