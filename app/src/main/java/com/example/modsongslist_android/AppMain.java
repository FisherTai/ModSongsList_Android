package com.example.modsongslist_android;

import android.app.Application;

import com.example.modsongslist_android.model.Song;

import java.util.ArrayList;

public class AppMain extends Application {

    private static AppMain app = null;

    public AppMain() {
        super();
        app = this;
    }

    public static AppMain getApp() {
        return app;
    }

    //存放歌單的Json字串
    public  String songListStr;
    //存放轉換好的清單
    public  ArrayList<Song> songList;
    //
    public boolean  getSongListComplete = false;

}
