package com.example.friendshipservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.friendshipservice.client")
public class FriendshipserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendshipserviceApplication.class, args);
	}

}
