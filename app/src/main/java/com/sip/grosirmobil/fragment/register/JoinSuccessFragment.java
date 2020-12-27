package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.MainActivity;
import com.sip.grosirmobil.activity.RegisterDataActivity;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.implement.LoginPresenterImp;
import com.sip.grosirmobil.base.presenter.LoginPresenter;
import com.sip.grosirmobil.base.view.LoginView;
import com.sip.grosirmobil.cloud.config.response.login.LoginResponse;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinSuccessFragment extends Fragment implements LoginView {

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;
    private LoginPresenter loginPresenter;

    public static JoinSuccessFragment newInstance(int page, String title) {
        JoinSuccessFragment fragmentFirst = new JoinSuccessFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_join_success, container, false);
        ButterKnife.bind(this, view);

        grosirMobilPreference = new GrosirMobilPreference(getActivity());
        grosirMobilFunction = new GrosirMobilFunction(getActivity());

        loginPresenter = new LoginPresenterImp(getActivity(), this);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        ((RegisterDataActivity)getActivity()).setFragment();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_telusuri)
    void btnTelusuriClick(){
        loginPresenter.login(grosirMobilPreference.getEmail(), grosirMobilPreference.getPassword());
    }

    @Override
    public void showValidationError() {
        grosirMobilFunction.showMessage(getString(R.string.app_name), getString(R.string.tv_empty_email_password));
    }

    @Override
    public void showDialogLoading(ProgressDialog progressDialog) {
        progressDialog.show();
    }

    @Override
    public void hideDialogLoading(ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }

    @Override
    public void loginSuccess(LoginResponse response) {
        grosirMobilPreference.saveToken(response.getDataLoginResponse().getToken());
        grosirMobilPreference.saveDataLogin(response.getDataLoginResponse());
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(REQUEST_MAIN, "");
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void loginErrorResponse(String errorResponse) {
        grosirMobilFunction.showMessage(getActivity(), getString(R.string.app_name), errorResponse);
    }

    @Override
    public void loginFailed() {
        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title),getString(R.string.base_null_server));
    }
}