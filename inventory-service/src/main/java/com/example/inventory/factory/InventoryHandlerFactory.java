package com.example.inventory.factory;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;

@Component
public class InventoryHandlerFactory {

    private final Map<String, InventoryHandler> handlers = new HashMap<>();

    public InventoryHandlerFactory(Map<String, InventoryHandler> beans) {
        // Spring will inject available handlers (e.g. defaultHandler)
        beans.forEach((k,v)-> handlers.put(k, v));
    }

    public InventoryHandler getHandler(String key) {
        // for now key 'default' maps to 'defaultHandler'
        if ("default".equalsIgnoreCase(key)) return handlers.getOrDefault("defaultHandler", handlers.values().stream().findFirst().orElse(null));
        return handlers.values().stream().findFirst().orElse(null);
    }
}
