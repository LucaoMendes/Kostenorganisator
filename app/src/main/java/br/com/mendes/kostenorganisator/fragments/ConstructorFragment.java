package br.com.mendes.kostenorganisator.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.DAO.AtividadesDAO;
import br.com.mendes.kostenorganisator.DAO.ListasDAO;
import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.adapters.ListaRecyclerAdapter;
import br.com.mendes.kostenorganisator.adapters.ResumoRecyclerAdapter;
import br.com.mendes.kostenorganisator.config.ConfigDB;
import br.com.mendes.kostenorganisator.models.AtividadeModel;
import br.com.mendes.kostenorganisator.models.CardModel;
import br.com.mendes.kostenorganisator.models.ListaModel;
import br.com.mendes.kostenorganisator.realm.models.AtividadeModelR;
import br.com.mendes.kostenorganisator.realm.models.ListaModelR;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConstructorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConstructorFragment extends Fragment {

    private String nomeLista;
    private String idLista;
    private long idListaR;

    private static List<ListaModelR> listasR = new ArrayList<>();
    private ListaModel lista;
    private boolean resumo;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public ConstructorFragment() {
        // Required empty public constructor
    }
    public static ConstructorFragment newInstance(Boolean b,ListaModel lista) {
        ConstructorFragment fragment = new ConstructorFragment();
        Bundle args = new Bundle();
        args.putBoolean("resumo",b);
        if(lista != null){
            args.putString("nomeLista",lista.getNomeLista());
            args.putString("idLista",lista.getIdLista());
        }

        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Passando lista do REALMS
     * @param b
     * @param lista
     * @return
     */
    public static ConstructorFragment newInstance(Boolean b, ListaModelR lista) {
        ConstructorFragment fragment = new ConstructorFragment();
        Bundle args = new Bundle();
        args.putBoolean("resumo",b);
        if(lista != null){
            args.putString("nomeLista",lista.getNomeLista());
            args.putString("idLista",lista.getKey());
            args.putLong("idListaR",lista.getIdLista());
            listasR.add(lista);
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resumo = getArguments().getBoolean("resumo");
            if(!resumo){
                idListaR = getArguments().getLong("idListaR");

            }


        }

    }
    public void setupRecycler(){

        if(resumo){
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            recyclerView.setHasFixedSize(true);
            ArrayList<CardModel> cartoes = new ArrayList<>();
            cartoes.add(new CardModel("Categorias"));
            ResumoRecyclerAdapter adapter = new ResumoRecyclerAdapter(cartoes);
            recyclerView.setAdapter(adapter);
        }else{

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);

            List<AtividadeModelR> atividades;
            //Atividades
            //TODO: REVISTO A FORMA DE FILTRO
            //TODO: Rever a forma de Filtro
//            for(AtividadeModel atv : AtividadesDAO.DataCache){
//                if(atv.getIdLista().equals(idLista)){
//                    atividades.add(atv);
//                }
//            }

            AtividadesDAO atividadesDAO = new AtividadesDAO();
            atividades = atividadesDAO.buscarAtividadesDaLista(listasR.get((int)idListaR -1));
            Log.v("DEBUG LISTA CONS","ID LISTA: "+ idListaR);
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