package com.desafio.seguradora.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "clientes")
@Data
public class Cliente {

    public Cliente() {
    }

    public Cliente(String id) {
        this.id = id;
    }

    public Cliente(String id, String nome, String cpf, String cidade, String uf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.uf = uf;
    }

    @Id
    private String id;

    @NotNull(message = "Campo nome é obrigatório")
    private String nome;

    @NotNull(message = "Campo CPF é obrigatório")
    @CPF(message = "cpf inválido")
    @Indexed(unique = true)
    private String cpf;

    @NotNull(message = "Campo cidade é obrigatório")
    private String cidade;

    @NotNull(message = "Campo UF é obrigatório")
    private String uf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return String.format("Cliente[id=%s, nome='%s', cpf='%s'", id, nome, cpf);
    }

}
