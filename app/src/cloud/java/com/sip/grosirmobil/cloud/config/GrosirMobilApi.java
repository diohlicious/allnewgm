package com.sip.grosirmobil.cloud.config;

import com.sip.grosirmobil.cloud.config.request.history.HistoryTransactionRequest;
import com.sip.grosirmobil.cloud.config.request.kabupaten.KabupatenRequest;
import com.sip.grosirmobil.cloud.config.request.kecamatan.KecamatanRequest;
import com.sip.grosirmobil.cloud.config.request.kelurahan.KelurahanRequest;
import com.sip.grosirmobil.cloud.config.request.login.LoginRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.history.HistoryBillPaymentResponse;
import com.sip.grosirmobil.cloud.config.response.kabupaten.KabupatenResponse;
import com.sip.grosirmobil.cloud.config.response.kecamatan.KecamatanResponse;
import com.sip.grosirmobil.cloud.config.response.kelurahan.KelurahanResponse;
import com.sip.grosirmobil.cloud.config.response.login.LoginResponse;
import com.sip.grosirmobil.cloud.config.response.province.ProvinceResponse;
import com.sip.grosirmobil.cloud.config.response.question.QuestionResponse;
import com.sip.grosirmobil.cloud.config.response.tipeusaha.TipeUsahaResponse;
import com.sip.grosirmobil.cloud.config.response.warehouse.WareHouseResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */

public interface GrosirMobilApi {

    String v1 = "v1";
    String v2 = "v2";

    String loginPath = "/api/auth/loginMobile";
    String tipeUsahaPath = "/api/registrasi/tipeusahamobile";
    String questionOnePath = "/api/registrasi/KebutuhanKendaraanBulanMobile";
    String questionTwoPath = "/api/registrasi/KebutuhanPembelianMobile";
    String questionThreePath = "/api/registrasi/JenisMobilMobile";
    String questionFourPath = "/api/registrasi/Rata2PenjualanMobile";
    String questionFivePath = "/api/registrasi/SumberInfoMobile";
    String tahunKendaraanPath = "/api/registrasi/TahunKendaraanMobile";
    String asalKendaraanPath = "/api/registrasi/AsalKendaraanMobile";
    String logoutPath = "/api/auth/logout";
    String provincePath = "/api/registrasi/Propinsi";
    String kabupatenPath = "/api/registrasi/Kabupaten";
    String kecamatanPath = "/api/registrasi/Kecamatan";
    String kelurahanPath = "/api/registrasi/Kelurahan";
    String historyBillPaymentPath = "app/"+v1+"/user/history/bill.php";
    String checkActiveTokenPath = "/api/cekaktiftokenMobile";
    String wareHousePath = "/api/lokasi/warehouseMobile";

    @Headers("X-Requested-With:XMLHttpRequest")
    @POST(loginPath)
    Single<LoginResponse> loginApi(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @GET(logoutPath)
    Call<GeneralResponse> logoutApi(@Header("Authorization") String authToken);

    @Headers("Content-Type: application/json")
    @POST(historyBillPaymentPath)
    Single<HistoryBillPaymentResponse> historyBillPaymentApi(@Header("Authorization") String authToken,
                                                             @Body HistoryTransactionRequest historyTransactionRequest);

    @Headers("Content-Type: application/json")
    @GET(checkActiveTokenPath)
    //TODO Gus nanti bikin model dari response api ini ya. lu test aja di postman /api/cekaktiftokenMobile
    
    Call<LoginResponse> refreshTokenApi(@Header("Authorization") String authToken);

    @Headers("Content-Type: application/json")
    @GET(provincePath)
    Call<ProvinceResponse> provinceApi();

    @Headers("Content-Type: application/json")
    @POST(kabupatenPath)
    Call<KabupatenResponse> kabupatenApi(@Body KabupatenRequest kabupatenRequest);

    @Headers("Content-Type: application/json")
    @POST(kecamatanPath)
    Call<KecamatanResponse> kecamatanApi(@Body KecamatanRequest kecamatanRequest);

    @Headers("Content-Type: application/json")
    @POST(kelurahanPath)
    Call<KelurahanResponse> kelurahanApi(@Body KelurahanRequest kelurahanRequest);

    @Headers("Content-Type: application/json")
    @GET(tipeUsahaPath)
    Call<TipeUsahaResponse> tipeUsahaApi(); 
    
    @Headers("Content-Type: application/json")
    @GET(questionOnePath)
    Call<QuestionResponse> questionOneApi();

    @Headers("Content-Type: application/json")
    @GET(questionTwoPath)
    Call<QuestionResponse> questionTwoApi();

    @Headers("Content-Type: application/json")
    @GET(questionThreePath)
    Call<QuestionResponse> questionThreeApi();

    @Headers("Content-Type: application/json")
    @GET(questionFourPath)
    Call<QuestionResponse> questionFourApi();

    @Headers("Content-Type: application/json")
    @GET(questionFivePath)
    Call<QuestionResponse> questionFiveApi();

    @Headers("Content-Type: application/json")
    @GET(tahunKendaraanPath)
    Call<QuestionResponse> tahunKendaraanApi();

    @Headers("Content-Type: application/json")
    @GET(asalKendaraanPath)
    Call<QuestionResponse> asalKendaraanApi();

    @Headers("Content-Type: application/json")
    @GET(wareHousePath)
    Call<WareHouseResponse> wareHouseApi(@Header("Authorization") String authToken);


}
