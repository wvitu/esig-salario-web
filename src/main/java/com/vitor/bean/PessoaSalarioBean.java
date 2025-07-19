package com.vitor.bean;

import com.vitor.dao.PessoaSalarioDAO;
import com.vitor.model.PessoaSalarioConsolidado;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PessoaSalarioBean implements Serializable {

    private List<PessoaSalarioConsolidado> listaPessoas;
    private PessoaSalarioDAO dao = new PessoaSalarioDAO();

    @PostConstruct
    public void init() {

    }

    public void recalcularSalarios() {
        dao.calcularSalarios();
        listaPessoas = dao.listarTodos();
        mostrarTabela = true; // exibe após clicar no botão
    }

    public List<PessoaSalarioConsolidado> getListaPessoas() {
        return listaPessoas;
    }

    private boolean mostrarTabela = false;

    public boolean isMostrarTabela() {
        return mostrarTabela;
    }

}
