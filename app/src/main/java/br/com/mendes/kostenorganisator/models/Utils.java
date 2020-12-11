package br.com.mendes.kostenorganisator.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.DAO.AtividadesDAO;
import br.com.mendes.kostenorganisator.DAO.ListasDAO;
import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.adapters.TabsAdapter;
import br.com.mendes.kostenorganisator.fragments.ConstructorFragment;
import br.com.mendes.kostenorganisator.realm.models.AtividadeModelR;
import br.com.mendes.kostenorganisator.realm.models.ListaModelR;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class Utils {
    public static final String DATE_FORMAT = "dd-MM-yyyy";


    public static void show(View v, String message){
        Snackbar.make(v,message, Snackbar.LENGTH_LONG).show();
    }
    public static void openActivity(Context c,Class clazz){
        Intent intent = new Intent(c, clazz);
        c.startActivity(intent);
    }

    public static void criarAbas(FragmentManager fm, ViewPager viewPager, TabLayout tabLayout,Boolean criouAba,AtividadeModelR atv) {
        //TODO: Verificar uma forma de atualizar as abas sem apagar tudo e criar denovo
        TabsAdapter adapter = new TabsAdapter(fm,1);
        adapter.add(ConstructorFragment.newInstance(true, (ListaModel) null),"Resumo");

        for(ListaModelR l : ListasDAO.DataCacheR){
            Log.v("DEBUG CRIAR ABAS","LISTA: "+l.toString());
            adapter.add(ConstructorFragment.newInstance(false,l),l.getNomeLista());
        }
        viewPager.setAdapter(adapter);
        if(criouAba)
            viewPager.setCurrentItem(adapter.getCount(),true);
        else
            if(atv!=null)
                viewPager.setCurrentItem((int) atv.getIdLista(),true);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static void fecharTeclado(Activity a){
        //TODO: Teclado está abrindo com essa função
        InputMethodManager imm = (InputMethodManager) a.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }


    public static void iniciarApp(final FragmentManager fm, final ViewPager viewPager, final TabLayout tabLayout){
        new ListasDAO().buscarListas();
        new AtividadesDAO().buscarAtividades();
        criarAbas(fm,viewPager,tabLayout,false,null);

        AtividadesDAO.ResultSet.addChangeListener(new RealmChangeListener<RealmResults<AtividadeModelR>>() {
            @Override
            public void onChange(RealmResults<AtividadeModelR> atividadeModelRS) {
                new ListasDAO().buscarListas();
                criarAbas(fm,viewPager,tabLayout,false,atividadeModelRS.last());
            }
        });
        ListasDAO.ResultSet.addChangeListener(new RealmChangeListener<RealmResults<ListaModelR>>() {
            @Override
            public void onChange(RealmResults<ListaModelR> ListaModelRS) {
                new ListasDAO().buscarListas();
                criarAbas(fm,viewPager,tabLayout,true,null);
            }
        });
    }
}
