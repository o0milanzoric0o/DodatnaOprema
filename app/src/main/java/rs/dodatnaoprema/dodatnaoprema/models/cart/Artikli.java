package rs.dodatnaoprema.dodatnaoprema.models.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Artikli implements Serializable {

    @SerializedName("ArtikalId")
    @Expose
    private Integer artikalId;
    @SerializedName("ArtikalNaziv")
    @Expose
    private String artikalNaziv;
    @SerializedName("ArtikalKratakOpis")
    @Expose
    private String artikalKratakOpis;
    @SerializedName("ArtikalNaAkciji")
    @Expose
    private Object artikalNaAkciji;
    @SerializedName("urlArtiklaLink")
    @Expose
    private String urlArtiklaLink;
    @SerializedName("cenaPrikaz")
    @Expose
    private String cenaPrikaz;
    @SerializedName("cenaPrikazBroj")
    @Expose
    private Double cenaPrikazBroj;
    @SerializedName("cenaSamoBrojFormat")
    @Expose
    private String cenaSamoBrojFormat;
    @SerializedName("cenaPrikazExt")
    @Expose
    private String cenaPrikazExt;
    @SerializedName("pozovite")
    @Expose
    private String pozovite;
    @SerializedName("BrendIme")
    @Expose
    private Object brendIme;
    @SerializedName("BrendId")
    @Expose
    private Object brendId;
    @SerializedName("TipUnit")
    @Expose
    private String tipUnit;
    @SerializedName("TipUnitCelo")
    @Expose
    private String tipUnitCelo;
    @SerializedName("MinimalnaKolArt")
    @Expose
    private Integer minimalnaKolArt;
    @SerializedName("slikaMain")
    @Expose
    private String slikaMain;
    @SerializedName("stanje")
    @Expose
    private Integer stanje;
    @SerializedName("codeVendor")
    @Expose
    private String codeVendor;
    @SerializedName("mozedasekupi")
    @Expose
    private Integer mozedasekupi;
    @SerializedName("ocenaut")
    @Expose
    private Object ocenaut;
    @SerializedName("KategorijaArtiklaNaziv")
    @Expose
    private Object kategorijaArtiklaNaziv;
    @SerializedName("KategorijaArtikalId")
    @Expose
    private Object kategorijaArtikalId;
    @SerializedName("KategorijaArtikalaLink")
    @Expose
    private Object kategorijaArtikalaLink;
    @SerializedName("korpa")
    @Expose
    private Korpa korpa;
    @SerializedName("spec")
    @Expose
    private List<Spec> spec = new ArrayList<Spec>();
    @SerializedName("slike")
    @Expose
    private List<Slike> slike = new ArrayList<Slike>();

    /**
     * @return The artikalId
     */
    public Integer getArtikalId() {
        return artikalId;
    }

    /**
     * @param artikalId The ArtikalId
     */
    public void setArtikalId(Integer artikalId) {
        this.artikalId = artikalId;
    }

    /**
     * @return The artikalNaziv
     */
    public String getArtikalNaziv() {
        return artikalNaziv;
    }

    /**
     * @param artikalNaziv The ArtikalNaziv
     */
    public void setArtikalNaziv(String artikalNaziv) {
        this.artikalNaziv = artikalNaziv;
    }

    /**
     * @return The artikalKratakOpis
     */
    public String getArtikalKratakOpis() {
        return artikalKratakOpis;
    }

    /**
     * @param artikalKratakOpis The ArtikalKratakOpis
     */
    public void setArtikalKratakOpis(String artikalKratakOpis) {
        this.artikalKratakOpis = artikalKratakOpis;
    }

    /**
     * @return The artikalNaAkciji
     */
    public Object getArtikalNaAkciji() {
        return artikalNaAkciji;
    }

    /**
     * @param artikalNaAkciji The ArtikalNaAkciji
     */
    public void setArtikalNaAkciji(Object artikalNaAkciji) {
        this.artikalNaAkciji = artikalNaAkciji;
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
     * @return The cenaPrikaz
     */
    public String getCenaPrikaz() {
        return cenaPrikaz;
    }

    /**
     * @param cenaPrikaz The cenaPrikaz
     */
    public void setCenaPrikaz(String cenaPrikaz) {
        this.cenaPrikaz = cenaPrikaz;
    }

    /**
     * @return The cenaPrikazBroj
     */
    public Double getCenaPrikazBroj() {
        return cenaPrikazBroj;
    }

    /**
     * @param cenaPrikazBroj The cenaPrikazBroj
     */
    public void setCenaPrikazBroj(Double cenaPrikazBroj) {
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
    public String getPozovite() {
        return pozovite;
    }

    /**
     * @param pozovite The pozovite
     */
    public void setPozovite(String pozovite) {
        this.pozovite = pozovite;
    }

    /**
     * @return The brendIme
     */
    public Object getBrendIme() {
        return brendIme;
    }

    /**
     * @param brendIme The BrendIme
     */
    public void setBrendIme(Object brendIme) {
        this.brendIme = brendIme;
    }

    /**
     * @return The brendId
     */
    public Object getBrendId() {
        return brendId;
    }

    /**
     * @param brendId The BrendId
     */
    public void setBrendId(Object brendId) {
        this.brendId = brendId;
    }

    /**
     * @return The tipUnit
     */
    public String getTipUnit() {
        return tipUnit;
    }

    /**
     * @param tipUnit The TipUnit
     */
    public void setTipUnit(String tipUnit) {
        this.tipUnit = tipUnit;
    }

    /**
     * @return The tipUnitCelo
     */
    public String getTipUnitCelo() {
        return tipUnitCelo;
    }

    /**
     * @param tipUnitCelo The TipUnitCelo
     */
    public void setTipUnitCelo(String tipUnitCelo) {
        this.tipUnitCelo = tipUnitCelo;
    }

    /**
     * @return The minimalnaKolArt
     */
    public Integer getMinimalnaKolArt() {
        return minimalnaKolArt;
    }

    /**
     * @param minimalnaKolArt The MinimalnaKolArt
     */
    public void setMinimalnaKolArt(Integer minimalnaKolArt) {
        this.minimalnaKolArt = minimalnaKolArt;
    }

    /**
     * @return The slikaMain
     */
    public String getSlikaMain() {
        return slikaMain;
    }

    /**
     * @param slikaMain The slikaMain
     */
    public void setSlikaMain(String slikaMain) {
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
     * @return The mozedasekupi
     */
    public Integer getMozedasekupi() {
        return mozedasekupi;
    }

    /**
     * @param mozedasekupi The mozedasekupi
     */
    public void setMozedasekupi(Integer mozedasekupi) {
        this.mozedasekupi = mozedasekupi;
    }

    /**
     * @return The ocenaut
     */
    public Object getOcenaut() {
        return ocenaut;
    }

    /**
     * @param ocenaut The ocenaut
     */
    public void setOcenaut(Object ocenaut) {
        this.ocenaut = ocenaut;
    }

    /**
     * @return The kategorijaArtiklaNaziv
     */
    public Object getKategorijaArtiklaNaziv() {
        return kategorijaArtiklaNaziv;
    }

    /**
     * @param kategorijaArtiklaNaziv The KategorijaArtiklaNaziv
     */
    public void setKategorijaArtiklaNaziv(Object kategorijaArtiklaNaziv) {
        this.kategorijaArtiklaNaziv = kategorijaArtiklaNaziv;
    }

    /**
     * @return The kategorijaArtikalId
     */
    public Object getKategorijaArtikalId() {
        return kategorijaArtikalId;
    }

    /**
     * @param kategorijaArtikalId The KategorijaArtikalId
     */
    public void setKategorijaArtikalId(Object kategorijaArtikalId) {
        this.kategorijaArtikalId = kategorijaArtikalId;
    }

    /**
     * @return The kategorijaArtikalaLink
     */
    public Object getKategorijaArtikalaLink() {
        return kategorijaArtikalaLink;
    }

    /**
     * @param kategorijaArtikalaLink The KategorijaArtikalaLink
     */
    public void setKategorijaArtikalaLink(Object kategorijaArtikalaLink) {
        this.kategorijaArtikalaLink = kategorijaArtikalaLink;
    }

    /**
     * @return The korpa
     */
    public Korpa getKorpa() {
        return korpa;
    }

    /**
     * @param korpa The korpa
     */
    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }

    /**
     * @return The spec
     */
    public List<Spec> getSpec() {
        return spec;
    }

    /**
     * @param spec The spec
     */
    public void setSpec(List<Spec> spec) {
        this.spec = spec;
    }

    /**
     * @return The slike
     */
    public List<Slike> getSlike() {
        return slike;
    }

    /**
     * @param slike The slike
     */
    public void setSlike(List<Slike> slike) {
        this.slike = slike;
    }

}
