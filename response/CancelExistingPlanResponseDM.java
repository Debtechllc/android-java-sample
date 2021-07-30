package debtechllc.deb.sonderblu.response;

import com.google.gson.annotations.SerializedName;

public class CancelExistingPlanResponseDM {
    @SerializedName("success")
    private boolean success;
    public CancelExistingPlanResponseDM(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
