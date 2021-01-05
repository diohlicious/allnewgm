package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderVehicleDetailData extends RecyclerView.ViewHolder {

    public TextView tvDescription, tvName;
    public ImageView ivStatus;

    public ViewHolderVehicleDetailData(View view) {
        super(view);
        ivStatus              = view.findViewById(R.id.iv_status);
        tvDescription         = view.findViewById(R.id.tv_description);
        tvName                = view.findViewById(R.id.tv_name);
    }
}
