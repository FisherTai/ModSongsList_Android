package com.example.modsongslist_android.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity (tableName = "SelfSong")
public class Song {

    @PrimaryKey @NonNull
    @ColumnInfo (name = "id")
    private String number;
    @NonNull
    private String name;
    @NonNull
    private String language;
    @NonNull
    private String singer;
    @NonNull
    private String char_count;
    @NonNull
    private String song_class;
    @NonNull
    private boolean isFavorite = false;

    public Song(String number, String name, String language, String singer, String char_count, String song_class) {
        this.number = number;
        this.name = name;
        this.language = language;
        this.singer = singer;
        this.char_count = char_count;
        this.song_class = song_class;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getChar_count() {
        return char_count;
    }

    public void setChar_count(String char_count) {
        this.char_count = char_count;
    }

    public String getSong_class() {
        return song_class;
    }

    public void setSong_class(String song_class) {
        this.song_class = song_class;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

