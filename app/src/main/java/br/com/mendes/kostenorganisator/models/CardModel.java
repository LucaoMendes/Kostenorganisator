package br.com.mendes.kostenorganisator.models;

public class CardModel {
    private String titulo;

    public CardModel(String titulo){
        this.titulo = titulo;
    }
    public String getTitulo(){
        return titulo;
    }
}
