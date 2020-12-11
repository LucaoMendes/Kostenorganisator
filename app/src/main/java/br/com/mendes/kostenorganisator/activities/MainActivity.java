package br.com.mendes.kostenorganisator.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
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
import br.com.mendes.kostenorganisator.adapters.TabsAdapter;
import br.com.mendes.kostenorganisator.config.ConfigDB;
import br.com.mendes.kostenorganisator.models.AtividadeModel;
import br.com.mendes.kostenorganisator.models.CategoriaModel;
import br.com.mendes.kostenorganisator.models.ListaModel;
import br.com.mendes.kostenorganisator.models.Utils;
import br.com.mendes.kostenorganisator.realm.models.AtividadeModelR;
import br.com.mendes.kostenorganisator.realm.models.CategoriaModelR;
import br.com.mendes.kostenorganisator.realm.models.ListaModelR;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private ExtendedFloatingActionButton fab_lista,fab_atividade;
    private ViewGroup hiddenPanelLista,hiddenPanelAtv,hiddenPanel;
    private boolean isPanelShown,isFabOpen;
    private Spinner categoriaDropDown;
    private Button btn_fecharAtv,btn_fecharList,btn_salvarLista,btn_salvarAtividade;
    private EditText tituloLista,tituloAtividade,valorAtividade;
    private List<ListaModel> listas;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ListasDAO listasDAO = new ListasDAO();
    private AtividadesDAO atividadesDAO = new AtividadesDAO();
    private boolean resumoOpen;
    Realm realm;

    //TODO: Preciso rever todo o código estrutural do projeto e ver onde estou errando, projeto está MAL ESTRUTURADO!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * Inicialização de Variavéis
         */

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

        hiddenPanelLista = findViewById(R.id.hidden_panel_lista);
        hiddenPanelAtv = findViewById(R.id.hidden_panel_atividade);


        isPanelShown = false;
        isFabOpen = false;

        fab_lista.setVisibility(View.INVISIBLE);
        fab_atividade.setVisibility(View.INVISIBLE);

        hiddenPanelAtv.setVisibility(View.INVISIBLE);
        hiddenPanelLista.setVisibility(View.INVISIBLE);

        fab_lista.setExtended(false);
        fab_atividade.setExtended(false);


        /**
         * OnClickListeners
         */

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
    }

    @Override
    protected void onStart() {


        /**
         * Esse Código tá certo?
         */
        /**
         * Código de obter Listas FIREBASE
         * Mesmo que não tenha nenhuma lista é OBRIGATORIO CRIAR a aba resumo
         */
        /*
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(ConfigDB.LISTASTABLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listasDAO.obterListas(getSupportFragmentManager(),viewPager,tabLayout);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
         */




        // TODO:Verificar todo esse código de obtenção de conteúdo.
        // TODO:Esse código tem que ficar de forma limpa, tá muito confuso
        // TODO:Tem que ver uma forma de atualizar a RecyclerView e as Abas de forma Otimizada.


        /**
         * Código de obter Atividades do FIREBASE
         */
        /*
        atividadesDAO.obterAtividades();
         */



        /**
         * Ao fazer a troca de páginas ele verifica se está em uma lista ou em um resumo.
         * Se estiver em uma lista, está habilitado para adicionar uma nova atividade, se não, não
         * Fecha também se estiver algum formulario aberto.
         */
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
        super.onStart();

        /**
         * Inicializar operações do app,
         * Criação de abas
         * Criação de listeners
         */
        Utils.iniciarApp(getSupportFragmentManager(),viewPager,tabLayout);
        realm = Realm.getDefaultInstance();
    }


//Códigos de Adicionar DO FIREBASE!!!
    private void addAtividade(View v) {
        //TODO: Rever todo o codigo de busca e envio de informações ao servidor FIREBASE


        String titulo = tituloAtividade.getText().toString();
        long idLista = viewPager.getCurrentItem();
        float valor = Float.parseFloat(valorAtividade.getText().toString());
        String categoria = categoriaDropDown.getSelectedItem().toString();
        CategoriaModelR categ;
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

        AtividadeModelR newAtividade = new AtividadeModelR(idLista,titulo, "09/12",valor,categ);
        // FIREBASE COMMAND
        // atividadesDAO.Create(newAtividade);
        atividadesDAO.inserirAtividade(newAtividade);

    }
    public void addLista(View v){
        //TODO: Rever todo o codigo de busca e envio de informações ao servidor FIREBASE

        String titulo = tituloLista.getText().toString();

        ListaModelR listaNew = new ListaModelR(titulo);
        listasDAO.inserirLista(listaNew);
        slideUpDown(hiddenPanelLista);
        Utils.show(v,"Lista adicionada com sucesso");
    }
    public void slideUpDown(ViewGroup v) {
        //TODO: Criar uma variavel para cada formulario e enviar essa função ao UTILS

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
        //TODO: Remover essa função e fazer ela funcionar na SlideUpDown


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
        //TODO: Essa função deveria estar em UTILS

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
        //TODO:Essa função deveria estar em Utils


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