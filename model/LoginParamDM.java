package debtechllc.deb.sonderblu.model;

public class LoginParamDM {
    private String login;
    private String password;
    private String deviceId;
    private String os;

    public LoginParamDM(String login, String password, String deviceId, String os) {
        this.login = login;
        this.password = password;
        this.deviceId = deviceId;
        this.os = os;
    }
}
