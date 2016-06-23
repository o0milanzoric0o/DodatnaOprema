package rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Child implements Serializable{

    @SerializedName("KategorijaArtikalaId")
    @Expose
    private Integer kategorijaArtikalaId;
    @SerializedName("ParentKategorijaArtikalaId")
    @Expose
    private Object parentKategorijaArtikalaId;
    @SerializedName("KatIme")
    @Expose
    private String katIme;
    @SerializedName("KategorijeVidljivZaMP")
    @Expose
    private Integer kategorijeVidljivZaMP;
    @SerializedName("KategorijaArtikalaSlika")
    @Expose
    private String kategorijaArtikalaSlika;
    @SerializedName("KategorijaArtikalaLink")
    @Expose
    private String kategorijaArtikalaLink;
    @SerializedName("KategorijaArtikalaActiveMasine")
    @Expose
    private Integer kategorijaArtikalaActiveMasine;
    @SerializedName("KategorijaArtikalaActive")
    @Expose
    private Integer kategorijaArtikalaActive;
    @SerializedName("daLiImaPodKat")
    @Expose
    private Integer daLiImaPodKat;
    @SerializedName("KategorijaArtikalaMesto")
    @Expose
    private Integer kategorijaArtikalaMesto;

    /**
     *
     * @return
     * The kategorijaArtikalaId
     */
    public Integer getKategorijaArtikalaId() {
        return kategorijaArtikalaId;
    }

    /**
     *
     * @param kategorijaArtikalaId
     * The KategorijaArtikalaId
     */
    public void setKategorijaArtikalaId(Integer kategorijaArtikalaId) {
        this.kategorijaArtikalaId = kategorijaArtikalaId;
    }

    /**
     *
     * @return
     * The parentKategorijaArtikalaId
     */
    public Object getParentKategorijaArtikalaId() {
        return parentKategorijaArtikalaId;
    }

    /**
     *
     * @param parentKategorijaArtikalaId
     * The ParentKategorijaArtikalaId
     */
    public void setParentKategorijaArtikalaId(Object parentKategorijaArtikalaId) {
        this.parentKategorijaArtikalaId = parentKategorijaArtikalaId;
    }

    /**
     *
     * @return
     * The katIme
     */
    public String getKatIme() {
        return katIme;
    }

    /**
     *
     * @param katIme
     * The KatIme
     */
    public void setKatIme(String katIme) {
        this.katIme = katIme;
    }

    /**
     *
     * @return
     * The kategorijeVidljivZaMP
     */
    public Integer getKategorijeVidljivZaMP() {
        return kategorijeVidljivZaMP;
    }

    /**
     *
     * @param kategorijeVidljivZaMP
     * The KategorijeVidljivZaMP
     */
    public void setKategorijeVidljivZaMP(Integer kategorijeVidljivZaMP) {
        this.kategorijeVidljivZaMP = kategorijeVidljivZaMP;
    }

    /**
     *
     * @return
     * The kategorijaArtikalaSlika
     */
    public String getKategorijaArtikalaSlika() {
        return kategorijaArtikalaSlika;
    }

    /**
     *
     * @param kategorijaArtikalaSlika
     * The KategorijaArtikalaSlika
     */
    public void setKategorijaArtikalaSlika(String kategorijaArtikalaSlika) {
        this.kategorijaArtikalaSlika = kategorijaArtikalaSlika;
    }

    /**
     *
     * @return
     * The kategorijaArtikalaLink
     */
    public String getKategorijaArtikalaLink() {
        return kategorijaArtikalaLink;
    }

    /**
     *
     * @param kategorijaArtikalaLink
     * The KategorijaArtikalaLink
     */
    public void setKategorijaArtikalaLink(String kategorijaArtikalaLink) {
        this.kategorijaArtikalaLink = kategorijaArtikalaLink;
    }

    /**
     *
     * @return
     * The kategorijaArtikalaActiveMasine
     */
    public Integer getKategorijaArtikalaActiveMasine() {
        return kategorijaArtikalaActiveMasine;
    }

    /**
     *
     * @param kategorijaArtikalaActiveMasine
     * The KategorijaArtikalaActiveMasine
     */
    public void setKategorijaArtikalaActiveMasine(Integer kategorijaArtikalaActiveMasine) {
        this.kategorijaArtikalaActiveMasine = kategorijaArtikalaActiveMasine;
    }

    /**
     *
     * @return
     * The kategorijaArtikalaActive
     */
    public Integer getKategorijaArtikalaActive() {
        return kategorijaArtikalaActive;
    }

    /**
     *
     * @param kategorijaArtikalaActive
     * The KategorijaArtikalaActive
     */
    public void setKategorijaArtikalaActive(Integer kategorijaArtikalaActive) {
        this.kategorijaArtikalaActive = kategorijaArtikalaActive;
    }

    /**
     *
     * @return
     * The daLiImaPodKat
     */
    public Integer getDaLiImaPodKat() {
        return daLiImaPodKat;
    }

    /**
     *
     * @param daLiImaPodKat
     * The daLiImaPodKat
     */
    public void setDaLiImaPodKat(Integer daLiImaPodKat) {
        this.daLiImaPodKat = daLiImaPodKat;
    }

    /**
     *
     * @return
     * The kategorijaArtikalaMesto
     */
    public Integer getKategorijaArtikalaMesto() {
        return kategorijaArtikalaMesto;
    }

    /**
     *
     * @param kategorijaArtikalaMesto
     * The KategorijaArtikalaMesto
     */
    public void setKategorijaArtikalaMesto(Integer kategorijaArtikalaMesto) {
        this.kategorijaArtikalaMesto = kategorijaArtikalaMesto;
    }



}