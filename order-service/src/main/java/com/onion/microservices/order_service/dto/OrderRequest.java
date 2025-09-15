package com.onion.microservices.order_service.dto;

public record OrderRequest(
    Long id,
    String orderNumber,
    String skuCode,
    String price,
    Integer quantity
) {
    public record UserDetails(
        String email,
        String firstName,
        String lastName
    ) {}   
}