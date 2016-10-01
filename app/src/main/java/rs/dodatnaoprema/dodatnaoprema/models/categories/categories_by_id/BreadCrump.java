package rs.dodatnaoprema.dodatnaoprema.models.categories.categories_by_id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BreadCrump implements Serializable{

    @SerializedName("BrendIme")
    @Expose
    private String ime;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("idBc")
    @Expose
    private Integer idBc;

    /**
     * @return The ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * @param ime The ime
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * @return The link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return The idBc
     */
    public Integer getIdBc() {
        return idBc;
    }

    /**
     * @param idBc The idBc
     */
    public void setIdBc(Integer idBc) {
        this.idBc = idBc;
    }
}
