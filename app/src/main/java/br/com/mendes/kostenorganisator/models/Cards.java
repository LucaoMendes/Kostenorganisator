package br.com.mendes.kostenorganisator.models;

public class Cards {
    private String titulo;
    private String descricao;
    private String corpo;

    public Cards (String titulo,String descricao, String corpo){
        this.titulo = titulo;
        this.descricao = descricao;
        this.corpo = corpo;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getDescricao(){
        return descricao;
    }
    public String getCorpo(){
        return corpo;
    }
}
