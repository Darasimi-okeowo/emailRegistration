package com.darasimi.emailRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EmailRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailRegistrationApplication.class, args);
	}

}
