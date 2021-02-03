package com.desafio.seguradora.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.desafio.seguradora.model.Apolice;

public interface ApoliceRepository extends MongoRepository <Apolice, String> {
    
}
