package com.example.modsongslist_android.songs_list;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.modsongslist_android.BaseFragment;
import com.example.modsongslist_android.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class SongViewPagerFragment extends BaseFragment {

    TabLayout mTabLayout;
    ViewPager2 mViewPager;


    @Override
    protected int getLayout() {
        return R.layout.fragment_viewpager;
    }

    @Override
    protected void findView() {
        mTabLayout = rootView.findViewById(R.id.song_tab_layout);
        mViewPager = rootView.findViewById(R.id.song_view_pager);
    }

    @Override
    protected void initLayoutView() {
        String []titles = getResources().getStringArray(R.array.song_class_array);
        PagerAdapter mPagerAdapter = new PagerAdapter(this,titles);
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_ALLSONG));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_LIHO));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_SONJAIN));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_FLASH));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_GOODSONG));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_HUANGCHUN));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_MEIHUA));
        mPagerAdapter.addFragment(new SongListFragment(SongListFragment.CLASS_KSONG));

        mViewPager.setAdapter(mPagerAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> tab.setText(titles[position])).attach();
    }


    private static class PagerAdapter extends FragmentStateAdapter {

        private String[] titles;
        private List<Fragment> songFragments = new ArrayList<>();

        public PagerAdapter(Fragment fragment,String[] titles) {
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


}
