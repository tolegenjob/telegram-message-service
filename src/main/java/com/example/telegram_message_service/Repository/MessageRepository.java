package com.example.telegram_message_service.Repository;

import com.example.telegram_message_service.Entity.Message;
import com.example.telegram_message_service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByUserOrderBySentAtDesc(User user);

}
