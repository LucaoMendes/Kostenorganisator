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

import br.com.mendes.kostenorganisator.DAO.ListasDAO;
import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.adapters.TabsAdapter;
import br.com.mendes.kostenorganisator.fragments.ConstructorFragment;

public class Utils {
    public static final String DATE_FORMAT = "dd-MM-yyyy";


    public static void show(View v, String message){
        Snackbar.make(v,message, Snackbar.LENGTH_LONG).show();
    }
    public static void openActivity(Context c,Class clazz){
        Intent intent = new Intent(c, clazz);
        c.startActivity(intent);
    }

    public static void criarAbas(FragmentManager fm, ViewPager viewPager, TabLayout tabLayout) {

        TabsAdapter adapter = new TabsAdapter(fm,1);
        adapter.add(ConstructorFragment.newInstance(true,null),"Resumo");

        for(ListaModel l : ListasDAO.DataCache){
            adapter.add(ConstructorFragment.newInstance(false,l),l.getNomeLista());
        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static void fecharTeclado(Activity a){
        InputMethodManager imm = (InputMethodManager) a.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
