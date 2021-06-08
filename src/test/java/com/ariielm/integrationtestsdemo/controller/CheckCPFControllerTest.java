package com.ariielm.integrationtestsdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class CheckCPFControllerTest {

    @LocalServerPort
    int port;

    @Test
    void checkCPF() {
        stubFor(get(urlEqualTo("/5ae973d7a997af13f0aaf2bf60e65803/9/00000000000"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello world!")));

        given()
                .port(port)
                .get("/check-cpf")
                .then()
                .statusCode(OK.value());
    }

    @Test
    void checkInvalidCPF() {
        stubFor(get(urlEqualTo("/5ae973d7a997af13f0aaf2bf60e65803/9/00000000000"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("No way!")));

        given()
                .port(port)
                .get("/check-cpf")
                .then()
                .statusCode(BAD_REQUEST.value());
    }
}