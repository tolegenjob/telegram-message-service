package com.example.telegram_message_service.Repository;

import com.example.telegram_message_service.Entity.Message;
import com.example.telegram_message_service.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findAllByUserOrderBySentAtDesc(User user, Pageable pageable);

}
