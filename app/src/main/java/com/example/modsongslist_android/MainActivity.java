package com.example.modsongslist_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.modsongslist_android.model.RepositoryCallBack;
import com.example.modsongslist_android.model.Song;
import com.example.modsongslist_android.model.SongRepository;
import com.example.modsongslist_android.songs_list.AllFragment;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());
        setContentView(R.layout.activity_main);

        //Todo 獲取歌單跟自選要改成在啟動頁
        getSelfList();
        SongRepository.getINSTANCE().InitSonglist();


        AllFragment allFragment = new AllFragment();
        MyUtil.getInstance().addFragmentToActivity(getSupportFragmentManager(), allFragment, R.id.fragment_conten);
    }


    private void getSelfList() {
        SongRepository.getINSTANCE().getSelfListFromDB(new RepositoryCallBack<List<Song>>() {
            @Override
            public void onSuccess(List<Song> result) {
                MainActivity.this.runOnUiThread(() ->
                        SongRepository.getINSTANCE().setSelfList(result)
                );
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "onFailure: " + e);
            }
        });
    }
}
