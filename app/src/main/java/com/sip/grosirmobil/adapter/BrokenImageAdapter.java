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
import com.sip.grosirmobil.adapter.viewholder.ViewHolderBrokenImage;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.ImageBrokenResponse;

import java.util.List;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;

public class BrokenImageAdapter extends RecyclerView.Adapter<ViewHolderBrokenImage> {

    private final List<ImageBrokenResponse> imageBrokenResponseList;
    private final Context contexts;


    public BrokenImageAdapter(Context context, List<ImageBrokenResponse> imageBrokenResponses) {
        this.contexts = context;
        this.imageBrokenResponseList = imageBrokenResponses;
    }

    @NonNull
    @Override
    public ViewHolderBrokenImage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_broken_image, viewGroup, false);
        return new ViewHolderBrokenImage(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderBrokenImage holder, int position) {
        try {
            ImageBrokenResponse imageBrokenResponse = imageBrokenResponseList.get(position);
            holder.tvDescription.setText(imageBrokenResponse.getDescription());

            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(contexts);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(contexts)
                    .load(imageBrokenResponse.getUrlImage())
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
//                          .error(R.drawable.ic_image_empty)
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(holder.ivImage);

            holder.tvImageNumber.setText(String.valueOf(position+1));

            holder.cardView.setOnClickListener(view -> {
                Intent intent = new Intent(contexts, PreviewImageActivity.class);
                intent.putExtra(FROM_PAGE, "brokenImage");
//            intent.putExtra(URL_IMAGE, imageBrokenResponse.getUrlImage());
//            intent.putExtra(DESCRIPTION, imageBrokenResponse.getDescription());
                contexts.startActivity(intent);
            });
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }
    }

    @Override
    public int getItemCount() {
        return imageBrokenResponseList.size();
    }

}
