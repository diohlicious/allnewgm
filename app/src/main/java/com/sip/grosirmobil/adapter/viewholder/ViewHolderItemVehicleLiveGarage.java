package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderItemVehicleLiveGarage extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvPlatNumber, tvCity, tvPrice, tvTimer;
    public CardView cardFavorite, cardViewSuccessBidding;
    public ImageView ivImage, ivMin, ivPlus, ivClearPrice, ivFavorite;
    public Button btnNego;

    public ViewHolderItemVehicleLiveGarage(View view) {
        super(view);
        ivImage                 = view.findViewById(R.id.iv_image);
        tvTimer                 = view.findViewById(R.id.tv_timer);
        tvVehicleName           = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber            = view.findViewById(R.id.tv_plat_number);
        tvCity                  = view.findViewById(R.id.tv_city);
        tvPrice                 = view.findViewById(R.id.tv_price);
        ivMin                   = view.findViewById(R.id.iv_min);
        ivPlus                  = view.findViewById(R.id.iv_plus);
        ivClearPrice            = view.findViewById(R.id.iv_clear_price);
        btnNego                 = view.findViewById(R.id.btn_nego);
        cardFavorite            = view.findViewById(R.id.card_favorite);
        ivFavorite              = view.findViewById(R.id.iv_favorite);
        cardViewSuccessBidding  = view.findViewById(R.id.card_view_success_bidding);
    }
}
