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

    String loginPath = "/api/auth/login";
    String logoutPath = "/api/auth/logout";
    String provincePath = "/api/registrasi/Propinsi";
    String kabupatenPath = "/api/registrasi/Kabupaten";
    String kecamatanPath = "/api/registrasi/Kecamatan";
    String kelurahanPath = "/api/registrasi/Kelurahan";
    String historyBillPaymentPath = "app/"+v1+"/user/history/bill.php";
    String refreshTokenPath = "app/"+v1+"/refreshtoken.php";

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
    @POST(refreshTokenPath)
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


}
