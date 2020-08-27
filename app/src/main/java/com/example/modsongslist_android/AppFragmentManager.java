package com.example.modsongslist_android;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.modsongslist_android.BuildConfig;
import com.example.modsongslist_android.songs_list.SongListFragment;

import java.util.Map;


/**
 * 管理Fragment的類別
 */

public class AppFragmentManager {

    private static AppFragmentManager instance;

    private Map<Integer, SongListFragment> mFragmentContainer;



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
        fragmentTransaction.replace(frameId, fragment).commit();
    }

    public void setFragmentInContainer(int id,SongListFragment fragment){
        mFragmentContainer.put(id,fragment);
    }

    public SongListFragment getFragmentByID(int id) {
        return mFragmentContainer.get(id);
    }


}
