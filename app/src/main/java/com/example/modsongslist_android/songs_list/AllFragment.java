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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {
    private static final String TAG = "AllFragment";
    private RecyclerView rv;
    private SearchView sv;
    private SongAdapter songAdapter;
    private BottomNavigationView bnv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        findView(view);
        setRecyclerView();
        setSerchView();
        setBottomBar();

        return view;
    }

    private void findView(View view) {
        bnv = getActivity().findViewById(R.id.bnv_bottom);
        rv = view.findViewById(R.id.song_listview);
        sv = getActivity().findViewById(R.id.search_bar);
    }

    private void setRecyclerView() {
        songAdapter = new SongAdapter(SongRepository.getINSTANCE().getSongList());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(songAdapter);
    }

    private void setSerchView() {
        setSerchView(SongRepository.getINSTANCE().getSongList());
    }

    private void setSerchView(List<Song> list) {

        sv.setQueryHint("输入歌名");
        //設置搜索的事件
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Song> searchList = new ArrayList<>();
                for (Song song : list) {
                    if (song.getName().contains(query)) {
                        searchList.add(song);
                    }
                }
                songAdapter.setSongList(searchList);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setBottomBar() {
        bnv.setSelectedItemId(R.id.item_allSong);
        bnv.setOnNavigationItemSelectedListener(item -> {

            rv.removeAllViews();

            switch(item.getItemId()){
                case R.id.item_allSong:
                    songAdapter.setSongList(SongRepository.getINSTANCE().getSongList());
                    setSerchView();
                    break;

                case R.id.item_favorite:
                    SongRepository.getINSTANCE().getSelfListFromDB(new RepositoryCallBack<List<Song>>() {
                        @Override
                        public void onSuccess(List<Song> result) {
                            getActivity().runOnUiThread(()->songAdapter.setSongList(result));
                            setSerchView(result);
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "onFailure: "+e.toString());
                        }
                    });
                    break;
            }
            return true;
        });
    }

}
