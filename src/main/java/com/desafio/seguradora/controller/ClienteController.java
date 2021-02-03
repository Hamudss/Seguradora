package com.desafio.seguradora.controller;

import com.desafio.seguradora.repository.ClienteRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.desafio.seguradora.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente) {
        try {
            // Tive que criar o validador de duplicidade pois o Indexed unique não esta funcionando no Spring.
            Cliente clienteValidation = clienteRepository.findByCpf(cliente.getCpf());
            if(clienteValidation != null){
                throw new Exception("Cliente com o CPF: " + cliente.getCpf() + " já cadastrado!");
            }
            return new ResponseEntity<Cliente>(clienteRepository.save(cliente), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Cliente> atualizar(@PathVariable String id, @Valid @RequestBody Cliente cliente){
        try {
            cliente.setId(id);
            // Tive que criar o validador de duplicidade pois o Indexed unique não esta funcionando no Spring.
            Cliente clienteValidation = clienteRepository.findByCpf(cliente.getCpf());
            if(clienteValidation != null){
                throw new Exception("Cliente com o CPF: " + cliente.getCpf() + " já cadastrado!");
            }
            return new ResponseEntity<Cliente>(clienteRepository.save(cliente), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Cliente>> listar(){
        try {
            return new ResponseEntity<List<Cliente>>(clienteRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível listar os clientes:" + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Cliente> obterPorId(@PathVariable String id){
        try {
            return new ResponseEntity<Cliente>(clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não localizado - " + id)), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> deletePorId(@PathVariable String id){
        try {
            clienteRepository.deleteById(id);
            return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
