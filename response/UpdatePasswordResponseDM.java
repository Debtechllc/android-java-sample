package debtechllc.deb.sonderblu.response;

public class UpdatePasswordResponseDM {
    private VerifyOtpResponseDM.Response response;

    public UpdatePasswordResponseDM(VerifyOtpResponseDM.Response response) {
        this.response = response;
    }

    public VerifyOtpResponseDM.Response getResponse() {
        return response;
    }

    public void setResponse(VerifyOtpResponseDM.Response response) {
        this.response = response;
    }

    public class Response {
        private String error;
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
