package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemCart;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.List;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;


public class CartAdapter extends RecyclerView.Adapter<ViewHolderItemCart> {

    private List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModelList;
    private Context context;
    private String totalPrice;

    public CartAdapter(Context context, String totalPrice, List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModels) {
        this.context = context;
        this.hardCodeDataBaruMasukModelList = hardCodeDataBaruMasukModels;
    }

    @NonNull
    @Override
    public ViewHolderItemCart onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_cart, viewGroup, false);
        return new ViewHolderItemCart(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemCart holder, int position) {
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = hardCodeDataBaruMasukModelList.get(position);
        holder.tvVehicleName.setText(hardCodeDataBaruMasukModel.getVehicleName());
        holder.tvPlatNumber.setText(hardCodeDataBaruMasukModel.getPlatNumber());
        holder.tvPrice.setText("Rp "+setCurrencyFormat(hardCodeDataBaruMasukModel.getPrice()));

        holder.cbCart.setOnCheckedChangeListener((compoundButton, checked) -> {
            if(checked){
                totalPrice = hardCodeDataBaruMasukModel.getPrice();
                holder.linearCart.setBackgroundResource(R.color.colorPrimaryThemeCart);
                holder.tvVehicleName.setTextColor(context.getResources().getColor(R.color.colorPrimaryTheme));
            }else {
                totalPrice = "0";
                holder.linearCart.setBackgroundResource(R.color.colorPrimaryWhite);
                holder.tvVehicleName.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
            }
        });

    }

    @Override
    public int getItemCount() {
        return hardCodeDataBaruMasukModelList.size();
    }

}
