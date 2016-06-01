
package rs.dodatnaoprema.dodatnaoprema.models.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Spec implements Serializable{

    @SerializedName("imeSpecGrupe")
    @Expose
    private String imeSpecGrupe;
    @SerializedName("IdSpecGrupe")
    @Expose
    private Integer idSpecGrupe;
    @SerializedName("vredSpecGrupe")
    @Expose
    private String vredSpecGrupe;
    @SerializedName("IdSpecVrednosti")
    @Expose
    private Integer idSpecVrednosti;

    /**
     * @return The imeSpecGrupe
     */
    public String getImeSpecGrupe() {
        return imeSpecGrupe;
    }

    /**
     * @param imeSpecGrupe The imeSpecGrupe
     */
    public void setImeSpecGrupe(String imeSpecGrupe) {
        this.imeSpecGrupe = imeSpecGrupe;
    }

    /**
     * @return The idSpecGrupe
     */
    public Integer getIdSpecGrupe() {
        return idSpecGrupe;
    }

    /**
     * @param idSpecGrupe The IdSpecGrupe
     */
    public void setIdSpecGrupe(Integer idSpecGrupe) {
        this.idSpecGrupe = idSpecGrupe;
    }

    /**
     * @return The vredSpecGrupe
     */
    public String getVredSpecGrupe() {
        return vredSpecGrupe;
    }

    /**
     * @param vredSpecGrupe The vredSpecGrupe
     */
    public void setVredSpecGrupe(String vredSpecGrupe) {
        this.vredSpecGrupe = vredSpecGrupe;
    }

    /**
     * @return The idSpecVrednosti
     */
    public Integer getIdSpecVrednosti() {
        return idSpecVrednosti;
    }

    /**
     * @param idSpecVrednosti The IdSpecVrednosti
     */
    public void setIdSpecVrednosti(Integer idSpecVrednosti) {
        this.idSpecVrednosti = idSpecVrednosti;
    }

}
