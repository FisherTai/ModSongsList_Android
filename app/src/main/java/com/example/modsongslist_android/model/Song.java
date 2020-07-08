package com.example.modsongslist_android.model;

public class Song {

    private String number;
    private String name;
    private String language;
    private String singer;
    private String char_count;
    private String song_class;

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
}

