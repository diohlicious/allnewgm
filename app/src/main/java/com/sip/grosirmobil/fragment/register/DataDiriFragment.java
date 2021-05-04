package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.naa.data.Nson;
import com.naa.data.UtilityAndroid;
import com.shuhart.stepview.StepView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.RegisterDataActivity;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.fragment.OnBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataDiriFragment extends Fragment implements OnBackListener {

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
    @BindView(R.id.et_email) EditText etEmail;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_phone_number) EditText etPhoneNumber;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_password) EditText etPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_confirm_password) EditText etConfirmPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.step_view) StepView stepView;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_diri, container, false);
        ButterKnife.bind(this, view);

        grosirMobilPreference = new GrosirMobilPreference(getActivity());
        grosirMobilFunction = new GrosirMobilFunction(getActivity());

        etFullName.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        etFullName.setText(grosirMobilPreference.getFullName());
        etPhoneNumber.setText(grosirMobilPreference.getPhoneNumber());

        stepView.go(0, true);

        Nson nson = Nson.readJson(UtilityAndroid.getSetting("daftar") ) ;
        etNik.setText(nson.get("datadiri-nik").asString());
        etFullName.setText(nson.get("datadiri-fullname").asString());


        return view;
    }




    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        onBack();
        getActivity().finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_next_data_diri)
    void btnNextDataDiriClick(){
        if(etNik.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Mohon Isi NIK (KTP/SIM)", Toast.LENGTH_SHORT).show();
        }else if(etNik.getText().toString().length()<15){
            Toast.makeText(getActivity(), "NIK (KTP/SIM) Minimal 16 Angka", Toast.LENGTH_SHORT).show();
        }else if(etFullName.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Mohon Isi Nama Lengkap", Toast.LENGTH_SHORT).show();
        }else if(etEmail.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Mohon Isi Email", Toast.LENGTH_SHORT).show();
        }else if(!etEmail.getText().toString().contains("@")){
            Toast.makeText(getActivity(), "Mohon Isi Valid Email", Toast.LENGTH_SHORT).show();
        }else if(etPhoneNumber.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Mohon Isi Nomor Telepon", Toast.LENGTH_SHORT).show();
        }else if(etPassword.getText().toString().length()<8){
            Toast.makeText(getActivity(), "Mohon Isi Password Minimal 8 Karakter", Toast.LENGTH_SHORT).show();
        }else if(etConfirmPassword.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Mohon Isi Confirm Password", Toast.LENGTH_SHORT).show();
        }else {
            if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
                grosirMobilPreference.saveNoKtp(etNik.getText().toString());
                grosirMobilPreference.saveFullName(etFullName.getText().toString());
                grosirMobilPreference.saveEmail(etEmail.getText().toString());
                grosirMobilPreference.savePhoneNumber(etPhoneNumber.getText().toString());
                grosirMobilPreference.savePassword(etPassword.getText().toString());

                onBack();
                ((RegisterDataActivity)getActivity()).replaceFragment(new ProfileUsahaFragment());
            }else {
                Toast.makeText(getActivity(), "Password Tidak Sama", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBack() {
        Nson nson = Nson.readJson(UtilityAndroid.getSetting("daftar") ) ;
        nson.set("datadiri-nik", etNik.getText().toString());
        nson.set("datadiri-fullname", etFullName.getText().toString());

        UtilityAndroid.setSetting("daftar", nson.toJson());
    }
}