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
    private boolean mostrarTabela = false;

    private int paginaAtual = 0;
    private int tamanhoPagina = 50;

    private PessoaSalarioDAO dao = new PessoaSalarioDAO();

    @PostConstruct
    public void init() {
        // Não carregamos os dados aqui para não exibir a lista inicialmente
    }

    public void recalcularSalarios() {
        dao.calcularSalarios();
        listaPessoas = dao.listarTodos();
        paginaAtual = 0;
        mostrarTabela = true;
    }

    public List<PessoaSalarioConsolidado> getPaginaPessoas() {
        if (listaPessoas == null) return null;
        int inicio = paginaAtual * tamanhoPagina;
        int fim = Math.min(inicio + tamanhoPagina, listaPessoas.size());
        return listaPessoas.subList(inicio, fim);
    }

    public void proximaPagina() {
        if ((paginaAtual + 1) * tamanhoPagina < listaPessoas.size()) {
            paginaAtual++;
        }
    }

    public void paginaAnterior() {
        if (paginaAtual > 0) {
            paginaAtual--;
        }
    }

    public int getPaginaAtual() {
        return paginaAtual + 1;
    }

    public int getTotalPaginas() {
        if (listaPessoas == null || listaPessoas.isEmpty()) return 1;
        return (int) Math.ceil((double) listaPessoas.size() / tamanhoPagina);
    }

    public boolean isMostrarTabela() {
        return mostrarTabela;
    }

    public void setMostrarTabela(boolean mostrarTabela) {
        this.mostrarTabela = mostrarTabela;
    }
}
