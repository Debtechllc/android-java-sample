package debtechllc.deb.sonderblu.retrofit;

import org.json.JSONObject;

import java.util.List;

public class APIError {

    private int statusCode;
    private String error;
    private String message;
    private JSONObject errors;

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String error() {
        return error;
    }
    public String message() {
        return message;
    }
    public JSONObject errors() {
        return errors;
    }
}
