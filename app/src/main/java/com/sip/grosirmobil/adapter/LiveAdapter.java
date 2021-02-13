package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.base.adapter.BaseViewHolder;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.request.favorite.FavoriteRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.homelive.DataHomeLiveResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
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

public class LiveAdapter
        extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private final List<DataHomeLiveResponse> dataHomeLiveResponseList;
    private final Context contexts;
    private final String timeServer;
    private final GrosirMobilPreference grosirMobilPreference;

    public LiveAdapter(List<DataHomeLiveResponse> dataHomeLiveResponses, Context context, String timeServer) {
        this.dataHomeLiveResponseList     = dataHomeLiveResponses;
        this.contexts                       = context;
        this.timeServer = timeServer;
        grosirMobilPreference = new GrosirMobilPreference(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle_home_live, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == dataHomeLiveResponseList.size() ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return dataHomeLiveResponseList == null ? 0 : dataHomeLiveResponseList.size();
    }

    public void addItems(List<DataHomeLiveResponse> postItems) {
        dataHomeLiveResponseList.addAll(postItems);
        notifyDataSetChanged();
    }

    public void clear() {
        try {
            if (dataHomeLiveResponseList != null) {
                dataHomeLiveResponseList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            GrosirMobilLog.printStackTrace(e);
        }
    }

    public class ViewHolder extends BaseViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_vehicle_name) TextView tvVehicleName;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_plat_number) TextView tvPlatNumber;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_city) TextView tvCity;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_open_price) TextView tvOpenPrice;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_bottom_price) TextView tvBottomPrice;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_timer) TextView tvTimer;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_initial_name) TextView tvInitialName;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.card_vehicle) CardView cardVehicle;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.iv_image) ImageView ivImage;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.iv_favorite) ImageView ivFavorite;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.linear_description) LinearLayout linearDescription;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.circle_image_view_item) CircleImageView circleImageViewItem;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        protected void clear() {}

        @SuppressLint("SetTextI18n")
        public void onBind(int position) {
            super.onBind(position);
            try {
                DataHomeLiveResponse dataHomeLiveResponse = dataHomeLiveResponseList.get(position);
                tvVehicleName.setText(dataHomeLiveResponse.getVehicleName());
                tvPlatNumber.setText(dataHomeLiveResponse.getKikNumber() + " - ");
//                tvPlatNumber.setText(dataHomeLiveResponse.getKikNumber().substring(0, 10) + " - ");
                tvCity.setText(dataHomeLiveResponse.getWareHouse().replace("WAREHOUSE ", ""));
                tvOpenPrice.setText("Rp " + setCurrencyFormat(dataHomeLiveResponse.getBottomPrice()));
                tvBottomPrice.setText("Rp " + setCurrencyFormat(dataHomeLiveResponse.getPriceNow()));
                tvInitialName.setText(dataHomeLiveResponse.getGrade());

                AtomicBoolean favorite = new AtomicBoolean(false);

                if(dataHomeLiveResponse.getIsFavorite()==1){
                    favorite.set(true);
                    ivFavorite.setImageResource(R.drawable.ic_favorite);
                }else if(dataHomeLiveResponse.getIsFavorite()==0){
                    favorite.set(false);
                    ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                }else {
                    favorite.set(false);
                    ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                }

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
                        .into(ivImage);
                System.out.println("Data Favorite : "+ dataHomeLiveResponse.getIsFavorite());

                String startDate = convertDate(timeServer,"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
                String endDate   = convertDate(dataHomeLiveResponse.getEndDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
                startTimer(tvTimer, calculateDate(startDate,endDate));
                System.out.println("TIME SERVER BEFORE : "+timeServer);
                System.out.println("END DATE           : "+dataHomeLiveResponse.getEndDate());

                ivFavorite.setOnClickListener(view -> {
                    if (favorite.get()) {
                        favorite.set(false);
                        setAndUnsetFavorite(contexts, ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeLiveResponse.getKik(), dataHomeLiveResponse.getAgreementNo(), String.valueOf(dataHomeLiveResponse.getOpenHouseId()),"0");
                        ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                    } else {
                        favorite.set(true);
                        ivFavorite.setImageResource(R.drawable.ic_favorite);
                        setAndUnsetFavorite(contexts, ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeLiveResponse.getKik(), dataHomeLiveResponse.getAgreementNo(), String.valueOf(dataHomeLiveResponse.getOpenHouseId()),"1");
                    }
                });
                cardVehicle.setOnClickListener(view -> {
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
    }

    private class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @Override
        protected void clear() {}
    }

//        extends RecyclerView.Adapter<ViewHolderItemVehicle> {
//
//    private final List<DataHomeLiveResponse> dataHomeLiveResponseList;
//    private final Context contexts;
//    private final String timeServer;
//    private final GrosirMobilPreference grosirMobilPreference;
//
//    public LiveAdapter(Context context, String timeServer, List<DataHomeLiveResponse> dataHomeLiveResponses) {
//        this.contexts = context;
//        this.timeServer = timeServer;
//        this.dataHomeLiveResponseList = dataHomeLiveResponses;
//        grosirMobilPreference = new GrosirMobilPreference(context);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolderItemVehicle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.item_vehicle_home_live, viewGroup, false);
//        return new ViewHolderItemVehicle(itemView);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @SuppressLint({"SetTextI18n", "RestrictedApi"})
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolderItemVehicle holder, int position) {
//        try {
//            DataHomeLiveResponse dataHomeLiveResponse = dataHomeLiveResponseList.get(position);
//            holder.tvVehicleName.setText(dataHomeLiveResponse.getVehicleName());
//            holder.tvPlatNumber.setText(dataHomeLiveResponse.getKikNumber().substring(0, 10) + " - ");
//            holder.tvCity.setText(dataHomeLiveResponse.getWareHouse().replace("WAREHOUSE ", ""));
//            holder.tvOpenPrice.setText("Rp " + setCurrencyFormat(dataHomeLiveResponse.getBottomPrice()));
//            holder.tvBottomPrice.setText("Rp " + setCurrencyFormat(dataHomeLiveResponse.getBottomPrice()));
//            holder.tvInitialName.setText(dataHomeLiveResponse.getGrade());
//
//            AtomicBoolean favorite = new AtomicBoolean(false);
//
//            if(dataHomeLiveResponse.getIsFavorite()==1){
//                favorite.set(true);
//                holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
//            }else if(dataHomeLiveResponse.getIsFavorite()==0){
//                favorite.set(false);
//                holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
//            }else {
//                favorite.set(false);
//                holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
//            }
//
//            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(contexts);
//            circularProgressDrawable.setStrokeWidth(5f);
//            circularProgressDrawable.setCenterRadius(30f);
//            circularProgressDrawable.start();
//            Glide.with(contexts)
//                    .load(dataHomeLiveResponse.getImage())
//                    .apply(new RequestOptions()
//                            .placeholder(circularProgressDrawable)
//                            .error(R.drawable.ic_broken_image)
//                            .dontAnimate()
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(false))
//                    .into(holder.ivImage);
//            System.out.println("Data Favorite : "+ dataHomeLiveResponse.getIsFavorite());
//
//            String startDate = convertDate(timeServer,"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
//            String endDate   = convertDate(dataHomeLiveResponse.getEndDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
//            startTimer(holder.tvTimer, calculateDate(startDate,endDate));
//            System.out.println("TIME SERVER BEFORE : "+timeServer);
//            System.out.println("END DATE           : "+dataHomeLiveResponse.getEndDate());
//
//            holder.ivFavorite.setOnClickListener(view -> {
//                if (favorite.get()) {
//                    favorite.set(false);
//                    setAndUnsetFavorite(contexts, holder.ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeLiveResponse.getKik(), dataHomeLiveResponse.getAgreementNo(), String.valueOf(dataHomeLiveResponse.getOpenHouseId()),"0");
//                    holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
//                } else {
//                    favorite.set(true);
//                    holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
//                    setAndUnsetFavorite(contexts, holder.ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeLiveResponse.getKik(), dataHomeLiveResponse.getAgreementNo(), String.valueOf(dataHomeLiveResponse.getOpenHouseId()),"1");
//                }
//            });
//            holder.cardVehicle.setOnClickListener(view -> {
//                Intent intent = new Intent(contexts, VehicleDetailActivity.class);
//                intent.putExtra(ID_VEHICLE, String.valueOf(dataHomeLiveResponse.getOpenHouseId()));
//                intent.putExtra(KIK, dataHomeLiveResponse.getKik());
//                intent.putExtra(FROM_PAGE, "LIVE");
//                contexts.startActivity(intent);
//            });
//        }
//        catch (Exception e){
//            GrosirMobilLog.printStackTrace(e);
//        }
//    }
//    @Override
//    public int getItemCount() {
//        return dataHomeLiveResponseList.size();
//    }

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
                            if(isFavorit.equals("0")){
                                ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                            }else {
                                ivFavorite.setImageResource(R.drawable.ic_favorite);
                            }
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
