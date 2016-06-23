package rs.dodatnaoprema.dodatnaoprema.models.categories.categories_by_id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Child;

public class CategoriesByID {

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
    @SerializedName("breadCrump")
    @Expose
    private List<BreadCrump> breadCrump = new ArrayList<BreadCrump>();
    @SerializedName("kategorije")
    @Expose
    private List<List<Child>> kategorije = new ArrayList<List<Child>>();

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
     * @return The breadCrump
     */
    public List<BreadCrump> getBreadCrump() {
        return breadCrump;
    }

    /**
     * @param breadCrump The breadCrump
     */
    public void setBreadCrump(List<BreadCrump> breadCrump) {
        this.breadCrump = breadCrump;
    }

    /**
     * @return The kategorije
     */
    public List<List<Child>> getKategorije() {
        return kategorije;
    }

    /**
     * @param kategorije The kategorije
     */
    public void setKategorije(List<List<Child>> kategorije) {
        this.kategorije = kategorije;
    }

}
