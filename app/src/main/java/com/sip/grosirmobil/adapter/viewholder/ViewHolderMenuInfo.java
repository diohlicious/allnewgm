package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderMenuInfo extends RecyclerView.ViewHolder {

    public TextView tvPromo, tvDate;
    public ImageView ivImage;

    public ViewHolderMenuInfo(View view) {
        super(view);
        tvDate           = view.findViewById(R.id.tv_date);
        tvPromo           = view.findViewById(R.id.tv_promo);
        ivImage           = view.findViewById(R.id.iv_image);
    }
}
