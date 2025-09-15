package com.onion.microservices.order_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;

import com.onion.microservices.order_service.stubs.InventoryClientStub;

import io.restassured.RestAssured;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mySQLContainer.start();
    }

    @Test
    void shouldSubmitOrder() {
        String submitOrderJson = """
            {
                "skuCode": "iphone_15",
                "price": 1000,
                "quantity": 1
            }
        """;

		InventoryClientStub.stubInventoryCall("iphone_15", 1);

        var responseBodyString = RestAssured.given()
            .contentType("application/json")
            .body(submitOrderJson)
        .when()
            .post("/api/order")
        .then()
            .log().all()
            .statusCode(201)
            .extract()
            .asString();

        assertThat(responseBodyString, is("Order Placed Successfully"));
    }
}
