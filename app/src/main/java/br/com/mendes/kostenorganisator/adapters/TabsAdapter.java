package br.com.mendes.kostenorganisator.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.kostenorganisator.fragments.ConstructorFragment;

public class TabsAdapter extends FragmentPagerAdapter  {

    private List<ConstructorFragment> listFragments = new ArrayList<>();
    private List<String> listFragmentsTitle =  new ArrayList<>();

    public TabsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void add(ConstructorFragment frag, String title){
        this.listFragments.add(frag);
        this.listFragmentsTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return listFragmentsTitle.get(position);
    }
}
