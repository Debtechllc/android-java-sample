package debtechllc.deb.sonderblu.repository;
import android.app.ProgressDialog;
import android.util.Log;
import com.google.gson.Gson;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
import debtechllc.deb.sonderblu.retrofit.ApiRequest;
import debtechllc.deb.sonderblu.retrofit.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SonderBluRepository {
    private ApiRequest apiRequest;
    private MutableLiveData<SocialRegisterResponseDM> socialRegisterMutableLiveData;
    private MutableLiveData<LoginResponseDM> loginResponseLiveData;
    private MutableLiveData<LogOutResponseDM> logOutResponseDMMutableLiveData;
    private MutableLiveData<RecoverPassResponseDM> recoverPassResponseLiveData;
    private MutableLiveData<VerifyOtpResponseDM> verifyOtpResponseLiveData;
    private MutableLiveData<UpdatePasswordResponseDM> updatePassResponseLiveData;
    private MutableLiveData<ChangePasswordResponseDM> changePasswordResponseDMMutableLiveData;
    private MutableLiveData<SubscriptionExistingPlansResponseDM> subscriptionExistPlansResponseLiveData;
    private MutableLiveData<RegistrationDM> registerResponseLiveData;
    private MutableLiveData<RegistrationVerifyOtpDM> registerVerifyOTPResponseLiveData;
    private MutableLiveData<CancelExistingPlanResponseDM> cancelExistingPlanMutableLiveData;

    public SonderBluRepository() {
        subscriptionExistPlansResponseLiveData = new MutableLiveData<>();
        registerVerifyOTPResponseLiveData = new MutableLiveData<>();
        cancelExistingPlanMutableLiveData = new MutableLiveData<>();
        loginResponseLiveData = new MutableLiveData<>();
        logOutResponseDMMutableLiveData = new MutableLiveData<>();
        recoverPassResponseLiveData = new MutableLiveData<>();
        verifyOtpResponseLiveData = new MutableLiveData<>();
        updatePassResponseLiveData = new MutableLiveData<>();
        changePasswordResponseDMMutableLiveData = new MutableLiveData<>();
        socialRegisterMutableLiveData = new MutableLiveData<>();
        registerResponseLiveData = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }


    public void getUserRegistration(String name, String userName, String email, String password, ProgressDialog progress) {
        progress.show();
        RegistrationParamDM registrationParam=new RegistrationParamDM(name,userName,email,password);
        apiRequest.registrationApi(registrationParam)
                .enqueue(new Callback<RegistrationDM>() {
                    @Override
                    public void onResponse(Call<RegistrationDM> call, Response<RegistrationDM> response) {
                        if (response.isSuccessful() && response.body() != null)
                        {
                            registerResponseLiveData.postValue(response.body());
                            progress.dismiss();
                        }
                        else
                        {
                            progress.dismiss();
                            Log.d("errorBody", String.valueOf(response.errorBody()));
                        }
                    }
                    @Override
                    public void onFailure(Call<RegistrationDM> call, Throwable t) {
                        registerResponseLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }

    public void getSocialRegister(String name,String email,String provider,String socialId,ProgressDialog progress) {
        progress.show();
        Social_Register_ParamDM social_register_param=new Social_Register_ParamDM(name,email,provider,socialId);
        apiRequest.socialRegisterApi(social_register_param)
                .enqueue(new Callback<SocialRegisterResponseDM>() {
                    @Override
                    public void onResponse(Call<SocialRegisterResponseDM> call, Response<SocialRegisterResponseDM> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            socialRegisterMutableLiveData.postValue(response.body());
                            progress.dismiss();
                        }
                        else {
                            progress.dismiss();
                            Log.d("errorBody", String.valueOf(response.errorBody()));
                        }
                    }
                    @Override
                    public void onFailure(Call<SocialRegisterResponseDM> call, Throwable t) {
                        socialRegisterMutableLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }


    public void getUserLogin(String login_username, String password, String deviceId, String os, ProgressDialog progress) {
        progress.show();
        LoginParamDM loginParam=new LoginParamDM(login_username,password,deviceId,os);
        Gson gson=new Gson();
        String jsonLOginParam=gson.toJson(loginParam);
        apiRequest.loginApi(loginParam)
                .enqueue(new Callback<LoginResponseDM>() {
                    @Override
                    public void onResponse(Call<LoginResponseDM> call, Response<LoginResponseDM> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            loginResponseLiveData.postValue(response.body());
                            progress.dismiss();
                        }
                        else {
                             progress.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponseDM> call, Throwable t) {
                        loginResponseLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }

    public void logOutApi(String bearer_token, Boolean type, ProgressDialog progressDialog) {
        apiRequest.logOutApi(bearer_token, type)
                .enqueue(new Callback<LogOutResponseDM>() {
                    @Override
                    public void onResponse(Call<LogOutResponseDM> call, Response<LogOutResponseDM> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            logOutResponseDMMutableLiveData.postValue(response.body());
                        }
                        else {
                            Log.d("onProfileRsp", String.valueOf(response.errorBody()));
                        }
                    }
                    @Override
                    public void onFailure(Call<LogOutResponseDM> call, Throwable t) {
                        logOutResponseDMMutableLiveData.postValue(null);
                    }
                });
    }

    public void getRecoverPassApi(String token, String email, ProgressDialog progress) {
        progress.show();
        RecoverPassParamDM recoverPassParam=new RecoverPassParamDM(email);
        apiRequest.recoverPassApi(recoverPassParam)
                .enqueue(new Callback<RecoverPassResponseDM>() {
                    @Override
                    public void onResponse(Call<RecoverPassResponseDM> call, Response<RecoverPassResponseDM> response) {
                        if (response.isSuccessful() && response.body() != null)
                        {
                            recoverPassResponseLiveData.postValue(response.body());
                            progress.dismiss();
                        }
                        else
                        {
                            progress.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<RecoverPassResponseDM> call, Throwable t) {
                        recoverPassResponseLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }

    public void getVerifyOtpApi(String otp, String userId, ProgressDialog progress) {
        progress.show();
        VerifyOtpParamDM verifyOtpParam = new VerifyOtpParamDM(otp, userId);
        apiRequest.verifyOtpApi(verifyOtpParam)
                .enqueue(new Callback<VerifyOtpResponseDM>() {
                    @Override
                    public void onResponse(Call<VerifyOtpResponseDM> call, Response<VerifyOtpResponseDM> response) {
                        if (response.isSuccessful() && response.body() != null)
                        {
                            verifyOtpResponseLiveData.postValue(response.body());
                            progress.dismiss();
                        }
                        else
                        {
                            progress.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<VerifyOtpResponseDM> call, Throwable t) {
                        verifyOtpResponseLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }

    public void getRegistrationVerifyOtpApi(String token, String otp, ProgressDialog progress) {
        progress.show();
        RegistrationVerifyOTPParamDM registrationVerifyOTPParam = new RegistrationVerifyOTPParamDM(otp);
        Gson gson=new Gson();
        apiRequest.registrationVerifyOtpApi(token,registrationVerifyOTPParam)
                .enqueue(new Callback<RegistrationVerifyOtpDM>() {
                    @Override
                    public void onResponse(Call<RegistrationVerifyOtpDM> call, Response<RegistrationVerifyOtpDM> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            registerVerifyOTPResponseLiveData.postValue(response.body());
                            progress.dismiss();
                        }
                        else {
                            progress.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<RegistrationVerifyOtpDM> call, Throwable t) {
                        registerVerifyOTPResponseLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }

    public void getUpdatePassApi(String otp, String userId, ProgressDialog progress) {
        progress.show();
        UpdatePasswordParamDM updatePasswordParam = new UpdatePasswordParamDM(otp, userId);
        apiRequest.updatePasswordApi(updatePasswordParam)
                .enqueue(new Callback<UpdatePasswordResponseDM>() {
                    @Override
                    public void onResponse(Call<UpdatePasswordResponseDM> call, Response<UpdatePasswordResponseDM> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            updatePassResponseLiveData.postValue(response.body());
                            progress.dismiss();
                        }
                        else {
                            progress.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<UpdatePasswordResponseDM> call, Throwable t) {
                        updatePassResponseLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }

    public void changePasswordApi(String token, String oldPass, String newPass, ProgressDialog progress) {
        progress.show();
        apiRequest.changePasswordApi(token, oldPass, newPass)
                .enqueue(new Callback<ChangePasswordResponseDM>() {
                    @Override
                    public void onResponse(Call<ChangePasswordResponseDM> call, Response<ChangePasswordResponseDM> response) {
                        if (response.isSuccessful() && response.body() != null)
                        {
                            changePasswordResponseDMMutableLiveData.postValue(response.body());
                            progress.dismiss();
                        }
                        else
                        {
                            progress.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<ChangePasswordResponseDM> call, Throwable t) {
                        changePasswordResponseDMMutableLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }
    public void getSubscriptionExistingPlans(String token, ProgressDialog progress) {
        progress.show();
        apiRequest.subscriptionExistingplansApi("Bearer "+token)
                .enqueue(new Callback<SubscriptionExistingPlansResponseDM>() {
                    @Override
                    public void onResponse(Call<SubscriptionExistingPlansResponseDM> call, Response<SubscriptionExistingPlansResponseDM> response) {
                        if (response.code() == 200)
                        {
                            subscriptionExistPlansResponseLiveData.postValue(response.body());
                            progress.dismiss();
                        }
                        else
                        {
                            progress.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<SubscriptionExistingPlansResponseDM> call, Throwable t) {
                        subscriptionExistPlansResponseLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }


    public void getCancelExistingPlans(String token, ProgressDialog progress) {
        progress.show();
        apiRequest.cancelplansApi("Bearer "+token)
                .enqueue(new Callback<CancelExistingPlanResponseDM>() {
                    @Override
                    public void onResponse(Call<CancelExistingPlanResponseDM> call, Response<CancelExistingPlanResponseDM> response) {
                        cancelExistingPlanMutableLiveData.postValue(response.body());
                        progress.dismiss();
                    }
                    @Override
                    public void onFailure(Call<CancelExistingPlanResponseDM> call, Throwable t) {
                        cancelExistingPlanMutableLiveData.postValue(null);
                        progress.dismiss();
                    }
                });
    }
    public LiveData<SocialRegisterResponseDM> getSocialRegisterResponseLiveData() { return socialRegisterMutableLiveData; }
    public LiveData<LoginResponseDM> getLoginResponseLiveData() { return loginResponseLiveData; }
    public LiveData<LogOutResponseDM> getLogOutResponseLiveData() { return logOutResponseDMMutableLiveData; }
    public LiveData<RecoverPassResponseDM> getRecoverPassResponseLiveData() { return recoverPassResponseLiveData; }
    public LiveData<VerifyOtpResponseDM> getVerifyOtpResponseLiveData() { return verifyOtpResponseLiveData; }
    public LiveData<UpdatePasswordResponseDM> getUpdatePassResponseLiveData() { return updatePassResponseLiveData; }
    public LiveData<ChangePasswordResponseDM> getChangePassResponseLiveData() { return changePasswordResponseDMMutableLiveData; }
    public LiveData<SubscriptionExistingPlansResponseDM> getSubscriptionExistingPlansResponseLiveData() { return subscriptionExistPlansResponseLiveData; }
    public LiveData<RegistrationDM> getRegisterResponseLiveData() { return registerResponseLiveData; }
    public LiveData<RegistrationVerifyOtpDM> getRegisterVerifyOTPResponseLiveData() { return registerVerifyOTPResponseLiveData; }
    public LiveData<CancelExistingPlanResponseDM> getCancelExistingPlanResponseLiveData() { return cancelExistingPlanMutableLiveData; }
}