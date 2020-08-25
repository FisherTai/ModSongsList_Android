package com.example.modsongslist_android.songs_list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modsongslist_android.R;
import com.example.modsongslist_android.model.Song;
import com.example.modsongslist_android.model.SongRepository;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewholder> {
    private static final String TAG = "SongAdapter";
    private List<Song> injectionSongList;
    private int current = 0;

    public SongAdapter(List<Song> songList, int current) {
        this.injectionSongList = songList;
        this.current = current;
    }

    public void changeList(List<Song> songList, int current) {
        this.injectionSongList = songList;
        this.current = current;
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
//        holder.CharCount.setText(song.getChar_count());
        holder.singer.setText(song.getSinger());
        holder.language.setText(song.getLanguage());
        holder.addSong.setOnClickListener(v -> {
            song.setFavorite(!song.isFavorite());
            if (song.isFavorite()) {
                SongRepository.getINSTANCE().setSelfSongInDB(song);
                Log.d(TAG, "save song:" + song.getNumber());
            } else {
                SongRepository.getINSTANCE().deleteSongOutDb(song);
                Log.d(TAG, "remove song:" + song.getNumber());
                //因為全館歌單不是從DB取出的，必須另外修改物件狀態
                for (Song s : SongRepository.getINSTANCE().getSongList()) {
                    if (s.getNumber().equals(song.getNumber())) {
                        s.setFavorite(false);
                        break;
                    }
                }
            }
            notifyItemChanged(position);

            if (current == SongListFragment.CLASS_FAVORITE) {
                //這裡傳入List的remove直接將物件傳入，省的處理position的邏輯
                injectionSongList.remove(song);
                Log.d(TAG, "remove self list position:" + position);
                notifyItemRemoved(position);
                //因為position變更了，需要從position位置開始刷新範圍資料。
                notifyItemRangeChanged(position,injectionSongList.size()-position);
            }

        });

        if (!song.isFavorite()) {
            holder.addSong.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        } else if (current == SongListFragment.CLASS_FAVORITE) {
            holder.addSong.setImageResource(R.drawable.ic_close_black_24dp);
        } else {
            holder.addSong.setImageResource(R.drawable.ic_favorite_black_24dp);
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
