package br.com.mendes.kostenorganisator.config;

import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.models.CategoriaModel;
import br.com.mendes.kostenorganisator.realm.models.CategoriaModelR;

public interface ConfigDB {
    //TODO: isso aqui não era pra estar no Utils?

    public final String DBNAME = "kostenorganisator";
    public final String ATIVIDADESTABLE = "atividades";
    public final String LISTASTABLE = "listas";
    public final String CATEGORIASTABLE = "categorias";

    public final int CREATE_OP = 0;
    public final int READ_OP = 1;
    public final int UPDATE_OP = 2;
    public final int DELETE_OP = 3;


    //Categorias

    public CategoriaModelR alimentacao = new CategoriaModelR(1,"Alimentação", R.color.red,R.drawable.alimentacao);
    public CategoriaModelR mobilidade = new CategoriaModelR(2,"Mobilidade",R.color.cinza,R.drawable.mobilidade);
    public CategoriaModelR compras = new CategoriaModelR(3,"Compras",R.color.blue,R.drawable.compras);
}
