package br.com.mendes.kostenorganisator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.adapters.TabsAdapter;
import br.com.mendes.kostenorganisator.fragments.ListasFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(),1);
        List<String> Nomes = new ArrayList<>();
        Nomes.add("Resumo");
        Nomes.add("Primeiro");
        Nomes.add("Segundo");
        Nomes.add("Terceiro");
        Nomes.add("Quarto");
        Nomes.add("Quinto");
        for(String nome : Nomes){
            adapter.add(ListasFragment.newInstance(nome),nome + " TAB");
        }
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);


        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }
}