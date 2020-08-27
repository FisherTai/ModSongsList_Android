package com.example.modsongslist_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.orhanobut.logger.Logger;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    protected View rootView;
    protected MaterialToolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayout(), container, false);
        findView();
        initLayoutView();

        Logger.d("onCreateView: " + getClass().getSimpleName());
        return rootView;
    }

    protected abstract int getLayout();

    protected  void findView(){
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar);
    }

    protected abstract void initLayoutView();

    public static BaseFragment getInstance() {
        return null;
    }
}
