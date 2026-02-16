package com.edureka.order.controller;

import com.edureka.order.dto.OrderResponse;
import com.edureka.order.entity.Order;
import com.edureka.order.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody Order order) {
        service.placeOrder(order);
        String msg="Order is placed in  category :"+order.getCategory();
        return ResponseEntity.ok(new OrderResponse(msg));
    }

    @GetMapping
    public List<Order> getOrders() {
        return service.getOrders();
    }

    @GetMapping("/id/{id}")
    public Order getById(@PathVariable("id") Long id) {
        System.out.println("Controller reached with id: " + id);
        return service.getById(id);
    }
    @PutMapping("/cancel/id/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id") Long id) {
        String message = service.cancelOrder(id);
        return ResponseEntity.ok(message);
    }
    @PutMapping("/reorder/id/{id}")
    public ResponseEntity<String> reOrder(@PathVariable("id") Long id) {
        String message = service.reOrder(id);
        return ResponseEntity.ok(message);
    }
}
