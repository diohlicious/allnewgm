package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderMenuInfo;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.infomenu.DataInfoMenuResponse;

import java.util.List;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDate;

public class InfoMenuAdapter extends RecyclerView.Adapter<ViewHolderMenuInfo> {

    private final List<DataInfoMenuResponse> dataInfoMenuResponseList;
    private final Context context;

    public InfoMenuAdapter(List<DataInfoMenuResponse> dataInfoMenuResponses, Context context) {
        this.dataInfoMenuResponseList = dataInfoMenuResponses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderMenuInfo onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_menu_info, viewGroup, false);
        return new ViewHolderMenuInfo(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderMenuInfo holder, int position) {
        try {
            DataInfoMenuResponse dataInfoMenuResponse = dataInfoMenuResponseList.get(position);
            holder.tvDate.setText(convertDate(dataInfoMenuResponse.getTanggal(),"yyyy-MM-dd hh:mm:ss","dd-MM-yyyy"));
            holder.tvPromo.setText(dataInfoMenuResponse.getNews());
            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(context);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(context)
                    .load(dataInfoMenuResponse.getGambar())
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(holder.ivImage);
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }
    }

    @Override
    public int getItemCount() {
        return dataInfoMenuResponseList.size();
    }
}
