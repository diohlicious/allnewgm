package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderVehicleDescription extends RecyclerView.ViewHolder {

    public TextView tvDescription;

    public ViewHolderVehicleDescription(View view) {
        super(view);
        tvDescription         = view.findViewById(R.id.tv_description);
    }
}
