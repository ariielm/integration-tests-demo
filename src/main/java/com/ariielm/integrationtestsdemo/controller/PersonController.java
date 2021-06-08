package com.ariielm.integrationtestsdemo.controller;

import com.ariielm.integrationtestsdemo.model.Person;
import com.ariielm.integrationtestsdemo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;

    @GetMapping
    public Page<Person> get(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable int id) {
        return this.repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Person person) {
        var savedPerson = this.repository.save(person);
        return ResponseEntity
                .created(URI.create(savedPerson.getId().toString()))
                .build();
    }

}
