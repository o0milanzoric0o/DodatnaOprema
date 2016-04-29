package rs.dodatnaoprema.dodatnaoprema.models.articles.products_of_the_week;

/**
 * Created by 1 on 4/28/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("ArtikalId")
    @Expose
    private Integer ArtikalId;
    @SerializedName("ArtikalNaziv")
    @Expose
    private String ArtikalNaziv;
    @SerializedName("NaAkciji")
    @Expose
    private Integer NaAkciji;
    @SerializedName("urlArtiklaLink")
    @Expose
    private String urlArtiklaLink;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("cenaBroj")
    @Expose
    private String cenaBroj;
    @SerializedName("pravaMp")
    @Expose
    private String pravaMp;
    @SerializedName("pozovite")
    @Expose
    private Object pozovite;
    @SerializedName("brend")
    @Expose
    private String brend;
    @SerializedName("ArtikalKratakOpis")
    @Expose
    private Object ArtikalKratakOpis;
    @SerializedName("stanje")
    @Expose
    private Integer stanje;
    @SerializedName("mozedasekupi")
    @Expose
    private Integer mozedasekupi;
    @SerializedName("minimalnaKol")
    @Expose
    private Integer minimalnaKol;
    @SerializedName("tipUnit")
    @Expose
    private String tipUnit;
    @SerializedName("tipUnitcelo")
    @Expose
    private String tipUnitcelo;
    @SerializedName("ocenaut")
    @Expose
    private String ocenaut;
    @SerializedName("KategorijaArtikalaIdOS")
    @Expose
    private Integer KategorijaArtikalaIdOS;
    @SerializedName("KategorijaArtikalaNaziv")
    @Expose
    private String KategorijaArtikalaNaziv;
    @SerializedName("KategorijaArtikalaLink")
    @Expose
    private String KategorijaArtikalaLink;
    @SerializedName("mala_slika")
    @Expose
    private String malaSlika;
    @SerializedName("srednja_slika")
    @Expose
    private String srednjaSlika;

    /**
     *
     * @return
     *     The ArtikalId
     */
    public Integer getArtikalId() {
        return ArtikalId;
    }

    /**
     *
     * @param ArtikalId
     *     The ArtikalId
     */
    public void setArtikalId(Integer ArtikalId) {
        this.ArtikalId = ArtikalId;
    }

    /**
     *
     * @return
     *     The ArtikalNaziv
     */
    public String getArtikalNaziv() {
        return ArtikalNaziv;
    }

    /**
     *
     * @param ArtikalNaziv
     *     The ArtikalNaziv
     */
    public void setArtikalNaziv(String ArtikalNaziv) {
        this.ArtikalNaziv = ArtikalNaziv;
    }

    /**
     *
     * @return
     *     The NaAkciji
     */
    public Integer getNaAkciji() {
        return NaAkciji;
    }

    /**
     *
     * @param NaAkciji
     *     The NaAkciji
     */
    public void setNaAkciji(Integer NaAkciji) {
        this.NaAkciji = NaAkciji;
    }

    /**
     *
     * @return
     *     The urlArtiklaLink
     */
    public String getUrlArtiklaLink() {
        return urlArtiklaLink;
    }

    /**
     *
     * @param urlArtiklaLink
     *     The urlArtiklaLink
     */
    public void setUrlArtiklaLink(String urlArtiklaLink) {
        this.urlArtiklaLink = urlArtiklaLink;
    }

    /**
     *
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     *     The cenaBroj
     */
    public String getCenaBroj() {
        return cenaBroj;
    }

    /**
     *
     * @param cenaBroj
     *     The cenaBroj
     */
    public void setCenaBroj(String cenaBroj) {
        this.cenaBroj = cenaBroj;
    }

    /**
     *
     * @return
     *     The pravaMp
     */
    public String getPravaMp() {
        return pravaMp;
    }

    /**
     *
     * @param pravaMp
     *     The pravaMp
     */
    public void setPravaMp(String pravaMp) {
        this.pravaMp = pravaMp;
    }

    /**
     *
     * @return
     *     The pozovite
     */
    public Object getPozovite() {
        return pozovite;
    }

    /**
     *
     * @param pozovite
     *     The pozovite
     */
    public void setPozovite(Object pozovite) {
        this.pozovite = pozovite;
    }

    /**
     *
     * @return
     *     The brend
     */
    public String getBrend() {
        return brend;
    }

    /**
     *
     * @param brend
     *     The brend
     */
    public void setBrend(String brend) {
        this.brend = brend;
    }

    /**
     *
     * @return
     *     The ArtikalKratakOpis
     */
    public Object getArtikalKratakOpis() {
        return ArtikalKratakOpis;
    }

    /**
     *
     * @param ArtikalKratakOpis
     *     The ArtikalKratakOpis
     */
    public void setArtikalKratakOpis(Object ArtikalKratakOpis) {
        this.ArtikalKratakOpis = ArtikalKratakOpis;
    }

    /**
     *
     * @return
     *     The stanje
     */
    public Integer getStanje() {
        return stanje;
    }

    /**
     *
     * @param stanje
     *     The stanje
     */
    public void setStanje(Integer stanje) {
        this.stanje = stanje;
    }

    /**
     *
     * @return
     *     The mozedasekupi
     */
    public Integer getMozedasekupi() {
        return mozedasekupi;
    }

    /**
     *
     * @param mozedasekupi
     *     The mozedasekupi
     */
    public void setMozedasekupi(Integer mozedasekupi) {
        this.mozedasekupi = mozedasekupi;
    }

    /**
     *
     * @return
     *     The minimalnaKol
     */
    public Integer getMinimalnaKol() {
        return minimalnaKol;
    }

    /**
     *
     * @param minimalnaKol
     *     The minimalnaKol
     */
    public void setMinimalnaKol(Integer minimalnaKol) {
        this.minimalnaKol = minimalnaKol;
    }

    /**
     *
     * @return
     *     The tipUnit
     */
    public String getTipUnit() {
        return tipUnit;
    }

    /**
     *
     * @param tipUnit
     *     The tipUnit
     */
    public void setTipUnit(String tipUnit) {
        this.tipUnit = tipUnit;
    }

    /**
     *
     * @return
     *     The tipUnitcelo
     */
    public String getTipUnitcelo() {
        return tipUnitcelo;
    }

    /**
     *
     * @param tipUnitcelo
     *     The tipUnitcelo
     */
    public void setTipUnitcelo(String tipUnitcelo) {
        this.tipUnitcelo = tipUnitcelo;
    }

    /**
     *
     * @return
     *     The ocenaut
     */
    public String getOcenaut() {
        return ocenaut;
    }

    /**
     *
     * @param ocenaut
     *     The ocenaut
     */
    public void setOcenaut(String ocenaut) {
        this.ocenaut = ocenaut;
    }

    /**
     *
     * @return
     *     The KategorijaArtikalaIdOS
     */
    public Integer getKategorijaArtikalaIdOS() {
        return KategorijaArtikalaIdOS;
    }

    /**
     *
     * @param KategorijaArtikalaIdOS
     *     The KategorijaArtikalaIdOS
     */
    public void setKategorijaArtikalaIdOS(Integer KategorijaArtikalaIdOS) {
        this.KategorijaArtikalaIdOS = KategorijaArtikalaIdOS;
    }

    /**
     *
     * @return
     *     The KategorijaArtikalaNaziv
     */
    public String getKategorijaArtikalaNaziv() {
        return KategorijaArtikalaNaziv;
    }

    /**
     *
     * @param KategorijaArtikalaNaziv
     *     The KategorijaArtikalaNaziv
     */
    public void setKategorijaArtikalaNaziv(String KategorijaArtikalaNaziv) {
        this.KategorijaArtikalaNaziv = KategorijaArtikalaNaziv;
    }

    /**
     *
     * @return
     *     The KategorijaArtikalaLink
     */
    public String getKategorijaArtikalaLink() {
        return KategorijaArtikalaLink;
    }

    /**
     *
     * @param KategorijaArtikalaLink
     *     The KategorijaArtikalaLink
     */
    public void setKategorijaArtikalaLink(String KategorijaArtikalaLink) {
        this.KategorijaArtikalaLink = KategorijaArtikalaLink;
    }

    /**
     *
     * @return
     *     The malaSlika
     */
    public String getMalaSlika() {
        return malaSlika;
    }

    /**
     *
     * @param malaSlika
     *     The mala_slika
     */
    public void setMalaSlika(String malaSlika) {
        this.malaSlika = malaSlika;
    }

    /**
     *
     * @return
     *     The srednjaSlika
     */
    public String getSrednjaSlika() {
        return srednjaSlika;
    }

    /**
     *
     * @param srednjaSlika
     *     The srednja_slika
     */
    public void setSrednjaSlika(String srednjaSlika) {
        this.srednjaSlika = srednjaSlika;
    }

}
