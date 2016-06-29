package rs.dodatnaoprema.dodatnaoprema.models.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Slike implements Serializable {

    @SerializedName("IdArtikliSlike")
    @Expose
    private Integer idArtikliSlike;
    @SerializedName("glavna")
    @Expose
    private Integer glavna;
    @SerializedName("mala_slika")
    @Expose
    private String malaSlika;
    @SerializedName("srednja_slika")
    @Expose
    private String srednjaSlika;
    @SerializedName("velika_slika")
    @Expose
    private String velikaSlika;

    /**
     * @return The idArtikliSlike
     */
    public Integer getIdArtikliSlike() {
        return idArtikliSlike;
    }

    /**
     * @param idArtikliSlike The IdArtikliSlike
     */
    public void setIdArtikliSlike(Integer idArtikliSlike) {
        this.idArtikliSlike = idArtikliSlike;
    }

    /**
     * @return The glavna
     */
    public Integer getGlavna() {
        return glavna;
    }

    /**
     * @param glavna The glavna
     */
    public void setGlavna(Integer glavna) {
        this.glavna = glavna;
    }

    /**
     * @return The malaSlika
     */
    public String getMalaSlika() {
        return malaSlika;
    }

    /**
     * @param malaSlika The mala_slika
     */
    public void setMalaSlika(String malaSlika) {
        this.malaSlika = malaSlika;
    }

    /**
     * @return The srednjaSlika
     */
    public String getSrednjaSlika() {
        return srednjaSlika;
    }

    /**
     * @param srednjaSlika The srednja_slika
     */
    public void setSrednjaSlika(String srednjaSlika) {
        this.srednjaSlika = srednjaSlika;
    }

    /**
     * @return The velikaSlika
     */
    public String getVelikaSlika() {
        return velikaSlika;
    }

    /**
     * @param velikaSlika The velika_slika
     */
    public void setVelikaSlika(String velikaSlika) {
        this.velikaSlika = velikaSlika;
    }

}
