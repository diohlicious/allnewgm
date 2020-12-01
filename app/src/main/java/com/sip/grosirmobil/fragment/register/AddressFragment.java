package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.shuhart.stepview.StepView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.RegisterDataActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {

    public static AddressFragment newInstance(int page, String title) {
        AddressFragment fragmentFirst = new AddressFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.step_view) StepView stepView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_provinsi) TextInputEditText etProvinsi;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_kabupaten) TextInputEditText etKabupaten;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_kecamatan) TextInputEditText etKecamatan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_kelurahan) TextInputEditText etKelurahan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_kode_pos) TextInputEditText etKodePos;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_dialog) RelativeLayout relativeDialog;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_choose) RecyclerView rvChoose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ButterKnife.bind(this, view);

        stepView.go(2, true);
        return view;
    }

    private void showDialogChoose(){
        relativeDialog.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManagerChoose = new LinearLayoutManager(getActivity());
        rvChoose.setLayoutManager(layoutManagerChoose);
        rvChoose.setNestedScrollingEnabled(false);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_content_dialog)
    void linearContentDialogClick(){
        relativeDialog.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_dialog)
    void relativeDialogClick(){
        relativeDialog.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_provinsi)
    void etProvinsiClick(){
        showDialogChoose();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_kabupaten)
    void etKabupatenClick(){
        showDialogChoose();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_kecamatan)
    void etKecamatanClick(){
        showDialogChoose();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_kelurahan)
    void etKelurahanClick(){
        showDialogChoose();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_kode_pos)
    void etKodePosClick(){
        showDialogChoose();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        ((RegisterDataActivity)getActivity()).setFragment();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_next_data_address)
    void btnNextDataAddressClick(){
        ((RegisterDataActivity)getActivity()).replaceFragment(new DocumentFragment());
    }
}