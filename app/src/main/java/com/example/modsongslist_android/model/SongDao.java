package com.example.modsongslist_android.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SongDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(Song song);

        @Update
        void update(Song song);

        @Delete
        void deleate(Song song);

        @Query("SELECT * FROM SongsList WHERE id LIKE :number")
        Song findByID(int number);

        @Query("SELECT * FROM SongsList")
        List<Song> getAll();

}

