package com.Shopping.CartItemsManagementService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CartItemsManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartItemsManagementServiceApplication.class, args);
	}

}
