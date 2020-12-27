package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.LocationUnitActivity;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicleReadyTakeOut;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.List;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;

public class ReadyTakeOutAdapter extends RecyclerView.Adapter<ViewHolderItemVehicleReadyTakeOut> {

    private List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModelList;
    private Context contexts;


    public ReadyTakeOutAdapter(Context context, List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModels) {
        this.contexts = context;
        this.hardCodeDataBaruMasukModelList = hardCodeDataBaruMasukModels;
    }

    @NonNull
    @Override
    public ViewHolderItemVehicleReadyTakeOut onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_ready_take_out, viewGroup, false);
        return new ViewHolderItemVehicleReadyTakeOut(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemVehicleReadyTakeOut holder, int position) {
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = hardCodeDataBaruMasukModelList.get(position);
        holder.tvVehicleName.setText(hardCodeDataBaruMasukModel.getVehicleName());
        holder.tvPlatNumber.setText(hardCodeDataBaruMasukModel.getPlatNumber()+" - ");
        holder.tvCity.setText(hardCodeDataBaruMasukModel.getCity());
        holder.tvPrice.setText(hardCodeDataBaruMasukModel.getPrice());
        holder.cardVehicle.setOnClickListener(view -> {
            Intent intent = new Intent(contexts, VehicleDetailActivity.class);
            intent.putExtra(ID_VEHICLE, "");
            intent.putExtra(KIK, "");
            intent.putExtra(FROM_PAGE, "");
            contexts.startActivity(intent);
        });
        holder.btnTakeOutLocation.setOnClickListener(view -> {
            Intent intent = new Intent(contexts, LocationUnitActivity.class);
            contexts.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hardCodeDataBaruMasukModelList.size();
    }

}
