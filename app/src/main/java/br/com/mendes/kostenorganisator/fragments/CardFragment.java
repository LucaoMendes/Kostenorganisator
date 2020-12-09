package br.com.mendes.kostenorganisator.fragments;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.mendes.kostenorganisator.R;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CardFragment extends RecyclerView.ViewHolder{
    public TextView txt_titulo;
    public PieChart chart;
    public CardFragment(@NonNull View itemView) {
        super(itemView);
        txt_titulo = itemView.findViewById(R.id.txt_tituloCartao);
        chart = itemView.findViewById(R.id.chart);
        float itensGrafico[] = {5.0f,25f,15.0f};
        String descricao[] = {"Item 1", "Item 2", "Item 3"};

        List<PieEntry> entradaGrafico = new ArrayList<>();

        for(int i = 0; i < itensGrafico.length; i++){
            entradaGrafico.add(new PieEntry(itensGrafico[i],descricao[i]));
        }

        PieDataSet dataSet = new PieDataSet(entradaGrafico,"");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(dataSet);
        Description desc = chart.getDescription();
        desc.setEnabled(false);



        chart.setDrawHoleEnabled(false);
        chart.setDescription(desc);
        chart.setData(pieData);
        chart.invalidate();

    }
}