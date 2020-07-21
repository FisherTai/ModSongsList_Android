package com.example.modsongslist_android.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Song.class}, version = 1)
public abstract class SongDataBase extends RoomDatabase {

    private static SongDataBase Instance;

    public abstract SongDao SongDao();

    private static final Object sLock = new Object();

    public static SongDataBase buildDatabase(Context context) {
//        synchronized (sLock) {
            if (Instance == null) {
                Instance = Room.databaseBuilder(context.getApplicationContext(), SongDataBase.class, "SongDataBase.db")
                        .build();
            }
            return Instance;
//        }
    }
}
