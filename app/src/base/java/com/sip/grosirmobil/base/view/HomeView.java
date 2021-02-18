package com.sip.grosirmobil.base.view;

import com.sip.grosirmobil.cloud.config.response.homecomingsoon.HomeComingSoonResponse;
import com.sip.grosirmobil.cloud.config.response.homehistory.HomeHistoryResponse;
import com.sip.grosirmobil.cloud.config.response.homelive.DataPageHomeLiveResponse;

public interface HomeView {

    void showDialogLoading();

    void hideDialogLoading();

    void showDialogLoadMoreLoading();

    void hideDialogLoadMoreLoading();

    void homeLiveSuccess(DataPageHomeLiveResponse dataPageHomeLiveResponse, String timeServer);

    void timeServerSuccess(boolean success);

    void homeComingSoonSuccess(HomeComingSoonResponse homeComingSoonResponse, String timeServer);

    void homeHistorySuccess(HomeHistoryResponse homeHistoryResponse);



}
