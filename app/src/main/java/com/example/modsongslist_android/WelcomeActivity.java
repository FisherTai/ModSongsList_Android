package com.example.modsongslist_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.modsongslist_android.model.RepositoryCallBack;
import com.example.modsongslist_android.model.Song;
import com.example.modsongslist_android.model.SongRepository;


import java.util.List;

public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "WelcomeActivity";

    private static int SPLASH_TIME_OUT = 3000;
    private static final int REQ_LOGIN = 1;
    LottieAnimationView lottieAnimationView;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void initLayoutView() {
        //播放動畫
        playAnimation();
        //如果歌單還未讀取完畢，就繼續等候
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (SongRepository.getSelfListComplete && SongRepository.getSongListComplete) {
                    mHandler.removeCallbacks(mRunnable);
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class); //MainActivity為主要檔案名稱
                    WelcomeActivity.this.startActivityForResult(intent, REQ_LOGIN);
                    WelcomeActivity.this.finish();
                } else {
                    mHandler.postDelayed(this, SPLASH_TIME_OUT);
                }
            }
        };
        mHandler.postDelayed(mRunnable, SPLASH_TIME_OUT);

        //讀取歌單
        SongRepository.getINSTANCE().InitSonglist();
        //讀取自選清單
        getSelfList();
    }


    private void playAnimation() {
        //Lottie動畫
        lottieAnimationView = findViewById(R.id.lottie_anim);
//        lottieAnimationView.pauseAnimation(); //動畫暫停
//        lottieAnimationView.cancelAnimation();  //動畫取消
        lottieAnimationView.setSpeed(2.2f); //播放速度
        lottieAnimationView.playAnimation(); //動畫播放
    }


    private void getSelfList() {
        SongRepository.getINSTANCE().getSelfListFromDB(new RepositoryCallBack<List<Song>>() {
            @Override
            public void onSuccess(List<Song> result) {
                WelcomeActivity.this.runOnUiThread(() ->
                        SongRepository.getINSTANCE().setSelfList(result)
                );
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "onFailure: " + e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lottieAnimationView.cancelAnimation();
        mHandler.removeCallbacks(mRunnable);
    }
}
