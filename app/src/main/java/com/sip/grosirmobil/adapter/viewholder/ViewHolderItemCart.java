package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderItemCart extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvPlatNumber,tvPrice;
    public CardView cardVehicle;
    public ImageView ivImage;
    public CheckBox cbCart;
    public RelativeLayout relativeCart;

    public ViewHolderItemCart(View view) {
        super(view);
        cardVehicle         = view.findViewById(R.id.card_vehicle);
        ivImage             = view.findViewById(R.id.iv_image);
        tvVehicleName       = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber        = view.findViewById(R.id.tv_plat_number);
        tvPrice             = view.findViewById(R.id.tv_price);
        relativeCart        = view.findViewById(R.id.relative_cart);
        cbCart              = view.findViewById(R.id.cb_cart);
    }

//    public void bind(HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel, int selected, int position, final CartAdapter.OnItemClickListener listener) {
//        tvVehicleName.setText(hardCodeDataBaruMasukModel.getVehicleName());
//        tvPlatNumber.setText(hardCodeDataBaruMasukModel.getPlatNumber());
//        tvPrice.setText(hardCodeDataBaruMasukModel.getPrice());
//
//        cbCart.setOnCheckedChangeListener((compoundButton, b) -> listener.onItemClick(hardCodeDataBaruMasukModel));
//    }
}
