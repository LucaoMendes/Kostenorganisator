package br.com.mendes.kostenorganisator.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.List;

public class ListaModel implements Serializable {
    private String nomeLista;
    private String idLista;
    private String key;



    public ListaModel(String idLista, String nomeLista) {
        this.idLista = idLista;
        this.nomeLista = nomeLista;
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

    @Override
    public String toString() {
        return getNomeLista();
    }
}
