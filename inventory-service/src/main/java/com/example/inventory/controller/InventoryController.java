package com.example.inventory.controller;

import com.example.inventory.entity.Batch;
import com.example.inventory.service.InventoryService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<Batch>> getBatches(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getBatches(productId));
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateInventory(@RequestBody UpdateRequest req) {
        inventoryService.updateInventory(req.getProductId(), req.getQuantity());
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    @Data
    public static class UpdateRequest {
        private Long productId;
        private int quantity;
    }
}
