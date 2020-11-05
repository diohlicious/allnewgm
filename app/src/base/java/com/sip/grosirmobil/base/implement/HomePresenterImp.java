package com.sip.grosirmobil.base.implement;

import android.annotation.SuppressLint;
import android.content.Context;

import com.sip.grosirmobil.base.presenter.HomePresenter;
import com.sip.grosirmobil.base.view.HomeView;

public class HomePresenterImp implements HomePresenter {

    private HomeView homeView;
    private Context context;

    public HomePresenterImp(Context context, HomeView homeView) {
        this.context            = context;
        this.homeView           = homeView;
    }

    @SuppressLint("CheckResult")
    @Override
    public void historyPayment(int page) {
//        homeView.showDialogLoading();
//        GrosirMobilPreference grosirMobilPreference = new GrosirMobilPreference(context);
//        HistoryTransactionRequest historyTransactionRequest = new HistoryTransactionRequest(page);
//        final Single<HistoryBillPaymentResponse> loginAPi = getApiTemplate().historyBillPaymentApi("Bearer "+ grosirMobilPreference.getToken(), historyTransactionRequest);
//        loginAPi.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableSingleObserver<HistoryBillPaymentResponse>() {
//                    @Override
//                    public void onSuccess(HistoryBillPaymentResponse historyBillPaymentResponse) {
//                        homeView.hideDialogLoading();
//                        try {
//                            if(historyBillPaymentResponse.getErrorCode().equals("0000")){
//                                homeView.historyPaymentSuccess(historyBillPaymentResponse);
//                            }else {
//                                homeView.historyPaymentErrorResponse(historyBillPaymentResponse.getErrorDesc());
//                            }
//                        }catch (Exception e){
//                            GrosirMobilLog.printStackTrace(e);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        homeView.hideDialogLoading();
//                        GrosirMobilLog.printStackTrace(e);
//                        homeView.historyPaymentFailed();
//                    }
//                });
    }
}
