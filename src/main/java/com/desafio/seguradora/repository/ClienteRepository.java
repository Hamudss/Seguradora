package com.desafio.seguradora.repository;

import com.desafio.seguradora.model.Cliente;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Cliente findByCpf(String cpf);
    
}
