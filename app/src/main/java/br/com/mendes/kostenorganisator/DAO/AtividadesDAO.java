package br.com.mendes.kostenorganisator.DAO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.config.ConfigDB;
import br.com.mendes.kostenorganisator.models.AtividadeModel;
import br.com.mendes.kostenorganisator.models.ListaModel;

public class AtividadesDAO {
    private DatabaseReference databaseReference;
    public static List<AtividadeModel> DataCache =new ArrayList<>();

    public boolean Create(AtividadeModel atv){
        databaseReference = FirebaseDatabase.getInstance().getReference(ConfigDB.ATIVIDADESTABLE);
        String id = databaseReference.push().getKey();
        atv.setId(id);
        databaseReference.child(id).setValue(atv);
        return true;
    }

    public void obterAtividades(){
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
    }


}
