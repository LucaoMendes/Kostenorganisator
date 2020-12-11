package br.com.mendes.kostenorganisator.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class AtividadeModelR  extends RealmObject {

    @PrimaryKey
    private long id;
    private long idLista;
    private String tituloAtv,dataAtv;
    private Float valorAtv;
    private CategoriaModelR categoriaAtv;

    @Ignore
    private String Key;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public AtividadeModelR (long id, long idLista, String tituloAtv, String dataAtv, Float valorAtv, CategoriaModelR categoriaAtv) {
        this.id = id;
        this.idLista = idLista;
        this.tituloAtv = tituloAtv;
        this.dataAtv = dataAtv;
        this.valorAtv = valorAtv;
        this.categoriaAtv = categoriaAtv;
    }

    public AtividadeModelR (long idLista, String tituloAtv, String dataAtv, Float valorAtv, CategoriaModelR categoriaAtv) {
        this.idLista = idLista;
        this.tituloAtv = tituloAtv;
        this.dataAtv = dataAtv;
        this.valorAtv = valorAtv;
        this.categoriaAtv = categoriaAtv;
    }

    public AtividadeModelR () {
    }

    public long getIdLista() {
        return idLista;
    }

    public void setIdLista(long idLista) {
        this.idLista = idLista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTituloAtv() {
        return tituloAtv;
    }

    public void setTituloAtv(String tituloAtv) {
        this.tituloAtv = tituloAtv;
    }

    public Float getValorAtv() {
        return valorAtv;
    }

    public void setValorAtv(Float valorAtv) {
        this.valorAtv = valorAtv;
    }

    public String getDataAtv() {
        return dataAtv;
    }

    public void setDataAtv(String dataAtv) {
        this.dataAtv = dataAtv;
    }

    public CategoriaModelR getCategoriaAtv() {
        return categoriaAtv;
    }

    public void setCategoriaAtv(CategoriaModelR categoriaAtv) {
        this.categoriaAtv = categoriaAtv;
    }
    @Override
    public String toString() {
        return getTituloAtv();
    }
}
