package com.example.modsongslist_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.modsongslist_android.songs_list.SongListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private BottomNavigationView bnv;

    private int current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomBar();

        SongListFragment allFragment = new SongListFragment();
        MyUtil.getInstance().addFragmentToActivity(getSupportFragmentManager(), allFragment, R.id.fragment_conten);
    }

//
    private void setBottomBar() {
        bnv = findViewById(R.id.bnv_bottom);
        bnv.setSelectedItemId(R.id.item_allSong);
        bnv.setOnNavigationItemSelectedListener(item -> {

            switch (current = item.getItemId()) {
                //全部
                case R.id.item_allSong:
                    SongListFragment allFragment = new SongListFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), allFragment, R.id.fragment_conten);
                    break;
                //最爱
                case R.id.item_favorite:
                    SongListFragment favoriteFragment = new SongListFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), favoriteFragment, R.id.fragment_conten);
                    break;
                //麗厚廳
                case R.id.item_class1:
                    SongListFragment sub1Fragment = new SongListFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub1Fragment, R.id.fragment_conten);
                    break;
                //尚讚K歌王
                case R.id.item_class2:
                    SongListFragment sub2Fragment = new SongListFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub2Fragment, R.id.fragment_conten);
                    break;
                //閃亮大歌廳
                case R.id.item_class3:
                    SongListFragment sub3Fragment = new SongListFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub3Fragment, R.id.fragment_conten);
                    break;
                //好歌大家唱
                case R.id.item_class4:
                    SongListFragment sub4Fragment = new SongListFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub4Fragment, R.id.fragment_conten);
                    break;
                //歡唱K歌館
                case R.id.item_class5:
                    SongListFragment sub5Fragment = new SongListFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub5Fragment, R.id.fragment_conten);
                    break;
                //美華卡拉吧
                case R.id.item_class6:
                    SongListFragment sub6Fragment = new SongListFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub6Fragment, R.id.fragment_conten);
                    break;
                //K歌大聯盟
                case R.id.item_class7:
                    SongListFragment sub7Fragment = new SongListFragment();
                    MyUtil.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), sub7Fragment, R.id.fragment_conten);
                    break;
            }
            return true;
        });
    }

}
