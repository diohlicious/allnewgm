package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderItemVehicle extends RecyclerView.ViewHolder {

    public TextView tvVehicleName, tvPlatNumber, tvCity, tvOpenPrice, tvBottomPrice, tvTimer, tvInitialName;
    public CardView cardVehicle;
    public ImageView ivImage;
    public LinearLayout linearDescription;
    public CircleImageView circleImageViewItem;

    public ViewHolderItemVehicle(View view) {
        super(view);
        cardVehicle         = view.findViewById(R.id.card_vehicle);
        ivImage             = view.findViewById(R.id.iv_image);
        tvVehicleName       = view.findViewById(R.id.tv_vehicle_name);
        tvPlatNumber        = view.findViewById(R.id.tv_plat_number);
        tvCity              = view.findViewById(R.id.tv_city);
        tvOpenPrice         = view.findViewById(R.id.tv_open_price);
        tvBottomPrice       = view.findViewById(R.id.tv_bottom_price);
        linearDescription   = view.findViewById(R.id.linear_description);
        tvTimer             = view.findViewById(R.id.tv_timer);
        circleImageViewItem = view.findViewById(R.id.circle_image_view_item);
        tvInitialName       = view.findViewById(R.id.tv_initial_name);
    }
}
