package br.com.mendes.kostenorganisator.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.mendes.kostenorganisator.config.ConfigDB;
import br.com.mendes.kostenorganisator.models.AtividadeModel;

public class AtividadesDAO {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(ConfigDB.ATIVIDADESTABLE);

    public boolean Create(AtividadeModel atv){
        String id = databaseReference.push().getKey();
        atv.setId(id);
        databaseReference.child(id).setValue(atv);
        return true;
    }


}
