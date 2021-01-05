package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;

public class ViewHolderSearchData extends RecyclerView.ViewHolder {

    public TextView tvKeySearch;
    public LinearLayout linearKeySearch;

    public ViewHolderSearchData(View view) {
        super(view);
        tvKeySearch                = view.findViewById(R.id.tv_key_search);
        linearKeySearch            = view.findViewById(R.id.linear_key_search);
    }
}
