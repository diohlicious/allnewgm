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
import com.sip.grosirmobil.base.contract.GrosirMobilContract;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.request.negonbuynow.NegoAndBuyNowRequest;
import com.sip.grosirmobil.cloud.config.response.cart.DataCartResponse;
import com.sip.grosirmobil.cloud.config.response.nego.GeneralNegoAndBuyNowResponse;

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
    private final OnItemClickListener onItemClickListenerTawar;
    private CountDownTimer countDownTimer = null;

    public interface OnItemClickListener {
        void onItemClick(DataCartResponse dataCartResponse);
    }


    public LiveGarageAdapter(boolean first, Context context, String timeServer, List<DataCartResponse> dataCartResponses, OnItemClickListener onItemClickListener, OnItemClickListener onItemClickListenerTawar) {
        this.first = first;
        this.contexts = context;
        this.timeServer = timeServer;
        this.dataCartResponseList = dataCartResponses;
        this.onItemClickListener = onItemClickListener;
        this.onItemClickListenerTawar = onItemClickListenerTawar;
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
            if (dataCartResponse.getDataOtoJsonResponse() == null) {
            } else {
                if (dataCartResponse.getDataOtoJsonResponse().getLokasi() == null || dataCartResponse.getDataOtoJsonResponse().getLokasi().equals("")) {

                } else {
//                    holder.tvPlatNumber.setText(dataCartResponse.getKik().substring(0, 10) + " - "+dataCartResponse.getDataOtoJsonResponse().getLokasi().replace("WAREHOUSE ", ""));
                }
            }
            holder.tvPlatNumber.setText(dataCartResponse.getKik().substring(0, 10) + " - " + dataCartResponse.getDataOtoJsonResponse().getLokasi().replace("WAREHOUSE ", ""));
            holder.btnBuyNow.setText(contexts.getString(R.string.btn_buy_now) + " Rp " + setCurrencyFormat(String.valueOf(dataCartResponse.getOpenPrice())));
            holder.tvInitialName.setText(dataCartResponse.getGrade());
            holder.tvAdmin.setText("Rp " + setCurrencyFormat(String.valueOf(dataCartResponse.getAdminfee())));
            if (dataCartResponse.getUserTertinggi() == null) {
                holder.tvPenawaranAndaKet.setText("Harga Awal");
                holder.tvPenawaranAnda.setText("Rp " + setCurrencyFormat(dataCartResponse.getBottomPrice()));
            } else {
                holder.tvPenawaranAndaKet.setText(contexts.getString(R.string.tv_penawaran_anda));
                holder.tvPenawaranAnda.setText("Rp " + setCurrencyFormat(dataCartResponse.getUserTertinggi()));
            }

            if (dataCartResponse.getTertinggi() == null) {
                holder.tvPenawaranTerakhir.setText("Rp " + setCurrencyFormat(dataCartResponse.getBottomPrice()));
            } else {
                holder.tvPenawaranTerakhir.setText("Rp " + setCurrencyFormat(dataCartResponse.getTertinggi()));
            }


            String startDate = convertDate(timeServer, "yyyy-MM-dd HH:mm:ss", "dd-MM-yyyy HH:mm:ss");
            String endDate = convertDate(dataCartResponse.getEndDate(), "yyyy-MM-dd HH:mm:ss", "dd-MM-yyyy HH:mm:ss");
            startTimer(holder.tvTimer, calculateDate(startDate, endDate));

            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(contexts);
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
               /* Intent intent = new Intent(contexts, VehicleDetailActivity.class);
                intent.putExtra(GrosirMobilContract.ID_VEHICLE, String.valueOf(dataCartResponse.getOhid()));
                intent.putExtra(GrosirMobilContract.KIK, dataCartResponse.getKik());
                intent.putExtra(GrosirMobilContract.FROM_PAGE, "LIVE");
                contexts.startActivity(intent);*/
            });

            if (dataCartResponse.getOhid() == grosirMobilPreference.getDataCartLive().get(position).getOhid()) {
                if (dataCartResponse.getTertinggi() == null) {
                    lastPrice = Long.parseLong(dataCartResponse.getBottomPrice());
                } else {
                    lastPrice = Long.parseLong(dataCartResponse.getTertinggi());
                }
            }
            if (first) {
                if (dataCartResponse.getOhid() == grosirMobilPreference.getDataCartLive().get(position).getOhid()) {
//                    holder.tvPrice.setText("Rp "+setCurrencyFormat(dataCartResponse.getNego()));
                    if (grosirMobilPreference.getDataCartLive().get(position).getNego() == null || grosirMobilPreference.getDataCartLive().get(position).getNego().equals("")) {
                        List<DataCartResponse> dataCartResponseList1 = new ArrayList<>();
                        for (int i = 0; i < dataCartResponseList.size(); i++) {
                            DataCartResponse dataCartResponse1 = new DataCartResponse(dataCartResponseList.get(i).getUserIdGrosir(),
                                    dataCartResponseList.get(i).getUserIdWin(),
                                    dataCartResponseList.get(i).getOhid(),
                                    dataCartResponseList.get(i).getAgreementNo(),
                                    dataCartResponseList.get(i).getStart_Date(),
                                    dataCartResponseList.get(i).getEndDate(),
                                    dataCartResponseList.get(i).getKik(),
                                    dataCartResponseList.get(i).getVehicleName(),
//                                  dataCartResponseList.get(i).getBottomPrice(),
                                    dataCartResponseList.get(i).getNego(),
                                    dataCartResponseList.get(i).getTertinggi(),
                                    dataCartResponseList.get(i).getUserTertinggi(),
                                    dataCartResponseList.get(i).getIsKeranjang(),
                                    dataCartResponseList.get(i).getIsWinner(),
                                    dataCartResponseList.get(i).getUserWin(),
                                    dataCartResponseList.get(i).getBottomPrice(),
                                    dataCartResponseList.get(i).getAdminfee(),
                                    dataCartResponseList.get(i).getTotalbayar(),
                                    dataCartResponseList.get(i).getOpenPrice(),
                                    dataCartResponseList.get(i).getPriceNow(),
                                    dataCartResponseList.get(i).getGrade(),
                                    dataCartResponseList.get(i).getIsLive(),
                                    dataCartResponseList.get(i).getCategoryName(),
                                    dataCartResponseList.get(i).getIsBlock(),
                                    dataCartResponseList.get(i).getFoto(),
                                    dataCartResponseList.get(i).getStatus(),
                                    dataCartResponseList.get(i).getDataOtoJsonResponse());
                            dataCartResponseList1.add(dataCartResponse1);
                        }
                        grosirMobilPreference.saveDataCartLive(dataCartResponseList1);

//                        for(int i=0;i<dataCartResponseList.size();i++){
//                            DataCartResponse dataCartResponse1 =  new DataCartResponse();
//                            dataCartResponse1.setUserIdGrosir(dataCartResponseList.get(i).getUserIdGrosir());
//                            dataCartResponse1.setUserIdWin(dataCartResponseList.get(i).getUserIdWin());
//                            dataCartResponse1.setOhid(dataCartResponseList.get(i).getOhid());
//                            dataCartResponse1.setAgreementNo(dataCartResponseList.get(i).getAgreementNo());
//                            dataCartResponse1.setStart_Date(dataCartResponseList.get(i).getStart_Date());
//                            dataCartResponse1.setEndDate(dataCartResponseList.get(i).getEndDate());
//                            dataCartResponse1.setKik(dataCartResponseList.get(i).getKik());
//                            dataCartResponse1.setVehicleName(dataCartResponseList.get(i).getVehicleName());
//                            if(dataCartResponse1.getOhid()==grosirMobilPreference.getDataCartLive().get(position).getOhid()){
//                                dataCartResponse1.setNego(String.valueOf(negoPrice));
//                            }
//                            else {
//                                dataCartResponse1.setNego(dataCartResponseList.get(i).getNego());
//                            }
//                            dataCartResponse1.setTertinggi(dataCartResponseList.get(i).getTertinggi());
//                            dataCartResponse1.setUserTertinggi(dataCartResponseList.get(i).getUserTertinggi());
//                            dataCartResponse1.setIsKeranjang(dataCartResponseList.get(i).getIsKeranjang());
//                            dataCartResponse1.setIsWinner(dataCartResponseList.get(i).getIsWinner());
//                            dataCartResponse1.setUserWin(dataCartResponseList.get(i).getUserWin());
//                            dataCartResponse1.setBottomPrice(dataCartResponseList.get(i).getBottomPrice());
//                            dataCartResponse1.setOpenPrice(dataCartResponseList.get(i).getOpenPrice());
//                            dataCartResponse1.setGrade(dataCartResponseList.get(i).getGrade());
//                            dataCartResponse1.setIsLive(dataCartResponseList.get(i).getIsLive());
//                            dataCartResponse1.setCategoryName(dataCartResponseList.get(i).getCategoryName());
//                            dataCartResponse1.setIsBlock(dataCartResponseList.get(i).getIsBlock());
//                            dataCartResponse1.setFoto(dataCartResponseList.get(i).getFoto());
//                            dataCartResponse1.setStatus(dataCartResponseList.get(i).getStatus());
//
//                            dataCartResponseList1.add(dataCartResponse1);
//                        }
//                        grosirMobilPreference.saveDataCartLive(dataCartResponseList1);
                    }
//                    negoPrice = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getNego());
                }
            } else {
                if (dataCartResponse.getOhid() == grosirMobilPreference.getDataCartLive().get(position).getOhid()) {
                    if (grosirMobilPreference.getDataCartLive().get(position).getNego() == null || grosirMobilPreference.getDataCartLive().get(position).getNego().equals("")) {
                        holder.tvPrice.setText("Rp " + setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getBottomPrice()));
                    } else {
                        holder.tvPrice.setText("Rp " + setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getNego()));
                    }
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
                if (dataCartResponse.getOhid() == grosirMobilPreference.getDataCartLive().get(position).getOhid()) {
                    long negoPrice;
                    if (grosirMobilPreference.getDataCartLive().get(position).getNego() == null || grosirMobilPreference.getDataCartLive().get(position).getNego().equals("")) {
                        negoPrice = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getBottomPrice());
                    } else {
                        negoPrice = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getNego());
                    }
                    if (negoPrice <= lastPrice) {
                        Toast.makeText(contexts, "Minimum Tawar Harus Lebih Besar dari Penawaran Terakhir", Toast.LENGTH_SHORT).show();
                    } else {
                        negoPrice = negoPrice - 500000;
                        if (dataCartResponse.getOhid() == grosirMobilPreference.getDataCartLive().get(position).getOhid()) {
                            List<DataCartResponse> dataCartResponseList1 = new ArrayList<>();
                            for (int i = 0; i < dataCartResponseList.size(); i++) {
                                DataCartResponse dataCartResponse1 = new DataCartResponse();
                                dataCartResponse1.setUserIdGrosir(dataCartResponseList.get(i).getUserIdGrosir());
                                dataCartResponse1.setUserIdWin(dataCartResponseList.get(i).getUserIdWin());
                                dataCartResponse1.setOhid(dataCartResponseList.get(i).getOhid());
                                dataCartResponse1.setAgreementNo(dataCartResponseList.get(i).getAgreementNo());
                                dataCartResponse1.setStart_Date(dataCartResponseList.get(i).getStart_Date());
                                dataCartResponse1.setEndDate(dataCartResponseList.get(i).getEndDate());
                                dataCartResponse1.setKik(dataCartResponseList.get(i).getKik());
                                dataCartResponse1.setVehicleName(dataCartResponseList.get(i).getVehicleName());
                                if (dataCartResponse1.getOhid() == grosirMobilPreference.getDataCartLive().get(position).getOhid()) {
                                    dataCartResponse1.setNego(String.valueOf(negoPrice));
                                } else {
                                    dataCartResponse1.setNego(dataCartResponseList.get(i).getNego());
                                }
                                dataCartResponse1.setTertinggi(dataCartResponseList.get(i).getTertinggi());
                                dataCartResponse1.setUserTertinggi(dataCartResponseList.get(i).getUserTertinggi());
                                dataCartResponse1.setIsKeranjang(dataCartResponseList.get(i).getIsKeranjang());
                                dataCartResponse1.setIsWinner(dataCartResponseList.get(i).getIsWinner());
                                dataCartResponse1.setUserWin(dataCartResponseList.get(i).getUserWin());
                                dataCartResponse1.setBottomPrice(dataCartResponseList.get(i).getBottomPrice());
                                dataCartResponse1.setAdminfee(dataCartResponseList.get(i).getAdminfee());
                                dataCartResponse1.setTotalbayar(dataCartResponseList.get(i).getTotalbayar());
                                dataCartResponse1.setOpenPrice(dataCartResponseList.get(i).getOpenPrice());
                                dataCartResponse1.setPriceNow(dataCartResponseList.get(i).getPriceNow());
                                dataCartResponse1.setGrade(dataCartResponseList.get(i).getGrade());
                                dataCartResponse1.setIsLive(dataCartResponseList.get(i).getIsLive());
                                dataCartResponse1.setCategoryName(dataCartResponseList.get(i).getCategoryName());
                                dataCartResponse1.setIsBlock(dataCartResponseList.get(i).getIsBlock());
                                dataCartResponse1.setFoto(dataCartResponseList.get(i).getFoto());
                                dataCartResponse1.setStatus(dataCartResponseList.get(i).getStatus());

                                dataCartResponseList1.add(dataCartResponse1);
                            }
                            grosirMobilPreference.saveDataCartLive(dataCartResponseList1);
                        }
                    }
                }
            });

            holder.ivPlus.setOnClickListener(view -> {
                if (dataCartResponse.getOhid() == grosirMobilPreference.getDataCartLive().get(position).getOhid()) {
                    long negoPrice;
                    if (grosirMobilPreference.getDataCartLive().get(position).getNego() == null || grosirMobilPreference.getDataCartLive().get(position).getNego().equals("")) {
                        negoPrice = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getBottomPrice());
                    } else {
                        negoPrice = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getNego());
                    }
                    if (negoPrice >= grosirMobilPreference.getDataCartLive().get(position).getOpenPrice()) {
                    } else {
                        negoPrice = negoPrice + 500000;
                    }
                    List<DataCartResponse> dataCartResponseList1 = new ArrayList<>();
                    for (int i = 0; i < dataCartResponseList.size(); i++) {
                        DataCartResponse dataCartResponse1 = new DataCartResponse();
                        dataCartResponse1.setUserIdGrosir(dataCartResponseList.get(i).getUserIdGrosir());
                        dataCartResponse1.setUserIdWin(dataCartResponseList.get(i).getUserIdWin());
                        dataCartResponse1.setOhid(dataCartResponseList.get(i).getOhid());
                        dataCartResponse1.setAgreementNo(dataCartResponseList.get(i).getAgreementNo());
                        dataCartResponse1.setStart_Date(dataCartResponseList.get(i).getStart_Date());
                        dataCartResponse1.setEndDate(dataCartResponseList.get(i).getEndDate());
                        dataCartResponse1.setKik(dataCartResponseList.get(i).getKik());
                        dataCartResponse1.setVehicleName(dataCartResponseList.get(i).getVehicleName());
                        if (dataCartResponse1.getOhid() == grosirMobilPreference.getDataCartLive().get(position).getOhid()) {
                            dataCartResponse1.setNego(String.valueOf(negoPrice));
                        } else {
                            dataCartResponse1.setNego(dataCartResponseList.get(i).getNego());
                        }
                        dataCartResponse1.setTertinggi(dataCartResponseList.get(i).getTertinggi());
                        dataCartResponse1.setUserTertinggi(dataCartResponseList.get(i).getUserTertinggi());
                        dataCartResponse1.setIsKeranjang(dataCartResponseList.get(i).getIsKeranjang());
                        dataCartResponse1.setIsWinner(dataCartResponseList.get(i).getIsWinner());
                        dataCartResponse1.setUserWin(dataCartResponseList.get(i).getUserWin());
                        dataCartResponse1.setBottomPrice(dataCartResponseList.get(i).getBottomPrice());
                        dataCartResponse1.setAdminfee(dataCartResponseList.get(i).getAdminfee());
                        dataCartResponse1.setTotalbayar(dataCartResponseList.get(i).getTotalbayar());
                        dataCartResponse1.setOpenPrice(dataCartResponseList.get(i).getOpenPrice());
                        dataCartResponse1.setPriceNow(dataCartResponseList.get(i).getPriceNow());
                        dataCartResponse1.setGrade(dataCartResponseList.get(i).getGrade());
                        dataCartResponse1.setIsLive(dataCartResponseList.get(i).getIsLive());
                        dataCartResponse1.setCategoryName(dataCartResponseList.get(i).getCategoryName());
                        dataCartResponse1.setIsBlock(dataCartResponseList.get(i).getIsBlock());
                        dataCartResponse1.setFoto(dataCartResponseList.get(i).getFoto());
                        dataCartResponse1.setStatus(dataCartResponseList.get(i).getStatus());

                        System.out.println("PRICE : : " + dataCartResponse1.getOhid());
                        System.out.println("PRICE : : " + dataCartResponseList.get(i).getNego());
                        System.out.println("se : : " + negoPrice);
                        dataCartResponseList1.add(dataCartResponse1);
                    }
                    grosirMobilPreference.saveDataCartLive(dataCartResponseList1);
//                    holder.tvPrice.setText("Rp "+setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getNego()));
                }

                for (int i = 0; i < grosirMobilPreference.getDataCartLive().size(); i++) {
                    System.out.println("XXXX OHID SAVE : " + grosirMobilPreference.getDataCartLive().get(i).getOhid());
                    System.out.println("XXXX NEGO SAVE : " + grosirMobilPreference.getDataCartLive().get(i).getNego());
                    System.out.println("XXXX NAME SAVE : " + grosirMobilPreference.getDataCartLive().get(i).getVehicleName());
                }
//                System.out.println("XXXX ========================================================================");
//                for(int i=0;i<dataCartResponseList.size();i++){
//                    System.out.println("XXXX OHID DATA : "+dataCartResponseList.get(i).getOhid());
//                    System.out.println("XXXX NEGO DATA : "+dataCartResponseList.get(i).getNego());
//                    System.out.println("XXXX NAME DATA : "+dataCartResponseList.get(i).getVehicleName());
//                }
            });

            if (grosirMobilPreference.getDataCartLive().get(position).getNego() == null || grosirMobilPreference.getDataCartLive().get(position).getNego().equals("")) {
                holder.tvPrice.setText("Rp " + setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getBottomPrice()));
            } else {
                holder.tvPrice.setText("Rp " + setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getNego()));
            }
            holder.btnNego.setOnClickListener(view -> {
                int priceNow = Integer.valueOf(dataCartResponse.getBottomPrice()) + 500000;
                long highPriceNego = Long.valueOf(priceNow);
                long openPrice = Long.valueOf(dataCartResponse.getOpenPrice());
                if (highPriceNego < openPrice) {
                    NegoAndBuyNowRequest negoAndBuyNowRequest = new NegoAndBuyNowRequest(String.valueOf(dataCartResponse.getOhid()), dataCartResponse.getKik(), dataCartResponse.getAgreementNo().trim(), String.valueOf(highPriceNego));
                    liveNegoApi(negoAndBuyNowRequest);
                    Toast.makeText(contexts, "Nego "+highPriceNego, Toast.LENGTH_SHORT).show();
                } else {
                    String msg = "Maximal Nego : Rp." + setCurrencyFormat(String.valueOf(openPrice)) + " \n" + "silahkan langsung BUY NOW";
                    Toast.makeText(contexts, msg, Toast.LENGTH_SHORT).show();
                }

                /*if(dataCartResponse.getTertinggi()==null){
                    long highPriceNego = Long.parseLong(dataCartResponse.getBottomPrice());
                    NegoAndBuyNowRequest negoAndBuyNowRequest = new NegoAndBuyNowRequest(String.valueOf(dataCartResponse.getOhid()), dataCartResponse.getKik(), dataCartResponse.getAgreementNo().trim(), String.valueOf(highPriceNego));
                    liveNegoApi(negoAndBuyNowRequest);
                }else {
                    long highPriceNego;
                    if(grosirMobilPreference.getDataCartLive().get(position).getNego()==null||grosirMobilPreference.getDataCartLive().get(position).getNego().equals("")){
                        highPriceNego = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getBottomPrice());
                    }else {
                        holder.tvPrice.setText("Rp "+setCurrencyFormat(grosirMobilPreference.getDataCartLive().get(position).getNego()));
                        highPriceNego = Long.parseLong(grosirMobilPreference.getDataCartLive().get(position).getNego());
                    }
                    System.out.println("Test NEGO : " + highPriceNego);
                    NegoAndBuyNowRequest negoAndBuyNowRequest = new NegoAndBuyNowRequest(String.valueOf(dataCartResponse.getOhid()), dataCartResponse.getKik(), dataCartResponse.getAgreementNo().trim(), String.valueOf(highPriceNego));
                    liveNegoApi(negoAndBuyNowRequest);
                }*/
                holder.relativeSuccessNego.setVisibility(View.VISIBLE);
                //onItemClickListenerTawar.onItemClick(dataCartResponse);
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

        } catch (Exception e) {
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
        final Call<GeneralNegoAndBuyNowResponse> vehicleDetailApi = getApiGrosirMobil().liveNegoApi(BEARER + " " + grosirMobilPreference.getToken(), negoAndBuyNowRequest);
        vehicleDetailApi.enqueue(new Callback<GeneralNegoAndBuyNowResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GeneralNegoAndBuyNowResponse> call, Response<GeneralNegoAndBuyNowResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getMessage().equals("success")) {
                            notifyDataSetChanged();
                        } else {
                            grosirMobilFunction.showMessage(contexts, "Message", response.body().getDescription());
                        }
                    } catch (Exception e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                } else {
                    try {
                        grosirMobilFunction.showMessage(contexts, contexts.getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralNegoAndBuyNowResponse> call, Throwable t) {
                progressDialog.dismiss();
                grosirMobilFunction.showMessage(contexts, "Message", contexts.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    public void startTimer(TextView tvTimer, long noOfMinutes) {
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
                @SuppressLint({"SetTextI18n", "DefaultLocale"})
                public void onTick(long millisUntilFinished) {
                    String format = "%1$02d";
                    long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                    long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                    long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                    tvTimer.setText(days + " Hari " + String.format(format,hours) + " Jam " + String.format(format,minutes) + " Menit " + String.format(format,seconds) + " Detik");
                }

                @SuppressLint("SetTextI18n")
                public void onFinish() {
                    tvTimer.setText("Waktu Penawaran Habis");

                }
            }.start();
        }
    }

}
