package com.example.telegram_message_service.Util;

import com.example.telegram_message_service.Entity.User;
import com.example.telegram_message_service.Exception.NotFoundException;
import com.example.telegram_message_service.Repository.UserRepository;

public class UserUtil {

    public static User findUserOrThrow(UserRepository repository, String username) {
        return repository.findByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException("User with username %s not found".formatted(username)));
    }

}
