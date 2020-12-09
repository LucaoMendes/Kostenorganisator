package br.com.mendes.kostenorganisator.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.mendes.kostenorganisator.config.ConfigDB;
import br.com.mendes.kostenorganisator.models.AtividadeModel;

public class AtividadesDAO {
    private DatabaseReference databaseReference;

    public boolean Create(AtividadeModel atv){
        databaseReference = FirebaseDatabase.getInstance().getReference(ConfigDB.ATIVIDADESTABLE);
        String id = databaseReference.push().getKey();
        atv.setId(id);
        databaseReference.child(id).setValue(atv);
        return true;
    }


}
