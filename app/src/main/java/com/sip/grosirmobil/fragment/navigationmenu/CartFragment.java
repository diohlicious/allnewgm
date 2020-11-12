package com.sip.grosirmobil.fragment.navigationmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.PayPaymentActivity;
import com.sip.grosirmobil.adapter.CartAdapter;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends GrosirMobilFragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_cart) LinearLayout linearCart;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_cart) RecyclerView rvCart;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_harga_kendaraan) TextView tvHargaKendaraan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_biaya_admin) TextView tvBiayaAdmin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_total) TextView tvTotal;

    private String totalPrice = "";
    private String totalPriceAndAdmin = "";
    private List<HardCodeDataBaruMasukModel> liveHardCodeDataBaruMasukModelList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);

        setDataCart();

        RecyclerView.LayoutManager layoutManagerLive = new LinearLayoutManager(getActivity());
        rvCart.setLayoutManager(layoutManagerLive);
        rvCart.setNestedScrollingEnabled(false);
        CartAdapter cartAdapter = new CartAdapter(getActivity(), totalPrice, liveHardCodeDataBaruMasukModelList);
        rvCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        tvHargaKendaraan.setText("Rp "+setCurrencyFormat(totalPrice));
        tvTotal.setText("Rp "+setCurrencyFormat(totalPrice));

        return view;
    }

    private void setDataCart(){
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","","111000000","");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","","111000000","");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_pay)
    void btnPayClick(){
        Intent intent = new Intent(getActivity(), PayPaymentActivity.class);
        startActivity(intent);
    }
}