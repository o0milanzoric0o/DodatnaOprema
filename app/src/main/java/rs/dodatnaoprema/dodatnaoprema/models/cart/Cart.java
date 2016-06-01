
package rs.dodatnaoprema.dodatnaoprema.models.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{

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
    private List<Artikli> artikli = new ArrayList<Artikli>();
    @SerializedName("ukupnaCena")
    @Expose
    private Double ukupnaCena;
    @SerializedName("ukupnaKolicina")
    @Expose
    private Integer ukupnaKolicina;
    @SerializedName("cenaPrikazExt")
    @Expose
    private String cenaPrikazExt;
    @SerializedName("prevoz")
    @Expose
    private Integer prevoz;

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
     * @return The artikli
     */
    public List<Artikli> getArtikli() {
        return artikli;
    }

    /**
     * @param artikli The artikli
     */
    public void setArtikli(List<Artikli> artikli) {
        this.artikli = artikli;
    }

    /**
     * @return The ukupnaCena
     */
    public Double getUkupnaCena() {
        return ukupnaCena;
    }

    /**
     * @param ukupnaCena The ukupnaCena
     */
    public void setUkupnaCena(Double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    /**
     * @return The ukupnaKolicina
     */
    public Integer getUkupnaKolicina() {
        return ukupnaKolicina;
    }

    /**
     * @param ukupnaKolicina The ukupnaKolicina
     */
    public void setUkupnaKolicina(Integer ukupnaKolicina) {
        this.ukupnaKolicina = ukupnaKolicina;
    }

    /**
     * @return The cenaPrikazExt
     */
    public String getCenaPrikazExt() {
        return cenaPrikazExt;
    }

    /**
     * @param cenaPrikazExt The cenaPrikazExt
     */
    public void setCenaPrikazExt(String cenaPrikazExt) {
        this.cenaPrikazExt = cenaPrikazExt;
    }

    /**
     * @return The prevoz
     */
    public Integer getPrevoz() {
        return prevoz;
    }

    /**
     * @param prevoz The prevoz
     */
    public void setPrevoz(Integer prevoz) {
        this.prevoz = prevoz;
    }

}
