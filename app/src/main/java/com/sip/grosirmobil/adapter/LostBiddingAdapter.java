package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemHistoryBidding;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.List;

public class LostBiddingAdapter  extends RecyclerView.Adapter<ViewHolderItemHistoryBidding> {

    private List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModelList;
    private Context contexts;


    public LostBiddingAdapter(Context context, List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModels) {
        this.contexts = context;
        this.hardCodeDataBaruMasukModelList = hardCodeDataBaruMasukModels;
    }

    @NonNull
    @Override
    public ViewHolderItemHistoryBidding onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_history_bidding, viewGroup, false);
        return new ViewHolderItemHistoryBidding(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemHistoryBidding holder, int position) {
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = hardCodeDataBaruMasukModelList.get(position);
        holder.tvVehicleName.setText(hardCodeDataBaruMasukModel.getVehicleName());
        holder.tvPlatNumber.setText(hardCodeDataBaruMasukModel.getPlatNumber()+" - ");
        holder.tvCity.setText(hardCodeDataBaruMasukModel.getCity());
        holder.tvPriceWin.setText(hardCodeDataBaruMasukModel.getPrice());
        holder.tvOpenPrice.setText(hardCodeDataBaruMasukModel.getPrice());

    }

    @Override
    public int getItemCount() {
        return hardCodeDataBaruMasukModelList.size();
    }
}
