package br.com.mendes.kostenorganisator.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.mendes.kostenorganisator.DAO.AtividadesDAO;
import br.com.mendes.kostenorganisator.DAO.ListasDAO;
import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.adapters.TabsAdapter;
import br.com.mendes.kostenorganisator.config.ConfigDB;
import br.com.mendes.kostenorganisator.fragments.ConstructorFragment;
import br.com.mendes.kostenorganisator.models.AtividadeModel;
import br.com.mendes.kostenorganisator.models.CategoriaModel;
import br.com.mendes.kostenorganisator.models.ListaModel;
import br.com.mendes.kostenorganisator.models.Utils;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private ExtendedFloatingActionButton fab_lista,fab_atividade;
    private ViewGroup hiddenPanelLista,hiddenPanelAtv,hiddenPanel;
    private boolean isPanelShown,isFabOpen;
    private Spinner categoriaDropDown;
    private Button btn_fecharAtv,btn_fecharList,btn_salvarLista,btn_salvarAtividade;
    private EditText tituloLista,tituloAtividade,valorAtividade;
    private List<ListaModel> listas;
    private TabsAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ListasDAO listasDAO = new ListasDAO();
    private AtividadesDAO atividadesDAO = new AtividadesDAO();
    private boolean resumoOpen;
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
        valorAtividade = findViewById(R.id.valorAtv);

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
                addLista(v);
            }
        });

        btn_salvarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAtividade(v);
                Utils.fecharTeclado(MainActivity.this);
                slideUpDown(hiddenPanelAtv);
            }
        });

        ArrayList<CategoriaModel> entries = new ArrayList<>();
        categoriaDropDown = findViewById(R.id.spinner_categ);


        //Criação de abas




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




    }



    @Override
    protected void onStart() {

        listasDAO.obterListas(getSupportFragmentManager(),viewPager,tabLayout);
        atividadesDAO.obterAtividades();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String nomepag = tabLayout.getTabAt(position).getText().toString();
                if(nomepag == "Resumo"){
                    resumoOpen = true;
                    fab_atividade.setEnabled(false);
                }else{
                    resumoOpen = false;
                    fab_atividade.setEnabled(true);
                }
                if(isPanelShown){
                    DownAll(hiddenPanelLista,hiddenPanelAtv);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        atividadesDAO.obterAtividades();
        super.onStart();
    }

    private void addAtividade(View v) {
        String titulo = tituloAtividade.getText().toString();
        int indexLista = viewPager.getCurrentItem()-1;
        String idLista = ListasDAO.DataCache.get(indexLista).getIdLista();
        float valor = Float.parseFloat(valorAtividade.getText().toString());
        String categoria = categoriaDropDown.getSelectedItem().toString();
        CategoriaModel categ;
        switch (categoria){
            case "Alimentação":
                categ = ConfigDB.alimentacao;
                break;
            case "Compras":
                categ = ConfigDB.compras;
                break;
            default:
                categ = ConfigDB.mobilidade;
                break;
        }

        AtividadeModel newAtividade = new AtividadeModel(idLista,titulo, "09/12",valor,categ);
        atividadesDAO.Create(newAtividade);

    }
    public void addLista(View v){
        String titulo = tituloLista.getText().toString();

        ListaModel listaNew = new ListaModel(titulo);
        listaNew = new ListasDAO().create(listaNew);
        slideUpDown(hiddenPanelLista);
        Utils.show(v,"Lista adicionada com sucesso");
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
    public void DownAll(ViewGroup v, ViewGroup v1){
        Animation bottomDown = AnimationUtils.loadAnimation(this,
                R.anim.bottom_down);
        fab.show();
        if(v.getVisibility() == View.VISIBLE){
            v.startAnimation(bottomDown);
            v.setVisibility(View.INVISIBLE);
        }
        if(v1.getVisibility() == View.VISIBLE){
            v1.startAnimation(bottomDown);
            v1.setVisibility(View.INVISIBLE);
        }

        Utils.fecharTeclado(MainActivity.this);
        isPanelShown = false;
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
            if(!resumoOpen)
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