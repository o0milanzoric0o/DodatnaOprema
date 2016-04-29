package rs.dodatnaoprema.dodatnaoprema.models.one_article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;

public class OneArticle implements Serializable {

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
    @SerializedName("artikal")
    @Expose
    private Article artikal;

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

    public OneArticle withTag(String tag) {
        this.tag = tag;
        return this;
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

    public OneArticle withSuccess(Boolean success) {
        this.success = success;
        return this;
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

    public OneArticle withError(Integer error) {
        this.error = error;
        return this;
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

    public OneArticle withErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    /**
     *
     * @return
     * The artikal
     */
    public Article getArtikal() {
        return artikal;
    }

    /**
     *
     * @param artikal
     * The artikal
     */
    public void setArtikal(Article artikal) {
        this.artikal = artikal;
    }

    public OneArticle withArtikal(Article artikal) {
        this.artikal = artikal;
        return this;
    }

}