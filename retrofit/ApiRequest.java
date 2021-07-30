package debtechllc.deb.sonderblu.retrofit;

import debtechllc.deb.sonderblu.model.RecoverPassParamDM;
import debtechllc.deb.sonderblu.model.RegistrationVerifyOTPParamDM;
import debtechllc.deb.sonderblu.model.Social_Register_ParamDM;
import debtechllc.deb.sonderblu.model.UpdatePasswordParamDM;
import debtechllc.deb.sonderblu.model.VerifyOtpParamDM;
import debtechllc.deb.sonderblu.response.CancelExistingPlanResponseDM;
import debtechllc.deb.sonderblu.response.ChangePasswordResponseDM;
import debtechllc.deb.sonderblu.response.LogOutResponseDM;
import debtechllc.deb.sonderblu.response.LoginResponseDM;
import debtechllc.deb.sonderblu.model.LoginParamDM;
import debtechllc.deb.sonderblu.response.RecoverPassResponseDM;
import debtechllc.deb.sonderblu.response.RegistrationDM;
import debtechllc.deb.sonderblu.model.RegistrationParamDM;
import debtechllc.deb.sonderblu.response.RegistrationVerifyOtpDM;
import debtechllc.deb.sonderblu.response.SocialRegisterResponseDM;
import debtechllc.deb.sonderblu.response.SubscriptionExistingPlansResponseDM;
import debtechllc.deb.sonderblu.response.UpdatePasswordResponseDM;
import debtechllc.deb.sonderblu.response.VerifyOtpResponseDM;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequest {
    /*For social registration*/
  @POST("auth/social-register")
  Call<SocialRegisterResponseDM> socialRegisterApi(@Body Social_Register_ParamDM social_register_param);

    /*For  manual login*/
  @POST("auth/login")
  Call<LoginResponseDM> loginApi(@Body LoginParamDM loginParam);

    /*For  logout*/
  @POST("auth/logout")
  @FormUrlEncoded
  Call<LogOutResponseDM> logOutApi(@Header("Authorization") String token,
                                   @Field("all") Boolean all);

    /*For  recover password*/
  @POST("auth/recover")
  Call<RecoverPassResponseDM> recoverPassApi(@Body RecoverPassParamDM recoverPassParam);

    /*For  verify otp*/
  @POST("auth/verifyotp")
  Call<VerifyOtpResponseDM> verifyOtpApi(@Body VerifyOtpParamDM verifyOtpParam);


    /*For  verify registrationotp*/
  @POST("auth/verifyregistationotp")
  Call<RegistrationVerifyOtpDM> registrationVerifyOtpApi(@Header("Authorization") String token,
                                                         @Body RegistrationVerifyOTPParamDM registrationVerifyOTPParam);

    /*For  verify update password*/
  @POST("auth/updatepassword")
  Call<UpdatePasswordResponseDM> updatePasswordApi(@Body UpdatePasswordParamDM verifyOtpParam);

    /*For verify changepassword*/
  @POST("auth/changepassword")
  @FormUrlEncoded
  Call<ChangePasswordResponseDM> changePasswordApi(@Header("Authorization") String token,
                                                   @Field("old_password") String old_password,
                                                   @Field("new_password") String new_password);


    /*For cancel subscription*/
  @POST("billing/actions/free")
  Call<CancelExistingPlanResponseDM> cancelplansApi(@Header("Authorization") String token);

    /*For existing subscription*/
  @GET("billing/me")
  Call<SubscriptionExistingPlansResponseDM> subscriptionExistingplansApi(@Header("Authorization") String token);


    /*For new registration*/
  @POST("auth/register")
  Call<RegistrationDM> registrationApi(@Body RegistrationParamDM registrationParam);


}
