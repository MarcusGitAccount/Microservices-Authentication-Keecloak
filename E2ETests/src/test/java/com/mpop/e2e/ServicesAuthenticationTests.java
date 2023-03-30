package com.mpop.e2e;

import com.mpop.e2e.payload.OrderPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.*;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class ServicesAuthenticationTests {

    private OrderPayload payload;
    private String username;
    private String password;
    private int expectedStatus;

    public ServicesAuthenticationTests(String username, String password, int expectedStatus) {
        this.payload = new OrderPayload("box", "goaway@gmail.com");;
        this.username = username;
        this.password = password;
        this.expectedStatus = expectedStatus;
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;
        RestAssured.basePath = "";
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"notification_issuer", "123abc", HttpStatus.SC_CREATED},
                {"notification_issuer", "123abcd", HttpStatus.SC_UNAUTHORIZED}, // wrong password
                {"notification_", "123abc", HttpStatus.SC_UNAUTHORIZED}, // invalid user
                {"order_issuer", "123abc", HttpStatus.SC_UNAUTHORIZED}, // unauthorized
        });
    }

    @Test
    public void createOrderTest() {
        given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .header(HttpHeaders.AUTHORIZATION,
                        String.format("%s:%s", username, password))
        .when()
                .post("/order")
        .then()
                .statusCode(expectedStatus);
    }

}
