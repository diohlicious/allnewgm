package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.RegisterDataActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileUsahaFragment extends Fragment {

    public static ProfileUsahaFragment newInstance(int page, String title) {
        ProfileUsahaFragment fragmentFirst = new ProfileUsahaFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile_usaha, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_next_profile_usaha)
    void btnNextProfileUsahaClick(){
        ((RegisterDataActivity)getActivity()).replaceFragment(new AddressFragment());
    }
}