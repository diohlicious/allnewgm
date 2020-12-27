package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicleLiveGarage;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.List;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;


public class LiveGarageAdapter extends RecyclerView.Adapter<ViewHolderItemVehicleLiveGarage> {

    private List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModelList;
    private Context contexts;
    private long negoPrice = 0;


    public LiveGarageAdapter(Context context, List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModels) {
        this.contexts = context;
        this.hardCodeDataBaruMasukModelList = hardCodeDataBaruMasukModels;
    }

    @NonNull
    @Override
    public ViewHolderItemVehicleLiveGarage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_live_garage, viewGroup, false);
        return new ViewHolderItemVehicleLiveGarage(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemVehicleLiveGarage holder, int position) {
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = hardCodeDataBaruMasukModelList.get(position);
        holder.tvVehicleName.setText(hardCodeDataBaruMasukModel.getVehicleName());
        holder.tvPlatNumber.setText(hardCodeDataBaruMasukModel.getPlatNumber()+" - ");
        holder.tvCity.setText(hardCodeDataBaruMasukModel.getCity());
        holder.tvPenawaranTerakhir.setText("Rp "+setCurrencyFormat(hardCodeDataBaruMasukModel.getPrice()));
        holder.tvPrice.setText("Rp "+setCurrencyFormat(hardCodeDataBaruMasukModel.getPrice()));
        long lastPrice = Long.parseLong(hardCodeDataBaruMasukModel.getPrice());
        negoPrice = Long.parseLong(hardCodeDataBaruMasukModel.getPrice());
        holder.ivMin.setOnClickListener(view -> {
            if(negoPrice==lastPrice){
                Toast.makeText(contexts, "Minimum Tawar Harus Lebih Besar dari Penawaran Terakhir", Toast.LENGTH_SHORT).show();
            }else {
                negoPrice = negoPrice-500000;
                holder.tvPrice.setText("Rp "+setCurrencyFormat(String.valueOf(negoPrice)));
            }
        });

        holder.ivPlus.setOnClickListener(view -> {
            negoPrice = negoPrice+500000;
            holder.tvPrice.setText("Rp "+setCurrencyFormat(String.valueOf(negoPrice)));
        });

        holder.btnNego.setOnClickListener(view -> {
            holder.cardViewSuccessBidding.setVisibility(View.VISIBLE);
        });

        holder.ivClearPrice.setOnClickListener(view -> {
            negoPrice = Long.parseLong(hardCodeDataBaruMasukModel.getPrice());
            holder.tvPrice.setText("Rp "+setCurrencyFormat(hardCodeDataBaruMasukModel.getPrice()));
        });

        holder.relativeVehicle.setOnClickListener(view -> {
            Intent intent = new Intent(contexts, VehicleDetailActivity.class);
            intent.putExtra(ID_VEHICLE, "");
            intent.putExtra(KIK, "");
            intent.putExtra(FROM_PAGE, "");
            contexts.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hardCodeDataBaruMasukModelList.size();
    }

}
