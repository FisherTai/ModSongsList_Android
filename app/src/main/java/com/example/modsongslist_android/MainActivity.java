package com.example.modsongslist_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.modsongslist_android.model.Song;
import com.example.modsongslist_android.model.SongRepository;
import com.example.modsongslist_android.songs_list.AllFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());
        setContentView(R.layout.activity_main);

        InitSonglist();

        AllFragment allFragment = new AllFragment();
        MyUtil.getInstance().addFragmentToActivity(getSupportFragmentManager(), allFragment, R.id.fragment_conten);
    }

    private void InitSonglist(){
        AppMain.getApp().songListStr = MyUtil.getInstance().readAssetsJson(AppMain.getApp(),"song_list.json");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Song>>() {}.getType();
        ArrayList<Song> songList = gson.fromJson(AppMain.getApp().songListStr,listType);
        AppMain.getApp().songList = gson.fromJson(AppMain.getApp().songListStr,listType);

        SongRepository.getINSTANCE().setList(songList);

        AppMain.getApp().getSongListComplete = true;


    }




}
