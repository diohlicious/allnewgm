package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
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

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinSuccessFragment extends Fragment {

    private GrosirMobilPreference grosirMobilPreference;

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
        grosirMobilPreference.saveToken("TOKEN");
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(REQUEST_MAIN, "");
        startActivity(intent);
        getActivity().finish();
    }
}