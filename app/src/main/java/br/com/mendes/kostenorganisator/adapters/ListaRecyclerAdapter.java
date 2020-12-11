package br.com.mendes.kostenorganisator.adapters;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.fragments.AtividadeFragment;
import br.com.mendes.kostenorganisator.fragments.CardFragment;
import br.com.mendes.kostenorganisator.models.AtividadeModel;
import br.com.mendes.kostenorganisator.models.CardModel;
import br.com.mendes.kostenorganisator.realm.models.AtividadeModelR;

public class ListaRecyclerAdapter extends RecyclerView.Adapter<AtividadeFragment> {

    /**
     * Instanciando variaveis...
     * TODO: Ver como irei fazer para a recyclerView atualizar junto com as informações adicionadas
     */
    private List<AtividadeModelR> atividades;

    /**
     * Constructor
     * @param atividades
     */
    public ListaRecyclerAdapter(List<AtividadeModelR> atividades) {
        this.atividades = atividades;
    }

    @NonNull
    @Override
    public AtividadeFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AtividadeFragment(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_atividade,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AtividadeFragment holder, int position) {

        //Pega as informações na posição position e coloca nos objetos dentro do viewHolder
        String valorAtividade = "R$ "+Float.toString(atividades.get(position).getValorAtv()).replace(".",",");
        holder.txt_tituloAtividade.setText(atividades.get(position).getTituloAtv());
        holder.txt_valorAtividade.setText(valorAtividade);
        holder.txt_dataAtvidade.setText(atividades.get(position).getDataAtv());
        holder.chip_categoria.setText(atividades.get(position).getCategoriaAtv().getNomeCategoria());
        holder.chip_categoria.setChipIconResource(atividades.get(position).getCategoriaAtv().getIconeCategoria());
        holder.img_iconeCategoria.setImageResource(atividades.get(position).getCategoriaAtv().getIconeCategoria());
        holder.layoutIcone.setBackgroundTintList(ContextCompat.getColorStateList(holder.img_iconeCategoria.getContext(),atividades.get(position).getCategoriaAtv().getCorCategoria()));


        holder.btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BTN EDITAR
                //TODO: Botão de editar atividade (Acredito que seja mais facil se não for um ViewGroup e sim uma activity)
            }
        });

        holder.btn_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BTN APAGAR
                //TODO:Botão Apagar Atividade
            }
        });



    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }
}
