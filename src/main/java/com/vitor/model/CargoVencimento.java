package com.vitor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cargo_vencimentos")
public class CargoVencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "vencimento_id")
    private Vencimento vencimento;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    public Vencimento getVencimento() { return vencimento; }
    public void setVencimento(Vencimento vencimento) { this.vencimento = vencimento; }
}
