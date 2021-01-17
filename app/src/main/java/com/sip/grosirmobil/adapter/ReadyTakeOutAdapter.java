package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.LocationUnitActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicleReadyTakeOut;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.cart.DataCartResponse;

import java.util.List;

public class ReadyTakeOutAdapter extends RecyclerView.Adapter<ViewHolderItemVehicleReadyTakeOut> {

    private final List<DataCartResponse> dataCartResponseList;
    private final Context contexts;


    public ReadyTakeOutAdapter(Context context, List<DataCartResponse> dataCartResponses) {
        this.contexts = context;
        this.dataCartResponseList = dataCartResponses;
    }

    @NonNull
    @Override
    public ViewHolderItemVehicleReadyTakeOut onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_ready_take_out, viewGroup, false);
        return new ViewHolderItemVehicleReadyTakeOut(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemVehicleReadyTakeOut holder, int position) {
        DataCartResponse dataCartResponse = dataCartResponseList.get(position);
        try {
            holder.tvVehicleName.setText(dataCartResponse.getVehicleName());
            holder.tvPlatNumber.setText(dataCartResponse.getKik().substring(0, 10) + " - ");
            holder.tvCity.setText(dataCartResponse.getDataOtoJsonResponse().getLokasi().replace("WAREHOUSE ", ""));
            holder.tvPrice.setText(dataCartResponse.getBottomPrice());
            holder.cardVehicle.setOnClickListener(view -> {
//                Intent intent = new Intent(contexts, VehicleDetailActivity.class);
//                intent.putExtra(ID_VEHICLE, "");
//                intent.putExtra(KIK, "");
//                intent.putExtra(FROM_PAGE, "");
//                contexts.startActivity(intent);
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

            holder.btnTakeOutLocation.setOnClickListener(view -> {
                Intent intent = new Intent(contexts, LocationUnitActivity.class);
                contexts.startActivity(intent);
            });
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }

    }

    @Override
    public int getItemCount() {
        return dataCartResponseList.size();
    }

}
