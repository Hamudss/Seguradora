package com.desafio.seguradora.controller;

import java.util.List;

import javax.validation.Valid;

import com.desafio.seguradora.model.Apolice;
import com.desafio.seguradora.repository.ApoliceRepository;
import com.desafio.seguradora.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/apolices")
public class ApoliceController {

    @Autowired
    private ApoliceRepository apoliceRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Apolice> criar(@Valid @RequestBody Apolice apolice) {
        try {
            apolice = apoliceRepository.save(apolice);
            apolice.setCliente(clienteRepository.findById(apolice.getCliente().getId()).orElse(null));
            return new ResponseEntity<Apolice>(apolice, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Apolice> atualizar(@PathVariable String id, @Valid @RequestBody Apolice apolice) {
        try {
            apolice.setNumero(id);

            return new ResponseEntity<Apolice>(apoliceRepository.save(apolice), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Apolice>> listar() {
        try {
            return new ResponseEntity<List<Apolice>>(apoliceRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Não foi possível listar as apólices:" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Apolice> obterPorId(@PathVariable String id) {
        try {
            return new ResponseEntity<Apolice>(
                    apoliceRepository.findById(id).orElseThrow(() -> new Exception("Apólice não localizada - " + id)),
                    HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> deletePorId(@PathVariable String id) {
        try {
            apoliceRepository.deleteById(id);
            return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}