package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderUserBid extends RecyclerView.ViewHolder {

    public TextView tvNameBid, tvDateBid, tvPriceBid;

    public ViewHolderUserBid(View view) {
        super(view);
        tvNameBid         = view.findViewById(R.id.tv_name_bid);
        tvDateBid         = view.findViewById(R.id.tv_date_bid);
        tvPriceBid         = view.findViewById(R.id.tv_price_bid);
    }


}
