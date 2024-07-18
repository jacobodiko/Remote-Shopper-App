package com.Microservice.ProductService.Service;

import com.Microservice.ProductService.Entity.Product;
import com.Microservice.ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InventoryService {
@Autowired
private final ProductRepository productRepository;

    @Autowired
    public InventoryService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public Product updateStock(Long productId, int quantityChange) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int newQuantity = product.getQuantity() + quantityChange;
        if (newQuantity < 0) {
            throw new RuntimeException("Not enough stock available");
        } else if (newQuantity <= 5) {
            notifyLowStock(product);
        }


        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    private void notifyLowStock(Product product) {
        System.out.println("Low stock for product: " + product.getName());
        // Here I will integrate an email service or other notification service.
    }
}
