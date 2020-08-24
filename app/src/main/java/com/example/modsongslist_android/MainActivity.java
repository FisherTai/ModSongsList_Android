package com.example.modsongslist_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.modsongslist_android.songs_list.SongListFragment;
import com.example.modsongslist_android.songs_list.SongViewPagerFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomNavigationView;
    private MaterialToolbar mToolbar ;
    private int currentSelected = 0;
    private int current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        setBottomBar();
        initDarwerBar();
        setupDrawerContent(mNavigationView);
        SongListFragment allFragment = new SongListFragment(SongListFragment.CLASS_ALLSONG);
        MyUtil.getInstance().addFragmentToActivity(getSupportFragmentManager(), allFragment, R.id.fragment_conten);
    }

    private void findview(){
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mBottomNavigationView = findViewById(R.id.bnv_bottom);
    }
//
    private void setBottomBar() {

        mBottomNavigationView.setSelectedItemId(R.id.item_allSong);
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (current = item.getItemId()) {
                //全部
                case R.id.item_allSong:
                    SongListFragment allFragment = new SongListFragment(SongListFragment.CLASS_ALLSONG);
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), allFragment, R.id.fragment_conten);
                    setNavItemChecked();
                    break;
                //最爱
                case R.id.item_favorite:
                    SongListFragment favoriteFragment = new SongListFragment(SongListFragment.CLASS_FAVORITE);
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), favoriteFragment, R.id.fragment_conten);
                    setNavItemChecked();
                    break;
                case R.id.item_test:
                    SongViewPagerFragment viewPagerFragment = new SongViewPagerFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), viewPagerFragment, R.id.fragment_conten);
                    setNavItemChecked();
            }
            return true;
        });
    }

    protected void initDarwerBar() {
//        setSupportActionBar(findViewById(R.id.toolbar));
//        ActionBar ab = getSupportActionBar();
//        assert ab != null;
//        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    //移除先前點擊過的狀態
                    setNavItemChecked();
                    switch (menuItem.getItemId()) {
                        //麗厚廳
                        case R.id.item_class1:
                            SongListFragment sub1Fragment = new SongListFragment(SongListFragment.CLASS_LIHO);
                            MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub1Fragment, R.id.fragment_conten);
                            break;
                        //尚讚K歌王
                        case R.id.item_class2:
                            SongListFragment sub2Fragment = new SongListFragment(SongListFragment.CLASS_SONJAIN);
                            MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub2Fragment, R.id.fragment_conten);
                            break;
                        //閃亮大歌廳
                        case R.id.item_class3:
                            SongListFragment sub3Fragment = new SongListFragment(SongListFragment.CLASS_FLASH);
                            MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub3Fragment, R.id.fragment_conten);
                            break;
                        //好歌大家唱
                        case R.id.item_class4:
                            SongListFragment sub4Fragment = new SongListFragment(SongListFragment.CLASS_GOODSONG);
                            MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub4Fragment, R.id.fragment_conten);
                            break;
                        //歡唱K歌館
                        case R.id.item_class5:
                            SongListFragment sub5Fragment = new SongListFragment(SongListFragment.CLASS_HUANGCHUN);
                            MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub5Fragment, R.id.fragment_conten);
                            break;
                        //美華卡拉吧
                        case R.id.item_class6:
                            SongListFragment sub6Fragment = new SongListFragment(SongListFragment.CLASS_MEIHUA);
                            MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub6Fragment, R.id.fragment_conten);
                            break;
                        //K歌大聯盟
                        case R.id.item_class7:
                            SongListFragment sub7Fragment = new SongListFragment(SongListFragment.CLASS_KSONG);
                            MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub7Fragment, R.id.fragment_conten);
                            break;
                    }
                    // Close the navigation drawer when an item is selected.
                    currentSelected = menuItem.getItemId();
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                });
    }

    private void setNavItemChecked(){
        if(currentSelected != 0) {
            mNavigationView.getMenu().findItem(currentSelected).setChecked(false);
        }
    }

}
