package com.example.modsongslist_android.model;

/***
 * 容納各個廳別頁面的Enum
 */


public enum SongPageEnum {

    CLASS_ALLSONG(11),//全部
    CLASS_FAVORITE(12),//最爱
    CLASS_LIHO(13),//麗厚廳
    CLASS_SONJAIN(14),//尚讚K歌王
    CLASS_FLASH(15),//閃亮大歌廳
    CLASS_GOODSONG(16),//好歌大家唱
    CLASS_HUANGCHUN(17),//歡唱K歌館
    CLASS_MEIHUA(18),//美華卡拉吧
    CLASS_KSONG(19);//K歌大聯盟

    public int pageCode;
    SongPageEnum(int pageCode) {
        this.pageCode = pageCode;
    }

}
