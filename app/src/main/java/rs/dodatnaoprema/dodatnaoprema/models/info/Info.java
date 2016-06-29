package rs.dodatnaoprema.dodatnaoprema.models.info;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
    @SerializedName("podaci")
    @Expose
    private Data podaci;

    /**
     * @return The tag
     */
    public String getTag() {
        return tag;
    }


    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }


    /**
     * @return The error
     */
    public Integer getError() {
        return error;
    }


    /**
     * @return The errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }


    /**
     * @return The podaci
     */
    public Data getPodaci() {
        return podaci;
    }

}

