package br.com.mendes.kostenorganisator.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.activities.MainActivity;
import br.com.mendes.kostenorganisator.adapters.TabsAdapter;
import br.com.mendes.kostenorganisator.config.ConfigDB;
import br.com.mendes.kostenorganisator.models.ListaModel;
import br.com.mendes.kostenorganisator.models.Utils;

public class ListasDAO {
    private DatabaseReference databaseReference;
    public static List<ListaModel> DataCache =new ArrayList<>();


    public ListaModel create(ListaModel lista){
        databaseReference = FirebaseDatabase.getInstance().getReference(ConfigDB.LISTASTABLE);

        String id = databaseReference.push().getKey();
        lista.setIdLista(id);
        databaseReference.child(id).setValue(lista);
        return lista;
    }

    public void obterListas(final FragmentManager fm, final ViewPager viewPager, final TabLayout tabLayout){

        databaseReference = FirebaseDatabase.getInstance().getReference(ConfigDB.LISTASTABLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListasDAO.DataCache.clear();
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        ListaModel lista = ds.getValue(ListaModel.class);
                        lista.setIdLista(ds.getKey());
                        ListasDAO.DataCache.add(lista);
                    }
                        Utils.criarAbas(fm, viewPager, tabLayout);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
