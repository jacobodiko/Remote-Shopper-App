package com.Registration.UserManagementService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserManagementServiceApplication {

	public static void main(String[] args) {SpringApplication.run(UserManagementServiceApplication.class, args);
	}

}

/*User Service: This microservice handles user registration,
authentication, and management.
It includes functionalities like user signup, login, profile management,
 and password reset.*/