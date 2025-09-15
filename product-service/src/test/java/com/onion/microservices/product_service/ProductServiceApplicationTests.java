package com.onion.microservices.product_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void shouldCreateProduct() {
        String requestBody = """
            {
              "name": "iPhone 15 Pro",
              "description": "Apple iPhone 15 Pro with 6.1-inch Super Retina XDR display, A17 Pro chip, and 128GB storage.",
              "price": 999.00
            }
        """;

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/api/product")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("name", equalTo("iPhone 15 Pro"))
            .body("description", equalTo("Apple iPhone 15 Pro with 6.1-inch Super Retina XDR display, A17 Pro chip, and 128GB storage."))
            .body("price", equalTo(999.00f));
    }
}
