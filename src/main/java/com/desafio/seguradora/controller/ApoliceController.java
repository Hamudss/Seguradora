package com.desafio.seguradora.controller;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import com.desafio.seguradora.dto.ApoliceDTO;
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
            apolice = apoliceRepository.save(apolice);
            apolice.setCliente(clienteRepository.findById(apolice.getCliente().getId()).orElse(null));
            return new ResponseEntity<Apolice>(apolice, HttpStatus.OK);
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
    public ResponseEntity<ApoliceDTO> obterPorId(@PathVariable String id) {
        try {
            ApoliceDTO apoliceDto = ApoliceDTO.ApoliceToApoliceDTO(
                    apoliceRepository.findById(id).orElseThrow(() -> new Exception("Apólice não localizada - " + id)));
            Boolean vencida = compareDate(apoliceDto.getVigenciaFim());
            
            apoliceDto.setApoliceVencida(vencida);
            apoliceDto.setDiasParaVencer(daysToFinish(apoliceDto.getVigenciaFim()));

            return new ResponseEntity<ApoliceDTO>(apoliceDto, HttpStatus.OK);
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

    private Boolean compareDate(Date date){
        Date dateNow = new Date();
        if(date.compareTo(dateNow) == 1) {
            return false;
        }
        return true;
    }


    private Long daysToFinish(Date date){
        long diff = date.getTime() - new Date().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}