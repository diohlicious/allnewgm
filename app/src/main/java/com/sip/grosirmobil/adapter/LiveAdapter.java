package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.request.favorite.FavoriteRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.homelive.DataHomeLiveResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.calculateDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class LiveAdapter extends RecyclerView.Adapter<ViewHolderItemVehicle> {

    private final List<DataHomeLiveResponse> dataHomeLiveResponseList;
    private final Context contexts;
    private final String timeServer;

    public LiveAdapter(Context context, String timeServer, List<DataHomeLiveResponse> dataHomeLiveResponses) {
        this.contexts = context;
        this.timeServer = timeServer;
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

            System.out.println("Long In The Time : "+ calculateDate(timeServer,dataHomeLiveResponse.getEndDate()));
            startTimer(holder.tvTimer, calculateDate(timeServer,dataHomeLiveResponse.getEndDate()));

            AtomicBoolean favorite = new AtomicBoolean(false);

            holder.ivFavorite.setOnClickListener(view -> {
                if (favorite.get()) {
                    favorite.set(false);
                    unFavorite(contexts, holder.ivFavorite, dataHomeLiveResponse.getKik());
                    holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                } else {
                    favorite.set(true);
                    holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
                    setFavorite(contexts, holder.ivFavorite, dataHomeLiveResponse.getKik());
                }
            });
            holder.cardVehicle.setOnClickListener(view -> {
                Intent intent = new Intent(contexts, VehicleDetailActivity.class);
                intent.putExtra(ID_VEHICLE, String.valueOf(dataHomeLiveResponse.getOpenHouseId()));
                intent.putExtra(KIK, dataHomeLiveResponse.getKik());
                intent.putExtra(FROM_PAGE, "LIVE");
                contexts.startActivity(intent);
            });
        }
        catch (Exception e){
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

    public void setFavorite(Context contexts, ImageView ivFavorite, String kik){
        GrosirMobilPreference grosirMobilPreference = new GrosirMobilPreference(contexts);
        GrosirMobilFunction grosirMobilFunction = new GrosirMobilFunction(contexts);
        FavoriteRequest favoriteRequest = new FavoriteRequest(kik);
        final Call<GeneralResponse> timeServerApi = getApiGrosirMobil().setFavoriteApi(BEARER+" "+grosirMobilPreference.getToken(),favoriteRequest);
        timeServerApi.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            ivFavorite.setImageResource(R.drawable.ic_favorite);
                        }else {
                            grosirMobilFunction.showMessage(contexts, "POST Favorite", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(contexts, contexts.getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                grosirMobilFunction.showMessage(contexts, "POST Favorite", contexts.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    public void unFavorite(Context contexts, ImageView ivFavorite, String kik){
        GrosirMobilPreference grosirMobilPreference = new GrosirMobilPreference(contexts);
        GrosirMobilFunction grosirMobilFunction = new GrosirMobilFunction(contexts);
        FavoriteRequest favoriteRequest = new FavoriteRequest(kik);
        final Call<GeneralResponse> timeServerApi = getApiGrosirMobil().unFavoriteApi(BEARER+" "+grosirMobilPreference.getToken(),favoriteRequest);
        timeServerApi.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                        }else {
                            grosirMobilFunction.showMessage(contexts, "POST Favorite", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(contexts, contexts.getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                grosirMobilFunction.showMessage(contexts, "POST Favorite", contexts.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }
}
