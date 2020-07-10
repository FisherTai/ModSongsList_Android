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


}
