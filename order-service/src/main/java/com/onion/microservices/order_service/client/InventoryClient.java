package com.onion.microservices.order_service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    // @Timelimiter
    boolean isInStock(@RequestParam("skuCode") String skuCode,
                      @RequestParam("quantity") Integer quantity);

    // Fallback must match the original method's signature + Throwable at the end
    default boolean fallbackMethod(String skuCode, Integer quantity, Throwable throwable) {
        log.warn("Fallback triggered for skuCode={}, quantity={}. Reason: {}", skuCode, quantity, throwable.getMessage());
        return false;
    }
}
