package com.example.modsongslist_android.songs_list;


import androidx.viewpager2.widget.ViewPager2;

import com.example.modsongslist_android.AppFragmentManager;
import com.example.modsongslist_android.BaseFragment;
import com.example.modsongslist_android.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class SongViewPagerFragment extends BaseFragment {

    TabLayout mTabLayout;
    ViewPager2 mViewPager;

    private SongViewPagerFragment(){

    }

    public static SongViewPagerFragment getInstance(){
        if(AppFragmentManager.getInstance().getFragmentByID(AppFragmentManager.SONG_VIEW_PAGER) == null){
            AppFragmentManager.getInstance().setFragmentInContainer(AppFragmentManager.SONG_VIEW_PAGER,new SongViewPagerFragment());
        }
        return AppFragmentManager.getInstance().getViewPagerFragmentByID(AppFragmentManager.SONG_VIEW_PAGER);
    }

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
        SongPagerAdapter mPagerAdapter = new SongPagerAdapter(this,titles);
        mPagerAdapter.addFragment(SongListFragment.getInstance(AppFragmentManager.CLASS_ALLSONG));
        mPagerAdapter.addFragment(SongListFragment.getInstance(AppFragmentManager.CLASS_LIHO));
        mPagerAdapter.addFragment(SongListFragment.getInstance(AppFragmentManager.CLASS_SONJAIN));
        mPagerAdapter.addFragment(SongListFragment.getInstance(AppFragmentManager.CLASS_FLASH));
        mPagerAdapter.addFragment(SongListFragment.getInstance(AppFragmentManager.CLASS_GOODSONG));
        mPagerAdapter.addFragment(SongListFragment.getInstance(AppFragmentManager.CLASS_HUANGCHUN));
        mPagerAdapter.addFragment(SongListFragment.getInstance(AppFragmentManager.CLASS_MEIHUA));
        mPagerAdapter.addFragment(SongListFragment.getInstance(AppFragmentManager.CLASS_KSONG));

        mViewPager.setAdapter(mPagerAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> tab.setText(titles[position])).attach();
    }

}
