package br.com.mendes.kostenorganisator.DAO;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.config.ConfigDB;
import br.com.mendes.kostenorganisator.models.AtividadeModel;
import br.com.mendes.kostenorganisator.realm.models.AtividadeModelR;
import br.com.mendes.kostenorganisator.realm.models.ListaModelR;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;


public class AtividadesDAO {
    /**
     * Instanciando variaveis
     * TODO: Deveria criar um databaseReference no Utils para cada uma das utilizações
     * TODO: Rever todo o codigo de busca e envio de informações ao servidor FIREBASE
     */
    private DatabaseReference databaseReference;
    public static List<AtividadeModel> DataCache =new ArrayList<>();
    public static List<AtividadeModelR> DataCacheR =new ArrayList<>();
    public static RealmResults<AtividadeModelR> ResultSet;
    Realm realm;
    public boolean Create(AtividadeModel atv){
        //Criação de Atividades
        databaseReference = FirebaseDatabase.getInstance().getReference(ConfigDB.ATIVIDADESTABLE);
        //TODO: Usar o push GETKEY no REALM
        String id = databaseReference.push().getKey();
        atv.setId(id);
        databaseReference.child(id).setValue(atv);
        return true;
    }
    public void obterAtividades(){
        /**
         * Código de Obter atividades do FireBASE
         */
        /*
        databaseReference = FirebaseDatabase.getInstance().getReference(ConfigDB.ATIVIDADESTABLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                AtividadesDAO.DataCache.clear();
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        AtividadeModel atividades = ds.getValue(AtividadeModel.class);
                        atividades.setId(ds.getKey());
                        AtividadesDAO.DataCache.add(atividades);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
         */

        //TODO: Obter atividades do banco REALMS
    }

    //REALMS
    public void inserirAtividade(final AtividadeModelR atv){
        realm = Realm.getDefaultInstance();
        long current_id = realm.where(AtividadeModelR.class).count();

        long next_Id;
        if(current_id == 0){
            next_Id = 1;
        }else{
            next_Id = current_id+1;
        }
        atv.setId(next_Id);

        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(atv);
                }
            });
        }catch (Exception e){
            Log.v("DEBUG REALM EX",e.getMessage());
        }finally {
        }




    }
    public void buscarAtividades(){
        realm = Realm.getDefaultInstance();
        try{
            ResultSet = realm.where(AtividadeModelR.class).findAll().sort("id",Sort.ASCENDING);
            DataCacheR = ResultSet;
        }finally {
        }


    }
    public List<AtividadeModelR>buscarAtividadesDaLista(ListaModelR lista){
        realm = Realm.getDefaultInstance();
        List<AtividadeModelR> atividades = new ArrayList<>();
        if(realm.where(AtividadeModelR.class).count()> 0){
            try{
                atividades =  realm.where(AtividadeModelR.class).equalTo("idLista",lista.getIdLista()).findAll();
            }finally {
            }
        }
        return atividades;
    }



}
