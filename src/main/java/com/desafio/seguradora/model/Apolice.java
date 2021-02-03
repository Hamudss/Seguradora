package com.desafio.seguradora.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "apolices")
@Data
public class Apolice {

    public Apolice() {
        
    }

    public Apolice(String id, Date vigenciaInicio, Date vigenciaFim, String placa, Double valor, Cliente cliente) {
        this.id = id;
        this.vigenciaInicio = vigenciaInicio;
        this.vigenciaFim = vigenciaFim;
        this.placa = placa;
        this.valor = valor;
        this.cliente = cliente;
    }

    @Id
    private String id;

    @NotNull(message = "Campo vigenciaInicio é obrigatório")
    private Date vigenciaInicio;
    
    @NotNull(message = "Campo vigenciaFim é obrigatório")
    private Date vigenciaFim;

    @NotNull(message = "Campo placa é obrigatório")
    private String placa;

    @NotNull(message = "Campo valor é obrigatório")
    private Double valor;
    
    @NotNull(message = "Campo cliente é obrigatório")
    private Cliente cliente;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
