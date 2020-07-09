package com.example.modsongslist_android.model;

import androidx.annotation.NonNull;

import com.example.modsongslist_android.AppMain;
import com.example.modsongslist_android.ThreadPool;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.Future;

public class SongRepository {
    private static final String TAG = "SongRepository";
    private static SongRepository INSTANCE;

    public static SongRepository getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SongRepository();
        }
        return INSTANCE;
    }

    private SongDataBase sDB = SongDataBase.buildDatabase(AppMain.getApp());
    private SongDao songDao;

    private void setSongDao() {
        if (songDao == null) {
            songDao = sDB.SongDao();
        }
    }

    public Future<?> getAll(@NonNull final RepositoryCallBack<List<Song>> callBack) {
        return ThreadPool.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                List<Song> list = songDao.getAll();
                ThreadPool.getInstance().execute(() -> {
                    callBack.onSuccess(list);
                });
            }
        });
    }

    public void setList(List<Song> list) {
        setSongDao();
        for (Song item : list) {
            ThreadPool.getInstance().execute(() -> songDao.insert(item));
        }
    }
}
