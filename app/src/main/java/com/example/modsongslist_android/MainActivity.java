package com.example.modsongslist_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.modsongslist_android.model.RepositoryCallBack;
import com.example.modsongslist_android.model.Song;
import com.example.modsongslist_android.model.SongRepository;
import com.example.modsongslist_android.songs_list.AllFragment;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AllFragment allFragment = new AllFragment();
        MyUtil.getInstance().addFragmentToActivity(getSupportFragmentManager(), allFragment, R.id.fragment_conten);
    }

}
