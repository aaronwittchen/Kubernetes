package com.onion.microservices.inventory_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
class InventoryServiceApplicationTests {

    @Container
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldReadInventory() {
        Boolean response = given()
            .when()
            .get("/api/inventory?skuCode=iphone_15&quantity=1")
            .then()
            .log().all()
            .statusCode(200)
            .extract().response().as(Boolean.class);

        assertTrue(response);

        Boolean negativeResponse = given()
            .when()
            .get("/api/inventory?skuCode=iphone_15&quantity=1000")
            .then()
            .log().all()
            .statusCode(200)
            .extract().response().as(Boolean.class);

        assertFalse(negativeResponse);
    }
}
