package com.example.modsongslist_android;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.modsongslist_android.songs_list.SongListFragment;
import com.example.modsongslist_android.songs_list.SongViewPagerFragment;

import java.util.HashMap;
import java.util.Map;


/**
 * 管理Fragment的類別
 */

public class AppFragmentManager {
    private static final String TAG = "AppFragmentManager";
    private static AppFragmentManager instance;


    private Map<Integer, BaseFragment> mFragmentContainer = new HashMap<>();

    final public static int CLASS_ALLSONG = 11; //全部
    final public static int CLASS_FAVORITE = 12; //最爱
    final public static int CLASS_LIHO = 13; //麗厚廳
    final public static int CLASS_SONJAIN = 14; //尚讚K歌王
    final public static int CLASS_FLASH = 15; //閃亮大歌廳
    final public static int CLASS_GOODSONG = 16; //好歌大家唱
    final public static int CLASS_HUANGCHUN = 17;  //歡唱K歌館
    final public static int CLASS_MEIHUA = 18; //美華卡拉吧
    final public static int CLASS_KSONG = 19;//K歌大聯盟
    final public static int SONG_VIEW_PAGER = 20;
    final public static int NEW_SONG = 21; //新歌清單

    private AppFragmentManager() {
    }

    public static AppFragmentManager getInstance() {

        if (null == instance) {
            instance = new AppFragmentManager();
        }
        return instance;
    }

    public void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment,
                                      int frameId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameId, fragment).commit();
    }


    public void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                          @NonNull Fragment fragment,
                                          int frameId) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameId, fragment);
        fragmentTransaction.commit();
    }

    public void setFragmentInContainer(int id,BaseFragment fragment){
        mFragmentContainer.put(id,fragment);
    }

    public SongListFragment getFragmentByID(int id) {
        try{
            return (SongListFragment)mFragmentContainer.get(id);
        }catch (Exception e){
            Log.e(TAG,"getFragmentByID:",e);
        }
        return null;
    }

    public SongViewPagerFragment getViewPagerFragmentByID(int id) {
        try{
            return (SongViewPagerFragment)mFragmentContainer.get(id);
        }catch (Exception e){
            Log.e(TAG,"getViewPagerFragmentByID:",e);
        }
        return null;
    }

}
