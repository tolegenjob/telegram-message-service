package com.example.telegram_message_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TelegramMessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramMessageServiceApplication.class, args);
	}

}
