package br.com.mendes.kostenorganisator.models;

public class AtividadeModel {


    private String id,idLista,tituloAtv,dataAtv;
    private Float valorAtv;
    private CategoriaModel categoriaAtv;


    public AtividadeModel(String id, String idLista, String tituloAtv, String dataAtv, Float valorAtv, CategoriaModel categoriaAtv) {
        this.id = id;
        this.idLista = idLista;
        this.tituloAtv = tituloAtv;
        this.dataAtv = dataAtv;
        this.valorAtv = valorAtv;
        this.categoriaAtv = categoriaAtv;
    }

    public AtividadeModel(String idLista, String tituloAtv, String dataAtv, Float valorAtv, CategoriaModel categoriaAtv) {
        this.idLista = idLista;
        this.tituloAtv = tituloAtv;
        this.dataAtv = dataAtv;
        this.valorAtv = valorAtv;
        this.categoriaAtv = categoriaAtv;
    }

    public AtividadeModel() {
    }

    public String getIdLista() {
        return idLista;
    }

    public void setIdLista(String idLista) {
        this.idLista = idLista;
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
    @Override
    public String toString() {
        return getTituloAtv() +" "+getId()+" " + getIdLista();
    }
}
