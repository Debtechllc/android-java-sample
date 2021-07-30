package debtechllc.deb.sonderblu.response;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordResponseDM {
    @SerializedName("response")
    private Response response;

    public ChangePasswordResponseDM(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response{
        @SerializedName("error")
        private String error;
        @SerializedName("message")
        private String message;

        public Response(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
