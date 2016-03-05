package rs.dodatnaoprema.dodatnaoprema.models.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slike {

    @SerializedName("IdArtikliSlike")
    @Expose
    private Integer IdArtikliSlike;
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
     * @return The IdArtikliSlike
     */
    public Integer getIdArtikliSlike() {
        return IdArtikliSlike;
    }

    /**
     * @param IdArtikliSlike The IdArtikliSlike
     */
    public void setIdArtikliSlike(Integer IdArtikliSlike) {
        this.IdArtikliSlike = IdArtikliSlike;
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