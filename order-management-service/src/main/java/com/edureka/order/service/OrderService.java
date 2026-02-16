package com.edureka.order.service;

import com.edureka.order.client.ProductClient;
import com.edureka.order.entity.Order;
import com.edureka.order.dto.OrderResponse;
import com.edureka.order.exception.OrderNotPlacedException;
import com.edureka.order.repository.OrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
	private static final Logger log =
            LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository repository;
    private final ProductClient productClient;

    public OrderService(OrderRepository repository,
                        ProductClient productClient) {
        this.repository = repository;
        this.productClient = productClient;
    }
    public OrderResponse placeOrder(Order order) {
        log.info("inside placeOrder method {}", order);
        Boolean exists = productClient.isCategoryAvailable(order.getCategory());
        if (exists == null) {
        	String msg="Product service unavailable. Circuit Breaker Activated.";
            return new OrderResponse(msg);
        }
        if (!exists) {
            throw new OrderNotPlacedException("Category not available in product service");
        }
        order.setStatus("PLACED");
        log.info("exiting placeOrder method {}", order);
         repository.save(order);
        return new OrderResponse(
                 "Order placed successfully for category: " + order.getCategory()
        		    );
    }

 
	public List<Order> getOrders() {
    	log.info("inside getOrders method {}", repository.findAll());
        return repository.findAll();
    }

    public Order getById(Long id) {
    	log.info("Fetching order with id: {}", id);
        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new OrderNotPlacedException("Order not found with id: " + id));
        log.info("Order found: {}", order);
        return order;
    }

    public String cancelOrder(Long id) {
    	log.info("Cancelling order with id: {}", id);
        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new OrderNotPlacedException("Order not found with id: " + id));
        order.setStatus("CANCELLED");
        repository.save(order);
        log.info("Order {} cancelled successfully", id);
        return "Order with id " + id + " cancelled successfully";
    }
    public String reOrder(Long id) {
    	log.info("reordering order with id: {}", id);
        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new OrderNotPlacedException("Order not found with id: " + id));
        order.setStatus("CREATED");
        repository.save(order);
        log.info("Reordered the order successfully", id);
        return "Order with id " + id + " Reordered successfully";
    }
}
