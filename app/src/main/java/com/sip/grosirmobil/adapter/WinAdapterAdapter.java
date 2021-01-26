package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemCart;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.cart.DataCartResponse;

import java.util.List;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;


public class WinAdapterAdapter extends RecyclerView.Adapter<ViewHolderItemCart> {

    private final List<DataCartResponse> dataCartResponseList;
    private final List<DataCartResponse> dataWaitingPaymentSelectedList;
    private final Context context;
    private long totalPrice, totalPriceTemp;

//    public CartAdapter(Context context, TextView tvHargaKendaraan, TextView tvTotal, List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModels) {
//        this.context = context;
//        this.tvHargaKendaraan = tvHargaKendaraan;
//        this.tvTotal = tvTotal;
//        this.hardCodeDataBaruMasukModelList = hardCodeDataBaruMasukModels;
//    }

//    public interface OnCheckedChangeListener {
//        void onItemClick(DataCartResponse dataCartResponse);
//    }
//
//    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    public WinAdapterAdapter(Context context, List<DataCartResponse> dataWaitingPaymentSelectedList, List<DataCartResponse> dataCartResponses) {
        this.context = context;
        this.dataWaitingPaymentSelectedList = dataWaitingPaymentSelectedList;
        this.dataCartResponseList = dataCartResponses;
//        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @NonNull
    @Override
    public ViewHolderItemCart onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_cart, viewGroup, false);
        return new ViewHolderItemCart(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemCart holder, int position) {
        DataCartResponse dataCartResponse = dataCartResponseList.get(position);
        try {
            holder.tvVehicleName.setText(dataCartResponse.getVehicleName());
            holder.tvPlatNumber.setText(dataCartResponse.getKik().substring(0, 10) + " - ");
            holder.tvCity.setText(dataCartResponse.getDataOtoJsonResponse().getLokasi().replace("WAREHOUSE ", ""));
            holder.tvPrice.setText("Rp "+setCurrencyFormat(dataCartResponse.getBottomPrice()));
            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(context);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(context)
                    .load(dataCartResponse.getFoto())
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
                            .error(R.drawable.ic_broken_image)
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(false))
                    .into(holder.ivImage);

            holder.cbCart.setOnCheckedChangeListener((compoundButton, checked) -> {
                if(checked){
                    if(dataWaitingPaymentSelectedList.size()==3){
                        Toast.makeText(context, "Limit VA cuma 3 Kendaraan", Toast.LENGTH_SHORT).show();
                        holder.cbCart.setChecked(false);
                    }else {
                        dataWaitingPaymentSelectedList.add(dataCartResponse);
                    }
//                totalPriceTemp = Long.parseLong(hardCodeDataBaruMasukModel.getPrice());
//                holder.linearCart.setBackgroundResource(R.color.colorPrimaryThemeCart);
//                holder.tvVehicleName.setTextColor(context.getResources().getColor(R.color.colorPrimaryTheme));
//                totalPrice = totalPrice+Long.parseLong(dataCartResponse.getBottomPrice());
//                tvHargaKendaraan.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
//                totalPrice= totalPrice+500000;
                }else {
                    dataWaitingPaymentSelectedList.remove(dataCartResponse);
//                totalPriceTemp = Long.parseLong("0");
//                holder.linearCart.setBackgroundResource(R.color.colorPrimaryWhite);
//                holder.tvVehicleName.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
//                totalPrice = totalPrice-Long.parseLong(dataCartResponse.getBottomPrice());
//                tvHargaKendaraan.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
//                totalPrice= totalPrice-500000;
                }


//            tvTotal.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
            });

//        holder.bind(dataCartResponse, onCheckedChangeListener);

            holder.cardVehicle.setOnClickListener(view -> {
//            Intent intent = new Intent(context, VehicleDetailActivity.class);
//            intent.putExtra(ID_VEHICLE, "");
//            intent.putExtra(KIK, "");
//            intent.putExtra(FROM_PAGE, "CART");
//            context.startActivity(intent);
            });
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }
    }

    @Override
    public int getItemCount() {
        return dataCartResponseList.size();
    }

}
