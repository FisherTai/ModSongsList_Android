package com.example.modsongslist_android.songs_list;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.modsongslist_android.BuildConfig;


/**
 * 管理Fragment的類別
 */

public class AppFragmentManager {

    private static AppFragmentManager instance;

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

}
