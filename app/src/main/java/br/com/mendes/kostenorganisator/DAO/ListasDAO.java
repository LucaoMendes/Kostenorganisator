package br.com.mendes.kostenorganisator.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.models.ListaModel;

public class ListasDAO {
    private DatabaseReference databaseReference;
    private ArrayAdapter<ListaModel> adapter;
    private List<ListaModel> listas;
    private ListView list;
    private Context context;
    public ListaModel create(ListaModel lista){
        databaseReference = FirebaseDatabase.getInstance().getReference("Listas");

        String id = databaseReference.push().getKey();
        lista.setIdLista(id);
        databaseReference.child(id).setValue(lista);

        return lista;
    }
    public List<ListaModel> getAll(Context ctx){
        listas = new ArrayList<>();
        this.context = ctx;

       Log.v("DEBUG DB SIZEasdadasd",Integer.toString(listas.size()));

       return listas;
    }
}
