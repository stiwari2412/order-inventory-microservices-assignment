package com.example.inventory.factory;

import com.example.inventory.entity.Batch;

import java.util.List;

public interface InventoryHandler {
    List<Batch> getBatchesForProduct(Long productId);
    void updateInventory(Long productId, int quantity);
}
