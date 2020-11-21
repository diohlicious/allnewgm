package com.sip.grosirmobil.fragment.navigationmenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends GrosirMobilFragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipe_refresh_notification) SwipeRefreshLayout swipeRefreshNotification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        // setStatusBarNotificationFragment(this);
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshNotification.setOnRefreshListener(() -> {
            swipeRefreshNotification.setRefreshing(false);
            //
        });
        return view;
    }
}