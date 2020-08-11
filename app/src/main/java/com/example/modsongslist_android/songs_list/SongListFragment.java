package com.example.modsongslist_android.songs_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modsongslist_android.MyUtil;
import com.example.modsongslist_android.R;
import com.example.modsongslist_android.model.RepositoryCallBack;
import com.example.modsongslist_android.model.Song;
import com.example.modsongslist_android.model.SongRepository;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 依照廳別來顯示的Fragment
 */

public class SongListFragment extends Fragment {
    private static final String TAG = "SongClassFragment";

    final public static int CLASS_ALLSONG = 11; //全部
    final public static int CLASS_FAVORITE = 12; //最爱
    final public static int CLASS_LIHO = 13; //麗厚廳
    final public static int CLASS_SONJAIN = 14; //尚讚K歌王
    final public static int CLASS_FLASH = 15; //閃亮大歌廳
    final public static int CLASS_GOODSONG = 16; //好歌大家唱
    final public static int CLASS_HUANGCHUN = 17;  //歡唱K歌館
    final public static int CLASS_MEIHUA = 18; //美華卡拉吧
    final public static int CLASS_KSONG = 19;//K歌大聯盟


    private RecyclerView rv;
    private SearchView sv;
    private MaterialToolbar mToolbar;
//    private BottomNavigationView bnv;

    private SongAdapter songAdapter;
    private int current;

    /**
     * 傳入分類的ID標記
     *
     * @param id
     */
    public SongListFragment(int id) {
        current = id;
    }


    //存放當下顯示的歌單
    private ArrayList<Song> CurrentList;
    //存放自選歌單
    private ArrayList<Song> SelfList;
    //取得所有的歌單
    private final ArrayList<Song> AllList = SongRepository.getINSTANCE().getSongList();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        findView(view);
        setRecyclerView(current);
        setToolBar();
        setSerchView();
//        setBottomBar();

        return view;
    }

    private void findView(View view) {
//        bnv = Objects.requireNonNull(getActivity()).findViewById(R.id.bnv_bottom);
        rv = view.findViewById(R.id.song_listview);
        mToolbar = getActivity().findViewById(R.id.toolbar);
        sv = getActivity().findViewById(R.id.search_bar);
    }

    private void setRecyclerView(int current) {
        if (current == CLASS_ALLSONG) {
            songAdapter = new SongAdapter(AllList);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(songAdapter);
            return;
        }

        if (current == CLASS_FAVORITE) {
            SongRepository.getINSTANCE().getSelfListFromDB(new RepositoryCallBack<List<Song>>() {
                @Override
                public void onSuccess(List<Song> result) {
                    SelfList = (ArrayList<Song>) result;
                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                        songAdapter = new SongAdapter(SelfList);
                        rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        rv.setAdapter(songAdapter);
                    });
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "onFailure: " + e.toString());
                }
            });
            return;
        }

        if (current == CLASS_LIHO) {
            Log.d(TAG, "setRecyclerView: 顯示 麗厚廳");
            ArrayList<Song> list  = SongRepository.getINSTANCE().getLihoList();
            songAdapter = new SongAdapter(list);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(songAdapter);
            return;
        }

        if (current == CLASS_SONJAIN) {
            songAdapter = new SongAdapter(SongRepository.getINSTANCE().getSonjainList());
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(songAdapter);
            return;
        }

        if (current == CLASS_FLASH) {
            songAdapter = new SongAdapter(SongRepository.getINSTANCE().getFlashList());
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(songAdapter);
            return;
        }

        if (current == CLASS_GOODSONG) {
            songAdapter = new SongAdapter(SongRepository.getINSTANCE().getGoodSongList());
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(songAdapter);
            return;
        }

        if (current == CLASS_HUANGCHUN) {
            songAdapter = new SongAdapter(SongRepository.getINSTANCE().getHunagchunList());
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(songAdapter);
            return;
        }

        if (current == CLASS_MEIHUA) {
            songAdapter = new SongAdapter(SongRepository.getINSTANCE().getMeihuaList());
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(songAdapter);
            return;
        }

        if (current == CLASS_KSONG) {
            songAdapter = new SongAdapter(SongRepository.getINSTANCE().getKsongList());
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(songAdapter);
            return;
        }

    }

    private void setToolBar() {
        setSerchView();
        mToolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.tb_menu_language1:
                    setAdapterList(SongRepository.getINSTANCE().getListByLanguage("台語", getFilterList()));
                    MyUtil.getInstance().toastShort("篩選:台語歌曲");
                    break;
                case R.id.tb_menu_language2:
                    setAdapterList(SongRepository.getINSTANCE().getListByLanguage("國語", getFilterList()));
                    MyUtil.getInstance().toastShort("篩選:國語歌曲");
                    break;
            }
            return true;
        });
    }


    private void setSerchView() {
        setSerchView(AllList);
    }

    //設置搜索條，搜尋的清單要依頁面不同變動
    private void setSerchView(List<Song> list) {
        sv.setQueryHint("输入歌名或歌手名稱");
        sv.setSubmitButtonEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Song> searchList = new ArrayList<>();
                for (Song song : list) {
                    if (song.getName().contains(query) || song.getSinger().contains(query)) {
                        searchList.add(song);
                    }
                }
                setAdapterList(searchList);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


//    private void setBottomBar() {
//        bnv.setSelectedItemId(R.id.item_allSong);
//        bnv.setOnNavigationItemSelectedListener(item -> {
//
//            rv.removeAllViews();
//
//            switch (current = item.getItemId()) {
//                case R.id.item_allSong:
//                    setAdapterList(AllList);
//                    break;
//
//                case R.id.item_favorite:

//                    break;
//            }
//            return true;
//        });
//    }

    private void setAdapterList(List<Song> list) {
        CurrentList = (ArrayList<Song>) list;
        setSerchView(list);
        songAdapter.setSongList(list, current);
    }

    private ArrayList<Song> getFilterList() {
        if (current == R.id.item_allSong) {
            return AllList;
        } else {
            return SelfList;
        }
    }
}
