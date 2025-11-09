package com.example.order.controller;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest req) {
        Order o = orderService.placeOrder(req.getProductId(), req.getQuantity());
        return ResponseEntity.ok(o);
    }

    @Data
    public static class OrderRequest {
        private Long productId;
        private int quantity;
    }
}
