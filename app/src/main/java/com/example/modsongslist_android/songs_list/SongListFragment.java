package com.example.modsongslist_android.songs_list;

import android.text.TextUtils;
import android.util.Log;

import android.widget.SearchView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modsongslist_android.AppFragmentManager;
import com.example.modsongslist_android.BaseFragment;
import com.example.modsongslist_android.MainActivity;
import com.example.modsongslist_android.MyUtil;
import com.example.modsongslist_android.R;
import com.example.modsongslist_android.model.RepositoryCallBack;
import com.example.modsongslist_android.model.Song;
import com.example.modsongslist_android.model.SongRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 依照廳別來顯示的Fragment
 */

public class SongListFragment extends BaseFragment {
    private static final String TAG = "SongClassFragment";

    private RecyclerView rv;
    private SearchView sv;

    private SongAdapter songAdapter;
    private int current;

    /**
     * 傳入分類的ID標記
     *
     * @param id
     */
    private SongListFragment(int id) {
        current = id;
        AppFragmentManager.getInstance().setFragmentInContainer(id,this);
    }

    public static SongListFragment getInstance(int id){
        if(AppFragmentManager.getInstance().getFragmentByID(id) == null){
            new SongListFragment(id);
        }

        return AppFragmentManager.getInstance().getFragmentByID(id);
    }


    //存放當下顯示的歌單
    private ArrayList<Song> currentList;
    //存放自選歌單
    private ArrayList<Song> SelfList;
    //取得所有的歌單
    private final ArrayList<Song> ALL_LIST = SongRepository.getINSTANCE().getSongList();

    @Override
    protected int getLayout() {
        Log.d(TAG, "onCreateView: " + getTitle(current));
        return R.layout.fragment_main;
    }

    @Override
    protected void findView() {
        super.findView();
        rv = rootView.findViewById(R.id.song_listview);
        sv = getActivity().findViewById(R.id.search_bar);
    }

    @Override
    protected void initLayoutView() {
        setRecyclerView(current);
    }

    private void setRecyclerView(int current) {
        if (current == AppFragmentManager.CLASS_ALLSONG) {
            currentList = ALL_LIST;
            setSongAdapter(currentList, current);
            return;
        }

        if (current == AppFragmentManager.CLASS_FAVORITE) {
            SongRepository.getINSTANCE().getSelfListFromDB(new RepositoryCallBack<List<Song>>() {
                @Override
                public void onSuccess(List<Song> result) {
                    SelfList = (ArrayList<Song>) result;
                    currentList = SelfList;
                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                        setSongAdapter(currentList, current);
                    });
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "onFailure: " + e.toString());
                }
            });
            return;
        }

        if (current == AppFragmentManager.CLASS_LIHO) {
            currentList = SongRepository.getINSTANCE().getLihoList();
            setSongAdapter(currentList, current);
            return;
        }

        if (current == AppFragmentManager.CLASS_SONJAIN) {
            currentList = SongRepository.getINSTANCE().getSonjainList();
            setSongAdapter(currentList, current);
            return;
        }

        if (current == AppFragmentManager.CLASS_FLASH) {
            currentList = SongRepository.getINSTANCE().getFlashList();
            setSongAdapter(currentList, current);
            return;
        }

        if (current == AppFragmentManager.CLASS_GOODSONG) {
            currentList = SongRepository.getINSTANCE().getGoodSongList();
            setSongAdapter(currentList, current);
            return;
        }

        if (current == AppFragmentManager.CLASS_HUANGCHUN) {
            currentList = SongRepository.getINSTANCE().getHunagchunList();
            setSongAdapter(currentList, current);
            return;
        }

        if (current == AppFragmentManager.CLASS_MEIHUA) {
            currentList = SongRepository.getINSTANCE().getMeihuaList();
            setSongAdapter(currentList, current);
            return;
        }

        if (current == AppFragmentManager.CLASS_KSONG) {
            currentList = SongRepository.getINSTANCE().getKsongList();
            setSongAdapter(currentList, current);
        }

    }

    private void setSongAdapter(ArrayList<Song> currentList, int current) {
        songAdapter = new SongAdapter(currentList, current);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(songAdapter);
    }


    @Override
    public void onResume() {
        //在onResume操作可確保傳入ToolBar的會是ViewPager當前頁面
        setToolBar();
        if(songAdapter != null && MainActivity.currentType.equals("Type2")){
            songAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }


    private void setToolBar() {
        setSerchView();
        mToolbar.setTitle(getTitle(current));
        mToolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.tb_menu_language1:
                    setAdapterList(SongRepository.getINSTANCE().getListByLanguage("台語", getFilterList()));
                    MyUtil.getInstance().toastShort("篩選:台語歌曲");
                    break;
                case R.id.tb_menu_language2:
                    setAdapterList(SongRepository.getINSTANCE().getListByLanguage("國語", getFilterList()));
                    MyUtil.getInstance().toastShort("篩選:國語歌曲");
                    break;
            }
            return true;
        });
    }


    private void setSerchView() {
        setSerchView(currentList);
    }

    //設置搜索條，搜尋的清單要依頁面不同變動
    private void setSerchView(List<Song> list) {
        sv.setQueryHint("输入歌名或歌手名稱");
        sv.setSubmitButtonEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: " + query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange: " + newText + " on " + getTitle(current));
                if (TextUtils.isEmpty(newText.trim())) {
                    songAdapter.changeList(list, current);
                    return false;
                }

                ArrayList<Song> searchList = new ArrayList<>();
                char newWord = newText.charAt(newText.length() - 1);
                boolean isEng = String.valueOf(newWord).matches("[a-zA-Z]");

                for (Song song : list) {
                    if (song.getName().contains(newText) || song.getSinger().contains(newText)) {
                        searchList.add(song);
                        continue;
                    }
                    //如果不是英文字的話就不做大小寫轉換
                    if (!isEng) {
                        continue;
                    }

                    if (song.getName().toLowerCase().contains(newText.toLowerCase()) || song.getSinger().toLowerCase().contains(newText.toLowerCase())) {
                        searchList.add(song);
                    }
                }
                songAdapter.changeList(searchList, current);
                return false;
            }
        });
    }

    private void setAdapterList(List<Song> list) {
        currentList = (ArrayList<Song>) list;
        setSerchView(list);
        songAdapter.changeList(list, current);
    }


    private String getTitle(int current) {
        String title = "";
        switch (current) {
            case AppFragmentManager.CLASS_ALLSONG:
                title = getResources().getString(R.string.allsong);
                break;
            case AppFragmentManager.CLASS_FAVORITE:
                title = getResources().getString(R.string.favorite);
                break;
            case AppFragmentManager.CLASS_LIHO:
                title = getResources().getString(R.string.liho);
                break;
            case AppFragmentManager.CLASS_SONJAIN:
                title = getResources().getString(R.string.sonjain);
                break;
            case AppFragmentManager.CLASS_FLASH:
                title = getResources().getString(R.string.flash);
                break;
            case AppFragmentManager.CLASS_GOODSONG:
                title = getResources().getString(R.string.goodsong);
                break;
            case AppFragmentManager.CLASS_HUANGCHUN:
                title = getResources().getString(R.string.hunagchun);
                break;
            case AppFragmentManager.CLASS_MEIHUA:
                title = getResources().getString(R.string.meihua);
                break;
            case AppFragmentManager.CLASS_KSONG:
                title = getResources().getString(R.string.ksong);
                break;
        }
        return title;
    }

    private ArrayList<Song> getFilterList() {
        switch (current) {
            case AppFragmentManager.CLASS_ALLSONG:
                return ALL_LIST;
            case AppFragmentManager.CLASS_FAVORITE:
                return SelfList;
            case AppFragmentManager.CLASS_LIHO:
                return SongRepository.getINSTANCE().getLihoList();
            case AppFragmentManager.CLASS_SONJAIN:
                return SongRepository.getINSTANCE().getSonjainList();
            case AppFragmentManager.CLASS_FLASH:
                return SongRepository.getINSTANCE().getFlashList();
            case AppFragmentManager.CLASS_GOODSONG:
                return SongRepository.getINSTANCE().getGoodSongList();
            case AppFragmentManager.CLASS_HUANGCHUN:
                return SongRepository.getINSTANCE().getHunagchunList();
            case AppFragmentManager.CLASS_MEIHUA:
                return SongRepository.getINSTANCE().getMeihuaList();
            case AppFragmentManager.CLASS_KSONG:
                return SongRepository.getINSTANCE().getKsongList();
            default:
                Toast.makeText(getContext(), "篩選全部的歌曲", Toast.LENGTH_SHORT).show();
                return ALL_LIST;
        }
    }

}
