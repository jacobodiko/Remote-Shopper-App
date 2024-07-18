package com.Microservice.OrderService.Service;

import com.Microservice.OrderService.Entity.Order;
import com.Microservice.OrderService.Entity.OrderStatus;
import com.Microservice.OrderService.Feign.InventoryClient;
import com.Microservice.OrderService.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }
    @Transactional
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PLACED);
        if (inventoryClient.updateStock(order.getProductId(), -1)) {
            order.setStatus(OrderStatus.CONFIRMED);
        } else {
            throw new RuntimeException("Insufficient stock available");
        }
        return orderRepository.save(order);
    }

    @Transactional
    public Order shipOrder(Long orderId) {
        return updateOrderStatus(orderId, OrderStatus.SHIPPED);
    }

    @Transactional
    public Order deliverOrder(Long orderId) {
        return updateOrderStatus(orderId, OrderStatus.DELIVERED);
    }

    @Transactional
    public Order cancelOrder(Long orderId) {
        Order order = getOrderById(orderId);
        if (!OrderStatus.PLACED.equals(order.getStatus())) {
            throw new IllegalStateException("Only orders that are placed can be cancelled");
        }
        if (inventoryClient.updateStock(order.getProductId(), 1)) { // Restock the product
            order.setStatus(OrderStatus.CANCELLED);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Failed to update inventory");
        }
    }

    private Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }


}

/*
Explanation
Order Creation:
When creating an order, it is initially set to the PLACED status.
The service then calls the Inventory Service to decrement the stock.
If the stock update is successful, the status is set to CONFIRMED.
Order Shipping:
The shipOrder method updates the status of an existing order to SHIPPED. It calls a generic method updateOrderStatus which performs this operation and handles exceptions accordingly.
Order Delivery:
Similar to shipping, the deliverOrder method changes the order status to DELIVERED using the same updateOrderStatus method.
Order Cancellation:
Cancelling an order checks if the order status is PLACED.
If yes, it calls the Inventory Service to increment the stock (indicating restocking the item).
Upon successful stock update, it sets the order status to CANCELLED.
Status Update and Fetch Helper Methods:
updateOrderStatus simplifies changing the status of an order and saving it.
getOrderById fetches an order by its ID, throwing an exception if not found.
 */



//@Service
//public class OrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private InventoryClient inventoryClient; // Assuming this is a Feign client interacting with InventoryService
//
//    /**
//     * Creates a new order and updates inventory.
//     *
//     * @param order the order to be created
//     * @return the saved order
//     */
//    @Transactional
//    public Order createOrder(Order order) {
//        // Assuming each order decrements stock by the quantity of the product ordered
//        inventoryClient.updateStock(order.getProductId(), -order.getQuantity());
//        order.setStatus(OrderStatus.PLACED);
//        return orderRepository.save(order);
//    }
//
//    /**
//     * Updates the status of an existing order to CONFIRMED.
//     *
//     * @param orderId the ID of the order to update
//     * @return the updated order
//     */
//    @Transactional
//    public Optional<Order> confirmOrder(Long orderId) {
//        return updateOrderStatus(orderId, OrderStatus.CONFIRMED);
//    }
//
//    /**
//     * Updates the status of an existing order to SHIPPED.
//     *
//     * @param orderId the ID of the order to update
//     * @return the updated order
//     */
//    @Transactional
//    public Optional<Order> shipOrder(Long orderId) {
//        return updateOrderStatus(orderId, OrderStatus.SHIPPED);
//    }
//
//    /**
//     * Updates the status of an existing order to DELIVERED.
//     *
//     * @param orderId the ID of the order to update
//     * @return the updated order
//     */
//    @Transactional
//    public Optional<Order> deliverOrder(Long orderId) {
//        return updateOrderStatus(orderId, OrderStatus.DELIVERED);
//    }
//
//    /**
//     * Cancels an existing order and updates inventory accordingly.
//     *
//     * @param orderId the ID of the order to cancel
//     * @return the updated order
//     */
//    @Transactional
//    public Optional<Order> cancelOrder(Long orderId) {
//        Optional<Order> orderOptional = orderRepository.findById(orderId);
//        if (orderOptional.isPresent()) {
//            Order order = orderOptional.get();
//            // Assuming the stock should be incremented back by the quantity of the product ordered
//            inventoryClient.updateStock(order.getProductId(), order.getQuantity());
//            order.setStatus(OrderStatus.CANCELLED);
//            orderRepository.save(order);
//            return Optional.of(order);
//        }
//        return Optional.empty();
//    }
//
//    /**
//     * Generic method to update the status of an order.
//     *
//     * @param orderId the ID of the order to update
//     * @param newStatus the new status to set
//     * @return the updated order
//     */
//    private Optional<Order> updateOrderStatus(Long orderId, OrderStatus newStatus) {
//        Optional<Order> orderOptional = orderRepository.findById(orderId);
//        if (orderOptional.isPresent()) {
//            Order order = orderOptional.get();
//            order.setStatus(newStatus);
//            orderRepository.save(order);
//            return Optional.of(order);
//        }
//        return Optional.empty();
//    }
//}