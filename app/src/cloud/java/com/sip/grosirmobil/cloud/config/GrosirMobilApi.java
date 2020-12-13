package com.sip.grosirmobil.cloud.config;

import com.sip.grosirmobil.cloud.config.request.home.HomeLiveRequest;
import com.sip.grosirmobil.cloud.config.request.kabupaten.KabupatenRequest;
import com.sip.grosirmobil.cloud.config.request.kecamatan.KecamatanRequest;
import com.sip.grosirmobil.cloud.config.request.kelurahan.KelurahanRequest;
import com.sip.grosirmobil.cloud.config.request.login.LoginRequest;
import com.sip.grosirmobil.cloud.config.request.savedataregister.SaveDataRegisterRequest;
import com.sip.grosirmobil.cloud.config.request.vehicledetail.VehicleDetailRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.checkactivetoken.CheckActiveTokenResponse;
import com.sip.grosirmobil.cloud.config.response.home.HomeLiveResponse;
import com.sip.grosirmobil.cloud.config.response.kabupaten.KabupatenResponse;
import com.sip.grosirmobil.cloud.config.response.kecamatan.KecamatanResponse;
import com.sip.grosirmobil.cloud.config.response.kelurahan.KelurahanResponse;
import com.sip.grosirmobil.cloud.config.response.login.LoginResponse;
import com.sip.grosirmobil.cloud.config.response.province.ProvinceResponse;
import com.sip.grosirmobil.cloud.config.response.question.QuestionResponse;
import com.sip.grosirmobil.cloud.config.response.timeserver.TimeServerResponse;
import com.sip.grosirmobil.cloud.config.response.tipeusaha.TipeUsahaResponse;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.VehicleDetailResponse;
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
    String saveDataRegisterPath = "/api/registrasi/SimpanMobile";
    String logoutPath = "/api/auth/logout";
    String provincePath = "/api/registrasi/Propinsi";
    String kabupatenPath = "/api/registrasi/Kabupaten";
    String kecamatanPath = "/api/registrasi/Kecamatan";
    String kelurahanPath = "/api/registrasi/Kelurahan";
    String checkActiveTokenPath = "/api/cekaktiftokenMobile";
    String wareHousePath = "/api/lokasi/warehouseMobile";
    String homeLivePath = "/api/Live/HomeMobile";
    String liveVehicleDetailPath = "/api/Live/detailMobile";
    String timeServerPath = "/api/jamserverMobile";

    @Headers("X-Requested-With:XMLHttpRequest")
    @POST(loginPath)
    Single<LoginResponse> loginApi(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @GET(logoutPath)
    Call<GeneralResponse> logoutApi(@Header("Authorization") String authToken);

//    @Headers("Content-Type: application/json")
//    @POST(historyBillPaymentPath)
//    Single<HistoryBillPaymentResponse> historyBillPaymentApi(@Header("Authorization") String authToken,
//                                                             @Body HistoryTransactionRequest historyTransactionRequest);

    @Headers("Content-Type: application/json")
    @GET(checkActiveTokenPath)
    Call<CheckActiveTokenResponse> checkActiveTokenApi(@Header("Authorization") String authToken);

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
    @POST(saveDataRegisterPath)
    Call<GeneralResponse> saveDataRegisterApi(@Body SaveDataRegisterRequest saveDataRegisterRequest);

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

    @Headers("Content-Type: application/json")
    @POST(homeLivePath)
    Call<HomeLiveResponse> homeLiveApi(@Header("Authorization") String authToken,
                                       @Body HomeLiveRequest homeLiveRequest);

    @Headers("Content-Type: application/json")
    @POST(liveVehicleDetailPath)
    Call<VehicleDetailResponse> liveVehicleDetailApi(@Header("Authorization") String authToken,
                                                     @Body VehicleDetailRequest vehicleDetailRequest);

    @Headers("Content-Type: application/json")
    @POST(timeServerPath)
    Call<TimeServerResponse> timeServerApi();


}
