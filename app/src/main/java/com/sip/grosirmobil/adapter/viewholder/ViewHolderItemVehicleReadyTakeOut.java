package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderItemVehicleReadyTakeOut extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvPlatNumber, tvCity, tvPrice, tvInitialName;
    public CardView cardVehicle;
    public ImageView ivImage;
    public Button btnTakeOutLocation;
    public CircleImageView circleImageViewItem;

    public ViewHolderItemVehicleReadyTakeOut(View view) {
        super(view);
        cardVehicle         = view.findViewById(R.id.card_vehicle);
        ivImage             = view.findViewById(R.id.iv_image);
        tvVehicleName       = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber        = view.findViewById(R.id.tv_plat_number);
        tvCity              = view.findViewById(R.id.tv_city);
        tvPrice             = view.findViewById(R.id.tv_price);
        circleImageViewItem     = view.findViewById(R.id.circle_image_view_item);
        tvInitialName         = view.findViewById(R.id.tv_initial_name);
        btnTakeOutLocation      = view.findViewById(R.id.btn_take_out_location);
    }
}
