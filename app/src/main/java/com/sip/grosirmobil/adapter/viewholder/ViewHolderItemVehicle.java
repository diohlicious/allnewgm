package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderItemVehicle extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvPlatNumber, tvCity, tvPrice, tvDescription;
    public CardView cardVehicle;
    public ImageView ivImage,ivTimer;
    public LinearLayout linearDescription;

    public ViewHolderItemVehicle(View view) {
        super(view);
        cardVehicle         = view.findViewById(R.id.card_vehicle);
        ivImage             = view.findViewById(R.id.iv_image);
        tvVehicleName       = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber        = view.findViewById(R.id.tv_plat_number);
        tvCity              = view.findViewById(R.id.tv_city);
        tvPrice             = view.findViewById(R.id.tv_price);
        linearDescription   = view.findViewById(R.id.linear_description);
        ivTimer             = view.findViewById(R.id.iv_timer);
        tvDescription       = view.findViewById(R.id.tv_description);
    }
}
