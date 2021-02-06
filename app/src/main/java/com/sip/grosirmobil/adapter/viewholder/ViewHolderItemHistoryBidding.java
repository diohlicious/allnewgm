package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderItemHistoryBidding extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvEventDate, tvCity, tvHargaTertinggi, tvPriceWin, tvStatus, tvNoVa;
    public CardView cardVehicle;
    public ProgressBar progressBar;

    public ViewHolderItemHistoryBidding(View view) {
        super(view);
        cardVehicle         = view.findViewById(R.id.card_vehicle);
        tvVehicleName       = view.findViewById(R.id.tv_vehicle_name);
        tvCity              = view.findViewById(R.id.tv_city);
        tvEventDate         = view.findViewById(R.id.tv_event_date);
        tvHargaTertinggi    = view.findViewById(R.id.tv_harga_tertinggi);
        tvPriceWin          = view.findViewById(R.id.tv_price_win);
        tvNoVa              = view.findViewById(R.id.tv_no_va);
        tvStatus            = view.findViewById(R.id.tv_status);
        progressBar         = view.findViewById(R.id.progress_bar);
    }
}
