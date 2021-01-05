package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.cloud.config.model.BannerDataModel;

import java.util.List;

public class BannerGalleryAdapter extends PagerAdapter {

    private List<BannerDataModel> dataObjectList;
    private LayoutInflater layoutInflater;
    private Context context;

    public BannerGalleryAdapter(Context context, List<BannerDataModel> dataObjectList) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataObjectList = dataObjectList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataObjectList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (object);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = this.layoutInflater.inflate(R.layout.item_banner_gallery, container, false);
        ImageView ivBannerPromo = view.findViewById(R.id.iv_banner);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvContent = view.findViewById(R.id.tv_content);

//        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
//        circularProgressDrawable.setStrokeWidth(5f);
//        circularProgressDrawable.setCenterRadius(30f);
//        circularProgressDrawable.start();
//
//        Glide.with(context)
//                .load(BuildConfig.BASE_URL+"/"+this.dataObjectList.get(position).getImage())
//                .apply(new RequestOptions()
//                        .placeholder(circularProgressDrawable)
//                        .error(R.drawable.ic_image_empty)
//                        .dontAnimate()
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(false))
//                .into(ivBannerPromo);

        tvTitle.setText(this.dataObjectList.get(position).getImageNumber());
        tvContent.setText(this.dataObjectList.get(position).getDescription());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
