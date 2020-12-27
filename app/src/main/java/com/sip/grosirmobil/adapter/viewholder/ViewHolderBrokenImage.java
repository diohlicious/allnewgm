package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderBrokenImage extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView tvDescription, tvImageNumber;
    public ImageView ivImage;

    public ViewHolderBrokenImage(View view) {
        super(view);
        cardView              = view.findViewById(R.id.card_view);
        tvDescription         = view.findViewById(R.id.tv_description);
        tvImageNumber         = view.findViewById(R.id.tv_image_number);
        ivImage         = view.findViewById(R.id.iv_image);
    }
}
