package com.onion.microservices.product_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.onion.microservices.product_service.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {}
