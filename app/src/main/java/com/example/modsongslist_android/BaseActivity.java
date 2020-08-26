package com.example.modsongslist_android;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected MaterialToolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());
        setContentView(getLayout());
        findView();
        initLayoutView();

        Logger.d("onCreate: " + getClass().getSimpleName());
    }

    protected abstract int getLayout();

    protected  void findView() {
        mToolbar = findViewById(R.id.toolbar);
    }

    protected abstract void initLayoutView();


    @Override
    protected void onDestroy() {
        Logger.clearLogAdapters();
        super.onDestroy();
    }
}
