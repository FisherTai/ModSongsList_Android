package com.example.modsongslist_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.modsongslist_android.songs_list.AllFragment;

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
