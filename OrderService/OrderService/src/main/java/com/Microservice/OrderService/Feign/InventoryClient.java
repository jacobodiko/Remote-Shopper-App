package com.Microservice.OrderService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8081/api/inventory")
public interface InventoryClient {

    @PostMapping("/update/{productId}")
    boolean updateStock(@PathVariable Long productId, @RequestParam int quantityChange);
}
