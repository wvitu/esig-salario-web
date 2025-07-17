package com.vitor.service;

import com.vitor.model.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class SalarioService {

    private EntityManager em;

    public SalarioService(EntityManager em) {
        this.em = em;
    }

    public void calcularESalvarSalarios() {
        List<Pessoa> pessoas = em.createQuery("FROM Pessoa", Pessoa.class).getResultList();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM PessoaSalarioConsolidado").executeUpdate(); // limpa tabela antes
        for (Pessoa pessoa : pessoas) {

            String nomePessoa = pessoa.getNome();
            String nomeCargo = pessoa.getCargo().getNome();
            Integer pessoaId = pessoa.getId();

            // Vencimentos do cargo
            List<CargoVencimento> vinculos = em.createQuery(
                            "FROM CargoVencimento cv WHERE cv.cargo.id = :cargoId", CargoVencimento.class)
                    .setParameter("cargoId", pessoa.getCargo().getId())
                    .getResultList();

            BigDecimal salario = BigDecimal.ZERO;

            for (CargoVencimento cv : vinculos) {
                Vencimento v = cv.getVencimento();
                if ("CREDITO".equalsIgnoreCase(v.getTipo())) {
                    salario = salario.add(v.getValor());
                } else if ("DEBITO".equalsIgnoreCase(v.getTipo())) {
                    salario = salario.subtract(v.getValor());
                }
            }

            PessoaSalarioConsolidado consolidado = new PessoaSalarioConsolidado();
            consolidado.setPessoaId(pessoaId);
            consolidado.setNomePessoa(nomePessoa);
            consolidado.setNomeCargo(nomeCargo);
            consolidado.setSalario(salario);

            em.persist(consolidado);
        }
        em.getTransaction().commit();
    }
}
