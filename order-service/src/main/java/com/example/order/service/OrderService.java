package com.example.order.service;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${inventory.service.url}")
    private String inventoryBase;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(Long productId, int quantity) {
        // call inventory service to update
        String url = inventoryBase + "/inventory/update";
        Map<String,Object> req = Map.of("productId", productId, "quantity", quantity);

        ResponseEntity<Map> resp = restTemplate.postForEntity(url, req, Map.class);
        if (resp.getStatusCode().is2xxSuccessful()) {
            Order o = Order.builder().productId(productId).quantity(quantity).status("PLACED").build();
            return orderRepository.save(o);
        } else {
            throw new RuntimeException("Failed to update inventory");
        }
    }
}
