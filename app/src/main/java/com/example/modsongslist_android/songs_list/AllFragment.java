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


import com.example.modsongslist_android.R;
import com.example.modsongslist_android.model.RepositoryCallBack;
import com.example.modsongslist_android.model.Song;
import com.example.modsongslist_android.model.SongRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllFragment extends Fragment {
    private static final String TAG = "AllFragment";
    private RecyclerView rv;
    private SearchView sv;
    private SongAdapter songAdapter;
    private BottomNavigationView bnv;
    private int current = R.id.item_allSong;


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
        bnv = Objects.requireNonNull(getActivity()).findViewById(R.id.bnv_bottom);
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

    private void setBottomBar() {
        bnv.setSelectedItemId(R.id.item_allSong);
        bnv.setOnNavigationItemSelectedListener(item -> {

            rv.removeAllViews();

            switch(current = item.getItemId()){
                case R.id.item_allSong:
                    setAdapterList(SongRepository.getINSTANCE().getSongList());
                    setSerchView();
                    break;

                case R.id.item_favorite:
                    SongRepository.getINSTANCE().getSelfListFromDB(new RepositoryCallBack<List<Song>>() {
                        @Override
                        public void onSuccess(List<Song> result) {
                            Objects.requireNonNull(getActivity()).runOnUiThread(()->setAdapterList(result));
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

    private void setAdapterList(List<Song> list){
        songAdapter.setSongList(list,current);
    }

}
