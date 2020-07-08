package com.example.modsongslist_android.songs_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modsongslist_android.AppMain;
import com.example.modsongslist_android.MyUtil;
import com.example.modsongslist_android.R;
import com.example.modsongslist_android.model.Song;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AllFragment extends Fragment {
    private static final String TAG = "AllFragment";
    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        AppMain.getApp().songListStr = MyUtil.getInstance().readAssetsJson(AppMain.getApp(),"song_list.json");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Song>>() {}.getType();
        ArrayList<Song> songList = gson.fromJson(AppMain.getApp().songListStr,listType);
        rv = view.findViewById(R.id.song_listview);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new SongAdapter(songList));
        return view;
    }


    private class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewholder> {
        private List<Song> songList;

        public SongAdapter(List<Song> songList) {
            this.songList = songList;
        }

        @NonNull
        @Override
        public SongViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
            return new SongViewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SongViewholder holder, int position) {
            Song song = songList.get(position);
            holder.id.setText(song.getNumber());
            holder.name.setText(song.getName());
            holder.sClass.setText(song.getSong_class());
            holder.CharCount.setText(song.getChar_count());
            holder.singer.setText(song.getSinger());
            holder.language.setText(song.getLanguage());
            holder.addSong.setOnClickListener(v -> MyUtil.getInstance().toastShort(getActivity(),"加入最愛"));
        }

        @Override
        public int getItemCount() {
            return songList.size();
        }

        class SongViewholder extends RecyclerView.ViewHolder {

            private TextView id, name, sClass, CharCount, singer, language;
            private ImageView addSong;

            private SongViewholder(@NonNull View itemView) {
                super(itemView);
                id = itemView.findViewById(R.id.song_id);
                name = itemView.findViewById(R.id.song_name);
                sClass = itemView.findViewById(R.id.song_class);
                CharCount = itemView.findViewById(R.id.song_char_count);
                singer = itemView.findViewById(R.id.song_singer);
                language = itemView.findViewById(R.id.song_language);
                addSong = itemView.findViewById(R.id.add_song);
            }
        }


    }


}
