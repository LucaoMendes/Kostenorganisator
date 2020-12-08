package br.com.mendes.kostenorganisator.fragments;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.mendes.kostenorganisator.R;
import com.google.android.material.button.MaterialButton;

public class CardFragment extends RecyclerView.ViewHolder{
    public TextView txt_titulo,txt_secundario,texto;
    public MaterialButton btn_01,btn_02;
    public CardFragment(@NonNull View itemView) {
        super(itemView);
        txt_titulo = itemView.findViewById(R.id.txt_titulo);
        txt_secundario = itemView.findViewById(R.id.txt_secundario);
        texto = itemView.findViewById(R.id.texto);
        btn_01 = itemView.findViewById(R.id.btn_01);
        btn_02 = itemView.findViewById(R.id.btn_02);
    }
}