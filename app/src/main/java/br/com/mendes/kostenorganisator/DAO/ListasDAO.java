package br.com.mendes.kostenorganisator.DAO;

import android.content.Context;
import android.content.res.Resources;
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
import br.com.mendes.kostenorganisator.realm.models.AtividadeModelR;
import br.com.mendes.kostenorganisator.realm.models.ListaModelR;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListasDAO {
    /**
     * Instanciando variaveis
     * TODO: Deveria criar um databaseReference no Utils para cada uma das utilizações
     * TODO: Rever todo o codigo de busca e envio de informações ao servidor FIREBASE
     */
    private DatabaseReference databaseReference;
    private Realm realm;
    public static List<ListaModel> DataCache =new ArrayList<>();
    public static List<ListaModelR> DataCacheR =new ArrayList<>();
    public static RealmResults<ListaModelR> ResultSet;
    public ListaModel create(ListaModel lista){
        databaseReference = FirebaseDatabase.getInstance().getReference(ConfigDB.LISTASTABLE);

        String id = databaseReference.push().getKey();
        lista.setIdLista(id);
        databaseReference.child(id).setValue(lista);
        return lista;
    }

    public void obterListas(final FragmentManager fm, final ViewPager viewPager, final TabLayout tabLayout){

        /**
         * Código de Obter Listas do FIREBASE
         * Atenção que esse código era o responsável por chamar a criação de ABAS!
         */
        /*
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
         */

        //TODO: Obter Listas do banco REALMS e Adicionar ABAS
    }



    //REALMS

    public void buscarListas(){
        realm = Realm.getDefaultInstance();
        try{
            ResultSet = realm.where(ListaModelR.class).findAll().sort("idLista", Sort.ASCENDING);
            DataCacheR = ResultSet;
        }finally {
        }
    }
    public void inserirLista(final ListaModelR lista){
        realm = Realm.getDefaultInstance();
        long current_id = realm.where(ListaModelR.class).count();

        long next_Id;
        if(current_id == 0){
            next_Id = 1;
        }else{
            next_Id = current_id+1;
        }
        lista.setIdLista(next_Id);
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(lista);
                }
            });

        }finally {
        }
    }

}
