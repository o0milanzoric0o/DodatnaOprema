package rs.dodatnaoprema.dodatnaoprema.models.search;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;

public class Search implements Serializable{

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
    @SerializedName("artikli")
    @Expose
    private List<Article> artikli = new ArrayList<>();

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
     * @return The artikli
     */
    public List<Article> getArtikli() {
        return artikli;
    }


}

