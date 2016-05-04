package rs.dodatnaoprema.dodatnaoprema.models.articles.brands_subcategoty;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.articles.Brendovus;

public class BrandsSubcategory {

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
    @SerializedName("brendovi")
    @Expose
    private List<Brendovus> brendovi = new ArrayList<Brendovus>();

    /**
     *
     * @return
     * The tag
     */
    public String getTag() {
        return tag;
    }

    /**
     *
     * @param tag
     * The tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     *
     * @return
     * The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     * The error
     */
    public Integer getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(Integer error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     *
     * @param errorMsg
     * The error_msg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     *
     * @return
     * The brendovi
     */
    public List<Brendovus> getBrendovi() {
        return brendovi;
    }

    /**
     *
     * @param brendovi
     * The brendovi
     */
    public void setBrendovi(List<Brendovus> brendovi) {
        this.brendovi = brendovi;
    }

}
