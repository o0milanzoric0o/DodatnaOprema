package rs.dodatnaoprema.dodatnaoprema.models.articles;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArticleSpec implements Serializable{

    @SerializedName("ImeSpecGrupe")
    @Expose
    private String imeSpecGrupe;
    @SerializedName("IdSpecGrupe")
    @Expose
    private Integer idSpecGrupe;
    @SerializedName("VredSpecGrupe")
    @Expose
    private String vredSpecGrupe;
    @SerializedName("IdSpecVrednosti")
    @Expose
    private Integer idSpecVrednosti;

    /**
     *
     * @return
     * The imeSpecGrupe
     */
    public String getImeSpecGrupe() {
        return imeSpecGrupe;
    }

    /**
     *
     * @param imeSpecGrupe
     * The ImeSpecGrupe
     */
    public void setImeSpecGrupe(String imeSpecGrupe) {
        this.imeSpecGrupe = imeSpecGrupe;
    }

    /**
     *
     * @return
     * The idSpecGrupe
     */
    public Integer getIdSpecGrupe() {
        return idSpecGrupe;
    }

    /**
     *
     * @param idSpecGrupe
     * The IdSpecGrupe
     */
    public void setIdSpecGrupe(Integer idSpecGrupe) {
        this.idSpecGrupe = idSpecGrupe;
    }

    /**
     *
     * @return
     * The vredSpecGrupe
     */
    public String getVredSpecGrupe() {
        return vredSpecGrupe;
    }

    /**
     *
     * @param vredSpecGrupe
     * The VredSpecGrupe
     */
    public void setVredSpecGrupe(String vredSpecGrupe) {
        this.vredSpecGrupe = vredSpecGrupe;
    }

    /**
     *
     * @return
     * The idSpecVrednosti
     */
    public Integer getIdSpecVrednosti() {
        return idSpecVrednosti;
    }

    /**
     *
     * @param idSpecVrednosti
     * The IdSpecVrednosti
     */
    public void setIdSpecVrednosti(Integer idSpecVrednosti) {
        this.idSpecVrednosti = idSpecVrednosti;
    }

}