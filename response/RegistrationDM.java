package debtechllc.deb.sonderblu.response;

import com.google.gson.annotations.SerializedName;

public class RegistrationDM {
    @SerializedName("token")
    private String token;
    @SerializedName("message")
    private String message;

    public RegistrationDM(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
