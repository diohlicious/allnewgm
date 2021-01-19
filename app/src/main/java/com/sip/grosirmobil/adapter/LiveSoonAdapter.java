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
import java.util.concurrent.TimeUnit;
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
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class LiveSoonAdapter extends RecyclerView.Adapter<ViewHolderItemVehicle> {

    private final List<DataHomeComingSoonResponse> dataHomeComingSoonResponseList;
    private final Context contexts;
    private final String timeServer;
    private final GrosirMobilPreference grosirMobilPreference;

    public LiveSoonAdapter(Context context, String timeServer, List<DataHomeComingSoonResponse> dataHomeComingSoonResponses) {
        this.contexts = context;
        this.timeServer = timeServer;
        this.dataHomeComingSoonResponseList = dataHomeComingSoonResponses;
        grosirMobilPreference = new GrosirMobilPreference(context);
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
        holder.tvOpenPrice.setText("Rp "+setCurrencyFormat(dataHomeComingSoonResponse.getBottomPrice()));
        holder.tvBottomPrice.setText("Rp "+ setCurrencyFormat(dataHomeComingSoonResponse.getBottomPrice()));
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

        if(dataHomeComingSoonResponse.getIsFavorite()==null){
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
        }else {
            if(dataHomeComingSoonResponse.getIsFavorite().equals("1")){
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
            }else {
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
            }
        }

        String startDate = convertDate(timeServer,"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
        String endDate   = convertDate(dataHomeComingSoonResponse.getEndDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");

//        String startDate = convertDate(convertDateServer(timeServer),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
//        String endDate   = convertDate(dataHomeComingSoonResponse.getStartDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
//        System.out.println("-- END DATE VEHICLE     : "+ convertDate(dataHomeComingSoonResponse.getStartDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss"));
//        System.out.println("-- TIME SERVER BEFORE   : "+ timeServer);
//        System.out.println("-- TIME SERVER AFTER    : "+ convertDate(convertDateServer(timeServer),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss"));
//        System.out.println("Start DATE : "+ startDate);
//        System.out.println("End DATE   : "+ endDate);
        startTimer(holder.tvTimer, calculateDate(startDate,endDate));

//        startTimer(holder.tvTimer, calculateDate(timeServer,dataHomeComingSoonResponse.getStartDate()));

        AtomicBoolean favorite = new AtomicBoolean(false);

        holder.ivFavorite.setOnClickListener(view -> {
            if (favorite.get()) {
                favorite.set(false);
                setAndUnsetFavorite(contexts, holder.ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeComingSoonResponse.getKik(), dataHomeComingSoonResponse.getAgreementNo(), String.valueOf(dataHomeComingSoonResponse.getOpenHouseId()),"1");
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
            } else {
                favorite.set(true);
                holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
                setAndUnsetFavorite(contexts, holder.ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeComingSoonResponse.getKik(), dataHomeComingSoonResponse.getAgreementNo(), String.valueOf(dataHomeComingSoonResponse.getOpenHouseId()),"1");
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
//                int seconds = (int) (millisUntilFinished / 1000);
//                int hours = seconds / (60 * 60);
//                int tempMint = (seconds - (hours * 60 * 60));
//                int minutes = tempMint / 60;
//                seconds = tempMint - (minutes * 60);
//                tvTimer.setText(String.format("%02d", hours) + "h " +
//                        String.format("%02d", minutes) + "m " +
//                        String.format("%02d", seconds) + "s ");
                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                tvTimer.setText(days + " Hari " + hours + " Jam " + minutes + " Menit " + seconds+" Detik");
            }
            @SuppressLint("SetTextI18n")
            public void onFinish() {
                tvTimer.setText("Waktu Penawaran Habis");
            }
        }.start();
    }

    public void setAndUnsetFavorite(Context contexts, ImageView ivFavorite, String userId, String kik, String agreementNo, String openHouseId, String isFavorit){
        GrosirMobilPreference grosirMobilPreference = new GrosirMobilPreference(contexts);
        GrosirMobilFunction grosirMobilFunction = new GrosirMobilFunction(contexts);
        FavoriteRequest favoriteRequest = new FavoriteRequest(userId, kik, agreementNo, openHouseId, Integer.parseInt(isFavorit));
        final Call<GeneralResponse> timeServerApi = getApiGrosirMobil().setAndUnsetFavoriteApi(BEARER+" "+grosirMobilPreference.getToken(),favoriteRequest);
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

}
