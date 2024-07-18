package com.Microservice.ProductService.Repository;

import com.Microservice.ProductService.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByAvailable(boolean available);

    List<Product> findByNameContainingIgnoreCase(String name);

}
