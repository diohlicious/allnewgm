package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSearchData;
import com.sip.grosirmobil.cloud.config.model.SearchDataModel;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SearchDataAdapter extends RecyclerView.Adapter<ViewHolderSearchData> {

    private final List<SearchDataModel> searchDataModelList;
    private final Context contexts;
    private final Activity activity;

    public SearchDataAdapter(Activity activity, Context context, List<SearchDataModel> searchDataModels) {
        this.activity = activity;
        this.contexts = context;
        this.searchDataModelList = searchDataModels;
    }

    @NonNull
    @Override
    public ViewHolderSearchData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_search, viewGroup, false);
        return new ViewHolderSearchData(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderSearchData holder, int position) {
        SearchDataModel searchDataModel = searchDataModelList.get(position);
        holder.tvKeySearch.setText(searchDataModel.getKeyValue());
        holder.linearKeySearch.setOnClickListener(view -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("keySearch", searchDataModel.getKeyValue());
            activity.setResult(RESULT_OK, resultIntent);
            activity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return searchDataModelList.size();
    }

}
