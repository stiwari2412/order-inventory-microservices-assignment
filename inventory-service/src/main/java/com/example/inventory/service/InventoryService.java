package com.example.inventory.service;

import com.example.inventory.entity.Batch;
import com.example.inventory.factory.InventoryHandlerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryHandlerFactory handlerFactory;

    public InventoryService(InventoryHandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    public List<Batch> getBatches(Long productId) {
        return handlerFactory.getHandler("default").getBatchesForProduct(productId);
    }

    public void updateInventory(Long productId, int quantity) {
        handlerFactory.getHandler("default").updateInventory(productId, quantity);
    }
}
