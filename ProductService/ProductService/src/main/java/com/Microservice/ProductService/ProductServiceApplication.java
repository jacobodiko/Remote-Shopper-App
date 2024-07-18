package com.Microservice.ProductService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {SpringApplication.run(ProductServiceApplication.class, args);
	}

}
/*
Product Service: This microservice manages product information,
including details such as name, description, price, and availability.
It should handle CRUD operations for products and also support features like product search and filtering.
 Inventory Service: This microservice tracks the inventory of available products.
 It should update the stock levels when orders are placed and handle notifications for low stock or out-of-stock items.

 */



/*
In an online shopping platform, the functionality of the described classes interacts and operates together to manage products and their inventories effectively.
Here’s how they operate in a real-world scenario:

Controllers
ProductController
Manages basic CRUD operations on products.
Provides endpoints to create, retrieve, update, and delete products.
Offers additional functionality to search and filter products based on name and availability.
InventoryController
Specifically handles inventory-related operations.
Provides an endpoint to update the stock quantity of a specific product.
Services
ProductService
Handles the business logic associated with product operations.
Interacts directly with the ProductRepository to execute CRUD operations, searches, and filtering tasks on the database.
Methods include saving a product, finding all products, searching by name, filtering by availability, etc.
InventoryService
Manages the inventory aspect of products.
Uses the ProductRepository to find a product and update its stock.
Notifies if stock levels are low after an update.
Repositories
ProductRepository
Interface for the database operations related to Product entities.
Extends JpaRepository, which provides methods like findAll(), save(), deleteById(), etc.
Custom methods to find products by name and availability.
Interactions and Workflow Example
Here’s a step-by-step example showing how these classes might be used in an online shopping scenario:

Adding a New Product
A client (e.g., an administrator) sends a POST request to /api/products with product data.
ProductController receives this request and calls ProductService.createProduct().
ProductService uses ProductRepository.save() to persist the product in the database.
A response with the created product is returned to the client.
Updating Inventory
A client sends a POST request to /api/inventory/update/{productId} with a quantityChange parameter.
InventoryController receives this request and calls InventoryService.updateStock().
InventoryService retrieves the product using ProductRepository.findById(). If not found, it throws an exception.
If found, it adjusts the quantity. If the new quantity is below a threshold, it might trigger a notification for low stock.
The updated product is saved and returned to the client.
Searching for Products
A client sends a GET request to /api/products/search with a name query parameter.
ProductController handles this and calls ProductService.searchProductsByName().
ProductService uses ProductRepository to perform the search.
The search results are returned to the client.
Buying a Product
While not explicitly handled in the provided classes, a typical process would involve:
The customer adds a product to their cart and proceeds to checkout.
The stock is checked and then updated, potentially using the InventoryService.updateStock() method to decrement the quantity.
Order and payment processing would take place.
Real-World Integration
In a full application, these classes might integrate with other services and components, such as:

User authentication and authorization to ensure that only authorized users can update products or inventories.
Frontend services where these backend endpoints are consumed in web pages or mobile apps.
Payment processing services for handling transactions.
Notification services to alert administrators or users about various events (like low stock).
 */
