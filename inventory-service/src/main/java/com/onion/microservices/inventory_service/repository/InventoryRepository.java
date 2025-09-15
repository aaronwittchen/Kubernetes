package com.onion.microservices.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onion.microservices.inventory_service.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityGreaterThanEqual(String skuCode, Integer quantity);
}
