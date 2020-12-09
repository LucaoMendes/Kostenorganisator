package br.com.mendes.kostenorganisator.models;

public class AtividadeModel {
    private String id;
    private String tituloAtv;
    private Float valorAtv;
    private String dataAtv;
    private CategoriaModel categoriaAtv;

    public AtividadeModel(String id, String tituloAtv, Float valorAtv, String dataAtv, CategoriaModel categoriaAtv) {
        this.id = id;
        this.tituloAtv = tituloAtv;
        this.valorAtv = valorAtv;
        this.dataAtv = dataAtv;
        this.categoriaAtv = categoriaAtv;
    }

    public AtividadeModel(String tituloAtv, Float valorAtv, String dataAtv, CategoriaModel categoriaAtv) {
        this.tituloAtv = tituloAtv;
        this.valorAtv = valorAtv;
        this.dataAtv = dataAtv;
        this.categoriaAtv = categoriaAtv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public CategoriaModel getCategoriaAtv() {
        return categoriaAtv;
    }

    public void setCategoriaAtv(CategoriaModel categoriaAtv) {
        this.categoriaAtv = categoriaAtv;
    }
}
