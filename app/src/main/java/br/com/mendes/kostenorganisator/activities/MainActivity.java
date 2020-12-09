package br.com.mendes.kostenorganisator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.adapters.TabsAdapter;
import br.com.mendes.kostenorganisator.fragments.ConstructorFragment;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ExtendedFloatingActionButton fab_lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Criação das Abas
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(),1);
        List<String> Nomes = new ArrayList<>();

        adapter.add(ConstructorFragment.newInstance("Resumo"),"Resumo");

        Nomes.add("Primeiro");
        Nomes.add("Segundo");
        Nomes.add("Terceiro");
        Nomes.add("Quarto");
        Nomes.add("Quinto");
        for(String nome : Nomes){
            adapter.add(ConstructorFragment.newInstance("Listas"),nome + " TAB");
        }


        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);


        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        fab = findViewById(R.id.fab_menu);
        fab_lista = findViewById(R.id.fab_lista);

        fab_lista.setExtended(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 animarFabs();
            }
        });


    }

    public void animarFabs(){
        int visibilidade;
        float alpha0,alpha1,rotacao;
        long duracao = 500;
        boolean close;
        if(fab.getRotation() == 0){
            //Abrir
            visibilidade = View.VISIBLE;
            alpha0 = 0f;
            alpha1 = 1f;
            rotacao = 225;
            fab_lista.setVisibility(visibilidade);
            close = false;

            fab_lista.setAlpha(alpha0);
            fab_lista.animate()
                    .setDuration(duracao)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fab_lista.extend();
                            super.onAnimationEnd(animation);
                        }
                    })
                    .alpha(alpha1)
                    .start();
            if(close)
                fab_lista.setVisibility(visibilidade);
        }else{
            //Fechar
            Log.v("DEBUG","Fechar Fabs");
            visibilidade = View.INVISIBLE;
            alpha0 = 1f;
            alpha1 = 0f;
            rotacao = 0;
            close = true;
            fab_lista.setAlpha(alpha0);
            fab_lista.addOnShrinkAnimationListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    fab_lista.animate()
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {

                                    super.onAnimationEnd(animation);
                                }
                            })
                            .alpha(0)
                            .start();
                    super.onAnimationEnd(animation);
                }
            });
            fab_lista.shrink();



            if(close)
                fab_lista.setVisibility(visibilidade);
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
    }


}