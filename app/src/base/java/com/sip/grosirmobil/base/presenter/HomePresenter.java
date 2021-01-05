package com.sip.grosirmobil.base.presenter;

import com.sip.grosirmobil.base.util.AutoScrollViewPager;

import me.relex.circleindicator.CircleIndicator;

public interface HomePresenter {

    void getHomeLiveApi(int page, int max, String lokasi, int tahunStart, int tahunEnd, long hargaStart, long hargaEnd, String merek);

    void getHomeComingSoonApi();

    void setDataBannerHome(AutoScrollViewPager viewPagerHome, CircleIndicator circleIndicator);

    void getHomeHistoryApi(int page, int max, String isMenang);

    void getTimeServerApi();

    void getCheckActiveTokenApi();

    void tvLiveClick();

    void tvRecordClick();

    void tvLiveSoonClick();

}
