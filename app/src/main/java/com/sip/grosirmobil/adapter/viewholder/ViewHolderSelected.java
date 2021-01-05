package com.sip.grosirmobil.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.KabupatenAdapter;
import com.sip.grosirmobil.adapter.KecamatanAdapter;
import com.sip.grosirmobil.adapter.KelurahanAdapter;
import com.sip.grosirmobil.adapter.ProvinceAdapter;
import com.sip.grosirmobil.adapter.QuestionAdapter;
import com.sip.grosirmobil.adapter.TipeUsahaAdapter;
import com.sip.grosirmobil.adapter.WareHouseAdapter;
import com.sip.grosirmobil.cloud.config.response.kabupaten.DataKabupatenResponse;
import com.sip.grosirmobil.cloud.config.response.kecamatan.DataKecamatanResponse;
import com.sip.grosirmobil.cloud.config.response.kelurahan.DataKelurahanResponse;
import com.sip.grosirmobil.cloud.config.response.province.DataProvinceResponse;
import com.sip.grosirmobil.cloud.config.response.question.DataQuestionResponse;
import com.sip.grosirmobil.cloud.config.response.tipeusaha.DataTipeUsahaResponse;
import com.sip.grosirmobil.cloud.config.response.warehouse.DataWareHouseResponse;

public class ViewHolderSelected extends RecyclerView.ViewHolder {

    public TextView tvSelected;

    public ViewHolderSelected(View view) {
        super(view);
        tvSelected         = view.findViewById(R.id.tv_selected);
    }

    public void bind(DataProvinceResponse dataProvinceResponse, final ProvinceAdapter.OnItemClickListener listener) {
        tvSelected.setText(dataProvinceResponse.getProvinceName());
        tvSelected.setTag(dataProvinceResponse.getProvinceCode());
        tvSelected.setOnClickListener(view -> listener.onItemClick(dataProvinceResponse));
    }

    public void bind(DataKabupatenResponse dataKabupatenResponse, final KabupatenAdapter.OnItemClickListener listener) {
        tvSelected.setText(dataKabupatenResponse.getCity());
        tvSelected.setTag(dataKabupatenResponse.getCity());
        tvSelected.setOnClickListener(view -> listener.onItemClick(dataKabupatenResponse));
    }

    public void bind(DataKecamatanResponse dataKecamatanResponse, final KecamatanAdapter.OnItemClickListener listener) {
        tvSelected.setText(dataKecamatanResponse.getSubDistrict());
        tvSelected.setTag(dataKecamatanResponse.getSubDistrict());
        tvSelected.setOnClickListener(view -> listener.onItemClick(dataKecamatanResponse));
    }

    public void bind(DataKelurahanResponse dataKelurahanResponse, final KelurahanAdapter.OnItemClickListener listener) {
        tvSelected.setText(dataKelurahanResponse.getUrban());
        tvSelected.setTag(dataKelurahanResponse.getUrban());
        tvSelected.setOnClickListener(view -> listener.onItemClick(dataKelurahanResponse));
    }
    
    public void bind(DataTipeUsahaResponse dataTipeUsahaResponse, final TipeUsahaAdapter.OnItemClickListener listener) {
        tvSelected.setText(dataTipeUsahaResponse.getName());
        tvSelected.setTag(dataTipeUsahaResponse.getCode());
        tvSelected.setOnClickListener(view -> listener.onItemClick(dataTipeUsahaResponse));
    } 
    
    public void bind(DataQuestionResponse dataQuestionResponse, final QuestionAdapter.OnItemClickListener listener) {
        tvSelected.setText(dataQuestionResponse.getName());
        tvSelected.setTag(dataQuestionResponse.getCode());
        tvSelected.setOnClickListener(view -> listener.onItemClick(dataQuestionResponse));
    }

    public void bind(DataWareHouseResponse dataWareHouseResponse, final WareHouseAdapter.OnItemClickListener listener) {
        tvSelected.setText(dataWareHouseResponse.getName().replace("WAREHOUSE ", ""));
//        tvSelected.setText(dataWareHouseResponse.getName());
        tvSelected.setTag(dataWareHouseResponse.getWarehouseCode());
        tvSelected.setOnClickListener(view -> listener.onItemClick(dataWareHouseResponse));
    }

}
