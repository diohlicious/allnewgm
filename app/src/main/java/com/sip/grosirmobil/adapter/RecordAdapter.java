package com.sip.grosirmobil.adapter;

public class RecordAdapter {//extends RecyclerView.Adapter<ViewHolderItemHistoryBidding> {

//    private List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModelList;
//    private Context contexts;
//
//
//    public RecordAdapter(Context context, List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModels) {
//        this.contexts = context;
//        this.hardCodeDataBaruMasukModelList = hardCodeDataBaruMasukModels;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolderItemHistoryBidding onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.item_vehicle_home_record, viewGroup, false);
//        return new ViewHolderItemHistoryBidding(itemView);
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolderItemHistoryBidding holder, int position) {
//        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = hardCodeDataBaruMasukModelList.get(position);
//        holder.tvVehicleName.setText(hardCodeDataBaruMasukModel.getVehicleName());
//        holder.tvPlatNumber.setText(hardCodeDataBaruMasukModel.getPlatNumber()+" - ");
//        holder.tvCity.setText(hardCodeDataBaruMasukModel.getCity());
//        holder.tvPriceWin.setText(hardCodeDataBaruMasukModel.getPrice());
//        holder.tvOpenPrice.setText(hardCodeDataBaruMasukModel.getPrice());
//
//        holder.cardVehicle.setOnClickListener(view -> {
//            Intent intent = new Intent(contexts, VehicleDetailActivity.class);
//            intent.putExtra(ID_VEHICLE, "");
//            intent.putExtra(KIK, "");
//            intent.putExtra(FROM_PAGE, "");
//            contexts.startActivity(intent);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return hardCodeDataBaruMasukModelList.size();
//    }

}
