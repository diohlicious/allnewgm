package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.CartAdapter;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

public class ViewHolderItemCart extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvPlatNumber,tvPrice, tvCity;
    public CardView cardVehicle;
    public ImageView ivImage;
    public CheckBox cbCart;
    public LinearLayout linearCart;

    public ViewHolderItemCart(View view) {
        super(view);
        cardVehicle         = view.findViewById(R.id.card_vehicle);
        ivImage             = view.findViewById(R.id.iv_image);
        tvVehicleName       = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber        = view.findViewById(R.id.tv_plat_number);
        tvPrice             = view.findViewById(R.id.tv_price);
        tvCity             = view.findViewById(R.id.tv_city);
        cbCart              = view.findViewById(R.id.cb_cart);
        linearCart          = view.findViewById(R.id.linear_cart);
    }

    public void bind(HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel, final CartAdapter.OnItemClickListener listener) {
//        tvVehicleName.setText(hardCodeDataBaruMasukModel.getVehicleName());
//        tvPlatNumber.setText(hardCodeDataBaruMasukModel.getPlatNumber());
//        tvPrice.setText(hardCodeDataBaruMasukModel.getPrice());

        cbCart.setOnClickListener(view -> listener.onItemClick(hardCodeDataBaruMasukModel));
    }
}
