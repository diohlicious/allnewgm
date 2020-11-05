package com.sip.grosirmobil.fragment.navigationmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */

public class GarasiFragment extends GrosirMobilFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_garasi, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}