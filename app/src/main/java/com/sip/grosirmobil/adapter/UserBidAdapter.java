package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderUserBid;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.UserBidResponse;

import java.util.List;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class UserBidAdapter extends RecyclerView.Adapter<ViewHolderUserBid> {

    private final List<UserBidResponse> userBidResponseList;

    public UserBidAdapter(List<UserBidResponse> userBidResponses) {
        this.userBidResponseList = userBidResponses;
    }

    @NonNull
    @Override
    public ViewHolderUserBid onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_bid, viewGroup, false);
        return new ViewHolderUserBid(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderUserBid holder, int position) {
        UserBidResponse userBidResponse = userBidResponseList.get(position);
        holder.tvNameBid.setText(userBidResponse.getName());
        String date = userBidResponse.getDateBid().substring(0, 19);
        System.out.println("Date Bid : " + date);
        holder.tvDateBid.setText(convertDate(userBidResponse.getDateBid(),"yyyy-MM-dd'T'hh:mm:ss","dd-MM-yyyy hh:mm"));
        holder.tvPriceBid.setText("Rp "+setCurrencyFormat(userBidResponse.getPriceBid()));
    }

    @Override
    public int getItemCount() {
        return userBidResponseList.size();
    }
}
