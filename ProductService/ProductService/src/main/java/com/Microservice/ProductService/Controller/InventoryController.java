package com.Microservice.ProductService.Controller;

import com.Microservice.ProductService.Entity.Product;
import com.Microservice.ProductService.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId,
                                               @RequestParam int quantityChange) {
        Product updatedProduct = inventoryService.updateStock(productId, quantityChange);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }
}