package com.sip.grosirmobil.cloud.config;

import com.sip.grosirmobil.cloud.config.request.favorite.FavoriteRequest;
import com.sip.grosirmobil.cloud.config.request.changepassword.ChangePasswordRequest;
import com.sip.grosirmobil.cloud.config.request.filter.MerekRequest;
import com.sip.grosirmobil.cloud.config.request.generateva.GenerateVaRequest;
import com.sip.grosirmobil.cloud.config.request.history.CheckStatusHistoryRequest;
import com.sip.grosirmobil.cloud.config.request.history.HistoryTransactionRequest;
import com.sip.grosirmobil.cloud.config.request.home.HomeComingSoonRequest;
import com.sip.grosirmobil.cloud.config.request.home.HomeHistoryRequest;
import com.sip.grosirmobil.cloud.config.request.home.HomeLiveRequest;
import com.sip.grosirmobil.cloud.config.request.invoice.InvoiceVaRequest;
import com.sip.grosirmobil.cloud.config.request.kabupaten.KabupatenRequest;
import com.sip.grosirmobil.cloud.config.request.kecamatan.KecamatanRequest;
import com.sip.grosirmobil.cloud.config.request.kelurahan.KelurahanRequest;
import com.sip.grosirmobil.cloud.config.request.login.LoginRequest;
import com.sip.grosirmobil.cloud.config.request.negonbuynow.NegoAndBuyNowRequest;
import com.sip.grosirmobil.cloud.config.request.resendotp.ResendOtpRequest;
import com.sip.grosirmobil.cloud.config.request.savedataregister.SaveDataRegisterRequest;
import com.sip.grosirmobil.cloud.config.request.validationotp.ValidationOtpRequest;
import com.sip.grosirmobil.cloud.config.request.vehicledetail.VehicleDetailRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.cart.CartResponse;
import com.sip.grosirmobil.cloud.config.response.checkactivetoken.CheckActiveTokenResponse;
import com.sip.grosirmobil.cloud.config.response.filter.GradeResponse;
import com.sip.grosirmobil.cloud.config.response.filter.MerekResponse;
import com.sip.grosirmobil.cloud.config.response.generateva.GenerateVaResponse;
import com.sip.grosirmobil.cloud.config.response.historytransaction.CheckStatusHistoryTransactionResponse;
import com.sip.grosirmobil.cloud.config.response.historytransaction.HistoryTransactionResponse;
import com.sip.grosirmobil.cloud.config.response.homecomingsoon.HomeComingSoonResponse;
import com.sip.grosirmobil.cloud.config.response.homehistory.HomeHistoryResponse;
import com.sip.grosirmobil.cloud.config.response.homelive.HomeLiveResponse;
import com.sip.grosirmobil.cloud.config.response.infomenu.InfoMenuResponse;
import com.sip.grosirmobil.cloud.config.response.invoiceva.InvoiceVaResponse;
import com.sip.grosirmobil.cloud.config.response.kabupaten.KabupatenResponse;
import com.sip.grosirmobil.cloud.config.response.kecamatan.KecamatanResponse;
import com.sip.grosirmobil.cloud.config.response.kelurahan.KelurahanResponse;
import com.sip.grosirmobil.cloud.config.response.login.LoginResponse;
import com.sip.grosirmobil.cloud.config.response.nego.GeneralNegoAndBuyNowResponse;
import com.sip.grosirmobil.cloud.config.response.province.ProvinceResponse;
import com.sip.grosirmobil.cloud.config.response.question.QuestionResponse;
import com.sip.grosirmobil.cloud.config.response.savedataregister.SaveDataRegisterResponse;
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
    String questionTwoPath = "/api/registrasi/Rata2PenjualanMobile";
    String questionThreePath = "/api/registrasi/KebutuhanPembelianMobile";
    String questionFourPath = "/api/registrasi/JenisMobilMobile";
    String questionFivePath = "/api/registrasi/SumberInfoMobile";
    String tahunKendaraanPath = "/api/registrasi/TahunKendaraanMobile";
    String asalKendaraanPath = "/api/registrasi/AsalKendaraanMobile";
    String saveDataRegisterPath = "/api/registrasi/SimpanMobile";
    String validationOtpPath = "/api/registrasi/validasiOtpMobile";
    String resendOtpPath = "/api/registrasi/resendOtp";
    String logoutPath = "/api/auth/logout";
    String provincePath = "/api/registrasi/Propinsi";
    String kabupatenPath = "/api/registrasi/Kabupaten";
    String kecamatanPath = "/api/registrasi/Kecamatan";
    String kelurahanPath = "/api/registrasi/Kelurahan";
    String checkActiveTokenPath = "/api/cekaktiftokenMobile";
    String wareHousePath = "/api/lokasi/warehouseMobile";
    String homeLivePath = "/api/Live/HomeMobile";
    String homeComingSoonPath = "/api/comingsoon/listeventMobile";
    String homeHistoryPath = "/api/Live/RiwayatMobile";
    String liveVehicleDetailPath = "/api/Live/detailMobile";
    String timeServerPath = "/api/jamserverMobile";
    String liveNegoPath = "/api/Live/LiveNegoMobile";
    String liveBuyNowPath = "/api/Live/LiveBuyNow";
    String listCartPath = "/api/Live/datakeranjang";
    String setAndUnsetFavoritePath = "/api/favorite/setAndUnsetFavorite";
    String changePasswordPath = "/api/auth/changePasswordForgot";
    String generateVaPath = "/api/Pembayaran/GenerateVA";
    String invoiceVaPath = "/api/Pembayaran/InvoiceVA";
    String historyTransactionPath = "/api/Live/RiwayatMobile";
    String checkStatusHistoryTransactionPath = "/api/Pembayaran/cekStatusPembayaran";
    String merekPath = "/api/filter/merek";
    String gradePath = "/api/filter/grade";
    String infoMenuPath = "/api/info/getInfo";


    //@Headers("X-Requested-With:XMLHttpRequest")

    @Headers("Content-Type: application/json")
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
    Call<SaveDataRegisterResponse> saveDataRegisterApi(@Body SaveDataRegisterRequest saveDataRegisterRequest);

    @Headers("Content-Type: application/json")
    @POST(validationOtpPath)
    Call<GeneralResponse> validationOtpApi(@Body ValidationOtpRequest validationOtpRequest);


    @Headers("Content-Type: application/json")
    @POST(resendOtpPath)
    Call<GeneralResponse> resendOtpApi(@Body ResendOtpRequest resendOtpRequest);

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
    @POST(homeComingSoonPath)
    Call<HomeComingSoonResponse> homeComingSoonApi(@Header("Authorization") String authToken,
                                                   @Body HomeComingSoonRequest homeComingSoonRequest);

    @Headers("Content-Type: application/json")
    @POST(homeHistoryPath)
    Call<HomeHistoryResponse> homeHistoryApi(@Header("Authorization") String authToken,
                                          @Body HomeHistoryRequest homeHistoryRequest);

    @Headers("Content-Type: application/json")
    @POST(liveVehicleDetailPath)
    Call<VehicleDetailResponse> liveVehicleDetailApi(@Header("Authorization") String authToken,
                                                     @Body VehicleDetailRequest vehicleDetailRequest);

    @Headers("Content-Type: application/json")
    @POST(timeServerPath)
    Call<TimeServerResponse> timeServerApi();

    @Headers("Content-Type: application/json")
    @POST(liveNegoPath)
    Call<GeneralNegoAndBuyNowResponse> liveNegoApi(@Header("Authorization") String authToken,
                                                   @Body NegoAndBuyNowRequest negoAndBuyNowRequest);

    @Headers("Content-Type: application/json")
    @POST(liveBuyNowPath)
    Call<GeneralNegoAndBuyNowResponse> liveBuyNowApi(@Header("Authorization") String authToken,
                                        @Body NegoAndBuyNowRequest negoAndBuyNowRequest);

    @Headers("Content-Type: application/json")
    @POST(setAndUnsetFavoritePath)
    Call<GeneralResponse> setAndUnsetFavoriteApi(@Header("Authorization") String authToken,
                                                 @Body FavoriteRequest favoriteRequest);

    @Headers("Content-Type: application/json")
    @POST(listCartPath)
    Call<CartResponse> lisCartApi(@Header("Authorization") String authToken);

    @Headers("Content-Type: application/json")
    @POST(changePasswordPath)
    Call<GeneralResponse> changePasswordApi(@Body ChangePasswordRequest changePasswordRequest);

    @Headers("Content-Type: application/json")
    @POST(generateVaPath)
    Call<GenerateVaResponse> generateVaApi(@Header("Authorization") String authToken,
                                           @Body GenerateVaRequest favoriteRequest);

    @Headers("Content-Type: application/json")
    @POST(invoiceVaPath)
    Call<InvoiceVaResponse> invoiceVaApi(@Header("Authorization") String authToken,
                                         @Body InvoiceVaRequest invoiceVaRequest);

    @Headers("Content-Type: application/json")
    @POST(historyTransactionPath)
    Call<HistoryTransactionResponse> historyTransactionApi(@Header("Authorization") String authToken,
                                                           @Body HistoryTransactionRequest historyTransactionRequest);

    @Headers("Content-Type: application/json")
    @POST(merekPath)
    Call<MerekResponse> filterMerekApi(@Header("Authorization") String authToken,
                                       @Body MerekRequest merekRequest);

    @Headers("Content-Type: application/json")
    @POST(gradePath)
    Call<GradeResponse> filterGradeApi(@Header("Authorization") String authToken,
                                       @Body MerekRequest merekRequest);

    @Headers("Content-Type: application/json")
    @POST(infoMenuPath)
    Call<InfoMenuResponse> infoMenuApi(@Header("Authorization") String authToken,
                                       @Body MerekRequest merekRequest);

    @Headers("Content-Type: application/json")
    @POST(checkStatusHistoryTransactionPath)
    Call<CheckStatusHistoryTransactionResponse> checkStatusHistoryTransactionApi(@Header("Authorization") String authToken,
                                                                                 @Body CheckStatusHistoryRequest historyTransactionRequest);

}
