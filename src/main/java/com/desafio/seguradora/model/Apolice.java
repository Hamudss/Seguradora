package com.desafio.seguradora.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.desafio.seguradora.components.ClienteConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "apolices")
@Data
public class Apolice {

    public Apolice() {
        
    }

    public Apolice(String numero, Date vigenciaInicio, Date vigenciaFim, String placa, Double valor, Cliente cliente) {
        this.numero = numero;
        this.vigenciaInicio = vigenciaInicio;
        this.vigenciaFim = vigenciaFim;
        this.placa = placa;
        this.valor = valor;
        this.cliente = cliente;
    }

    @Id
    private String numero;

    @NotNull(message = "Campo vigenciaInicio é obrigatório")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date vigenciaInicio;
    
    @NotNull(message = "Campo vigenciaFim é obrigatório")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date vigenciaFim;

    @NotNull(message = "Campo placa é obrigatório")
    private String placa;

    @NotNull(message = "Campo valor é obrigatório")
    private Double valor;
    
    @NotNull(message = "Campo cliente é obrigatório")
    @DBRef
    private Cliente cliente;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(Date vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public Date getVigenciaFim() {
        return vigenciaFim;
    }

    public void setVigenciaFim(Date vigenciaFim) {
        this.vigenciaFim = vigenciaFim;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
