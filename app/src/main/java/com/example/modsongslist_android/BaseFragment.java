package com.example.modsongslist_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.orhanobut.logger.Logger;

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    protected View rootView;

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

    protected abstract void findView();

    protected abstract void initLayoutView();
}
