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
public class RegisterSuccessFragment extends Fragment {

    public static RegisterSuccessFragment newInstance(int page, String title) {
        RegisterSuccessFragment fragmentFirst = new RegisterSuccessFragment();
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
        View view = inflater.inflate(R.layout.fragment_register_success, container, false);
        ButterKnife.bind(this,view);
        
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        ((RegisterDataActivity)getActivity()).setFragment();
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_confirmation)
    void btnConfirmationClick(){
        ((RegisterDataActivity)getActivity()).replaceFragment(new JoinSuccessFragment());
    }
}