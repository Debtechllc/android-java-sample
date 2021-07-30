package debtechllc.deb.sonderblu.response;

import com.google.gson.annotations.SerializedName;

public class RegistrationVerifyOtpDM {
    @SerializedName("user")
    private User user;
    @SerializedName("response")
    private Response response;

    public RegistrationVerifyOtpDM(User user, Response response) {
        this.user = user;
        this.response = response;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public static class User {
        @SerializedName("consented")
        private Boolean consented;
        @SerializedName("legalStatus")
        private String legalStatus;
        @SerializedName("username")
        private String username;
        @SerializedName("email")
        private String email;
        @SerializedName("id")
        private String id;
        @SerializedName("createdAt")
        private String createdAt;
        @SerializedName("updatedAt")
        private String updatedAt;

        public User(Boolean consented, String legalStatus, String username, String email, String id, String createdAt, String updatedAt) {
            this.consented = consented;
            this.legalStatus = legalStatus;
            this.username = username;
            this.email = email;
            this.id = id;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public Boolean getConsented() {
            return consented;
        }

        public void setConsented(Boolean consented) {
            this.consented = consented;
        }

        public String getLegalStatus() {
            return legalStatus;
        }

        public void setLegalStatus(String legalStatus) {
            this.legalStatus = legalStatus;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
    public static class Response {
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
