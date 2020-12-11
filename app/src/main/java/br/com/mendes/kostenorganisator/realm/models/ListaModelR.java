package br.com.mendes.kostenorganisator.realm.models;

import io.realm.RealmObject;

public class ListaModelR  extends RealmObject {

    private long idLista;
    private String nomeLista;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ListaModelR (int idLista, String nomeLista) {
        this.idLista = idLista;
        this.nomeLista = nomeLista;
    }

    public ListaModelR (String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public ListaModelR () {
    }

    public long getIdLista() {
        return idLista;
    }

    public void setIdLista(long idLista) {
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
