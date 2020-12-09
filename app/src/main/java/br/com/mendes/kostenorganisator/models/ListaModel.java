package br.com.mendes.kostenorganisator.models;

import java.util.List;

public class ListaModel {
    private String idLista;
    private String nomeLista;
    private List<AtividadeModel> atividades;

    public ListaModel(String idLista, String nomeLista, List<AtividadeModel> atividades) {
        this.idLista = idLista;
        this.nomeLista = nomeLista;
        this.atividades = atividades;
    }

    public ListaModel(String nomeLista) {
        this.nomeLista = nomeLista;
    }

}
