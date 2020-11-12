package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderPaymentMethod extends RecyclerView.ViewHolder {

    public CardView cardViewMethod;
    public ImageView ivPaymentMethod;

    public ViewHolderPaymentMethod(View view) {
        super(view);
        cardViewMethod        = view.findViewById(R.id.card_payment_method);
        ivPaymentMethod       = view.findViewById(R.id.iv_payment_method);
    }
}
