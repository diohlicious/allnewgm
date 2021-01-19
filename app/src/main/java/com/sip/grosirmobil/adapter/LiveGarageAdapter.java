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
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicleLiveGarage;
import com.sip.grosirmobil.cloud.config.response.cart.DataCartResponse;

import java.util.List;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;


public class LiveGarageAdapter extends RecyclerView.Adapter<ViewHolderItemVehicleLiveGarage> {

    private final List<DataCartResponse> dataCartResponseList;
    private final Context contexts;
    private long negoPrice = 0;


    public LiveGarageAdapter(Context context, List<DataCartResponse> dataCartResponses) {
        this.contexts = context;
        this.dataCartResponseList = dataCartResponses;
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
        DataCartResponse dataCartResponse = dataCartResponseList.get(position);
        holder.tvVehicleName.setText(dataCartResponse.getVehicleName());
        holder.tvPlatNumber.setText(dataCartResponse.getKik().substring(0, 10) + " - ");
//        holder.tvCity.setText(dataCartResponse.getWareHouse().replace("WAREHOUSE ", ""));
        holder.tvPenawaranAnda.setText("Rp "+setCurrencyFormat(dataCartResponse.getUserTertinggi()));
        holder.tvPenawaranTerakhir.setText("Rp "+setCurrencyFormat(dataCartResponse.getTertinggi()));
        holder.tvPrice.setText("Rp "+setCurrencyFormat(dataCartResponse.getTertinggi()));
        holder.tvInitialName.setText(dataCartResponse.getGrade());
        long lastPrice = Long.parseLong(dataCartResponse.getUserTertinggi());
        negoPrice = Long.parseLong(dataCartResponse.getUserTertinggi());
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
            negoPrice = Long.parseLong(dataCartResponse.getUserTertinggi());
            holder.tvPrice.setText("Rp "+setCurrencyFormat(dataCartResponse.getUserTertinggi()));
        });

        CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(contexts);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        Glide.with(contexts)
                .load(dataCartResponse.getFoto())
                .apply(new RequestOptions()
                        .placeholder(circularProgressDrawable)
                        .error(R.drawable.ic_broken_image)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(false))
                .into(holder.ivImage);

        holder.relativeVehicle.setOnClickListener(view -> {
            Intent intent = new Intent(contexts, VehicleDetailActivity.class);
            intent.putExtra(ID_VEHICLE, String.valueOf(dataCartResponse.getOhid()));
            intent.putExtra(KIK, dataCartResponse.getKik());
            intent.putExtra(FROM_PAGE, "LIVE");
            contexts.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataCartResponseList.size();
    }

}
