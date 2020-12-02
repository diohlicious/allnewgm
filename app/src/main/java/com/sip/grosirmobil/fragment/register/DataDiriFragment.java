package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.shuhart.stepview.StepView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.RegisterDataActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataDiriFragment extends Fragment {

    public static DataDiriFragment newInstance(int page, String title) {
        DataDiriFragment fragmentFirst = new DataDiriFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_nik) EditText etNik;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_full_name) EditText etFullName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.step_view) StepView stepView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_diri, container, false);
        ButterKnife.bind(this, view);

        etFullName.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        stepView.go(0, true);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        getActivity().finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_next_data_diri)
    void btnNextDataDiriClick(){
        ((RegisterDataActivity)getActivity()).replaceFragment(new ProfileUsahaFragment());
    }

}