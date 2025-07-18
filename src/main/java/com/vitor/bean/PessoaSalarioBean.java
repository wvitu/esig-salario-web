package com.vitor.bean;

import com.vitor.model.PessoaSalarioConsolidado;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class PessoaSalarioBean implements Serializable {

    private List<PessoaSalarioConsolidado> listaPessoas;

    @PostConstruct
    public void init() {
        carregarDados();
    }

    public void calcularSalarios() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default-pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Limpar a tabela
        em.createQuery("DELETE FROM PessoaSalarioConsolidado").executeUpdate();

        // Executar cálculo baseado nas 4 tabelas
        List<Object[]> resultados = em.createNativeQuery("""
            SELECT p.id, p.nome, c.nome AS nome_cargo,
                   SUM(CASE v.tipo
                        WHEN 'CREDITO' THEN v.valor
                        WHEN 'DEBITO' THEN -v.valor
                        ELSE 0
                   END) AS salario
            FROM pessoa p
            JOIN cargo c ON p.cargo_id = c.id
            JOIN cargo_vencimentos cv ON cv.cargo_id = c.id
            JOIN vencimentos v ON v.id = cv.vencimento_id
            GROUP BY p.id, p.nome, c.nome
        """).getResultList();

        for (Object[] row : resultados) {
            PessoaSalarioConsolidado registro = new PessoaSalarioConsolidado();
            registro.setPessoaId((Integer) row[0]);
            registro.setNomePessoa((String) row[1]);
            registro.setNomeCargo((String) row[2]);
            registro.setSalario((BigDecimal) row[3]);

            em.persist(registro);
        }

        tx.commit();
        em.close();
        emf.close();

        // Recarregar a lista após cálculo
        carregarDados();
    }

    private void carregarDados() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default-pu");
        EntityManager em = emf.createEntityManager();

        listaPessoas = em.createQuery("SELECT p FROM PessoaSalarioConsolidado p", PessoaSalarioConsolidado.class)
                .getResultList();

        em.close();
        emf.close();
    }

    public List<PessoaSalarioConsolidado> getListaPessoas() {
        return listaPessoas;
    }
}
