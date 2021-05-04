package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderItemVehicleSuccessGarage extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvPlatNumber, tvCity, tvPrice, adminPrice, totalPrice, tvInitialName;
    public CardView cardVehicle;
    public ImageView ivImage;
    public Button btnNextPayment;
    public CircleImageView circleImageViewItem;

    public ViewHolderItemVehicleSuccessGarage(View view) {
        super(view);
        cardVehicle         = view.findViewById(R.id.card_vehicle);
        ivImage             = view.findViewById(R.id.iv_image);
        tvVehicleName       = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber        = view.findViewById(R.id.tv_plat_number);
        tvCity              = view.findViewById(R.id.tv_city);
        tvPrice             = view.findViewById(R.id.tv_price);
        adminPrice             = view.findViewById(R.id.admin_price);
        totalPrice             = view.findViewById(R.id.total_price);
        circleImageViewItem     = view.findViewById(R.id.circle_image_view_item);
        tvInitialName         = view.findViewById(R.id.tv_initial_name);
        btnNextPayment      = view.findViewById(R.id.btn_next_payment);
    }
}
