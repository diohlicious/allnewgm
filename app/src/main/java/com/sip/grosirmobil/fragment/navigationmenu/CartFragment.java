package com.sip.grosirmobil.fragment.navigationmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    @BindView(R.id.swipe_refresh_cart) SwipeRefreshLayout swipeRefreshCart;
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

    private List<HardCodeDataBaruMasukModel> liveHardCodeDataBaruMasukModelList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);

        setDataCart();

        loadData();

        swipeRefreshCart.setOnRefreshListener(() -> {
            loadData();
            swipeRefreshCart.setRefreshing(false);
        });

        return view;
    }

    private void loadData(){
        tvHargaKendaraan.setText("");
        tvTotal.setText("");
        RecyclerView.LayoutManager layoutManagerLive = new LinearLayoutManager(getActivity());
        rvCart.setLayoutManager(layoutManagerLive);
        rvCart.setNestedScrollingEnabled(false);
        @SuppressLint("SetTextI18n") CartAdapter cartAdapter = new CartAdapter(getActivity(), tvHargaKendaraan, liveHardCodeDataBaruMasukModelList, hardCodeDataBaruMasukModel -> {
            if(tvHargaKendaraan.getText().toString().equals("Rp 0")){
                tvTotal.setText("");
            }else {
                String hargaKendaraanTemp = tvHargaKendaraan.getText().toString().replace("Rp ", "");
                String hargaKendaraan = hargaKendaraanTemp.replace(".", "");
                long priceTotal = Long.parseLong(hargaKendaraan)+500000;
                tvTotal.setText("Rp "+ setCurrencyFormat(String.valueOf(priceTotal)));
            }
        });
        rvCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }

    private void setDataCart(){
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","100000000","");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","200000000","");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_pay)
    void btnPayClick(){
        if(tvTotal.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Mohon Pilih Kendaraan yang mau dibayar", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(getActivity(), PayPaymentActivity.class);
            startActivity(intent);
        }
    }
}