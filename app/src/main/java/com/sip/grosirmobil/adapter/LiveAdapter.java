package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicle;
import com.sip.grosirmobil.cloud.config.response.home.DataHomeLiveResponse;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class LiveAdapter extends RecyclerView.Adapter<ViewHolderItemVehicle> {

    private List<DataHomeLiveResponse> dataHomeLiveResponseList;
    private Context contexts;


    public LiveAdapter(Context context, List<DataHomeLiveResponse> dataHomeLiveResponses) {
        this.contexts = context;
        this.dataHomeLiveResponseList = dataHomeLiveResponses;
    }

    @NonNull
    @Override
    public ViewHolderItemVehicle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_home_live, viewGroup, false);
        return new ViewHolderItemVehicle(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemVehicle holder, int position) {
        DataHomeLiveResponse dataHomeLiveResponse = dataHomeLiveResponseList.get(position);
        holder.tvVehicleName.setText(dataHomeLiveResponse.getVehicleName());
        holder.tvPlatNumber.setText(dataHomeLiveResponse.getKikNumber().substring(0,10)+" - ");
        holder.tvCity.setText(dataHomeLiveResponse.getWareHouse().replace("WAREHOUSE ",""));
        holder.tvOpenPrice.setText("Rp "+setCurrencyFormat(dataHomeLiveResponse.getOpenPrice()));
        holder.tvBottomPrice.setText("Rp "+setCurrencyFormat(dataHomeLiveResponse.getOpenPrice()));
        holder.tvInitialName.setText(dataHomeLiveResponse.getGrade());
        startTimer(holder.tvTimer, 20000000);
        AtomicBoolean favorite = new AtomicBoolean(false);

        holder.ivFavorite.setOnClickListener(view -> {
            if(favorite.get()){
                favorite.set(false);
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
            }else {
                favorite.set(true);
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
            }
        });
        holder.cardVehicle.setOnClickListener(view -> {
            Intent intent = new Intent(contexts, VehicleDetailActivity.class);
            intent.putExtra(ID_VEHICLE, "");
            intent.putExtra(FROM_PAGE, "LIVE");
            contexts.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataHomeLiveResponseList.size();
    }

    public void startTimer(TextView tvTimer, long noOfMinutes) {
        new CountDownTimer(noOfMinutes,  1000) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int hours = seconds / (60 * 60);
                int tempMint = (seconds - (hours * 60 * 60));
                int minutes = tempMint / 60;
                seconds = tempMint - (minutes * 60);
                tvTimer.setText(String.format("%02d", hours) + "h " +
                                String.format("%02d", minutes) + "m " +
                                String.format("%02d", seconds) + "s");
            }
            @SuppressLint("SetTextI18n")
            public void onFinish() {
                tvTimer.setText("00h 00m 00s");
            }
        }.start();
    }

}
