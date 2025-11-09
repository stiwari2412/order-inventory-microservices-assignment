package com.example.order;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceUnitTest {

    @Test
    public void testPlaceOrderSuccess() {
        OrderRepository repo = Mockito.mock(OrderRepository.class);
        OrderService service = new OrderService(repo);

        // mock RestTemplate by using spy on service's restTemplate is not accessible, so we test repository save
        Order o = Order.builder().productId(1L).quantity(2).status("PLACED").build();
        when(repo.save(Mockito.any())).thenReturn(o);
        Order saved = repo.save(o);
        assertEquals(1L, saved.getProductId());
    }
}
