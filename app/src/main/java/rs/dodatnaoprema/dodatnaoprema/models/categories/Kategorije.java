package rs.dodatnaoprema.dodatnaoprema.models.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Kategorije {

    @SerializedName("KategorijaArtikalaId")
    @Expose
    private Integer KategorijaArtikalaId;
    @SerializedName("ParentKategorijaArtikalaId")
    @Expose
    private Object ParentKategorijaArtikalaId;
    @SerializedName("Katsrblat")
    @Expose
    private String Katsrblat;
    @SerializedName("KategorijeVidljivZaMP")
    @Expose
    private Integer KategorijeVidljivZaMP;
    @SerializedName("KategorijaArtikalaSlika")
    @Expose
    private String KategorijaArtikalaSlika;
    @SerializedName("KategorijaArtikalaLink")
    @Expose
    private String KategorijaArtikalaLink;
    @SerializedName("KategorijaArtikalaActiveMasine")
    @Expose
    private Integer KategorijaArtikalaActiveMasine;
    @SerializedName("child")
    @Expose
    private List<Child> child = new ArrayList<>();

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
     * @return The ParentKategorijaArtikalaId
     */
    public Object getParentKategorijaArtikalaId() {
        return ParentKategorijaArtikalaId;
    }

    /**
     * @param ParentKategorijaArtikalaId The ParentKategorijaArtikalaId
     */
    public void setParentKategorijaArtikalaId(Object ParentKategorijaArtikalaId) {
        this.ParentKategorijaArtikalaId = ParentKategorijaArtikalaId;
    }

    /**
     * @return The Katsrblat
     */
    public String getKatsrblat() {
        return Katsrblat;
    }

    /**
     * @param Katsrblat The Katsrblat
     */
    public void setKatsrblat(String Katsrblat) {
        this.Katsrblat = Katsrblat;
    }

    /**
     * @return The KategorijeVidljivZaMP
     */
    public Integer getKategorijeVidljivZaMP() {
        return KategorijeVidljivZaMP;
    }

    /**
     * @param KategorijeVidljivZaMP The KategorijeVidljivZaMP
     */
    public void setKategorijeVidljivZaMP(Integer KategorijeVidljivZaMP) {
        this.KategorijeVidljivZaMP = KategorijeVidljivZaMP;
    }

    /**
     * @return The KategorijaArtikalaSlika
     */
    public String getKategorijaArtikalaSlika() {
        return KategorijaArtikalaSlika;
    }

    /**
     * @param KategorijaArtikalaSlika The KategorijaArtikalaSlika
     */
    public void setKategorijaArtikalaSlika(String KategorijaArtikalaSlika) {
        this.KategorijaArtikalaSlika = KategorijaArtikalaSlika;
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
     * @return The KategorijaArtikalaActiveMasine
     */
    public Integer getKategorijaArtikalaActiveMasine() {
        return KategorijaArtikalaActiveMasine;
    }

    /**
     * @param KategorijaArtikalaActiveMasine The KategorijaArtikalaActiveMasine
     */
    public void setKategorijaArtikalaActiveMasine(Integer KategorijaArtikalaActiveMasine) {
        this.KategorijaArtikalaActiveMasine = KategorijaArtikalaActiveMasine;
    }

    /**
     * @return The child
     */
    public List<Child> getChild() {
        return child;
    }

    /**
     * @param child The child
     */
    public void setChild(List<Child> child) {
        this.child = child;
    }

}