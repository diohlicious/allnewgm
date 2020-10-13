package com.office.template.base.implement;

import android.app.ProgressDialog;
import android.content.Context;

import com.office.template.base.presenter.MainPresenter;
import com.office.template.base.view.MainView;
import com.office.template.fragment.homemenu.HomeFragment;

public class MainPresenterImp implements MainPresenter {

    private MainView mainView;
    private ProgressDialog progressDialog;
    private Context context;

    public MainPresenterImp(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
    }

    @Override
    public void cannotReplaceFragmentHome() {
        mainView.cannotReplaceFragment(new HomeFragment());
    }

    @Override
    public void replaceFragmentHome() {
        mainView.replaceFragment(new HomeFragment());
    }
}
