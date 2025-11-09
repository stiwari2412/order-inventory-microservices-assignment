package com.example.inventory;

import com.example.inventory.entity.Batch;
import com.example.inventory.factory.DefaultInventoryHandler;
import com.example.inventory.repository.BatchRepository;
import com.example.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class InventoryHandlerUnitTest {

    @Test
    public void testGetBatchesCallsRepository() {
        BatchRepository batchRepo = Mockito.mock(BatchRepository.class);
        ProductRepository prodRepo = Mockito.mock(ProductRepository.class);

        Batch b = Batch.builder().batchCode("B1").quantity(3).expiryDate(LocalDate.now()).build();
        when(batchRepo.findByProductIdOrderByExpiryDateAsc(1L)).thenReturn(List.of(b));

        DefaultInventoryHandler handler = new DefaultInventoryHandler(batchRepo, prodRepo);
        var batches = handler.getBatchesForProduct(1L);
        assertEquals(1, batches.size());
        assertEquals("B1", batches.get(0).getBatchCode());
    }
}
