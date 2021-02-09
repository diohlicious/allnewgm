package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.adapter.BaseViewHolder;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.homehistory.DataHomeHistoryResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class LiveHistoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private final List<DataHomeHistoryResponse> dataHomeHistoryResponseList;
    private final Context context;

    public interface OnItemClickListener {
        void onItemClick(DataHomeHistoryResponse dataHomeHistoryResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public LiveHistoryAdapter(List<DataHomeHistoryResponse> dataHomeHistoryResponses, Context context, OnItemClickListener onItemClickListener) {
        this.dataHomeHistoryResponseList     = dataHomeHistoryResponses;
        this.context                       = context;
        this.onItemClickListener           = onItemClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle_home_record, parent, false));
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
            return position == dataHomeHistoryResponseList.size() ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return dataHomeHistoryResponseList == null ? 0 : dataHomeHistoryResponseList.size();
    }

    public void addItems(List<DataHomeHistoryResponse> postItems) {
        dataHomeHistoryResponseList.addAll(postItems);
        notifyDataSetChanged();
    }

    public void clear() {
        try {
            if (dataHomeHistoryResponseList != null) {
                dataHomeHistoryResponseList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            GrosirMobilLog.printStackTrace(e);
        }
    }

    public class ViewHolder extends BaseViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_vehicle_name_history) TextView tvVehicleNameHistory;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_event_date_history) TextView tvEventDate;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_city_history) TextView tvCityHistory;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_open_price_history) TextView tvOpenPriceHistory;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_sold_price_history) TextView tvSoldPriceHistory;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_initial_name_history) TextView tvInitialNameHistory;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_status_history) TextView tvStatusHistory;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.card_vehicle_history) CardView cardVehicleHistory;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        protected void clear() {}

        @SuppressLint("SetTextI18n")
        public void onBind(int position) {
            super.onBind(position);
            try {
                DataHomeHistoryResponse dataHomeHistoryResponse = dataHomeHistoryResponseList.get(position);
                tvVehicleNameHistory.setText(dataHomeHistoryResponse.getVehicleName());
                tvCityHistory.setText(dataHomeHistoryResponse.getWareHouse().replace("WAREHOUSE ",""));
                tvOpenPriceHistory.setText("Rp "+setCurrencyFormat(dataHomeHistoryResponse.getHargaPembukaan()));
                tvInitialNameHistory.setText(dataHomeHistoryResponse.getVehicleName().substring(0,1));

                tvEventDate.setText("Event "+convertDate(dataHomeHistoryResponse.getEventDate(),"yyyy-MM-dd hh:mm:ss","dd MMM yyyy")+" - ");

                if(dataHomeHistoryResponse.getSoldPrice()==null){
                    tvStatusHistory.setText("Status");
                    tvSoldPriceHistory.setText(dataHomeHistoryResponse.getStatus());
                }else {
                    tvStatusHistory.setText("Terjual");
                    tvSoldPriceHistory.setText("Rp "+setCurrencyFormat(dataHomeHistoryResponse.getSoldPrice()));
                }
                cardVehicleHistory.setOnClickListener(view -> {
                    onItemClickListener.onItemClick(dataHomeHistoryResponse);
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
//        extends RecyclerView.Adapter<ViewHolderItemVehicleHomeHistory> {
//
//    private final List<DataHomeHistoryResponse> dataHomeHistoryResponseList;
//    private final Context contexts;
//
//
//    public LiveHistoryAdapter(Context context, List<DataHomeHistoryResponse> dataHomeHistoryResponseList) {
//        this.contexts = context;
//        this.dataHomeHistoryResponseList = dataHomeHistoryResponseList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolderItemVehicleHomeHistory onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.item_vehicle_home_record, viewGroup, false);
//        return new ViewHolderItemVehicleHomeHistory(itemView);
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolderItemVehicleHomeHistory holder, int position) {
//        DataHomeHistoryResponse dataHomeHistoryResponse = dataHomeHistoryResponseList.get(position);
//        holder.tvVehicleNameHistory.setText(dataHomeHistoryResponse.getVehicleName());
//        holder.tvCityHistory.setText(dataHomeHistoryResponse.getWareHouse().replace("WAREHOUSE ",""));
//        holder.tvOpenPriceHistory.setText("Rp "+setCurrencyFormat(dataHomeHistoryResponse.getHargaPembukaan()));
//        holder.tvInitialNameHistory.setText(dataHomeHistoryResponse.getVehicleName().substring(0,1));
//
//        holder.tvEventDate.setText("Event "+convertDate(dataHomeHistoryResponse.getEventDate(),"yyyy-MM-dd hh:mm:ss","dd MMM yyyy")+" - ");
//
//        if(dataHomeHistoryResponse.getSoldPrice()==null){
//            holder.tvSoldPriceHistory.setText("Rp 0");
//        }else {
//            holder.tvSoldPriceHistory.setText("Rp "+setCurrencyFormat(dataHomeHistoryResponse.getSoldPrice()));
//        }
//        holder.cardVehicleHistory.setOnClickListener(view -> {
//            Intent intent = new Intent(contexts, VehicleDetailActivity.class);
//            intent.putExtra(ID_VEHICLE, String.valueOf(dataHomeHistoryResponse.getOpenHouseId()));
//            intent.putExtra(KIK, dataHomeHistoryResponse.getKik());
//            intent.putExtra(FROM_PAGE, "HISTORY");
//            contexts.startActivity(intent);
//        });
//    }
//    @Override
//    public int getItemCount() {
//        return dataHomeHistoryResponseList.size();
//    }
}
