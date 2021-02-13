package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.request.negonbuynow.NegoAndBuyNowRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.cart.DataCartResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


public class LiveGarageAdapter extends RecyclerView.Adapter<ViewHolderItemVehicleLiveGarage> {

    private final List<DataCartResponse> dataCartResponseList;
    private final Context contexts;
    private long negoPrice = 0;
    private long lastPrice = 0;
    private final String timeServer;
    private String loadingShow;
    private GrosirMobilFunction grosirMobilFunction;
    private GrosirMobilPreference grosirMobilPreference;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(DataCartResponse dataCartResponse);
    }


    public LiveGarageAdapter(Context context, String timeServer, String loadingShow, List<DataCartResponse> dataCartResponses, OnItemClickListener onItemClickListener) {
        this.contexts = context;
        this.timeServer = timeServer;
        this.loadingShow = loadingShow;
        this.dataCartResponseList = dataCartResponses;
        this.onItemClickListener = onItemClickListener;
        grosirMobilFunction = new GrosirMobilFunction(context);
        grosirMobilPreference = new GrosirMobilPreference(context);
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
        holder.btnBuyNow.setText(contexts.getString(R.string.btn_buy_now)+ " Rp "+setCurrencyFormat(String.valueOf(dataCartResponse.getOpenPrice())));
        holder.tvInitialName.setText(dataCartResponse.getGrade());
        holder.tvPenawaranAnda.setText("Rp "+setCurrencyFormat(dataCartResponse.getUserTertinggi()));
        holder.tvPenawaranTerakhir.setText("Rp "+setCurrencyFormat(dataCartResponse.getTertinggi()));

        String startDate = convertDate(timeServer,"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
        String endDate   = convertDate(dataCartResponse.getEndDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
        startTimer(holder.tvTimer, calculateDate(startDate,endDate));

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

//        if(dataCartResponse.getUserTertinggi()==null){
//            holder.tvPenawaranAnda.setText("Rp 0");
////            lastPrice = 0;
//            negoPrice = 0;
//        }
//        else {
//
//        }
//        holder.setIsRecyclable(false);
        dataCartResponse.setNego(dataCartResponse.getTertinggi());
        lastPrice = Long.parseLong(dataCartResponse.getTertinggi());

        if(grosirMobilPreference.getBidPrice(String.valueOf(position))==null){
            grosirMobilPreference.saveBidPrice(dataCartResponse.getTertinggi(), String.valueOf(position));
        }
//        if(dataCartResponse.getTertinggi()==null){
////            holder.tvPenawaranTerakhir.setText("Rp 0");
//            long highPrice = Long.parseLong(dataCartResponse.getBottomPrice());
////            highPrice = highPrice+500000;
//            holder.tvPrice.setText("Rp "+setCurrencyFormat(String.valueOf(highPrice)));
//        }
//        else {
//
//        }
//        lastPrice = Long.parseLong(dataCartResponse.getTertinggi());
//        if(dataCartResponse.getTertinggi())
        negoPrice = Long.parseLong(grosirMobilPreference.getBidPrice(String.valueOf(position)));
        holder.tvPrice.setText("Rp "+setCurrencyFormat(grosirMobilPreference.getBidPrice(String.valueOf(position))));
        holder.ivMin.setOnClickListener(view -> {
            if(negoPrice<=lastPrice){
                Toast.makeText(contexts, "Minimum Tawar Harus Lebih Besar dari Penawaran Terakhir", Toast.LENGTH_SHORT).show();
            }else {
                negoPrice = negoPrice-500000;
//                dataCartResponse.setNego(String.valueOf(negoPrice));
                grosirMobilPreference.saveBidPrice(String.valueOf(negoPrice), String.valueOf(position));
                holder.tvPrice.setText("Rp "+setCurrencyFormat(grosirMobilPreference.getBidPrice(String.valueOf(position))));
            }
        });

        holder.ivPlus.setOnClickListener(view -> {
            if(negoPrice>=dataCartResponse.getOpenPrice()){

            }else {
                negoPrice = negoPrice+500000;
            }
//            dataCartResponse.setNego(String.valueOf(negoPrice));
            grosirMobilPreference.saveBidPrice(String.valueOf(negoPrice), String.valueOf(position));
            holder.tvPrice.setText("Rp "+setCurrencyFormat(grosirMobilPreference.getBidPrice(String.valueOf(position))));
        });

        holder.btnNego.setOnClickListener(view -> {
            if(dataCartResponse.getTertinggi()==null){
                long highPriceNego = Long.parseLong(dataCartResponse.getBottomPrice());
                NegoAndBuyNowRequest negoAndBuyNowRequest = new NegoAndBuyNowRequest(String.valueOf(dataCartResponse.getOhid()), dataCartResponse.getKik(), dataCartResponse.getAgreementNo().trim(), String.valueOf(highPriceNego));
                liveNegoApi(negoAndBuyNowRequest);
            }else {
                long highPriceNego = Long.parseLong(grosirMobilPreference.getBidPrice(String.valueOf(position)));
//                highPriceNego = highPriceNego+500000;
                System.out.println("Test NEGO : " + highPriceNego);
                NegoAndBuyNowRequest negoAndBuyNowRequest = new NegoAndBuyNowRequest(String.valueOf(dataCartResponse.getOhid()), dataCartResponse.getKik(), dataCartResponse.getAgreementNo().trim(), String.valueOf(highPriceNego));
                liveNegoApi(negoAndBuyNowRequest);
            }
            holder.relativeSuccessNego.setVisibility(View.VISIBLE);
        });

        holder.btnBuyNow.setOnClickListener(view -> {
            onItemClickListener.onItemClick(dataCartResponse);
        });
        holder.ivClearPrice.setOnClickListener(view -> {
            if(dataCartResponse.getUserTertinggi()==null){
                negoPrice = 0;
                holder.tvPrice.setText("Rp 0");
            }else {
                negoPrice = Long.parseLong(dataCartResponse.getUserTertinggi());
                holder.tvPrice.setText("Rp "+setCurrencyFormat(dataCartResponse.getUserTertinggi()));
            }
        });


    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    //    public void liveBuyNowApi(NegoAndBuyNowRequest negoAndBuyNowRequest) {
//        ProgressDialog progressDialog = new ProgressDialog(contexts);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage(contexts.getString(R.string.base_tv_please_wait));
//        progressDialog.show();
//        final Call<GeneralResponse> vehicleDetailApi = getApiGrosirMobil().liveBuyNowApi(BEARER+" "+grosirMobilPreference.getToken(),negoAndBuyNowRequest);
//        vehicleDetailApi.enqueue(new Callback<GeneralResponse>() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
//                progressDialog.dismiss();
//                if (response.isSuccessful()) {
//                    try {
//                        if(response.body().getMessage().equals("success")){
//                            Intent intent = new Intent(contexts, MainActivity.class);
//                            intent.putExtra(REQUEST_MAIN, "win");
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            contexts.startActivity(intent);
////                            finish();
//                        }else {
//                            grosirMobilFunction.showMessage(contexts, "POST Live Buy Now", response.body().getMessage());
//                        }
//                    }catch (Exception e){
//                        GrosirMobilLog.printStackTrace(e);
//                    }
//                }else {
//                    try {
//                        grosirMobilFunction.showMessage(contexts, contexts.getString(R.string.base_null_error_title), response.errorBody().string());
//                    } catch (IOException e) {
//                        GrosirMobilLog.printStackTrace(e);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GeneralResponse> call, Throwable t) {
//                progressDialog.dismiss();
//                grosirMobilFunction.showMessage(contexts, "POST Live Buy Now", contexts.getString(R.string.base_null_server));
//                GrosirMobilLog.printStackTrace(t);
//            }
//        });
//    }

    public void liveNegoApi(NegoAndBuyNowRequest negoAndBuyNowRequest) {
        ProgressDialog progressDialog = new ProgressDialog(contexts);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(contexts.getString(R.string.base_tv_please_wait));
        progressDialog.show();
        final Call<GeneralResponse> vehicleDetailApi = getApiGrosirMobil().liveNegoApi(BEARER+" "+grosirMobilPreference.getToken(),negoAndBuyNowRequest);
        vehicleDetailApi.enqueue(new Callback<GeneralResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){

                        }else {
                            grosirMobilFunction.showMessage(contexts, "POST Live Nego", response.body().getMessage());
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
                progressDialog.dismiss();
                grosirMobilFunction.showMessage(contexts, "POST Live Nego", contexts.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    public void startTimer(TextView tvTimer, long noOfMinutes) {
        new CountDownTimer(noOfMinutes,  1000) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            public void onTick(long millisUntilFinished) {
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

    @Override
    public int getItemCount() {
        return dataCartResponseList.size();
    }

}
