package br.com.mendes.kostenorganisator.models;

public class Atividades {
    private int id;
    private String tituloAtv;
    private Float valorAtv;
    private String dataAtv;

    public Atividades(String tituloAtv, Float valorAtv, String dataAtv) {
        this.tituloAtv = tituloAtv;
        this.valorAtv = valorAtv;
        this.dataAtv = dataAtv;
    }

    public Atividades(int id, String tituloAtv, Float valorAtv, String dataAtv) {
        this.id = id;
        this.tituloAtv = tituloAtv;
        this.valorAtv = valorAtv;
        this.dataAtv = dataAtv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
