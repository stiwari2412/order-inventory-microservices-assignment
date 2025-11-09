package com.example.inventory.factory;

import com.example.inventory.entity.Batch;
import com.example.inventory.entity.Product;
import com.example.inventory.repository.BatchRepository;
import com.example.inventory.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component("defaultHandler")
public class DefaultInventoryHandler implements InventoryHandler {

    private final BatchRepository batchRepository;
    private final ProductRepository productRepository;

    public DefaultInventoryHandler(BatchRepository batchRepository, ProductRepository productRepository) {
        this.batchRepository = batchRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Batch> getBatchesForProduct(Long productId) {
        return batchRepository.findByProductIdOrderByExpiryDateAsc(productId);
    }

    @Override
    @Transactional
    public void updateInventory(Long productId, int quantity) {
        // Simple depletion logic: take from earliest expiry first
        List<Batch> batches = batchRepository.findByProductIdOrderByExpiryDateAsc(productId);
        int remaining = quantity;
        for (Batch b : batches) {
            if (remaining <= 0) break;
            int take = Math.min(b.getQuantity(), remaining);
            b.setQuantity(b.getQuantity() - take);
            remaining -= take;
            batchRepository.save(b);
        }
        if (remaining > 0) {
            throw new RuntimeException("Insufficient stock for product " + productId);
        }
    }
}
