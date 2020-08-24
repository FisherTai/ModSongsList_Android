package com.example.modsongslist_android.songs_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.modsongslist_android.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SongViewPagerFragment extends Fragment {

    TabLayout mTabLayout;
    ViewPager mViewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager,container,false);
        mTabLayout = view.findViewById(R.id.song_tab_layout);
        mViewPager = view.findViewById(R.id.song_view_pager);
        String []titles = getResources().getStringArray(R.array.song_class_array);
//        String []titles = {
//                getResources().getString(R.string.allsong),
//                getResources().getString(R.string.favorite),
//                getResources().getString(R.string.liho),
//                getResources().getString(R.string.sonjain),
//                getResources().getString(R.string.flash),
//                getResources().getString(R.string.goodsong),
//                getResources().getString(R.string.hunagchun),
//                getResources().getString(R.string.meihua),
//                getResources().getString(R.string.ksong)
//        };

        PagerAdapter mPagerAdapter = new PagerAdapter(getChildFragmentManager(),titles);
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_ALLSONG));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_FAVORITE));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_LIHO));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_SONJAIN));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_FLASH));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_GOODSONG));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_HUANGCHUN));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_MEIHUA));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_KSONG));

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


        return view;
    }





    private static class PagerAdapter extends FragmentPagerAdapter{

        private String[] titles;
        private List<Fragment> songFragments = new ArrayList<>();

        PagerAdapter(@NonNull FragmentManager fm, String[] titles) {
            super(fm);
            this.titles = titles;
        }


        public void addFragment(Fragment fragment){
            songFragments.add(fragment);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return songFragments.get(position);
        }

        @Override
        public int getCount() {
            return songFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


}
