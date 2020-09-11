package com.example.modsongslist_android;

import android.app.AlertDialog;
import android.content.Intent;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.modsongslist_android.songs_list.SongListFragment;
import com.example.modsongslist_android.songs_list.SongViewPagerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomNavigationView;
    private BaseFragment mFragment;
    private int currentSelected = 0;
    public static String currentType;
    final private int SETTING_STYLE = 111;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void findView() {
        super.findView();
        mNavigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mBottomNavigationView = findViewById(R.id.bnv_bottom);
    }

    @Override
    protected void initLayoutView() {
        currentType = getSharedPreferences("setting", MODE_PRIVATE).getString("style", "Type1");
        setBottomBar();
        initDarwerBar();
        mFragment = currentType.equals("Type1")  ? SongListFragment.getInstance(AppFragmentManager.CLASS_ALLSONG) : SongViewPagerFragment.getInstance();
        AppFragmentManager.getInstance().addFragmentToActivity(mFragmentManager, mFragment, R.id.fragment_conten);
    }

    private void setBottomBar() {

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            mBottomNavigationView.getMenu().setGroupCheckable(0,true,true); //(1.操作的Group，2.選中後是否被標記 // 3.false為可複選)
            switch (item.getItemId()) {
                //全部
                case R.id.item_allSong:
                    mFragment = currentType.equals("Type1")  ? SongListFragment.getInstance(AppFragmentManager.CLASS_ALLSONG) : SongViewPagerFragment.getInstance();
                    break;
                //最爱
                case R.id.item_favorite:
                    mFragment = SongListFragment.getInstance(AppFragmentManager.CLASS_FAVORITE);
                    break;
            }
            AppFragmentManager.getInstance().replaceFragmentToActivity(mFragmentManager, mFragment, R.id.fragment_conten);
            return true;
        });
    }

    private void setNavItemChecked() {
        if (currentSelected != 0) {
            mNavigationView.getMenu().findItem(currentSelected).setChecked(false);
        }
    }

    protected void initDarwerBar() {

        if(currentType.equals("Type1")){
            mNavigationView.inflateMenu(R.menu.drawer_menu);
        }
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.setNavigationOnClickListener(v -> mDrawerLayout.openDrawer(GravityCompat.START));
        setupDrawerContent(mNavigationView);
        mNavigationView.getMenu().add(11, SETTING_STYLE, 0, "樣式設定").setIcon(R.drawable.ic_setting_style);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    //移除先前點擊過的狀態
                    setNavItemChecked();
                    switch (menuItem.getItemId()) {
                        //麗厚廳
                        case R.id.item_class1:
                            mFragment = SongListFragment.getInstance(AppFragmentManager.CLASS_LIHO);
                            break;
                        //尚讚K歌王
                        case R.id.item_class2:
                            mFragment = SongListFragment.getInstance(AppFragmentManager.CLASS_SONJAIN);
                            break;
                        //閃亮大歌廳
                        case R.id.item_class3:
                            mFragment = SongListFragment.getInstance(AppFragmentManager.CLASS_FLASH);
                            break;
                        //好歌大家唱
                        case R.id.item_class4:
                            mFragment = SongListFragment.getInstance(AppFragmentManager.CLASS_GOODSONG);
                            break;
                        //歡唱K歌館
                        case R.id.item_class5:
                            mFragment = SongListFragment.getInstance(AppFragmentManager.CLASS_HUANGCHUN);
                            break;
                        //美華卡拉吧
                        case R.id.item_class6:
                            mFragment = SongListFragment.getInstance(AppFragmentManager.CLASS_MEIHUA);
                            break;
                        //K歌大聯盟
                        case R.id.item_class7:
                            mFragment = SongListFragment.getInstance(AppFragmentManager.CLASS_KSONG);
                            break;
                        case SETTING_STYLE:
                            final String[] items = {"樣式1", "樣式2"};
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("樣式選擇")
                                    .setItems(items, (dialogInterface, i) -> {
                                        if (items[i].equals("樣式1")) {
                                            setStyle("Type1");
                                        } else {
                                            setStyle("Type2");
                                        }
                                        Snackbar.make(findViewById(android.R.id.content), "點擊OK，重新啟動後樣式即會生效", Snackbar.LENGTH_SHORT).
                                                setAction("ok", view -> {
                                                    Intent intent = getBaseContext().getPackageManager() .getLaunchIntentForPackage(getBaseContext().getPackageName());
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(intent);
                                                    finish();
                                                }).show();
                                    })
                                    .setNegativeButton("取消", null)
                                    .show();
                    }
                    //同時取消底部導航的選中顏色
                    mBottomNavigationView.getMenu().setGroupCheckable(0,false,true);
                    AppFragmentManager.getInstance().replaceFragmentToActivity(mFragmentManager, mFragment, R.id.fragment_conten);
                    // Close the navigation drawer when an item is selected.
                    currentSelected = menuItem.getItemId();
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                });
    }

    private void setStyle(String type) {
        getSharedPreferences("setting", MODE_PRIVATE).
                edit().putString("style", type).apply();
    }

}
