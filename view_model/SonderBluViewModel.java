package debtechllc.deb.sonderblu.view_model;
import android.app.Application;
import android.app.ProgressDialog;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import debtechllc.deb.sonderblu.response.CancelExistingPlanResponseDM;
import debtechllc.deb.sonderblu.response.ChangePasswordResponseDM;
import debtechllc.deb.sonderblu.response.LogOutResponseDM;
import debtechllc.deb.sonderblu.response.LoginResponseDM;
import debtechllc.deb.sonderblu.response.RecoverPassResponseDM;
import debtechllc.deb.sonderblu.response.RegistrationDM;
import debtechllc.deb.sonderblu.repository.SonderBluRepository;
import debtechllc.deb.sonderblu.response.RegistrationVerifyOtpDM;
import debtechllc.deb.sonderblu.response.SocialRegisterResponseDM;
import debtechllc.deb.sonderblu.response.SubscriptionExistingPlansResponseDM;
import debtechllc.deb.sonderblu.response.UpdatePasswordResponseDM;
import debtechllc.deb.sonderblu.response.VerifyOtpResponseDM;
public class SonderBluViewModel extends AndroidViewModel {
    private SonderBluRepository sendChatRepository;
    private LiveData<LoginResponseDM> loginResponseLiveData;
    private LiveData<LogOutResponseDM> logOutResponseDMLiveData;
    private LiveData<SubscriptionExistingPlansResponseDM> subscriptionExitsPlanResponseLiveData;
    private LiveData<CancelExistingPlanResponseDM> cancelExistingPlanResponseLiveData;
    private LiveData<RecoverPassResponseDM> recoverPassResponseLiveData;
    private LiveData<VerifyOtpResponseDM> verifyOtpResponseLiveData;
    private LiveData<UpdatePasswordResponseDM> updatePassResponseLiveData;
    private LiveData<ChangePasswordResponseDM> changePasswordResponseDMLiveData;
    private LiveData<SocialRegisterResponseDM> socialRegisterResponseLiveData;
    private LiveData<RegistrationDM> registrationResponseLiveData;
    private LiveData<RegistrationVerifyOtpDM> registrationVerifyOTPResponseLiveData;

    public SonderBluViewModel(@NonNull Application application) {
        super(application);
    }

    public void initLogin() {
        sendChatRepository = new SonderBluRepository();
        loginResponseLiveData = sendChatRepository.getLoginResponseLiveData();
    }

    public void initLogOut() {
        sendChatRepository = new SonderBluRepository();
        logOutResponseDMLiveData = sendChatRepository.getLogOutResponseLiveData();
    }

    public void initRecoverPass() {
        sendChatRepository = new SonderBluRepository();
        recoverPassResponseLiveData = sendChatRepository.getRecoverPassResponseLiveData();
    }

    public void initVerifyOtpPass() {
        sendChatRepository = new SonderBluRepository();
        verifyOtpResponseLiveData = sendChatRepository.getVerifyOtpResponseLiveData();
    }

    public void initUpdatePassword() {
        sendChatRepository = new SonderBluRepository();
        updatePassResponseLiveData = sendChatRepository.getUpdatePassResponseLiveData();
    }

    public void initChangePassword() {
        sendChatRepository = new SonderBluRepository();
        changePasswordResponseDMLiveData = sendChatRepository.getChangePassResponseLiveData();
    }

    public void initSubscriptionExistPlans() {
        sendChatRepository = new SonderBluRepository();
        subscriptionExitsPlanResponseLiveData = sendChatRepository.getSubscriptionExistingPlansResponseLiveData();
    }
    public void initCancelExistingPlans() {
        sendChatRepository = new SonderBluRepository();
        cancelExistingPlanResponseLiveData = sendChatRepository.getCancelExistingPlanResponseLiveData();
    }

    public void initSocialRegister() {
        sendChatRepository = new SonderBluRepository();
        socialRegisterResponseLiveData = sendChatRepository.getSocialRegisterResponseLiveData();
    }

    public void initRegistration() {
        sendChatRepository = new SonderBluRepository();
        registrationResponseLiveData = sendChatRepository.getRegisterResponseLiveData();
    }

    public void initRegistrationVerifyOTP() {
        sendChatRepository = new SonderBluRepository();
        registrationVerifyOTPResponseLiveData = sendChatRepository.getRegisterVerifyOTPResponseLiveData();
    }

    public void getUserLogin(ArrayList<String> params, ProgressDialog progress) {
        sendChatRepository.getUserLogin(params.get(0), params.get(1), params.get(2), params.get(3), progress);
    }

    public void getUserLogOut(String token, Boolean type, ProgressDialog progress) {
        sendChatRepository.logOutApi(token, type, progress);
    }

    public void getRecoverPass(ArrayList<String> params, ProgressDialog progress) {
        sendChatRepository.getRecoverPassApi(params.get(0), params.get(1), progress);
    }

    public void getVerifyOtp(ArrayList<String> params, ProgressDialog progress) {
        sendChatRepository.getVerifyOtpApi(params.get(0), params.get(1), progress);
    }

    public void getRegistrationVerifyOtp(ArrayList<String> params, ProgressDialog progress) {
        sendChatRepository.getRegistrationVerifyOtpApi(params.get(0), params.get(1), progress);
    }

    public void getUpdatePass(ArrayList<String> params, ProgressDialog progress) {
        sendChatRepository.getUpdatePassApi(params.get(0), params.get(1), progress);
    }

    public void getChangePass(String token, String oldPass, String newPass, ProgressDialog progress) {
        sendChatRepository.changePasswordApi(token, oldPass, newPass, progress);
    }

    public void getSubscriptionExistingPlans(ArrayList<String> params, ProgressDialog progress) {
        sendChatRepository.getSubscriptionExistingPlans(params.get(0), progress);
    }

    public void getCancelExistingPlans(ArrayList<String> params, ProgressDialog progress) {
        sendChatRepository.getCancelExistingPlans(params.get(0), progress);
    }

    public void getSocialRegister(ArrayList<String> params, ProgressDialog progress) {
        sendChatRepository.getSocialRegister(params.get(0), params.get(1), params.get(2), params.get(3), progress);
    }

    public void getRegistrationUser(ArrayList<String> params, ProgressDialog progress) {
        sendChatRepository.getUserRegistration(params.get(0), params.get(1), params.get(2), params.get(3), progress);
    }

    public LiveData<SocialRegisterResponseDM> getSocialRegisterResponseLiveData() {
        return socialRegisterResponseLiveData;
    }

    public LiveData<LoginResponseDM> getLoginResponseLiveData() {
        return loginResponseLiveData;
    }

    public LiveData<LogOutResponseDM> getLogOutResponseLiveData() {
        return logOutResponseDMLiveData;
    }

    public LiveData<RegistrationDM> getRegistrationResponseLiveData() {
        return registrationResponseLiveData;
    }

    public LiveData<RegistrationVerifyOtpDM> getRegistrationVerifyOTPResponseLiveData() {
        return registrationVerifyOTPResponseLiveData;
    }

    public LiveData<RecoverPassResponseDM> getRecoverPassResponseLiveData() {
        return recoverPassResponseLiveData;
    }

    public LiveData<VerifyOtpResponseDM> getVerifyOtpResponseLiveData() {
        return verifyOtpResponseLiveData;
    }

    public LiveData<UpdatePasswordResponseDM> getUpdatePassResponseLiveData() {
        return updatePassResponseLiveData;
    }

    public LiveData<ChangePasswordResponseDM> getChangePassResponseLiveData() {
        return changePasswordResponseDMLiveData;
    }

    public LiveData<SubscriptionExistingPlansResponseDM> getSubscriptionExistPlansResponseLiveData() {
        return subscriptionExitsPlanResponseLiveData;
    }

    public LiveData<CancelExistingPlanResponseDM> getCancelExistingPlansResponseLiveData() {
        return cancelExistingPlanResponseLiveData;
    }

}

