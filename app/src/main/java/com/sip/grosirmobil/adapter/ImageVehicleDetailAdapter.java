package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.sip.grosirmobil.activity.PreviewImageActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderImageVehicleDetail;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.ImageResponse;

import java.util.List;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.DESCRIPTION;

public class ImageVehicleDetailAdapter extends RecyclerView.Adapter<ViewHolderImageVehicleDetail> {

    private List<ImageResponse> imageResponseList;
    private Context contexts;


    public ImageVehicleDetailAdapter(Context context, List<ImageResponse> imageResponses) {
        this.contexts = context;
        this.imageResponseList = imageResponses;
    }

    @NonNull
    @Override
    public ViewHolderImageVehicleDetail onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_image_vehicle_detail, viewGroup, false);
        return new ViewHolderImageVehicleDetail(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderImageVehicleDetail holder, int position) {
        ImageResponse imageResponse = imageResponseList.get(position);
        CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(contexts);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        Glide.with(contexts)
                .load(imageResponse.getBlobUri())
                .apply(new RequestOptions()
                        .placeholder(circularProgressDrawable)
//                          .error(R.drawable.ic_image_empty)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(holder.ivImage);
        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(contexts, PreviewImageActivity.class);
            intent.putExtra(DESCRIPTION, imageResponse.getBlobUri());
            contexts.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return imageResponseList.size();
    }

}
