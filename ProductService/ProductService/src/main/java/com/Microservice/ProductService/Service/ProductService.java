package com.Microservice.ProductService.Service;

import com.Microservice.ProductService.Entity.Product;
import com.Microservice.ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {

        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {

        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    // Search products by name
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Filter products by availability
    public List<Product> filterProductsByAvailability(boolean available) {
        return productRepository.findByAvailable(available);
    }
}
