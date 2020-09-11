package com.example.modsongslist_android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.MaterialToolbar;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected FragmentManager mFragmentManager = null;
    protected MaterialToolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());
        setContentView(getLayout());
        mFragmentManager = this.getSupportFragmentManager();
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
        if(mFragmentManager != null){
            mFragmentManager = null;
        }
        Logger.clearLogAdapters();
        super.onDestroy();
    }
}
