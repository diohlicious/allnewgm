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
import com.sip.grosirmobil.activity.PayPaymentActivity;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicleSuccessGarage;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.cart.DataCartResponse;

import java.util.List;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.PRICE;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class SuccessGarageAdapter extends RecyclerView.Adapter<ViewHolderItemVehicleSuccessGarage> {

    private final List<DataCartResponse> dataCartResponseList;
    private final Context contexts;


    public SuccessGarageAdapter(Context context, List<DataCartResponse> dataCartResponses) {
        this.contexts = context;
        this.dataCartResponseList = dataCartResponses;
    }

    @NonNull
    @Override
    public ViewHolderItemVehicleSuccessGarage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_success_garage, viewGroup, false);
        return new ViewHolderItemVehicleSuccessGarage(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemVehicleSuccessGarage holder, int position) {
        try {
            DataCartResponse dataCartResponse = dataCartResponseList.get(position);
            holder.tvVehicleName.setText(dataCartResponse.getVehicleName());
//            holder.tvPlatNumber.setText(dataCartResponse.getKik().substring(0, 10) + " - ");
            if(dataCartResponse.getDataOtoJsonResponse()==null){

            }else {
                if(dataCartResponse.getDataOtoJsonResponse().getLokasi()==null||dataCartResponse.getDataOtoJsonResponse().getLokasi().equals("")){

                }
                else {
                    holder.tvPlatNumber.setText(dataCartResponse.getKik().substring(0, 10) + " - "+dataCartResponse.getDataOtoJsonResponse().getLokasi().replace("WAREHOUSE ", ""));
                }
            }

//        holder.tvCity.setText(dataCartResponse.getWareHouse().replace("WAREHOUSE ", ""));
            holder.tvPrice.setText("Rp "+setCurrencyFormat(dataCartResponse.getUserTertinggi()));
            holder.tvInitialName.setText(dataCartResponse.getGrade());
            holder.cardVehicle.setOnClickListener(view -> {
                Intent intent = new Intent(contexts, VehicleDetailActivity.class);
                intent.putExtra(ID_VEHICLE, String.valueOf(dataCartResponse.getOhid()));
                intent.putExtra(KIK, dataCartResponse.getKik());
                intent.putExtra(FROM_PAGE, "HISTORY");
                contexts.startActivity(intent);
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

            holder.btnNextPayment.setOnClickListener(view -> {
                Intent intent = new Intent(contexts, PayPaymentActivity.class);
                intent.putExtra(FROM_PAGE, "Cart");
                intent.putExtra(ID_VEHICLE, String.valueOf(dataCartResponse.getOhid()));
                intent.putExtra(KIK, dataCartResponse.getKik());
                intent.putExtra(PRICE, dataCartResponse.getUserTertinggi());
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
