package com.example.modsongslist_android.songs_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modsongslist_android.MyUtil;
import com.example.modsongslist_android.R;
import com.example.modsongslist_android.model.Song;
import com.example.modsongslist_android.model.SongRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewholder> {
    private List<Song> injectionSongList;

    public SongAdapter(List<Song> songList) {
        this.injectionSongList = songList;
    }

    public void setSongList(List<Song> songList) {
        this.injectionSongList = songList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SongViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewholder holder, int position) {
        Song song = injectionSongList.get(position);
        holder.id.setText(song.getNumber());
        holder.name.setText(song.getName());
        holder.sClass.setText(song.getSong_class());
        holder.CharCount.setText(song.getChar_count());
        holder.singer.setText(song.getSinger());
        holder.language.setText(song.getLanguage());
        holder.addSong.setOnClickListener(v -> {
            song.setFavorite(!song.isFavorite());
            if (song.isFavorite()) {
                SongRepository.getINSTANCE().setSelfSongInDB(song);
                MyUtil.getInstance().toastShort("加入最愛");
            } else {
                SongRepository.getINSTANCE().deleteSongOutDb(song);
                MyUtil.getInstance().toastShort("移除最愛");
            }
            notifyItemChanged(position);
        });

        if (song.isFavorite()) {
            holder.addSong.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            holder.addSong.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return injectionSongList.size();
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
