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
    private Integer MinimalnaKolArt;
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
    private List<ArticleSpec> spec = new ArrayList<ArticleSpec>();
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
     * @return The ArtikalNaziv
     */
    public String getArtikalNaziv() {
        return ArtikalNaziv;
    }

    /**
     * @return The ArtikalKratakOpis
     */
    public Object getArtikalKratakOpis() {
        return ArtikalKratakOpis;
    }

    /**
     * @return The OpisArtikliTekstovi
     */
    public String getOpisArtikliTekstovi() {
        return OpisArtikliTekstovi;
    }

    /**
     * @return The ArtikalNaAkciji
     */
    public Integer getArtikalNaAkciji() {
        return ArtikalNaAkciji;
    }

    /**
     * @return The urlArtiklaLink
     */
    public String getUrlArtiklaLink() {
        return urlArtiklaLink;
    }

    /**
     * @return The link
     */
    public String getLink() {
        return link;
    }

    /**
     * @return The cenaPrikaz
     */
    public String getCenaPrikaz() {
        return cenaPrikaz;
    }

    /**
     * @return The cenaPrikazBroj
     */
    public String getCenaPrikazBroj() {
        return cenaPrikazBroj;
    }

    /**
     * @return The cenaSamoBrojFormat
     */
    public String getCenaSamoBrojFormat() {
        return cenaSamoBrojFormat;
    }

    /**
     * @return The cenaPrikazExt
     */
    public String getCenaPrikazExt() {
        return cenaPrikazExt;
    }

    /**
     * @return The pozovite
     */
    public Object getPozovite() {
        return pozovite;
    }

    /**
     * @return The BrendIme
     */
    public String getBrendIme() {
        return BrendIme;
    }

    /**
     * @return The BrendId
     */
    public Integer getBrendId() {
        return BrendId;
    }

    /**
     * @return The TipUnit
     */
    public String getTipUnit() {
        return TipUnit;
    }

    /**
     * @return The TipUnitCelo
     */
    public String getTipUnitCelo() {
        return TipUnitCelo;
    }

    /**
     * @return The MinimalnaKolArt
     */
    public Integer getMinimalnaKolArt() {
        return MinimalnaKolArt;
    }

    /**
     * @return The slikaMain
     */
    public Object getSlikaMain() {
        return slikaMain;
    }

    /**
     * @return The stanje
     */
    public Integer getStanje() {
        return stanje;
    }

    /**
     * @return The codeVendor
     */
    public String getCodeVendor() {
        return codeVendor;
    }

    /**
     * @return The mozedaseKupi
     */
    public Integer getMozedaseKupi() {
        return mozedaseKupi;
    }

    /**
     * @return The ocenaut
     */
    public Integer getOcenaut() {
        return ocenaut;
    }

    /**
     * @return The KategorijaArtiklaNaziv
     */
    public String getKategorijaArtiklaNaziv() {
        return KategorijaArtiklaNaziv;
    }

    /**
     * @return The KategorijaArtikalId
     */
    public Integer getKategorijaArtikalId() {
        return KategorijaArtikalId;
    }

    /**
     * @return The KategorijaArtikalaLink
     */
    public String getKategorijaArtikalaLink() {
        return KategorijaArtikalaLink;
    }

    /**
     * @return The spec
     */
    public List<ArticleSpec> getSpec() {
        return spec;
    }

    /**
     * @return The slike
     */
    public List<Pictures> getSlike() {
        return slike;
    }


}

