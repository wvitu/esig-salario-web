package com.vitor.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    private Integer id;

    private String nome;

    // Relacionamento com Pessoa (um cargo pode ter várias pessoas)
    @OneToMany(mappedBy = "cargo")
    private List<Pessoa> pessoas;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}
