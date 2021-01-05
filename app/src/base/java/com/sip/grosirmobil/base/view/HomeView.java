package com.sip.grosirmobil.base.view;

import com.sip.grosirmobil.cloud.config.response.homehistory.HomeHistoryResponse;
import com.sip.grosirmobil.cloud.config.response.homelive.HomeLiveResponse;

public interface HomeView {

    void showDialogLoading();

    void hideDialogLoading();

    void homeLiveSuccess(HomeLiveResponse homeLiveResponse);

    void homeComingSoonSuccess();

    void homeHistorySuccess(HomeHistoryResponse homeHistoryResponse);



}
