package com.office.template.base.implement;

import android.annotation.SuppressLint;
import android.content.Context;

import com.office.template.base.data.TemplatePreference;
import com.office.template.base.log.TemplateLog;
import com.office.template.base.presenter.HomePresenter;
import com.office.template.base.view.HomeView;
import com.office.template.cloud.config.request.history.HistoryTransactionRequest;
import com.office.template.cloud.config.response.history.HistoryBillPaymentResponse;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.office.template.base.TemplateApp.getApiTemplate;

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
        homeView.showDialogLoading();
        TemplatePreference templatePreference = new TemplatePreference(context);
        HistoryTransactionRequest historyTransactionRequest = new HistoryTransactionRequest(page);
        final Single<HistoryBillPaymentResponse> loginAPi = getApiTemplate().historyBillPaymentApi("Bearer "+templatePreference.getToken(), historyTransactionRequest);
        loginAPi.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<HistoryBillPaymentResponse>() {
                    @Override
                    public void onSuccess(HistoryBillPaymentResponse historyBillPaymentResponse) {
                        homeView.hideDialogLoading();
                        try {
                            if(historyBillPaymentResponse.getErrorCode().equals("0000")){
                                homeView.historyPaymentSuccess(historyBillPaymentResponse);
                            }else {
                                homeView.historyPaymentErrorResponse(historyBillPaymentResponse.getErrorDesc());
                            }
                        }catch (Exception e){
                            TemplateLog.printStackTrace(e);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        homeView.hideDialogLoading();
                        TemplateLog.printStackTrace(e);
                        homeView.historyPaymentFailed();
                    }
                });
    }
}
