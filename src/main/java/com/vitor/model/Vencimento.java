package com.vitor.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vencimentos")
public class Vencimento {

    @Id
    private Integer id;

    private String descricao;

    private String tipo; // CREDITO ou DEBITO

    private BigDecimal valor;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}
