package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderItemVehicleLiveGarage extends RecyclerView.ViewHolder {

    public TextView tvAdmin, tvVehicleName, tvPenawaranAndaKet, tvPlatNumber, tvCity, tvPrice, tvPenawaranTerakhir, tvInitialName, tvPenawaranAnda, tvTimer;
    public ImageView ivImage, ivMin, ivPlus, ivClearPrice;
    public Button btnNego, btnBuyNow;
    public RelativeLayout relativeVehicle, relativeSuccessNego;
    public CircleImageView circleImageViewItem;

    public ViewHolderItemVehicleLiveGarage(View view) {
        super(view);
        relativeVehicle = view.findViewById(R.id.relative_vehicle);
        ivImage = view.findViewById(R.id.iv_image);
        tvVehicleName = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber = view.findViewById(R.id.tv_plat_number);
        tvPenawaranAndaKet = view.findViewById(R.id.tv_penawaran_anda_ket);
        tvCity = view.findViewById(R.id.tv_city);
        tvPenawaranTerakhir = view.findViewById(R.id.tv_penawaran_terakhir);
        tvPenawaranAnda = view.findViewById(R.id.tv_penawaran_anda);
        tvPrice = view.findViewById(R.id.tv_price);
        ivMin = view.findViewById(R.id.iv_min);
        ivPlus = view.findViewById(R.id.iv_plus);
        ivClearPrice = view.findViewById(R.id.iv_clear_price);
        btnNego = view.findViewById(R.id.btn_nego);
        btnBuyNow = view.findViewById(R.id.btn_buy_now);
        circleImageViewItem = view.findViewById(R.id.circle_image_view_item);
        tvInitialName = view.findViewById(R.id.tv_initial_name);
        tvTimer = view.findViewById(R.id.tv_timer);
        tvAdmin = view.findViewById(R.id.tv_biaya_admin);
        relativeSuccessNego = view.findViewById(R.id.relative_success_nego);
    }
}
