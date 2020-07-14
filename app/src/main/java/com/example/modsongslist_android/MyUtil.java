package com.example.modsongslist_android;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyUtil {
    private static final String TAG = "MyUtil";
    private static MyUtil instance;

    private MyUtil() {
    }

    public static MyUtil getInstance() {

        if (null == instance) {
            instance = new MyUtil();
        }
        return instance;
    }

    public void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment,
                                      int frameId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameId, fragment).commit();
    }


    public void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                          @NonNull Fragment fragment,
                                          int frameId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameId, fragment).commit();
    }


    /**
     * 簡化的長toast
     *
     * @param comment
     */
    public void toastLong(String comment) {
        Toast.makeText(AppMain.getApp(), comment, Toast.LENGTH_LONG).show();
    }

    /**
     * 簡化的短toast
     *
     * @param comment
     */
    public void toastShort(String comment) {
        Toast.makeText(AppMain.getApp(), comment, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param context
     */
//    public void initDB(Context context) {
//        String jsonFuncStr = readAssetsJson(context);
//        Config.getInstance().setKeepClassFromJson(jsonFuncStr);
//    }


    /**
     * 讀取assets下的json數據
     *
     * @return string
     */
    public String readAssetsJson(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();
        Log.d(TAG, "readAssetsJson: ");
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = assetManager.open(fileName);
            br = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            Log.e(TAG, "readAssetsJson: ",e);
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
