package br.com.mendes.kostenorganisator.config;

import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.models.CategoriaModel;

public interface ConfigDB {
    public final String DBNAME = "kostenorganisator";
    public final String ATIVIDADESTABLE = "atividades";
    public final String CATEGORIASTABLE = "categorias";

    public final int CREATE_OP = 0;
    public final int READ_OP = 1;
    public final int UPDATE_OP = 2;
    public final int DELETE_OP = 3;


    //Categorias

    public CategoriaModel alimentacao = new CategoriaModel("Alimentação", R.color.red,R.drawable.alimentacao);
    public CategoriaModel mobilidade = new CategoriaModel("Mobilidade",R.color.cinza,R.drawable.mobilidade);
    public CategoriaModel compras = new CategoriaModel("Compras",R.color.blue,R.drawable.compras);
}
