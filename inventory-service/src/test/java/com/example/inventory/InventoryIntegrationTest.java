package com.example.inventory;

import com.example.inventory.entity.Batch;
import com.example.inventory.repository.BatchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InventoryIntegrationTest {

    @Autowired
    private BatchRepository batchRepository;

    @Test
    public void testBatchesPresent() {
        List<Batch> all = batchRepository.findAll();
        assertTrue(all.size() >= 1);
    }
}
