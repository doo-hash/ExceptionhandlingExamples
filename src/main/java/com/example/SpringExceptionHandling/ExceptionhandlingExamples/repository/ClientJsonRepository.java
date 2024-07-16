package com.example.SpringExceptionHandling.ExceptionhandlingExamples.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringExceptionHandling.ExceptionhandlingExamples.model.ClientJson;

@Repository
public interface ClientJsonRepository extends CrudRepository<ClientJson,Long> {

}
