package com.example.modsongslist_android.songs_list;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class SongPagerAdapter extends FragmentStateAdapter {

    private String[] titles;
    private List<Fragment> songFragments = new ArrayList<>();

    public SongPagerAdapter(Fragment fragment, String[] titles) {
        super(fragment);
        this.titles = titles;
    }

    public void addFragment(Fragment fragment){
        songFragments.add(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return songFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return songFragments.size();
    }


}
