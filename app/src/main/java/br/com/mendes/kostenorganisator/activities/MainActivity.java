package br.com.mendes.kostenorganisator.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.DAO.ListasDAO;
import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.adapters.TabsAdapter;
import br.com.mendes.kostenorganisator.fragments.ConstructorFragment;
import br.com.mendes.kostenorganisator.models.AtividadeModel;
import br.com.mendes.kostenorganisator.models.CategoriaModel;
import br.com.mendes.kostenorganisator.models.ListaModel;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private ExtendedFloatingActionButton fab_lista,fab_atividade;
    private ViewGroup hiddenPanelLista,hiddenPanelAtv,hiddenPanel;
    private boolean isPanelShown,isFabOpen;
    private Spinner categoriaDropDown;
    private Button btn_fecharAtv,btn_fecharList,btn_salvarLista,btn_salvarAtividade;
    private EditText tituloLista,tituloAtividade;
    private List<ListaModel> listas;
    private TabsAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listas = new ArrayList<>();

        btn_fecharAtv = findViewById(R.id.btn_fecharatividade);
        btn_fecharList = findViewById(R.id.btn_fecharlista);
        btn_salvarAtividade = findViewById(R.id.btn_salvarAtividade);
        btn_salvarLista = findViewById(R.id.btn_salvarLista);

        tituloAtividade = findViewById(R.id.tituloAtv);
        tituloLista = findViewById(R.id.tituloLista);

        fab_atividade = findViewById(R.id.fab_atividade);
        fab = findViewById(R.id.fab_menu);
        fab_lista = findViewById(R.id.fab_lista);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);


        hiddenPanelLista = (ViewGroup)findViewById(R.id.hidden_panel_lista);
        hiddenPanelAtv = (ViewGroup)findViewById(R.id.hidden_panel_atividade);


        isPanelShown = false;
        isFabOpen = false;

        fab_lista.setVisibility(View.INVISIBLE);
        fab_atividade.setVisibility(View.INVISIBLE);

        hiddenPanelAtv.setVisibility(View.INVISIBLE);
        hiddenPanelLista.setVisibility(View.INVISIBLE);

        fab_lista.setExtended(false);
        fab_atividade.setExtended(false);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 animarFabs();
            }
        });

        fab_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animarFabs();
                slideUpDown(hiddenPanelLista);
            }
        });

        fab_atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animarFabs();
                slideUpDown(hiddenPanelAtv);
            }
        });


        btn_fecharAtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideUpDown(hiddenPanelAtv);
            }
        });

        btn_fecharList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideUpDown(hiddenPanelLista);
            }
        });

        btn_salvarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = tituloLista.getText().toString();

                ListaModel listaNew = new ListaModel(titulo);
                listaNew = new ListasDAO().create(listaNew);
                slideUpDown(hiddenPanelLista);
                Snackbar.make(v,"Lista Criada, Nome: "+listaNew.getNomeLista()+" ID: "+listaNew.getIdLista(), Snackbar.LENGTH_LONG).show();

            }
        });

        ArrayList<CategoriaModel> entries = new ArrayList<>();
        categoriaDropDown = findViewById(R.id.spinner_categ);

        /*
        * Spinner spinner = (Spinner) findViewById(R.id.spinner_categorias);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        * */









        //Criação das Abas

 /*
        List<ListaModel> listas = new ListasDAO().getAll(MainActivity.this);
        if(listas != null){
            for(ListaModel lista : listas){
                Nomes.add(lista.getNomeLista());
                adapter.add(ConstructorFragment.newInstance("Listas"),lista.getNomeLista());
                Snackbar.make(findViewById(android.R.id.content),"Lista Nome: "+lista.getNomeLista(), Snackbar.LENGTH_LONG).show();
            }
        }else{

        }*/

       // Log.v("DEBUG LISTa",Nomes.toString());
        //Log.v("DEBUG LISTA",Integer.toString(listas.size()));

    }

    @Override
    protected void onStart() {
        inicializarFireBase();
        adapter = new TabsAdapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        adapter.add(ConstructorFragment.newInstance("Resumo"),"Resumo");
        adapter.add(ConstructorFragment.newInstance("Listas"),"Nome da Lista");



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listas.clear();

                for( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    ListaModel l = dataSnapshot1.getValue(ListaModel.class);
                    listas.add(l);
                }


                Log.v("DEBUG TAMANHO DB ",Integer.toString(listas.size()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });





        Log.v("DEBUG TAMANHsadO DB ",Integer.toString(adapter.getCount()));


        super.onStart();
    }

    public void inicializarFireBase(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference("Listas");
    }

    public void slideUpDown(ViewGroup v) {
        hiddenPanel = v;
        if(!isPanelShown) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_up);

            hiddenPanel.startAnimation(bottomUp);
            hiddenPanel.setVisibility(View.VISIBLE);
            fab.hide();
            isPanelShown = true;
        }
        else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_down);
            fab.show();
            hiddenPanel.startAnimation(bottomDown);
            hiddenPanel.setVisibility(View.INVISIBLE);
            isPanelShown = false;
        }
    }
    public void animarFabs(){
        //Animação das fabs

        //Inicializando variaveis
        float rotacao;
        long duracao = 500;

        //Verifica a posição da fab Principal, se está rotacionada ou não
        if(!isFabOpen) {
            //adiciona a rotaçõa e isFabOpen
            rotacao = 225;
            isFabOpen = true;
            fab_atividade.setEnabled(true);
            fab_lista.setEnabled(true);
        }else{
            fab_atividade.setEnabled(false);
            fab_lista.setEnabled(false);
            rotacao = 0;
            isFabOpen = false;
        }


        fab.animate().rotation(rotacao).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                fab.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fab.setClickable(true);
                super.onAnimationEnd(animation);
            }
        }).setDuration(duracao).start();
        extendOrShrinkFabs(fab_atividade);
        extendOrShrinkFabs(fab_lista);
    }
    public void extendOrShrinkFabs(final ExtendedFloatingActionButton fab){
        if(!fab.isExtended()){
            fab.setVisibility(View.VISIBLE);
            fab.setAlpha(0f);
            fab.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fab.extend();
                            isFabOpen = true;
                            super.onAnimationEnd(animation);
                        }
                    }).start();
        }else{
            fab.addOnShrinkAnimationListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    fab.setAlpha(1f);
                    fab.animate()
                            .alpha(0f)
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    isFabOpen = false;
                                }
                            }).start();

                    super.onAnimationEnd(animation);
                }
            });
            fab.shrink();


        }
    }


}