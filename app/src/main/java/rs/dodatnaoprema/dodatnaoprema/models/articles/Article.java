package rs.dodatnaoprema.dodatnaoprema.models.articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Article implements Serializable {



    @SerializedName("ArtikalId")
    @Expose
    private Integer ArtikalId;
    @SerializedName("ArtikalNaziv")
    @Expose
    private String ArtikalNaziv;
    @SerializedName("ArtikalKratakOpis")
    @Expose
    private Object ArtikalKratakOpis;
    @SerializedName("OpisArtikliTekstovi")
    @Expose
    private String OpisArtikliTekstovi;
    @SerializedName("ArtikalNaAkciji")
    @Expose
    private Integer ArtikalNaAkciji;
    @SerializedName("urlArtiklaLink")
    @Expose
    private String urlArtiklaLink;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("cenaPrikaz")
    @Expose
    private String cenaPrikaz;
    @SerializedName("cenaPrikazBroj")
    @Expose
    private String cenaPrikazBroj;
    @SerializedName("cenaSamoBrojFormat")
    @Expose
    private String cenaSamoBrojFormat;
    @SerializedName("cenaPrikazExt")
    @Expose
    private String cenaPrikazExt;
    @SerializedName("pozovite")
    @Expose
    private Object pozovite;
    @SerializedName("BrendIme")
    @Expose
    private String BrendIme;
    @SerializedName("BrendId")
    @Expose
    private Integer BrendId;
    @SerializedName("TipUnit")
    @Expose
    private String TipUnit;
    @SerializedName("TipUnitCelo")
    @Expose
    private String TipUnitCelo;
    @SerializedName("MinimalnaKolArt")
    @Expose
    private Object MinimalnaKolArt;
    @SerializedName("slikaMain")
    @Expose
    private Object slikaMain;
    @SerializedName("stanje")
    @Expose
    private Integer stanje;
    @SerializedName("codeVendor")
    @Expose
    private String codeVendor;
    @SerializedName("mozedaseKupi")
    @Expose
    private Integer mozedaseKupi;
    @SerializedName("ocenaut")
    @Expose
    private Integer ocenaut;
    @SerializedName("KategorijaArtiklaNaziv")
    @Expose
    private String KategorijaArtiklaNaziv;
    @SerializedName("KategorijaArtikalId")
    @Expose
    private Integer KategorijaArtikalId;
    @SerializedName("KategorijaArtikalaLink")
    @Expose
    private String KategorijaArtikalaLink;
    @SerializedName("spec")
    @Expose
    private List<Object> spec = new ArrayList<Object>();
    @SerializedName("slike")
    @Expose
    private List<Pictures> slike = new ArrayList<Pictures>();

    /**
     * @return The ArtikalId
     */
    public Integer getArtikalId() {
        return ArtikalId;
    }

    /**
     * @param ArtikalId The ArtikalId
     */
    public void setArtikalId(Integer ArtikalId) {
        this.ArtikalId = ArtikalId;
    }

    /**
     * @return The ArtikalNaziv
     */
    public String getArtikalNaziv() {
        return ArtikalNaziv;
    }

    /**
     * @param ArtikalNaziv The ArtikalNaziv
     */
    public void setArtikalNaziv(String ArtikalNaziv) {
        this.ArtikalNaziv = ArtikalNaziv;
    }

    /**
     * @return The ArtikalKratakOpis
     */
    public Object getArtikalKratakOpis() {
        return ArtikalKratakOpis;
    }

    /**
     * @param ArtikalKratakOpis The ArtikalKratakOpis
     */
    public void setArtikalKratakOpis(Object ArtikalKratakOpis) {
        this.ArtikalKratakOpis = ArtikalKratakOpis;
    }

    /**
     *
     * @return
     * The OpisArtikliTekstovi
     */
    public String getOpisArtikliTekstovi() {
        return OpisArtikliTekstovi;
    }

    /**
     *
     * @param OpisArtikliTekstovi
     * The OpisArtikliTekstovi
     */
    public void setOpisArtikliTekstovi(String OpisArtikliTekstovi) {
        this.OpisArtikliTekstovi = OpisArtikliTekstovi;
    }

    /**
     * @return The ArtikalNaAkciji
     */
    public Integer getArtikalNaAkciji() {
        return ArtikalNaAkciji;
    }

    /**
     * @param ArtikalNaAkciji The ArtikalNaAkciji
     */
    public void setArtikalNaAkciji(Integer ArtikalNaAkciji) {
        this.ArtikalNaAkciji = ArtikalNaAkciji;
    }

    /**
     * @return The urlArtiklaLink
     */
    public String getUrlArtiklaLink() {
        return urlArtiklaLink;
    }

    /**
     * @param urlArtiklaLink The urlArtiklaLink
     */
    public void setUrlArtiklaLink(String urlArtiklaLink) {
        this.urlArtiklaLink = urlArtiklaLink;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The cenaPrikaz
     */
    public String getCenaPrikaz() {
        return cenaPrikaz;
    }

    /**
     *
     * @param cenaPrikaz
     * The cenaPrikaz
     */
    public void setCenaPrikaz(String cenaPrikaz) {
        this.cenaPrikaz = cenaPrikaz;
    }

    /**
     * @return The cenaPrikazBroj
     */
    public String getCenaPrikazBroj() {
        return cenaPrikazBroj;
    }

    /**
     * @param cenaPrikazBroj The cenaPrikazBroj
     */
    public void setCenaPrikazBroj(String cenaPrikazBroj) {
        this.cenaPrikazBroj = cenaPrikazBroj;
    }

    /**
     * @return The cenaSamoBrojFormat
     */
    public String getCenaSamoBrojFormat() {
        return cenaSamoBrojFormat;
    }

    /**
     * @param cenaSamoBrojFormat The cenaSamoBrojFormat
     */
    public void setCenaSamoBrojFormat(String cenaSamoBrojFormat) {
        this.cenaSamoBrojFormat = cenaSamoBrojFormat;
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
     * @return The pozovite
     */
    public Object getPozovite() {
        return pozovite;
    }

    /**
     * @param pozovite The pozovite
     */
    public void setPozovite(Object pozovite) {
        this.pozovite = pozovite;
    }

    /**
     * @return The BrendIme
     */
    public String getBrendIme() {
        return BrendIme;
    }

    /**
     * @param BrendIme The BrendIme
     */
    public void setBrendIme(String BrendIme) {
        this.BrendIme = BrendIme;
    }

    /**
     * @return The BrendId
     */
    public Integer getBrendId() {
        return BrendId;
    }

    /**
     * @param BrendId The BrendId
     */
    public void setBrendId(Integer BrendId) {
        this.BrendId = BrendId;
    }

    /**
     * @return The TipUnit
     */
    public String getTipUnit() {
        return TipUnit;
    }

    /**
     * @param TipUnit The TipUnit
     */
    public void setTipUnit(String TipUnit) {
        this.TipUnit = TipUnit;
    }

    /**
     * @return The TipUnitCelo
     */
    public String getTipUnitCelo() {
        return TipUnitCelo;
    }

    /**
     * @param TipUnitCelo The TipUnitCelo
     */
    public void setTipUnitCelo(String TipUnitCelo) {
        this.TipUnitCelo = TipUnitCelo;
    }

    /**
     *
     * @return
     * The MinimalnaKolArt
     */
    public Object getMinimalnaKolArt() {
        return MinimalnaKolArt;
    }

    /**
     *
     * @param MinimalnaKolArt
     * The MinimalnaKolArt
     */
    public void setMinimalnaKolArt(Object MinimalnaKolArt) {
        this.MinimalnaKolArt = MinimalnaKolArt;
    }

    /**
     *
     * @return
     * The slikaMain
     */
    public Object getSlikaMain() {
        return slikaMain;
    }

    /**
     *
     * @param slikaMain
     * The slikaMain
     */
    public void setSlikaMain(Object slikaMain) {
        this.slikaMain = slikaMain;
    }

    /**
     * @return The stanje
     */
    public Integer getStanje() {
        return stanje;
    }

    /**
     * @param stanje The stanje
     */
    public void setStanje(Integer stanje) {
        this.stanje = stanje;
    }

    /**
     * @return The codeVendor
     */
    public String getCodeVendor() {
        return codeVendor;
    }

    /**
     * @param codeVendor The codeVendor
     */
    public void setCodeVendor(String codeVendor) {
        this.codeVendor = codeVendor;
    }

    /**
     *
     * @return
     * The mozedaseKupi
     */
    public Integer getMozedaseKupi() {
        return mozedaseKupi;
    }

    /**
     *
     * @param mozedaseKupi
     * The mozedaseKupi
     */
    public void setMozedaseKupi(Integer mozedaseKupi) {
        this.mozedaseKupi = mozedaseKupi;
    }

    /**
     * @return The ocenaut
     */
    public Integer getOcenaut() {
        return ocenaut;
    }

    /**
     * @param ocenaut The ocenaut
     */
    public void setOcenaut(Integer ocenaut) {
        this.ocenaut = ocenaut;
    }

    /**
     * @return The KategorijaArtiklaNaziv
     */
    public String getKategorijaArtiklaNaziv() {
        return KategorijaArtiklaNaziv;
    }

    /**
     * @param KategorijaArtiklaNaziv The KategorijaArtiklaNaziv
     */
    public void setKategorijaArtiklaNaziv(String KategorijaArtiklaNaziv) {
        this.KategorijaArtiklaNaziv = KategorijaArtiklaNaziv;
    }

    /**
     * @return The KategorijaArtikalId
     */
    public Integer getKategorijaArtikalId() {
        return KategorijaArtikalId;
    }

    /**
     * @param KategorijaArtikalId The KategorijaArtikalId
     */
    public void setKategorijaArtikalId(Integer KategorijaArtikalId) {
        this.KategorijaArtikalId = KategorijaArtikalId;
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
     * @return The spec
     */
    public List<Object> getSpec() {
        return spec;
    }

    /**
     * @param spec The spec
     */
    public void setSpec(List<Object> spec) {
        this.spec = spec;
    }

    /**
     * @return The slike
     */
    public List<Pictures> getSlike() {
        return slike;
    }

    /**
     * @param slike The slike
     */
    public void setSlike(List<Pictures> slike) {
        this.slike = slike;
    }

}

