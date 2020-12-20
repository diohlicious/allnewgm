package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderItemVehicleHomeHistory extends RecyclerView.ViewHolder {

    public TextView tvVehicleNameHistory, tvEventDate, tvCityHistory, tvOpenPriceHistory, tvSoldPriceHistory, tvInitialNameHistory,tvStatusHistory;
    public CardView cardVehicleHistory;
    public LinearLayout linearDescription;
    public CircleImageView circleImageViewItem;

    public ViewHolderItemVehicleHomeHistory(View view) {
        super(view);
        cardVehicleHistory          = view.findViewById(R.id.card_vehicle_history);
        tvVehicleNameHistory        = view.findViewById(R.id.tv_vehicle_name_history);
        tvEventDate                 = view.findViewById(R.id.tv_event_date_history);
        tvCityHistory               = view.findViewById(R.id.tv_city_history);
        tvOpenPriceHistory          = view.findViewById(R.id.tv_open_price_history);
        tvSoldPriceHistory          = view.findViewById(R.id.tv_bottom_price);
        linearDescription           = view.findViewById(R.id.linear_description);
        tvSoldPriceHistory          = view.findViewById(R.id.tv_sold_price_history);
        tvInitialNameHistory        = view.findViewById(R.id.tv_initial_name_history);
        tvStatusHistory             = view.findViewById(R.id.tv_status_history);
    }
}
