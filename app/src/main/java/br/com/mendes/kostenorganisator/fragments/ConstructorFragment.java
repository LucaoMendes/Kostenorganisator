package br.com.mendes.kostenorganisator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.adapters.ListaRecyclerAdapter;
import br.com.mendes.kostenorganisator.adapters.ResumoRecyclerAdapter;
import br.com.mendes.kostenorganisator.config.ConfigDB;
import br.com.mendes.kostenorganisator.models.AtividadeModel;
import br.com.mendes.kostenorganisator.models.CardModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConstructorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConstructorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public ConstructorFragment() {
        // Required empty public constructor
    }
    public static ConstructorFragment newInstance(String param1) {
        ConstructorFragment fragment = new ConstructorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }

    }
    public void setupRecycler(){

        if(mParam1 == "Resumo"){
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            recyclerView.setHasFixedSize(true);
            ArrayList<CardModel> cartoes = new ArrayList<>();
            cartoes.add(new CardModel("Categorias"));
            ResumoRecyclerAdapter adapter = new ResumoRecyclerAdapter(cartoes);
            recyclerView.setAdapter(adapter);
        }else if(mParam1 == "Listas"){

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);

            ArrayList<AtividadeModel> atividades = new ArrayList<>();


            ListaRecyclerAdapter adapter = new ListaRecyclerAdapter(atividades);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_constructor, container, false);
        //texto = v.findViewById(R.id.texto);
        //texto.setText(mParam1);
        recyclerView = v.findViewById(R.id.recycler);
        fab = getActivity().findViewById(R.id.fab_menu);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx,int dy){
                super.onScrolled(recyclerView, dx, dy);

                if (dy >0) {
                    // Scroll Down
                    if (fab.isShown()) {
                        fab.hide();
                    }
                }
                else if (dy <0) {
                    // Scroll Up
                    if (!fab.isShown()) {
                        fab.show();
                    }
                }
            }
        });



        setupRecycler();
        return v;
    }
}