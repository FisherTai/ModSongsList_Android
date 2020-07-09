package com.example.modsongslist_android.model;

import java.util.List;

public interface RepositoryCallBack<T> {

    void onSuccess(T result);

    void onFailure(Exception e);

}
