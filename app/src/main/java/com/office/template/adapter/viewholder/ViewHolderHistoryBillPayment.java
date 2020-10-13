package com.office.template.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.office.template.R;

public class ViewHolderHistoryBillPayment extends RecyclerView.ViewHolder {

    public TextView tvProduct, tvDate, tvTotal, tvOrderStatus, tvCodeProduct;
    public Button btnBuyAgain;

    public ViewHolderHistoryBillPayment(View view) {
        super(view);
        btnBuyAgain       = view.findViewById(R.id.btn_buy_again);
        tvCodeProduct     = view.findViewById(R.id.tv_code_product);
        tvProduct           = view.findViewById(R.id.tv_product);
        tvDate           = view.findViewById(R.id.tv_date);
        tvTotal           = view.findViewById(R.id.tv_total);
        tvOrderStatus  = view.findViewById(R.id.tv_order_status);
    }
}
