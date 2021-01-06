package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicle;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.homelive.DataHomeLiveResponse;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.getSecondTime;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class LiveAdapter extends RecyclerView.Adapter<ViewHolderItemVehicle> {

    private final List<DataHomeLiveResponse> dataHomeLiveResponseList;
    private final Context contexts;


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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"SetTextI18n", "RestrictedApi"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemVehicle holder, int position) {
        try {
            DataHomeLiveResponse dataHomeLiveResponse = dataHomeLiveResponseList.get(position);
            holder.tvVehicleName.setText(dataHomeLiveResponse.getVehicleName());
            holder.tvPlatNumber.setText(dataHomeLiveResponse.getKikNumber().substring(0, 10) + " - ");
            holder.tvCity.setText(dataHomeLiveResponse.getWareHouse().replace("WAREHOUSE ", ""));
            holder.tvOpenPrice.setText("Rp " + setCurrencyFormat(dataHomeLiveResponse.getBottomPrice()));
            holder.tvBottomPrice.setText("Rp " + setCurrencyFormat(dataHomeLiveResponse.getBottomPrice()));
            holder.tvInitialName.setText(dataHomeLiveResponse.getGrade());

            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(contexts);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(contexts)
                    .load(dataHomeLiveResponse.getImage())
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
                            .error(R.drawable.ic_broken_image)
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(false))
                    .into(holder.ivImage);

            if(dataHomeLiveResponse.getIsFavorite().equals("1")){
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
            }else {
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
            }

            long count = getSecondTime(dataHomeLiveResponse.getStartDate(), dataHomeLiveResponse.getEndDate());
//            long count = getSecondTime("2020-12-12 10:00:00","2020-12-12 11:00:00");
            long countTime = count;

            System.out.println("Second : : "+ countTime);
            startTimer(holder.tvTimer, getSecondTime(dataHomeLiveResponse.getStartDate(), dataHomeLiveResponse.getEndDate()));

            AtomicBoolean favorite = new AtomicBoolean(false);

            holder.ivFavorite.setOnClickListener(view -> {
                if (favorite.get()) {
                    favorite.set(false);
                    holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                } else {
                    favorite.set(true);
                    holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
                }
            });
            holder.cardVehicle.setOnClickListener(view -> {
                Intent intent = new Intent(contexts, VehicleDetailActivity.class);
                intent.putExtra(ID_VEHICLE, String.valueOf(dataHomeLiveResponse.getOpenHouseId()));
                intent.putExtra(KIK, dataHomeLiveResponse.getKik());
                intent.putExtra(FROM_PAGE, "LIVE");
                contexts.startActivity(intent);
            });
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }
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
