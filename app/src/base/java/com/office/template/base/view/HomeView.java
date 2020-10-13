package com.office.template.base.view;

import com.office.template.cloud.config.response.history.HistoryBillPaymentResponse;

public interface HomeView {

    void showDialogLoading();

    void hideDialogLoading();

    void historyPaymentSuccess(HistoryBillPaymentResponse historyBillPaymentResponse);

    void historyPaymentErrorResponse(String errorResponse);

    void historyPaymentFailed();

}
