package com.example.inventory;

import com.example.inventory.entity.Batch;
import com.example.inventory.entity.Product;
import com.example.inventory.repository.BatchRepository;
import com.example.inventory.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer {

    private final ProductRepository productRepository;
    private final BatchRepository batchRepository;

    public DataInitializer(ProductRepository productRepository, BatchRepository batchRepository) {
        this.productRepository = productRepository;
        this.batchRepository = batchRepository;
    }

    @PostConstruct
    public void init() {
        Product p = Product.builder().name("Sample Product").build();
        productRepository.save(p);

        Batch b1 = Batch.builder().batchCode("BATCH-1").quantity(5).expiryDate(LocalDate.now().plusDays(10)).product(p).build();
        Batch b2 = Batch.builder().batchCode("BATCH-2").quantity(10).expiryDate(LocalDate.now().plusDays(30)).product(p).build();
        batchRepository.saveAll(Arrays.asList(b1, b2));
    }
}
