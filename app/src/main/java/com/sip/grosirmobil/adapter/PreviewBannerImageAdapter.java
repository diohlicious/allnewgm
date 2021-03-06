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
import com.sip.grosirmobil.adapter.viewholder.ViewHolderPreviewImage;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.ImageResponse;

import java.util.List;

public class PreviewBannerImageAdapter extends RecyclerView.Adapter<ViewHolderPreviewImage> {

    private final List<ImageResponse> imageResponseList;
    private final Context contexts;


    public PreviewBannerImageAdapter(Context context, List<ImageResponse> imageResponses) {
        this.contexts = context;
        this.imageResponseList = imageResponses;
    }

    @NonNull
    @Override
    public ViewHolderPreviewImage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_preview_image, viewGroup, false);
        return new ViewHolderPreviewImage(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPreviewImage holder, int position) {
        try {
            ImageResponse imageResponse = imageResponseList.get(position);
            if(imageResponse.getDescription().equals("")){
                holder.linearDescription.setVisibility(View.GONE);
            }else {
                holder.linearDescription.setVisibility(View.VISIBLE);
                holder.tvDescription.setText(imageResponse.getDescription());
            }

            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(contexts);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(contexts)
                    .load(imageResponse.getUrlImage())
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
//                          .error(R.drawable.ic_image_empty)
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(holder.photoView);
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }

    }

    @Override
    public int getItemCount() {
        return imageResponseList.size();
    }

}
