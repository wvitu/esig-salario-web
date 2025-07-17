package com.vitor.bean;

import com.vitor.model.PessoaSalarioConsolidado;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class PessoaSalarioBean implements Serializable {

    private List<PessoaSalarioConsolidado> salarios;

    @PostConstruct
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default-pu");
        EntityManager em = emf.createEntityManager();

        salarios = em.createQuery("FROM PessoaSalarioConsolidado", PessoaSalarioConsolidado.class)
                .getResultList();

        em.close();
        emf.close();
    }

    public List<PessoaSalarioConsolidado> getSalarios() {
        return salarios;
    }
}
