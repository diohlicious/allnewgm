package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.shuhart.stepview.StepView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.CodeOtpFragment;
import com.sip.grosirmobil.activity.RegisterDataActivity;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionFragment extends Fragment {

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.step_view) StepView stepView;

    public static QuestionFragment newInstance(int page, String title) {
        QuestionFragment fragmentFirst = new QuestionFragment();
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
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, view);

        grosirMobilPreference = new GrosirMobilPreference(getActivity());
        grosirMobilFunction = new GrosirMobilFunction(getActivity());

        stepView.go(4, true);
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        ((RegisterDataActivity)getActivity()).setFragment();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_save)
    void btnSaveClick(){
        ((RegisterDataActivity)getActivity()).replaceFragment(new CodeOtpFragment());
    }
}