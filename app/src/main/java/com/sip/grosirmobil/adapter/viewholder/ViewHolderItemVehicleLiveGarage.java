package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderItemVehicleLiveGarage extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvPlatNumber, tvCity, tvPrice, tvPenawaranTerakhir, tvInitialName;
    public CardView cardViewSuccessBidding;
    public ImageView ivImage, ivMin, ivPlus, ivClearPrice;
    public Button btnNego;
    public CircleImageView circleImageViewItem;

    public ViewHolderItemVehicleLiveGarage(View view) {
        super(view);
        ivImage                 = view.findViewById(R.id.iv_image);
        tvVehicleName           = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber            = view.findViewById(R.id.tv_plat_number);
        tvCity                  = view.findViewById(R.id.tv_city);
        tvPenawaranTerakhir     = view.findViewById(R.id.tv_penawaran_terakhir);
        tvPrice                 = view.findViewById(R.id.tv_price);
        ivMin                   = view.findViewById(R.id.iv_min);
        ivPlus                  = view.findViewById(R.id.iv_plus);
        ivClearPrice            = view.findViewById(R.id.iv_clear_price);
        btnNego                 = view.findViewById(R.id.btn_nego);
        circleImageViewItem     = view.findViewById(R.id.circle_image_view_item);
        tvInitialName         = view.findViewById(R.id.tv_initial_name);
        cardViewSuccessBidding  = view.findViewById(R.id.card_view_success_bidding);
    }
}
