package com.example.modsongslist_android;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.modsongslist_android.songs_list.SongListFragment;
import com.example.modsongslist_android.songs_list.SongViewPagerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomNavigationView;
    private BaseFragment mFragment;
    private int currentSelected = 0;

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
        setBottomBar();
        initDarwerBar();
        mFragment = MyUtil.isOrignal ? SongListFragment.getInstance(SongListFragment.CLASS_ALLSONG) : SongViewPagerFragment.getInstance();
        AppFragmentManager.getInstance().addFragmentToActivity(getSupportFragmentManager(), mFragment, R.id.fragment_conten);
    }

    private void setBottomBar() {

        mBottomNavigationView.setSelectedItemId(R.id.item_allSong);
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                //全部
                case R.id.item_allSong:
                    mFragment = MyUtil.isOrignal ? SongListFragment.getInstance(SongListFragment.CLASS_ALLSONG) : SongViewPagerFragment.getInstance();
                    break;
                //最爱
                case R.id.item_favorite:
                    mFragment = SongListFragment.getInstance(SongListFragment.CLASS_FAVORITE);
                    break;
            }
            AppFragmentManager.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), mFragment, R.id.fragment_conten);
            setNavItemChecked();
            return true;
        });
    }

    private void setNavItemChecked() {
        if (currentSelected != 0) {
            mNavigationView.getMenu().findItem(currentSelected).setChecked(false);
        }
    }

    protected void initDarwerBar() {

        if (MyUtil.isOrignal) {
            mToolbar.setNavigationIcon(R.drawable.ic_menu);
            mToolbar.setNavigationOnClickListener(v -> mDrawerLayout.openDrawer(GravityCompat.START));
            setupDrawerContent(mNavigationView);
        } else {
            mDrawerLayout.removeView(mNavigationView);
            mToolbar.setLogo(R.mipmap.logo);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    //移除先前點擊過的狀態
                    setNavItemChecked();
                    switch (menuItem.getItemId()) {
                        //麗厚廳
                        case R.id.item_class1:
                            mFragment = SongListFragment.getInstance(SongListFragment.CLASS_LIHO);
                            break;
                        //尚讚K歌王
                        case R.id.item_class2:
                            mFragment = SongListFragment.getInstance(SongListFragment.CLASS_SONJAIN);
                            break;
                        //閃亮大歌廳
                        case R.id.item_class3:
                            mFragment = SongListFragment.getInstance(SongListFragment.CLASS_FLASH);
                            break;
                        //好歌大家唱
                        case R.id.item_class4:
                            mFragment = SongListFragment.getInstance(SongListFragment.CLASS_GOODSONG);
                            break;
                        //歡唱K歌館
                        case R.id.item_class5:
                            mFragment = SongListFragment.getInstance(SongListFragment.CLASS_HUANGCHUN);
                            break;
                        //美華卡拉吧
                        case R.id.item_class6:
                            mFragment = SongListFragment.getInstance(SongListFragment.CLASS_MEIHUA);
                            break;
                        //K歌大聯盟
                        case R.id.item_class7:
                            mFragment = SongListFragment.getInstance(SongListFragment.CLASS_KSONG);
                            break;
                    }
                    AppFragmentManager.getInstance().replaceFragmentToActivity(getSupportFragmentManager(), mFragment, R.id.fragment_conten);
                    // Close the navigation drawer when an item is selected.
                    currentSelected = menuItem.getItemId();
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                });
    }


}
