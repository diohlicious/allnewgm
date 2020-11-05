package com.sip.grosirmobil.base.implement;

import android.app.ProgressDialog;
import android.content.Context;

import com.sip.grosirmobil.base.presenter.MainPresenter;
import com.sip.grosirmobil.base.view.MainView;
import com.sip.grosirmobil.fragment.navigationmenu.CartFragment;
import com.sip.grosirmobil.fragment.navigationmenu.GarasiFragment;
import com.sip.grosirmobil.fragment.navigationmenu.HomeFragment;
import com.sip.grosirmobil.fragment.navigationmenu.NotificationFragment;

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

    @Override
    public void replaceFragmentGarasi() {
        mainView.replaceFragment(new GarasiFragment());
    }

    @Override
    public void replaceFragmentNotification() {
        mainView.replaceFragment(new NotificationFragment());
    }

    @Override
    public void replaceFragmentCart() {
        mainView.replaceFragment(new CartFragment());
    }
}
