package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
import com.sip.grosirmobil.cloud.config.response.homecomingsoon.DataHomeComingSoonResponse;

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

public class LiveSoonAdapter extends RecyclerView.Adapter<ViewHolderItemVehicle> {

    private final List<DataHomeComingSoonResponse> dataHomeComingSoonResponseList;
    private final Context contexts;
    private final String timeServer;


    public LiveSoonAdapter(Context context, String timeServer, List<DataHomeComingSoonResponse> dataHomeComingSoonResponses) {
        this.contexts = context;
        this.timeServer = timeServer;
        this.dataHomeComingSoonResponseList = dataHomeComingSoonResponses;
    }

    @NonNull
    @Override
    public ViewHolderItemVehicle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_home_soon, viewGroup, false);
        return new ViewHolderItemVehicle(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemVehicle holder, int position) {
        DataHomeComingSoonResponse dataHomeComingSoonResponse = dataHomeComingSoonResponseList.get(position);
        holder.tvVehicleName.setText(dataHomeComingSoonResponse.getVehicleName());
        holder.tvPlatNumber.setText(dataHomeComingSoonResponse.getKikNumber().substring(0, 10) + " - ");
        holder.tvCity.setText(dataHomeComingSoonResponse.getWareHouse().replace("WAREHOUSE ", ""));
        holder.tvOpenPrice.setText(dataHomeComingSoonResponse.getBottomPrice());
        holder.tvBottomPrice.setText(dataHomeComingSoonResponse.getBottomPrice());
        holder.tvInitialName.setText(dataHomeComingSoonResponse.getGrade());

        CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(contexts);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        Glide.with(contexts)
                .load(dataHomeComingSoonResponse.getImage())
                .apply(new RequestOptions()
                        .placeholder(circularProgressDrawable)
                        .error(R.drawable.ic_broken_image)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(false))
                .into(holder.ivImage);

        if(dataHomeComingSoonResponse.getIsFavorite().equals("1")){
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
        }else {
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
        }

        startTimer(holder.tvTimer, calculateDate(timeServer,dataHomeComingSoonResponse.getStartDate()));
//        startTimer(holder.tvTimer, 20000000);

        AtomicBoolean favorite = new AtomicBoolean(false);

        holder.ivFavorite.setOnClickListener(view -> {
            if (favorite.get()) {
                favorite.set(false);
                unFavorite(contexts, holder.ivFavorite, dataHomeComingSoonResponse.getKik());
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
            } else {
                favorite.set(true);
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
                setFavorite(contexts, holder.ivFavorite, dataHomeComingSoonResponse.getKik());
            }
        });
        holder.cardVehicle.setOnClickListener(view -> {
            Intent intent = new Intent(contexts, VehicleDetailActivity.class);
            intent.putExtra(ID_VEHICLE, String.valueOf(dataHomeComingSoonResponse.getOpenHouseId()));
            intent.putExtra(KIK, dataHomeComingSoonResponse.getKik());
            intent.putExtra(FROM_PAGE, "COMING SOON");
            contexts.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return dataHomeComingSoonResponseList.size();
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
                        String.format("%02d", seconds) + "s ");
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
