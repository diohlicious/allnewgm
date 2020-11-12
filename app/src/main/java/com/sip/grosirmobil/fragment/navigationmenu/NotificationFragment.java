package com.sip.grosirmobil.fragment.navigationmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;

import java.util.Objects;

import butterknife.ButterKnife;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarNotificationFragment;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarSplashScreen;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends GrosirMobilFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        // setStatusBarNotificationFragment(this);
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}