package com.example.modsongslist_android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected FragmentManager mFragmentManager = null;
    protected FragmentTransaction mFragmentTransaction = null;
    protected BaseFragment mCurrentFragment = null;
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


    public void showFragment(BaseFragment mBaseFragment,int tag) {
        if (mFragmentManager == null) {
            mFragmentManager = this.getSupportFragmentManager();
        }
        mFragmentTransaction = mFragmentManager.beginTransaction();

        // 若当前fragment不存在则添加
        if (null == mFragmentManager.findFragmentByTag(String.valueOf(tag))) {
            mFragmentTransaction.add(R.id.fragment_conten, mBaseFragment, String.valueOf(tag));
        }
        if (null != mCurrentFragment) {
            mFragmentTransaction.hide(mCurrentFragment);
        }
        mFragmentTransaction.show(mBaseFragment);
        mFragmentTransaction.commitAllowingStateLoss();
        mCurrentFragment = mBaseFragment;
    }




    @Override
    protected void onDestroy() {
        if(mFragmentManager != null){
            mFragmentManager = null;
        }

        if (mFragmentTransaction != null){
            mFragmentTransaction = null;
        }
        Logger.clearLogAdapters();
        super.onDestroy();
    }
}
