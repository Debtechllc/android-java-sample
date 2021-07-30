package debtechllc.deb.sonderblu.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponseDM {
    @SerializedName("token")
    private String token;
    @SerializedName("user")
    private User user;
    @SerializedName("session")
    private Session session;

    public LoginResponseDM(String token, User user, Session session) {
        this.token = token;
        this.user = user;
        this.session = session;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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
    public static class Session {
        @SerializedName("deviceId")
        private String deviceId;
        @SerializedName("os")
        private String os;
        public Session(String deviceId, String os) {
            this.deviceId = deviceId;
            this.os = os;
        }
        public String getDeviceId() {
            return deviceId;
        }
        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }
        public String getOs() {
            return os;
        }
        public void setOs(String os) {
            this.os = os;
        }
    }
}
