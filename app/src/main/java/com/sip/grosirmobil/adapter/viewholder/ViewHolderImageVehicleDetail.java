package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderImageVehicleDetail extends RecyclerView.ViewHolder {

    public CardView cardView;
    public ImageView ivImage;

    public ViewHolderImageVehicleDetail(View view) {
        super(view);
        cardView         = view.findViewById(R.id.card_view);
        ivImage          = view.findViewById(R.id.iv_image);
    }
}
