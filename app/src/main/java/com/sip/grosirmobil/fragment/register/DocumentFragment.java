package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.RegisterDataActivity;
import com.sip.grosirmobil.fragment.navigationmenu.HomeFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentFragment extends Fragment {

    public static DocumentFragment newInstance(int page, String title) {
        DocumentFragment fragmentFirst = new DocumentFragment();
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
        View view = inflater.inflate(R.layout.fragment_document, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_end_register_data)
    void btnEndRegisterDataClick(){
        ((RegisterDataActivity)getActivity()).replaceFragment(new HomeFragment());
    }

}