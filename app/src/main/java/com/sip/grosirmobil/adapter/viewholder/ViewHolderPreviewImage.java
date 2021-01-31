package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.chrisbanes.photoview.PhotoView;
import com.sip.grosirmobil.R;

public class ViewHolderPreviewImage extends RecyclerView.ViewHolder {

    public TextView tvDescription;
    public LinearLayout linearDescription;
    public PhotoView photoView;

    public ViewHolderPreviewImage(View view) {
        super(view);
        tvDescription         = view.findViewById(R.id.tv_description);
        linearDescription     = view.findViewById(R.id.linear_description);
        photoView             = view.findViewById(R.id.photo_view);
    }
}
