package br.com.mendes.kostenorganisator.models;

import java.util.List;

public class ListaModel {
    private List<AtividadeModel> atividades;
    private String nomeLista;
    private String idLista;



    public ListaModel(String idLista, String nomeLista, List<AtividadeModel> atividades) {
        this.idLista = idLista;
        this.nomeLista = nomeLista;
        this.atividades = atividades;
    }

    public ListaModel(String nomeLista,List<AtividadeModel> atividades) {
        this.nomeLista = nomeLista;
        this.atividades = atividades;
    }

    public ListaModel(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public ListaModel() {
    }

    public String getIdLista() {
        return idLista;
    }

    public void setIdLista(String idLista) {
        this.idLista = idLista;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public List<AtividadeModel> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeModel> atividades) {
        this.atividades = atividades;
    }
}
