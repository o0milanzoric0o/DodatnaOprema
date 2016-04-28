package rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class CategorySpecification {
    @SerializedName("tag")
    private String tag;
    @SerializedName("success")
    private Boolean success;
    @SerializedName("error")
    private Integer error;
    @SerializedName("error_msg")
    private String errorMsg;
    @SerializedName("spec")
    private List<Spec> spec = new ArrayList<>();

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
     * The spec
     */
    public List<Spec> getSpec() {
        return spec;
    }

    /**
     *
     * @param spec
     * The spec
     */
    public void setSpec(List<Spec> spec) {
        this.spec = spec;
    }
}
