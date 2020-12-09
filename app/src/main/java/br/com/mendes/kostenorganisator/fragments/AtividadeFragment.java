package br.com.mendes.kostenorganisator.fragments;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import br.com.mendes.kostenorganisator.R;

public class AtividadeFragment extends RecyclerView.ViewHolder{

    public TextView txt_tituloAtividade,txt_valorAtividade,txt_dataAtvidade;
    public ConstraintLayout layoutIcone;
    public ImageView img_iconeCategoria;
    public Chip chip_categoria;
    public Button btn_editar,btn_apagar;

    public AtividadeFragment(@NonNull View itemView) {
        super(itemView);


        //pega todos os itens e coloca como variaveis publicas para serem acessadas pelo ListaRecyclerAdapter
        txt_tituloAtividade = itemView.findViewById(R.id.txt_TituloAtividade);
        txt_valorAtividade = itemView.findViewById(R.id.txt_ValorAtividade);
        txt_dataAtvidade = itemView.findViewById(R.id.txt_dataAtvidade);
        img_iconeCategoria = itemView.findViewById(R.id.img_iconeCategoria);
        chip_categoria = itemView.findViewById(R.id.chip_categoria);
        btn_apagar = itemView.findViewById(R.id.btn_apagar);
        btn_editar = itemView.findViewById(R.id.btn_editar);
        layoutIcone = itemView.findViewById(R.id.iconeLayout);
    }

}