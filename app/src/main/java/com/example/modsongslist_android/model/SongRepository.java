package com.example.modsongslist_android.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.modsongslist_android.AppMain;
import com.example.modsongslist_android.MyUtil;
import com.example.modsongslist_android.ThreadPool;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class SongRepository {
    //存放歌單的Json字串
    private String songListStr;

    //读取歌单状态 (歌單+自選都要完成)
    public static boolean getSongListComplete = false;
    public static boolean getSelfListComplete = false;
    //存放自選歌曲的清單
    //存放轉換好的清單
    private ArrayList<Song> songList;
    private List<Song> selfList;
    private ArrayList<Song> lihoList;
    private ArrayList<Song> sonjainList;
    private ArrayList<Song> flashList;
    private ArrayList<Song> goodSongList;
    private ArrayList<Song> hunagchunList;
    private ArrayList<Song> meihuaList;
    private ArrayList<Song> ksongList;

    private static final String TAG = "SongRepository";
    private static SongRepository INSTANCE;

    public static SongRepository getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SongRepository();
        }
        return INSTANCE;
    }

    private SongDataBase sDB = SongDataBase.buildDatabase(AppMain.getApp());

    /**
     * 將整個自選清單加入DB
     *
     * @param list
     */
    public void setSelfListInDB(List<Song> list) {
        for (Song item : list) {
            ThreadPool.getInstance().execute(() -> sDB.SongDao().insert(item));
        }
    }

    /**
     * 從DB取得整個自選清單
     *
     * @param callBack
     * @return
     */
    public Future<?> getSelfListFromDB(@NonNull final RepositoryCallBack<List<Song>> callBack) {
        return ThreadPool.getInstance().submit(() -> {
            List<Song> list = sDB.SongDao().getAll();
            getSelfListComplete = true;
            Log.d(TAG, "getSelfListComplete = true");
            ThreadPool.getInstance().execute(() ->
                    callBack.onSuccess(list));
        });
    }

    /**
     * 依照語言從DB獲取自選歌單
     *
     * @return
     */
    public Future<?> getListByLanguageFromDB(String language, @NonNull final RepositoryCallBack<List<Song>> callBack) {
        return ThreadPool.getInstance().submit(() -> {
            List<Song> list = sDB.SongDao().getListByLanguage(language);
            ThreadPool.getInstance().execute(() ->
                    callBack.onSuccess(list));
        });
    }

    /**
     * 加入單曲進DB的自選清單
     *
     * @param song
     */
    public void setSelfSongInDB(Song song) {
        ThreadPool.getInstance().execute(() -> sDB.SongDao().insert(song));
    }

    /**
     * 從DB的自選清單中刪除單曲
     *
     * @param song
     */
    public void deleteSongOutDb(Song song) {
        ThreadPool.getInstance().execute(() -> sDB.SongDao().deleate(song));
    }


    /**
     * 獲得全部的歌單
     *
     * @return
     */
    public ArrayList<Song> getSongList() {
        return songList;
    }

    public List<Song> getSelfList() {
        return selfList;
    }

    public ArrayList<Song> getLihoList() {
        return lihoList;
    }

    public ArrayList<Song> getSonjainList() {
        return sonjainList;
    }

    public ArrayList<Song> getFlashList() {
        return flashList;
    }

    public ArrayList<Song> getGoodSongList() {
        return goodSongList;
    }

    public ArrayList<Song> getHunagchunList() {
        return hunagchunList;
    }

    public ArrayList<Song> getMeihuaList() {
        return meihuaList;
    }

    public ArrayList<Song> getKsongList() {
        return ksongList;
    }

    /**
     * 從傳入的歌單中篩選指定語言的歌曲
     *
     * @return
     */
    public ArrayList<Song> getListByLanguage(String language, List<Song> sonlist) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Log.e(TAG, "getListByLanguage: " + language);
            return (ArrayList<Song>) sonlist.stream().filter(Song -> Song.getLanguage().equals(language)).collect(Collectors.toList());
        } else {
            ArrayList<Song> filterList = new ArrayList<>();
            for (Song s : sonlist) {
                if (s.getLanguage().equals(language)) {
                    filterList.add(s);
                }
            }
            return filterList;
        }
    }


    /**
     * 從Assets取出全部歌單設定進 songList
     */
    public void InitSonglist() {
        lihoList = new ArrayList<>();
        sonjainList = new ArrayList<>();
        flashList = new ArrayList<>();
        goodSongList = new ArrayList<>();
        hunagchunList = new ArrayList<>();
        meihuaList = new ArrayList<>();
        ksongList = new ArrayList<>();

        if (songList == null) {
            songListStr = MyUtil.getInstance().readAssetsJson(AppMain.getApp(), "song_list.json");
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Song>>() {
            }.getType();
            songList = gson.fromJson(SongRepository.getINSTANCE().songListStr, listType);

            //分類歌單
            for (Song song : songList) {
                switch (song.getSong_class()) {
                    case "麗厚廳":
                        lihoList.add(song);
                        break;
                    case "尚讚K歌王":
                        sonjainList.add(song);
                        break;
                    case "閃亮大歌廳":
                        flashList.add(song);
                        break;
                    case "好歌大家唱":
                        goodSongList.add(song);
                        break;
                    case "歡唱K歌館":
                        hunagchunList.add(song);
                        break;
                    case "美華卡拉吧":
                        meihuaList.add(song);
                        break;
                    case "K歌大聯盟":
                        ksongList.add(song);
                        break;
                }
            }

        }
        getSongListComplete = true;
        Log.d(TAG, "getSongListComplete = true");
    }

    public void setSelfList(List<Song> selfList) {
        this.selfList = selfList;
        Log.d(TAG, "setSelfList: complete");
    }
}
