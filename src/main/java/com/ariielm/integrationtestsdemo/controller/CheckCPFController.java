package com.ariielm.integrationtestsdemo.controller;

import com.ariielm.integrationtestsdemo.service.CheckCPFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CheckCPFController {

    private final CheckCPFService service;

    @GetMapping("/check-cpf")
    public ResponseEntity checkCPF() throws Exception {
        return this.service.isAValidCPF() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

}
