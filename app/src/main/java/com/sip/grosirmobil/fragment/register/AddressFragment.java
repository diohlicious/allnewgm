package com.sip.grosirmobil.fragment.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sip.grosirmobil.R;

import butterknife.ButterKnife;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ButterKnife.bind(this, view);
        
        return view;
    }
}