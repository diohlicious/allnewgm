package com.sip.grosirmobil.fragment.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sip.grosirmobil.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnBoardingStepOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoardingStepOneFragment extends Fragment {

    public static OnBoardingStepOneFragment newInstance(int page, String title) {
        OnBoardingStepOneFragment fragmentFirst = new OnBoardingStepOneFragment();
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
        View view = inflater.inflate(R.layout.fragment_on_boarding_step_one, container, false);
        ButterKnife.bind(this, view);
        
        return view;
    }
}