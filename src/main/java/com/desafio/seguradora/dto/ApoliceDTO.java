package com.desafio.seguradora.dto;

import java.util.Date;

import com.desafio.seguradora.model.Apolice;
import com.desafio.seguradora.model.Cliente;

public class ApoliceDTO {

    public ApoliceDTO() {
    }

    public ApoliceDTO(String numero, Date vigenciaInicio, Date vigenciaFim, String placa, Double valor, Cliente cliente,
            Boolean apoliceVencida, Long diasParaVencer) {
        this.numero = numero;
        this.vigenciaInicio = vigenciaInicio;
        this.vigenciaFim = vigenciaFim;
        this.placa = placa;
        this.valor = valor;
        this.cliente = cliente;
        this.apoliceVencida = apoliceVencida;
        this.diasParaVencer = diasParaVencer;
    }

    private String numero;

    private Date vigenciaInicio;

    private Date vigenciaFim;

    private String placa;

    private Double valor;

    private Cliente cliente;

    private Boolean apoliceVencida;

    private Long diasParaVencer;

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

    public Boolean getApoliceVencida() {
        return apoliceVencida;
    }

    public void setApoliceVencida(Boolean apoliceVencida) {
        this.apoliceVencida = apoliceVencida;
    }

    public Long getDiasParaVencer() {
        return diasParaVencer;
    }

    public void setDiasParaVencer(Long diasParaVencer) {
        this.diasParaVencer = diasParaVencer;
    }

    public static ApoliceDTO ApoliceToApoliceDTO(Apolice apolice) {
        return new ApoliceDTO(apolice.getNumero(), apolice.getVigenciaInicio(), apolice.getVigenciaFim(),
                apolice.getPlaca(), apolice.getValor(), apolice.getCliente(), false, Long.parseLong("0"));
    }

}
