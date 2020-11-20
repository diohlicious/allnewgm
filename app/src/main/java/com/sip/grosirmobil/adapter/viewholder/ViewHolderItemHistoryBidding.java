package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderItemHistoryBidding extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvPlatNumber, tvCity, tvOpenPrice, tvInitialName, tvPriceWin;
    public CardView cardVehicle;
    public CircleImageView circleImageViewItem;

    public ViewHolderItemHistoryBidding(View view) {
        super(view);
        cardVehicle         = view.findViewById(R.id.card_vehicle);
        tvVehicleName       = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber        = view.findViewById(R.id.tv_plat_number);
        tvCity              = view.findViewById(R.id.tv_city);
        tvOpenPrice         = view.findViewById(R.id.tv_open_price);
        circleImageViewItem = view.findViewById(R.id.circle_image_view_item);
        tvInitialName       = view.findViewById(R.id.tv_initial_name);
        tvPriceWin          = view.findViewById(R.id.tv_price_win);
    }
}
