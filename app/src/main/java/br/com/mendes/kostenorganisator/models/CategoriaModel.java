package br.com.mendes.kostenorganisator.models;

public class CategoriaModel {
    private String idCategoria;
    private String nomeCategoria;
    private int CorCategoria;
    private int IconeCategoria;

    public CategoriaModel(String idCategoria, String nomeCategoria, int corCategoria, int iconeCategoria) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        CorCategoria = corCategoria;
        IconeCategoria = iconeCategoria;
    }
    public CategoriaModel(String nomeCategoria, int corCategoria, int iconeCategoria) {
        this.nomeCategoria = nomeCategoria;
        CorCategoria = corCategoria;
        IconeCategoria = iconeCategoria;
    }

    public CategoriaModel() {
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
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
