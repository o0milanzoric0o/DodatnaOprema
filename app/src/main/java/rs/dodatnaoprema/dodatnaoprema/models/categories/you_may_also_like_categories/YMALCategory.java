
package rs.dodatnaoprema.dodatnaoprema.models.categories.you_may_also_like_categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class YMALCategory implements Serializable {

    @SerializedName("KategorijaArtikalaId")
    @Expose
    private Integer KategorijaArtikalaId;
    @SerializedName("NazivKategorije")
    @Expose
    private String NazivKategorije;
    @SerializedName("KategorijaArtikalaLink")
    @Expose
    private String KategorijaArtikalaLink;
    @SerializedName("brojpregleda")
    @Expose
    private String brojpregleda;

    /**
     * @return The KategorijaArtikalaId
     */
    public Integer getKategorijaArtikalaId() {
        return KategorijaArtikalaId;
    }

    /**
     * @param KategorijaArtikalaId The KategorijaArtikalaId
     */
    public void setKategorijaArtikalaId(Integer KategorijaArtikalaId) {
        this.KategorijaArtikalaId = KategorijaArtikalaId;
    }

    /**
     * @return The NazivKategorije
     */
    public String getNazivKategorije() {
        return NazivKategorije;
    }

    /**
     * @param NazivKategorije The NazivKategorije
     */
    public void setNazivKategorije(String NazivKategorije) {
        this.NazivKategorije = NazivKategorije;
    }

    /**
     * @return The KategorijaArtikalaLink
     */
    public String getKategorijaArtikalaLink() {
        return KategorijaArtikalaLink;
    }

    /**
     * @param KategorijaArtikalaLink The KategorijaArtikalaLink
     */
    public void setKategorijaArtikalaLink(String KategorijaArtikalaLink) {
        this.KategorijaArtikalaLink = KategorijaArtikalaLink;
    }

    /**
     * @return The brojpregleda
     */
    public String getBrojpregleda() {
        return brojpregleda;
    }

    /**
     * @param brojpregleda The brojpregleda
     */
    public void setBrojpregleda(String brojpregleda) {
        this.brojpregleda = brojpregleda;
    }

}
