package com.office.template.fragment.homemenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.office.template.R;
import com.office.template.adapter.HistoryBillPaymentPaginationAdapter;
import com.office.template.base.function.TemplateFunction;
import com.office.template.base.implement.HomePresenterImp;
import com.office.template.base.presenter.HomePresenter;
import com.office.template.base.util.TemplateFragment;
import com.office.template.base.view.HomeView;
import com.office.template.cloud.config.response.history.HistoryBillPaymentResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */

public class HomeFragment extends TemplateFragment implements HomeView {

    private TemplateFunction templateFunction;

    @BindView(R.id.linear_content) LinearLayout linearContent;

    private HistoryBillPaymentPaginationAdapter historyBillPaymentAdapter;
    private HomePresenter homePresenter;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int page = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        templateFunction = new TemplateFunction(getActivity());
        homePresenter = new HomePresenterImp(getActivity(), this);


        return view;
    }

    private void loadDataHistory(HistoryBillPaymentResponse historyBillPaymentResponse){
        isLoading = false;
        if (historyBillPaymentResponse != null) {
            historyBillPaymentAdapter.addItems(historyBillPaymentResponse.getData().getRows());
            if (historyBillPaymentResponse.getData().getPage().equals(historyBillPaymentResponse.getData().getTotalPage())) {
                isLastPage = true;
            } else {
                page = Integer.parseInt(historyBillPaymentResponse.getData().getPage()) + 1;
            }
        }
    }

    @Override
    public void showDialogLoading() {

    }

    @Override
    public void hideDialogLoading() {

    }

    @Override
    public void historyPaymentSuccess(HistoryBillPaymentResponse historyBillPaymentResponse) {
        loadDataHistory(historyBillPaymentResponse);
    }

    @Override
    public void historyPaymentErrorResponse(String errorResponse) {
        templateFunction.showMessage(getActivity(), getString(R.string.app_name), errorResponse);
    }

    @Override
    public void historyPaymentFailed() {
        templateFunction.showMessage(getActivity(), getString(R.string.base_null_error_title),getString(R.string.base_null_server));
    }
}
