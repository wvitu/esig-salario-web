package com.vitor.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    private Integer id;

    private String nome;

    @Column(name = "data_nasc")
    private LocalDate dataNasc;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNasc() { return dataNasc; }
    public void setDataNasc(LocalDate dataNasc) { this.dataNasc = dataNasc; }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
}
