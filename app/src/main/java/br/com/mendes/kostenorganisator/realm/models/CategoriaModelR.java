package br.com.mendes.kostenorganisator.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CategoriaModelR  extends RealmObject {

    private long idCategoria;
    private String nomeCategoria;
    private int CorCategoria;
    private int IconeCategoria;

    public CategoriaModelR (long idCategoria, String nomeCategoria, int corCategoria, int iconeCategoria) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        CorCategoria = corCategoria;
        IconeCategoria = iconeCategoria;
    }
    public CategoriaModelR (String nomeCategoria, int corCategoria, int iconeCategoria) {
        this.nomeCategoria = nomeCategoria;
        CorCategoria = corCategoria;
        IconeCategoria = iconeCategoria;
    }

    public CategoriaModelR () {
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getCorCategoria() {
        return CorCategoria;
    }

    public void setCorCategoria(int corCategoria) {
        CorCategoria = corCategoria;
    }

    public int getIconeCategoria() {
        return IconeCategoria;
    }

    public void setIconeCategoria(int iconeCategoria) {
        IconeCategoria = iconeCategoria;
    }
}
