package debtechllc.deb.sonderblu.model;

public class VerifyOtpParamDM {
    private String otp;
    private String userId;

    public VerifyOtpParamDM(String otp, String userId) {
        this.otp = otp;
        this.userId = userId;
    }
}
