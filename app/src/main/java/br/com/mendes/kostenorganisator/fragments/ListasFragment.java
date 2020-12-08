package br.com.mendes.kostenorganisator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.R;
import br.com.mendes.kostenorganisator.adapters.RecyclerAdapter;
import br.com.mendes.kostenorganisator.models.Cards;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView texto;
    private RecyclerView recyclerView;
    public ListasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment mainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListasFragment newInstance(String param1) {
        ListasFragment fragment = new ListasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);



        }

    }
    public void setupRecycler(){

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setHasFixedSize(true);
        ArrayList<Cards> cartoes = new ArrayList<>();
        cartoes.add(new Cards("Titulo 01","\tLorem ipsum per lorem id quis habitasse purus posuere et, odio id cursus nunc felis suspendisse praesent enim, iaculis nunc dolor aenean vivamus malesuada odio aptent. at morbi curae turpis accumsan semper pellentesque integer sociosqu aenean, at interdum feugiat tortor aliquam varius gravida malesuada pharetra platea, massa donec vehicula porttitor praesent placerat justo diam. habitasse vel aptent lacus dapibus fringilla interdum ut fringilla posuere nullam, viverra libero litora eget vivamus scelerisque condimentum malesuada eleifend potenti, himenaeos torquent suscipit auctor mattis vehicula potenti inceptos elit. vel velit pharetra urna curabitur et leo vel, malesuada lacus tortor convallis per class adipiscing tristique, consequat litora platea elit porttitor etiam. \n" +
                "\n" +
                "\tMi vulputate tortor curabitur fringilla imperdiet dolor maecenas ad leo eleifend, lectus dapibus molestie posuere iaculis eu laoreet ante hendrerit, nec nostra morbi ante bibendum cras a nisi cubilia. tempor nunc volutpat mollis aenean volutpat egestas proin torquent ad praesent, varius interdum himenaeos lectus aliquam orci placerat nibh malesuada, pharetra rutrum hendrerit aliquam torquent tincidunt blandit proin volutpat. cursus condimentum eleifend ut morbi vivamus convallis laoreet suspendisse tortor posuere aenean, ornare euismod vel phasellus etiam aenean sem aenean placerat fusce velit, hac etiam aenean placerat per dui vivamus fringilla phasellus bibendum. \n" +
                "\n" +
                "\tPorta arcu erat turpis fusce pretium, convallis habitant nam potenti ullamcorper potenti, eros integer accumsan netus. phasellus libero dapibus senectus odio rhoncus nibh aliquam, nullam fusce tempor quisque interdum per, pulvinar aliquet amet velit pulvinar ipsum. quisque fringilla ornare amet eleifend nec hendrerit interdum mi rhoncus, ipsum lacinia gravida vulputate nulla fames commodo est vel, est pellentesque nullam etiam class arcu scelerisque augue. pellentesque integer iaculis commodo vestibulum etiam quisque gravida bibendum erat conubia est, egestas per senectus cubilia habitant sodales praesent ut arcu risus pellentesque, primis sed ante habitant condimentum id in faucibus porta in. \n" +
                "\n" +
                "\tEuismod magna dapibus venenatis duis vehicula aenean tempor faucibus maecenas enim praesent, ad augue scelerisque purus netus aptent habitasse sed pulvinar. risus potenti semper sodales placerat tristique curabitur eros dapibus sagittis fermentum, mi ad ante aptent lorem tempor suscipit erat blandit fermentum commodo, quis ipsum quam congue posuere curabitur velit ipsum pretium. auctor luctus condimentum nam diam nisi egestas neque nisl et, luctus ac potenti varius condimentum odio pharetra ultrices, urna justo ut mollis ultricies dapibus felis curae. rhoncus non porta curae litora pellentesque taciti vestibulum semper dictum commodo, varius amet lobortis nec id fermentum maecenas nibh bibendum venenatis donec, felis consectetur platea a cubilia pellentesque nunc pretium porta. \n" +
                "\n" +
                "\tAliquam etiam posuere sem pharetra convallis fringilla ipsum iaculis proin, class ornare curabitur accumsan fames taciti volutpat ante donec, volutpat cubilia a vulputate consequat vivamus pulvinar sem. ultrices leo dui eu nunc suscipit tortor varius morbi nec vel phasellus, curabitur tristique tincidunt ipsum nunc platea vitae a quisque. proin nibh turpis lorem scelerisque tempus fusce fames, donec egestas vivamus lacus consequat aptent, fringilla enim tellus adipiscing platea viverra. congue eget feugiat etiam vestibulum at enim, lacus ullamcorper amet leo bibendum at, iaculis placerat morbi quisque sit. orci quisque cubilia luctus nunc, quis condimentum malesuada scelerisque, commodo ut ante. ","Fim"));
        cartoes.add(new Cards("Titulo 02","Lorem ipsum urna fringilla vitae a massa sem euismod, posuere rutrum elit et ut accumsan curae rhoncus, convallis lacinia rhoncus sem porttitor cras scelerisque. donec elementum sollicitudin aptent turpis lectus a curabitur aliquam consectetur, sagittis nostra erat sem arcu ante neque. consectetur eget torquent vitae lacus est nam, dictumst nam phasellus tincidunt pellentesque habitasse, nec blandit euismod arcu massa. ullamcorper donec elementum primis commodo morbi vulputate proin aliquam, scelerisque cursus sem per litora suspendisse turpis volutpat, viverra malesuada est convallis leo porttitor orci. fringilla habitant morbi eros nulla iaculis interdum accumsan mauris, porta viverra odio tortor phasellus sociosqu quis rutrum, aptent imperdiet justo gravida integer cras sem. ","fim"));
        cartoes.add(new Cards("Titulo 03","Odio inceptos feugiat potenti dictum porta aliquam nam, arcu nunc erat purus semper convallis malesuada pharetra, lorem nullam molestie at dapibus neque. inceptos sem eros laoreet cursus nullam eleifend tincidunt dui tempus praesent, consectetur sapien dapibus na",""));
        cartoes.add(new Cards("Titulo 04","Turpis id curabitur etiam turpis fames posuere, felis dictumst et arcu magna, eu ante dui vel class. porta lacinia laoreet lorem ad donec ullamcorper, luctus a habitasse at vel, condimentum quis a vel cras. augue massa tincidunt per porttitor condimentum habitant sodales velit pellentesque  isque per maecenas. ",""));
        RecyclerAdapter adapter = new RecyclerAdapter(cartoes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_listas, container, false);
        //texto = v.findViewById(R.id.texto);
        //texto.setText(mParam1);
        recyclerView = v.findViewById(R.id.recycler);
        setupRecycler();
        return v;
    }
}