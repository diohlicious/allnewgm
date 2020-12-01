package com.sip.grosirmobil.cloud.config;

import com.sip.grosirmobil.cloud.config.request.history.HistoryTransactionRequest;
import com.sip.grosirmobil.cloud.config.request.login.LoginRequest;
import com.sip.grosirmobil.cloud.config.response.history.HistoryBillPaymentResponse;
import com.sip.grosirmobil.cloud.config.response.login.LoginResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
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
    String historyBillPaymentPath = "app/"+v1+"/user/history/bill.php";
    String refreshTokenPath = "app/"+v1+"/refreshtoken.php";

    @Headers("X-Requested-With:XMLHttpRequest")
    @POST(loginPath)
    Single<LoginResponse> loginApi(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST(historyBillPaymentPath)
    Single<HistoryBillPaymentResponse> historyBillPaymentApi(@Header("Authorization") String authToken,
                                                             @Body HistoryTransactionRequest historyTransactionRequest);

    @Headers("Content-Type: application/json")
    @POST(refreshTokenPath)
    Call<LoginResponse> refreshTokenApi(@Header("Authorization") String authToken);

}
