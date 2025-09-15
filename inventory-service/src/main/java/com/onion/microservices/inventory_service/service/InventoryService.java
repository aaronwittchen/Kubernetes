package com.onion.microservices.inventory_service.service;

import org.springframework.stereotype.Service;

import com.onion.microservices.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    
    public boolean isInStock(String skuCode, Integer quantity) {
        // Find an inventory for a given SKU code where quantity >= 0
        return inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}
