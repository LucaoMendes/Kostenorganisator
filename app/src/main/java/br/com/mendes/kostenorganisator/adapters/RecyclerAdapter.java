package br.com.mendes.kostenorganisator.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.fragments.CardFragment;
import br.com.mendes.kostenorganisator.models.Cards;

public class RecyclerAdapter extends RecyclerView.Adapter<CardFragment> {
    private ArrayList<Cards> cartoes;

    public RecyclerAdapter(ArrayList<Cards> cartoes) {
        this.cartoes = cartoes;
    }

    @NonNull
    @Override
    public CardFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardFragment(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardFragment holder, int position) {
        holder.txt_titulo.setText(cartoes.get(position).getTitulo());
        holder.txt_secundario.setText(cartoes.get(position).getCorpo());
        holder.texto.setText(cartoes.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return cartoes.size();
    }
}
