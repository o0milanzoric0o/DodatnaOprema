package rs.dodatnaoprema.dodatnaoprema.models.articles.articles_on_sale;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.articles.Pictures;

public class Article implements Serializable {

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
    @SerializedName("cenaBroj")
    @Expose
    private String cenaBroj;
    @SerializedName("pravaMpSmall")
    @Expose
    private String pravaMpSmall;
    @SerializedName("pozovite")
    @Expose
    private Object pozovite;
    @SerializedName("brend")
    @Expose
    private Object brend;
    @SerializedName("stanje")
    @Expose
    private Integer stanje;
    @SerializedName("spec")
    @Expose
    private List<Object> spec = new ArrayList<Object>();
    @SerializedName("slike")
    @Expose
    private List<Pictures> slike = new ArrayList<Pictures>();

    /**
     *
     * @return
     * The ArtikalId
     */
    public Integer getArtikalId() {
        return ArtikalId;
    }

    /**
     *
     * @param ArtikalId
     * The ArtikalId
     */
    public void setArtikalId(Integer ArtikalId) {
        this.ArtikalId = ArtikalId;
    }

    /**
     *
     * @return
     * The ArtikalNaziv
     */
    public String getArtikalNaziv() {
        return ArtikalNaziv;
    }

    /**
     *
     * @param ArtikalNaziv
     * The ArtikalNaziv
     */
    public void setArtikalNaziv(String ArtikalNaziv) {
        this.ArtikalNaziv = ArtikalNaziv;
    }

    /**
     *
     * @return
     * The NaAkciji
     */
    public Integer getNaAkciji() {
        return NaAkciji;
    }

    /**
     *
     * @param NaAkciji
     * The NaAkciji
     */
    public void setNaAkciji(Integer NaAkciji) {
        this.NaAkciji = NaAkciji;
    }

    /**
     *
     * @return
     * The urlArtiklaLink
     */
    public String getUrlArtiklaLink() {
        return urlArtiklaLink;
    }

    /**
     *
     * @param urlArtiklaLink
     * The urlArtiklaLink
     */
    public void setUrlArtiklaLink(String urlArtiklaLink) {
        this.urlArtiklaLink = urlArtiklaLink;
    }

    /**
     *
     * @return
     * The cenaBroj
     */
    public String getCenaBroj() {
        return cenaBroj;
    }

    /**
     *
     * @param cenaBroj
     * The cenaBroj
     */
    public void setCenaBroj(String cenaBroj) {
        this.cenaBroj = cenaBroj;
    }

    /**
     *
     * @return
     * The pravaMpSmall
     */
    public String getPravaMpSmall() {
        return pravaMpSmall;
    }

    /**
     *
     * @param pravaMpSmall
     * The pravaMpSmall
     */
    public void setPravaMpSmall(String pravaMpSmall) {
        this.pravaMpSmall = pravaMpSmall;
    }

    /**
     *
     * @return
     * The pozovite
     */
    public Object getPozovite() {
        return pozovite;
    }

    /**
     *
     * @param pozovite
     * The pozovite
     */
    public void setPozovite(Object pozovite) {
        this.pozovite = pozovite;
    }

    /**
     *
     * @return
     * The brend
     */
    public Object getBrend() {
        return brend;
    }

    /**
     *
     * @param brend
     * The brend
     */
    public void setBrend(Object brend) {
        this.brend = brend;
    }

    /**
     *
     * @return
     * The stanje
     */
    public Integer getStanje() {
        return stanje;
    }

    /**
     *
     * @param stanje
     * The stanje
     */
    public void setStanje(Integer stanje) {
        this.stanje = stanje;
    }

    /**
     *
     * @return
     * The spec
     */
    public List<Object> getSpec() {
        return spec;
    }

    /**
     *
     * @param spec
     * The spec
     */
    public void setSpec(List<Object> spec) {
        this.spec = spec;
    }

    /**
     *
     * @return
     * The slike
     */
    public List<Pictures> getSlike() {
        return slike;
    }

    /**
     *
     * @param slike
     * The slike
     */
    public void setSlike(List<Pictures> slike) {
        this.slike = slike;
    }


}