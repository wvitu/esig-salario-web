package com.vitor.dao;

import com.vitor.model.PessoaSalarioConsolidado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PessoaSalarioDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default-pu");

    public List<PessoaSalarioConsolidado> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<PessoaSalarioConsolidado> lista = em
                .createQuery("SELECT p FROM PessoaSalarioConsolidado p", PessoaSalarioConsolidado.class)
                .getResultList();
        em.close();
        return lista;
    }

    public void calcularSalarios() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.createNativeQuery("DELETE FROM pessoa_salario_consolidado").executeUpdate();

        em.createNativeQuery("""
            INSERT INTO pessoa_salario_consolidado (pessoa_id, nome_pessoa, nome_cargo, salario)
            SELECT 
                p.id,
                p.nome,
                c.nome,
                SUM(CASE WHEN v.tipo = 'CREDITO' THEN v.valor ELSE -v.valor END) AS salario
            FROM pessoa p
            JOIN cargo c ON p.cargo_id = c.id
            JOIN cargo_vencimentos cv ON c.id = cv.cargo_id
            JOIN vencimentos v ON cv.vencimento_id = v.id
            GROUP BY p.id, p.nome, c.nome
        """).executeUpdate();

        em.getTransaction().commit();
        em.close();
    }
}
