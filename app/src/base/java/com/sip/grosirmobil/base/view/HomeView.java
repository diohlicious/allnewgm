package com.sip.grosirmobil.base.view;

import com.sip.grosirmobil.cloud.config.response.homehistory.HomeHistoryResponse;
import com.sip.grosirmobil.cloud.config.response.homelive.DataPageHomeLiveResponse;

public interface HomeView {

    void showDialogLoading();

    void hideDialogLoading();

    void homeLiveSuccess(DataPageHomeLiveResponse dataPageHomeLiveResponse, String timeServer);

    void homeComingSoonSuccess();

    void homeHistorySuccess(HomeHistoryResponse homeHistoryResponse);



}
