package com.onion.microservices.order_service.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.onion.microservices.order_service.client.InventoryClient;
import com.onion.microservices.order_service.dto.OrderRequest;
import com.onion.microservices.order_service.entity.Order;
import com.onion.microservices.order_service.exception.OutOfStockException;
import com.onion.microservices.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    
    // perform an action (save an order) rather than produce a result.
    public void placeOrder(OrderRequest orderRequest) {

        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isProductInStock) {
        // map OrderRequest to Order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(new BigDecimal(orderRequest.price()));
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        // save order to OrderRepository
        orderRepository.save(order);
        } else {
            throw new OutOfStockException("Product is not in stock: " + orderRequest.skuCode());
        }
    }
}
