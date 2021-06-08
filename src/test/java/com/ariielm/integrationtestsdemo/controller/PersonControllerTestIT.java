package com.ariielm.integrationtestsdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.ResourceUtils.getFile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PersonControllerTestIT {

    @LocalServerPort
    int port;

    @Test
    void get() {
        given()
            .port(port)
            .get("/persons")
        .then()
            .statusCode(OK.value())
            .contentType(APPLICATION_JSON_VALUE)
            .body("size", is(20))
            .body("totalElements", is(1))
            .body("totalPages", is(1))
            .body("number", is(0))
            .body("content[0].id", is(1))
            .body("content[0].name", is("Bond, James"))
            .body("content[0].age", is(7));
    }

    @Test
    void getById() {
        given()
            .port(port)
            .get("/persons/1")
            .then()
            .statusCode(OK.value())
            .contentType(APPLICATION_JSON_VALUE)
            .body("id", is(1))
            .body("name", is("Bond, James"))
            .body("age", is(7));
    }

    @Test
    void post() throws FileNotFoundException {
        var location = given()
                .port(port)
                .body(getFile("classpath:scenarios/post-person.json"))
                .contentType(APPLICATION_JSON_VALUE)
                .post("/persons")
                .then()
                .statusCode(CREATED.value())
                .extract()
                .header(LOCATION);

        given()
                .port(port)
                .get("/persons/" + location)
                .then()
                .statusCode(OK.value())
                .contentType(APPLICATION_JSON_VALUE)
                .body("id", is(Integer.parseInt(location)))
                .body("name", is("Test, Integration"))
                .body("age", is(99));
    }
}