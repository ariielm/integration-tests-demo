package com.ariielm.integrationtestsdemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CheckCPFService {

    @Value("${cpf.api}")
    private String api;

    public boolean isAValidCPF() throws Exception {
        var request = HttpRequest.newBuilder(new URI(api + "/5ae973d7a997af13f0aaf2bf60e65803/9/00000000000"))
                .GET()
                .build();

        var statusCode = HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString())
                .statusCode();

        return statusCode == HttpStatus.OK.value();
    }
}
