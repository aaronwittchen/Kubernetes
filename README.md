cd product-service
cd order-service
cd inventory-service
docker compose up -d
docker compose up -d
manually run in e.g mysql workbench:
CREATE DATABASE IF NOT EXISTS `order-service`;

MONGO_PASSWORD should be database password
start product service

post http://localhost:8080/api/product
{
"name": "iPhone 15",
"description": "iPhone 15 is a smartphone from Apple",
"price": 1000
}

testcontainers
restassured
mvn test

flyway

post http://localhost:8081/api/order
{
"skuCode": "iphone_15",
"price": 1000,
"quantity": 1
}

get http://localhost:8082/api/inventory?skuCode=iphone_15&quantity=10
{
"skuCode": "iphone_15",
"price": 1000,
"quantity": 1
}

spring cloud open feign
synchronous between order and inventory service
order -> http request -> inventory
inventory -> response -> orderservice

wiremock to mock api calls, stub call

api gateway
spring cloud gateway
functional endpoints
now we can use localhost:9000 routes
keycloak

swagger openapi
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/api-docs
Product Service API

http://localhost:8081/swagger-ui/index.html#/
http://localhost:8081/api-docs
Order Service API

http://localhost:8082/swagger-ui/index.html#/
http://localhost:8082/api-docs
Inventory Service API

main
http://localhost:9000/swagger-ui/index.html#/

Aggregating REST API Documentation and Refactoring
Rest Client

Circuit Breaker Pattern

Angular

Kafka
asynch communication between order service and notification service
testcontainers
