package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicleLiveGarage;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.request.negonbuynow.NegoAndBuyNowRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.cart.DataCartResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.calculateDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;


public class LiveGarageAdapter extends RecyclerView.Adapter<ViewHolderItemVehicleLiveGarage> {

    private final List<DataCartResponse> dataCartResponseList;
    private final Context contexts;

    private long lastPrice = 0;
    private final boolean first;
    private final String timeServer;
    private final GrosirMobilFunction grosirMobilFunction;
    private final GrosirMobilPreference grosirMobilPreference;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(DataCartResponse dataCartResponse);
    }


    public LiveGarageAdapter(boolean first, Context context, String timeServer, List<DataCartResponse> dataCartResponses, OnItemClickListener onItemClickListener) {
        this.first = first;
        this.contexts = context;
        this.timeServer = timeServer;
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
        try {
            DataCartResponse dataCartResponse = dataCartResponseList.get(position);
            holder.tvVehicleName.setText(dataCartResponse.getVehicleName());
            holder.tvPlatNumber.setText(dataCartResponse.getKik().substring(0, 10) + " - ");
            holder.btnBuyNow.setText(contexts.getString(R.string.btn_buy_now)+ " Rp "+setCurrencyFormat(String.valueOf(dataCartResponse.getOpenPrice())));
            holder.tvInitialName.setText(dataCartResponse.getGrade());
            if(dataCartResponse.getUserTertinggi()==null){}
            else {
                holder.tvPenawaranAnda.setText("Rp "+setCurrencyFormat(dataCartResponse.getUserTertinggi()));
            }

            if (dataCartResponse.getTertinggi() == null) {}
            else {
                holder.tvPenawaranTerakhir.setText("Rp "+setCurrencyFormat(dataCartResponse.getTertinggi()));
            }

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
//                Intent intent = new Intent(contexts, VehicleDetailActivity.class);
//                intent.putExtra(ID_VEHICLE, String.valueOf(dataCartResponse.getOhid()));
//                intent.putExtra(KIK, dataCartResponse.getKik());
//                intent.putExtra(FROM_PAGE, "LIVE");
//                contexts.startActivity(intent);
            });

            if(dataCartResponse.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
                lastPrice = Long.parseLong(dataCartResponse.getTertinggi());
            }
            if(first){
                if(dataCartResponse.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
//                    holder.tvPrice.setText("Rp "+setCurrencyFormat(dataCartResponse.getNego()));
                    if(grosirMobilPreference.getDataCartLive().get(position).getNego()==null){
                        List<DataCartResponse> dataCartResponseList1 = new ArrayList<>();
                        for(int i=0;i<dataCartResponseList.size();i++){
                            DataCartResponse dataCartResponse1 =  new DataCartResponse(dataCartResponseList.get(i).getUserIdGrosir(),
                                    dataCartResponseList.get(i).getUserIdWin(),
                                    dataCartResponseList.get(i).getOhid(),
                                    dataCartResponseList.get(i).getAgreementNo(),
                                    dataCartResponseList.get(i).getStart_Date(),
                                    dataCartResponseList.get(i).getEndDate(),
                                    dataCartResponseList.get(i).getKik(),
                                    dataCartResponseList.get(i).getVehicleName(),
                                    dataCartResponseList.get(i).getNego(),
                                    dataCartResponseList.get(i).getTertinggi(),
                                    dataCartResponseList.get(i).getUserTertinggi(),
                                    dataCartResponseList.get(i).getIsKeranjang(),
                                    dataCartResponseList.get(i).getIsWinner(),
                                    dataCartResponseList.get(i).getUserWin(),
                                    dataCartResponseList.get(i).getBottomPrice(),
                                    dataCartResponseList.get(i).getOpenPrice(),
                                    dataCartResponseList.get(i).getGrade(),
                                    dataCartResponseList.get(i).getIsLive(),
                                    dataCartResponseList.get(i).getCategoryName(),
                                    dataCartResponseList.get(i).getIsBlock(),
                                    dataCartResponseList.get(i).getFoto(),
                                    dataCartResponseList.get(i).getStatus());
                            dataCartResponseList1.add(dataCartResponse1);
                        }
                        grosirMobilPreference.saveDataCartLive(dataCartResponseList1);
                    }
//                    negoPrice = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getNego());
                }
            }
            else {
                if(dataCartResponse.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
                    holder.tvPrice.setText("Rp "+setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getNego()));
                }
//                if(dataCartResponse.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
//                    if(grosirMobilPreference.getDataCartLive().get(position).getNego()==null){
////                        negoPrice = Long.parseLong(dataCartResponse.getNego());
//                    }else {
////                        negoPrice = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getNego());
//                    }
//                }
            }
            holder.ivMin.setOnClickListener(view -> {
                if(dataCartResponse.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
                    long negoPrice = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getNego());
                    if(negoPrice<=lastPrice){
                        Toast.makeText(contexts, "Minimum Tawar Harus Lebih Besar dari Penawaran Terakhir", Toast.LENGTH_SHORT).show();
                    }else {
                        negoPrice = negoPrice-500000;
                        if(dataCartResponse.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
                            List<DataCartResponse> dataCartResponseList1 = new ArrayList<>();
                            for(int i=0;i<dataCartResponseList.size();i++){
                                DataCartResponse dataCartResponse1 =  new DataCartResponse();
                                dataCartResponse1.setUserIdGrosir(dataCartResponseList.get(i).getUserIdGrosir());
                                dataCartResponse1.setUserIdWin(dataCartResponseList.get(i).getUserIdWin());
                                dataCartResponse1.setOhid(dataCartResponseList.get(i).getOhid());
                                dataCartResponse1.setAgreementNo(dataCartResponseList.get(i).getAgreementNo());
                                dataCartResponse1.setStart_Date(dataCartResponseList.get(i).getStart_Date());
                                dataCartResponse1.setEndDate(dataCartResponseList.get(i).getEndDate());
                                dataCartResponse1.setKik(dataCartResponseList.get(i).getKik());
                                dataCartResponse1.setVehicleName(dataCartResponseList.get(i).getVehicleName());
                                if(dataCartResponse1.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
                                    dataCartResponse1.setNego(String.valueOf(negoPrice));
                                }
                                else {
                                    dataCartResponse1.setNego(dataCartResponseList.get(i).getNego());
                                }
                                dataCartResponse1.setTertinggi(dataCartResponseList.get(i).getTertinggi());
                                dataCartResponse1.setUserTertinggi(dataCartResponseList.get(i).getUserTertinggi());
                                dataCartResponse1.setIsKeranjang(dataCartResponseList.get(i).getIsKeranjang());
                                dataCartResponse1.setIsWinner(dataCartResponseList.get(i).getIsWinner());
                                dataCartResponse1.setUserWin(dataCartResponseList.get(i).getUserWin());
                                dataCartResponse1.setBottomPrice(dataCartResponseList.get(i).getBottomPrice());
                                dataCartResponse1.setOpenPrice(dataCartResponseList.get(i).getOpenPrice());
                                dataCartResponse1.setGrade(dataCartResponseList.get(i).getGrade());
                                dataCartResponse1.setIsLive(dataCartResponseList.get(i).getIsLive());
                                dataCartResponse1.setCategoryName(dataCartResponseList.get(i).getCategoryName());
                                dataCartResponse1.setIsBlock(dataCartResponseList.get(i).getIsBlock());
                                dataCartResponse1.setFoto(dataCartResponseList.get(i).getFoto());
                                dataCartResponse1.setStatus(dataCartResponseList.get(i).getStatus());

                                dataCartResponseList1.add(dataCartResponse1);
                            }
                            grosirMobilPreference.saveDataCartLive(dataCartResponseList1);
//                            holder.tvPrice.setText("Rp "+setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getNego()));
                        }
                    }
                }
            });

            holder.ivPlus.setOnClickListener(view -> {
                if(dataCartResponse.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
                    long negoPrice = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getNego());
                    if(negoPrice>=grosirMobilPreference.getDataCartLive().get(position).getOpenPrice()){}
                    else {
                        negoPrice = negoPrice + 500000;
                    }
                    List<DataCartResponse> dataCartResponseList1 = new ArrayList<>();
                    for(int i=0;i<dataCartResponseList.size();i++){
//                        DataCartResponse dataCartResponse1 =  new DataCartResponse(
//                                dataCartResponseList.get(i).getUserIdGrosir(),
//                                dataCartResponseList.get(i).getUserIdWin(),
//                                dataCartResponseList.get(i).getOhid(),
//                                dataCartResponseList.get(i).getAgreementNo(),
//                                dataCartResponseList.get(i).getStart_Date(),
//                                dataCartResponseList.get(i).getEndDate(),
//                                dataCartResponseList.get(i).getKik(),
//                                dataCartResponseList.get(i).getVehicleName(),
//                                String.valueOf(negoPrice),
//                                dataCartResponseList.get(i).getTertinggi(),
//                                dataCartResponseList.get(i).getUserTertinggi(),
//                                dataCartResponseList.get(i).getIsKeranjang(),
//                                dataCartResponseList.get(i).getIsWinner(),
//                                dataCartResponseList.get(i).getUserWin(),
//                                dataCartResponseList.get(i).getBottomPrice(),
//                                dataCartResponseList.get(i).getOpenPrice(),
//                                dataCartResponseList.get(i).getGrade(),
//                                dataCartResponseList.get(i).getIsLive(),
//                                dataCartResponseList.get(i).getCategoryName(),
//                                dataCartResponseList.get(i).getIsBlock(),
//                                dataCartResponseList.get(i).getFoto(),
//                                dataCartResponseList.get(i).getStatus());

                        DataCartResponse dataCartResponse1 =  new DataCartResponse();
                        dataCartResponse1.setUserIdGrosir(dataCartResponseList.get(i).getUserIdGrosir());
                        dataCartResponse1.setUserIdWin(dataCartResponseList.get(i).getUserIdWin());
                        dataCartResponse1.setOhid(dataCartResponseList.get(i).getOhid());
                        dataCartResponse1.setAgreementNo(dataCartResponseList.get(i).getAgreementNo());
                        dataCartResponse1.setStart_Date(dataCartResponseList.get(i).getStart_Date());
                        dataCartResponse1.setEndDate(dataCartResponseList.get(i).getEndDate());
                        dataCartResponse1.setKik(dataCartResponseList.get(i).getKik());
                        dataCartResponse1.setVehicleName(dataCartResponseList.get(i).getVehicleName());
                        if(dataCartResponse1.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
                            dataCartResponse1.setNego(String.valueOf(negoPrice));
                        }
                        else {
                            dataCartResponse1.setNego(dataCartResponseList.get(i).getNego());
                        }
                        dataCartResponse1.setTertinggi(dataCartResponseList.get(i).getTertinggi());
                        dataCartResponse1.setUserTertinggi(dataCartResponseList.get(i).getUserTertinggi());
                        dataCartResponse1.setIsKeranjang(dataCartResponseList.get(i).getIsKeranjang());
                        dataCartResponse1.setIsWinner(dataCartResponseList.get(i).getIsWinner());
                        dataCartResponse1.setUserWin(dataCartResponseList.get(i).getUserWin());
                        dataCartResponse1.setBottomPrice(dataCartResponseList.get(i).getBottomPrice());
                        dataCartResponse1.setOpenPrice(dataCartResponseList.get(i).getOpenPrice());
                        dataCartResponse1.setGrade(dataCartResponseList.get(i).getGrade());
                        dataCartResponse1.setIsLive(dataCartResponseList.get(i).getIsLive());
                        dataCartResponse1.setCategoryName(dataCartResponseList.get(i).getCategoryName());
                        dataCartResponse1.setIsBlock(dataCartResponseList.get(i).getIsBlock());
                        dataCartResponse1.setFoto(dataCartResponseList.get(i).getFoto());
                        dataCartResponse1.setStatus(dataCartResponseList.get(i).getStatus());

                        System.out.println("PRICE : : "+ dataCartResponse1.getOhid());
                        System.out.println("PRICE : : "+ dataCartResponseList.get(i).getNego());
                        System.out.println("se : : "+ negoPrice);
                        dataCartResponseList1.add(dataCartResponse1);
                    }
                    grosirMobilPreference.saveDataCartLive(dataCartResponseList1);
//                    holder.tvPrice.setText("Rp "+setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getNego()));
                }

                for(int i=0;i<grosirMobilPreference.getDataCartLive().size();i++){
                    System.out.println("XXXX OHID SAVE : "+grosirMobilPreference.getDataCartLive().get(i).getOhid());
                    System.out.println("XXXX NEGO SAVE : "+grosirMobilPreference.getDataCartLive().get(i).getNego());
                    System.out.println("XXXX NAME SAVE : "+grosirMobilPreference.getDataCartLive().get(i).getVehicleName());
                }
//                System.out.println("XXXX ========================================================================");
//                for(int i=0;i<dataCartResponseList.size();i++){
//                    System.out.println("XXXX OHID DATA : "+dataCartResponseList.get(i).getOhid());
//                    System.out.println("XXXX NEGO DATA : "+dataCartResponseList.get(i).getNego());
//                    System.out.println("XXXX NAME DATA : "+dataCartResponseList.get(i).getVehicleName());
//                }
            });

            holder.tvPrice.setText("Rp "+setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getNego()));
            holder.btnNego.setOnClickListener(view -> {
                if(dataCartResponse.getTertinggi()==null){
                    long highPriceNego = Long.parseLong(dataCartResponse.getBottomPrice());
                    NegoAndBuyNowRequest negoAndBuyNowRequest = new NegoAndBuyNowRequest(String.valueOf(dataCartResponse.getOhid()), dataCartResponse.getKik(), dataCartResponse.getAgreementNo().trim(), String.valueOf(highPriceNego));
                    liveNegoApi(negoAndBuyNowRequest);
                }else {
                    long highPriceNego = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getNego());
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
//                if(dataCartResponse.getUserTertinggi()==null){
//                    negoPrice = 0;
//                    holder.tvPrice.setText("Rp 0");
//                }else {
//                    negoPrice = Long.parseLong(dataCartResponse.getUserTertinggi());
//                    holder.tvPrice.setText("Rp "+setCurrencyFormat(dataCartResponse.getUserTertinggi()));
//                }
            });

        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataCartResponseList.size();
    }

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
                            notifyDataSetChanged();
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

}
