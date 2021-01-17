package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderVehicleToBuy extends RecyclerView.ViewHolder {

    public TextView tvVehicleToBuy;

    public ViewHolderVehicleToBuy(View view) {
        super(view);
        tvVehicleToBuy         = view.findViewById(R.id.tv_vehicle_name);
    }

}
