package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemCart;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.List;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;


public class CartAdapter extends RecyclerView.Adapter<ViewHolderItemCart> {

    private List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModelList;
    private final Context context;
    private long totalPrice, totalPriceTemp;
    private final TextView tvHargaKendaraan;
    private TextView tvTotal;

//    public CartAdapter(Context context, TextView tvHargaKendaraan, TextView tvTotal, List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModels) {
//        this.context = context;
//        this.tvHargaKendaraan = tvHargaKendaraan;
//        this.tvTotal = tvTotal;
//        this.hardCodeDataBaruMasukModelList = hardCodeDataBaruMasukModels;
//    }

    public interface OnItemClickListener {
        void onItemClick(HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel);
    }

    private final OnItemClickListener onItemClickListener;

    public CartAdapter(Context context, TextView tvHargaKendaraan, List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModels,  OnItemClickListener onItemClickListener) {
        this.context = context;
        this.tvHargaKendaraan = tvHargaKendaraan;
        this.hardCodeDataBaruMasukModelList = hardCodeDataBaruMasukModels;
        this.onItemClickListener = onItemClickListener;
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
        holder.tvPlatNumber.setText(hardCodeDataBaruMasukModel.getPlatNumber()+" - ");
        holder.tvCity.setText(hardCodeDataBaruMasukModel.getCity());
        holder.tvPrice.setText("Rp "+setCurrencyFormat(hardCodeDataBaruMasukModel.getPrice()));

        holder.cbCart.setOnCheckedChangeListener((compoundButton, checked) -> {
            if(checked){
//                totalPriceTemp = Long.parseLong(hardCodeDataBaruMasukModel.getPrice());
                holder.linearCart.setBackgroundResource(R.color.colorPrimaryThemeCart);
                holder.tvVehicleName.setTextColor(context.getResources().getColor(R.color.colorPrimaryTheme));
                totalPrice = totalPrice+Long.parseLong(hardCodeDataBaruMasukModel.getPrice());
                tvHargaKendaraan.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
//                totalPrice= totalPrice+500000;
            }else {
//                totalPriceTemp = Long.parseLong("0");
                holder.linearCart.setBackgroundResource(R.color.colorPrimaryWhite);
                holder.tvVehicleName.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
                totalPrice = totalPrice-Long.parseLong(hardCodeDataBaruMasukModel.getPrice());
                tvHargaKendaraan.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
//                totalPrice= totalPrice-500000;
            }


//            tvTotal.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
        });

        holder.bind(hardCodeDataBaruMasukModel, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return hardCodeDataBaruMasukModelList.size();
    }

}
