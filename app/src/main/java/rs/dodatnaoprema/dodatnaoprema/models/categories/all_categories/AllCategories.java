package rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class AllCategories {

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
    @SerializedName("kategorije")
    @Expose
    private List<Category> kategorije = new ArrayList<>();

    /**
     * @return The tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag The tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return The error
     */
    public Integer getError() {
        return error;
    }

    /**
     * @param error The error
     */
    public void setError(Integer error) {
        this.error = error;
    }

    /**
     * @return The errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg The error_msg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return The kategorije
     */
    public List<Category> getKategorije() {
        return kategorije;
    }

    /**
     * @param kategorije The kategorije
     */
    public void setKategorije(List<Category> kategorije) {
        this.kategorije = kategorije;
    }

}