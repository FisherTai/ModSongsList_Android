package com.example.modsongslist_android.songs_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modsongslist_android.AppMain;
import com.example.modsongslist_android.R;
import com.example.modsongslist_android.model.Song;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    private static final String TAG = "AllFragment";
    private RecyclerView rv;
    private SearchView sv;
    private SongAdapter songAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        rv = view.findViewById(R.id.song_listview);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        songAdapter  = new SongAdapter(AppMain.getApp().songList);
        rv.setAdapter(songAdapter);

        setSerchView();
        return view;
    }





    private void setSerchView(){
        sv = getActivity().findViewById(R.id.search_bar);
        sv.setQueryHint("输入歌名");
        //設置搜索的事件
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Song> searchList = new ArrayList<>();
                for(Song song : AppMain.getApp().songList){
                    if(song.getName().contains(query)){
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


}
