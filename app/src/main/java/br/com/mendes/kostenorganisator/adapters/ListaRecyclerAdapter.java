package br.com.mendes.kostenorganisator.adapters;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.fragments.AtividadeFragment;
import br.com.mendes.kostenorganisator.fragments.CardFragment;
import br.com.mendes.kostenorganisator.models.AtividadeModel;
import br.com.mendes.kostenorganisator.models.CardModel;

public class ListaRecyclerAdapter extends RecyclerView.Adapter<AtividadeFragment> {
    private ArrayList<AtividadeModel> atividades;

    public ListaRecyclerAdapter(ArrayList<AtividadeModel> atividades) {
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

        holder.txt_tituloAtividade.setText(atividades.get(position).getTituloAtv());
        holder.txt_valorAtividade.setText("R$ "+Float.toString(atividades.get(position).getValorAtv()).replace(".",","));
        holder.txt_dataAtvidade.setText(atividades.get(position).getDataAtv());
        holder.chip_categoria.setText(atividades.get(position).getCategoriaAtv().getNomeCategoria());
        holder.chip_categoria.setChipIconResource(atividades.get(position).getCategoriaAtv().getIconeCategoria());
        holder.img_iconeCategoria.setImageResource(atividades.get(position).getCategoriaAtv().getIconeCategoria());
        holder.layoutIcone.setBackgroundTintList(ContextCompat.getColorStateList(holder.img_iconeCategoria.getContext(),atividades.get(position).getCategoriaAtv().getCorCategoria()));


        holder.btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BTN EDITAR
            }
        });

        holder.btn_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BTN APAGAR
            }
        });



    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }
}
