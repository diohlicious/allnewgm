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
import com.sip.grosirmobil.cloud.config.response.homecomingsoon.DataHomeComingSoonResponse;

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

public class LiveSoonAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private final List<DataHomeComingSoonResponse> dataHomeComingSoonResponseList;
    private final Context context;
    private final String timeServer;
    private final GrosirMobilPreference grosirMobilPreference;

    public LiveSoonAdapter(List<DataHomeComingSoonResponse> dataHomeComingSoonResponses, Context context, String timeServer) {
        this.dataHomeComingSoonResponseList     = dataHomeComingSoonResponses;
        this.context                       = context;
        this.timeServer = timeServer;
        grosirMobilPreference = new GrosirMobilPreference(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle_home_soon, parent, false));
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
            return position == dataHomeComingSoonResponseList.size() ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return dataHomeComingSoonResponseList == null ? 0 : dataHomeComingSoonResponseList.size();
    }

    public void addItems(List<DataHomeComingSoonResponse> postItems) {
        dataHomeComingSoonResponseList.addAll(postItems);
        notifyDataSetChanged();
    }

    public void clear() {
        try {
            if (dataHomeComingSoonResponseList != null) {
                dataHomeComingSoonResponseList.clear();
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
                DataHomeComingSoonResponse dataHomeComingSoonResponse = dataHomeComingSoonResponseList.get(position);
                tvVehicleName.setText(dataHomeComingSoonResponse.getVehicleName());
                tvPlatNumber.setText(dataHomeComingSoonResponse.getKikNumber() + " - ");
//                tvPlatNumber.setText(dataHomeComingSoonResponse.getKikNumber().substring(0, 10) + " - ");
                tvCity.setText(dataHomeComingSoonResponse.getWareHouse().replace("WAREHOUSE ", ""));
                tvOpenPrice.setText("Rp "+setCurrencyFormat(dataHomeComingSoonResponse.getBottomPrice()));
                tvBottomPrice.setText("Rp "+ setCurrencyFormat(dataHomeComingSoonResponse.getBottomPrice()));
                tvInitialName.setText(dataHomeComingSoonResponse.getGrade());

                CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(context);
                circularProgressDrawable.setStrokeWidth(5f);
                circularProgressDrawable.setCenterRadius(30f);
                circularProgressDrawable.start();
                Glide.with(context)
                        .load(dataHomeComingSoonResponse.getImage())
                        .apply(new RequestOptions()
                                .placeholder(circularProgressDrawable)
                                .error(R.drawable.ic_broken_image)
                                .dontAnimate()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(false))
                        .into(ivImage);

                if(dataHomeComingSoonResponse.getIsFavorite()==null){
                    ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                }else {
                    if(dataHomeComingSoonResponse.getIsFavorite().equals("1")){
                        ivFavorite.setImageResource(R.drawable.ic_favorite);
                    }else {
                        ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                    }
                }

                String timeServerDate = convertDate(timeServer,"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
                String startDateSoonDate   = convertDate(dataHomeComingSoonResponse.getStartDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");

                System.out.println("Time StartDate  : "+timeServer);
                System.out.println("Time StartDate  : "+timeServerDate);
                System.out.println("Time EndDate    : "+startDateSoonDate);

                startTimer(tvTimer, calculateDate(timeServerDate,startDateSoonDate), position, dataHomeComingSoonResponseList);

                AtomicBoolean favorite = new AtomicBoolean(false);

                ivFavorite.setOnClickListener(view -> {
                    if (favorite.get()) {
                        favorite.set(false);
                        setAndUnsetFavorite(context, ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeComingSoonResponse.getKik(), dataHomeComingSoonResponse.getAgreementNo(), String.valueOf(dataHomeComingSoonResponse.getOpenHouseId()),"1");
                        ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                    } else {
                        favorite.set(true);
                        ivFavorite.setImageResource(R.drawable.ic_favorite);
                        setAndUnsetFavorite(context, ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeComingSoonResponse.getKik(), dataHomeComingSoonResponse.getAgreementNo(), String.valueOf(dataHomeComingSoonResponse.getOpenHouseId()),"1");
                    }
                });
                cardVehicle.setOnClickListener(view -> {
                    Intent intent = new Intent(context, VehicleDetailActivity.class);
                    intent.putExtra(ID_VEHICLE, String.valueOf(dataHomeComingSoonResponse.getOpenHouseId()));
                    intent.putExtra(KIK, dataHomeComingSoonResponse.getKik());
                    intent.putExtra(FROM_PAGE, "COMING SOON");
                    context.startActivity(intent);
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
//    private final List<DataHomeComingSoonResponse> dataHomeComingSoonResponseList;
//    private final Context contexts;
//    private final String timeServer;
//    private final GrosirMobilPreference grosirMobilPreference;
//
//    public LiveSoonAdapter(Context context, String timeServer, List<DataHomeComingSoonResponse> dataHomeComingSoonResponses) {
//        this.contexts = context;
//        this.dataHomeComingSoonResponseList = dataHomeComingSoonResponses;
//        this.timeServer = timeServer;
//        grosirMobilPreference = new GrosirMobilPreference(context);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolderItemVehicle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.item_vehicle_home_soon, viewGroup, false);
//        return new ViewHolderItemVehicle(itemView);
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolderItemVehicle holder, int position) {
//        DataHomeComingSoonResponse dataHomeComingSoonResponse = dataHomeComingSoonResponseList.get(position);
//        holder.tvVehicleName.setText(dataHomeComingSoonResponse.getVehicleName());
//        holder.tvPlatNumber.setText(dataHomeComingSoonResponse.getKikNumber().substring(0, 10) + " - ");
//        holder.tvCity.setText(dataHomeComingSoonResponse.getWareHouse().replace("WAREHOUSE ", ""));
//        holder.tvOpenPrice.setText("Rp "+setCurrencyFormat(dataHomeComingSoonResponse.getBottomPrice()));
//        holder.tvBottomPrice.setText("Rp "+ setCurrencyFormat(dataHomeComingSoonResponse.getBottomPrice()));
//        holder.tvInitialName.setText(dataHomeComingSoonResponse.getGrade());
//
//        CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(contexts);
//        circularProgressDrawable.setStrokeWidth(5f);
//        circularProgressDrawable.setCenterRadius(30f);
//        circularProgressDrawable.start();
//        Glide.with(contexts)
//                .load(dataHomeComingSoonResponse.getImage())
//                .apply(new RequestOptions()
//                        .placeholder(circularProgressDrawable)
//                        .error(R.drawable.ic_broken_image)
//                        .dontAnimate()
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(false))
//                .into(holder.ivImage);
//
//        if(dataHomeComingSoonResponse.getIsFavorite()==null){
//            holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
//        }else {
//            if(dataHomeComingSoonResponse.getIsFavorite().equals("1")){
//                holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
//            }else {
//                holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
//            }
//        }
//
//        String startDate = convertDate(timeServer,"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
//        String endDate   = convertDate(dataHomeComingSoonResponse.getEndDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
//        startTimer(holder.tvTimer, calculateDate(startDate,endDate));
//
//        AtomicBoolean favorite = new AtomicBoolean(false);
//
//        holder.ivFavorite.setOnClickListener(view -> {
//            if (favorite.get()) {
//                favorite.set(false);
//                setAndUnsetFavorite(contexts, holder.ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeComingSoonResponse.getKik(), dataHomeComingSoonResponse.getAgreementNo(), String.valueOf(dataHomeComingSoonResponse.getOpenHouseId()),"1");
//                holder.ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
//            } else {
//                favorite.set(true);
//                holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
//                setAndUnsetFavorite(contexts, holder.ivFavorite, grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getId(), dataHomeComingSoonResponse.getKik(), dataHomeComingSoonResponse.getAgreementNo(), String.valueOf(dataHomeComingSoonResponse.getOpenHouseId()),"1");
//            }
//        });
//        holder.cardVehicle.setOnClickListener(view -> {
//            Intent intent = new Intent(contexts, VehicleDetailActivity.class);
//            intent.putExtra(ID_VEHICLE, String.valueOf(dataHomeComingSoonResponse.getOpenHouseId()));
//            intent.putExtra(KIK, dataHomeComingSoonResponse.getKik());
//            intent.putExtra(FROM_PAGE, "COMING SOON");
//            contexts.startActivity(intent);
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataHomeComingSoonResponseList.size();
//    }

    public void startTimer(TextView tvTimer, long noOfMinutes, int position, List<DataHomeComingSoonResponse> dataHomeComingSoonResponseList) {
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
                dataHomeComingSoonResponseList.remove(position);
                notifyDataSetChanged();
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
