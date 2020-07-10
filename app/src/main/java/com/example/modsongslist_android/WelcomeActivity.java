package com.example.modsongslist_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class WelcomeActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    private static final int REQ_LOGIN = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class); //MainActivity為主要檔案名稱
            WelcomeActivity.this.startActivityForResult(intent,REQ_LOGIN);
            WelcomeActivity.this.finish();
        }, SPLASH_TIME_OUT);
    }


}
