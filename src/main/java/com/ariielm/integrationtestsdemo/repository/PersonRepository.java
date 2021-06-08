package com.ariielm.integrationtestsdemo.repository;

import com.ariielm.integrationtestsdemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
