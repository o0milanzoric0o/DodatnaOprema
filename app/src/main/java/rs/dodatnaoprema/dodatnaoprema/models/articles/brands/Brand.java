package rs.dodatnaoprema.dodatnaoprema.models.articles.brands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Brand implements Serializable {

    @SerializedName("BrendId")
    @Expose
    private Integer brendId;
    @SerializedName("BrendIme")
    @Expose
    private String brendIme;
    @SerializedName("BrendLink")
    @Expose
    private String brendLink;
    @SerializedName("BrendSlikaMala")
    @Expose
    private String brendSlikaMala;
    @SerializedName("BrendSlika172")
    @Expose
    private String brendSlika172;
    @SerializedName("BrendActive")
    @Expose
    private Integer brendActive;
    @SerializedName("BrendNaslovna")
    @Expose
    private Integer brendNaslovna;
    @SerializedName("BrendShow")
    @Expose
    private Integer brendShow;
    @SerializedName("BrendSajt")
    @Expose
    private Integer brendSajt;
    @SerializedName("BrendSajtMasine")
    @Expose
    private Integer brendSajtMasine;

    /**
     *
     * @return
     *     The brendId
     */
    public Integer getBrendId() {
        return brendId;
    }

    /**
     *
     * @param brendId
     *     The BrendId
     */
    public void setBrendId(Integer brendId) {
        this.brendId = brendId;
    }

    /**
     *
     * @return
     *     The brendIme
     */
    public String getBrendIme() {
        return brendIme;
    }

    /**
     *
     * @param brendIme
     *     The BrendIme
     */
    public void setBrendIme(String brendIme) {
        this.brendIme = brendIme;
    }

    /**
     *
     * @return
     *     The brendLink
     */
    public String getBrendLink() {
        return brendLink;
    }

    /**
     *
     * @param brendLink
     *     The BrendLink
     */
    public void setBrendLink(String brendLink) {
        this.brendLink = brendLink;
    }

    /**
     *
     * @return
     *     The brendSlikaMala
     */
    public String getBrendSlikaMala() {
        return brendSlikaMala;
    }

    /**
     *
     * @param brendSlikaMala
     *     The BrendSlikaMala
     */
    public void setBrendSlikaMala(String brendSlikaMala) {
        this.brendSlikaMala = brendSlikaMala;
    }

    /**
     *
     * @return
     *     The brendSlika172
     */
    public String getBrendSlika172() {
        return brendSlika172;
    }

    /**
     *
     * @param brendSlika172
     *     The BrendSlika172
     */
    public void setBrendSlika172(String brendSlika172) {
        this.brendSlika172 = brendSlika172;
    }

    /**
     *
     * @return
     *     The brendActive
     */
    public Integer getBrendActive() {
        return brendActive;
    }

    /**
     *
     * @param brendActive
     *     The BrendActive
     */
    public void setBrendActive(Integer brendActive) {
        this.brendActive = brendActive;
    }

    /**
     *
     * @return
     *     The brendNaslovna
     */
    public Integer getBrendNaslovna() {
        return brendNaslovna;
    }

    /**
     *
     * @param brendNaslovna
     *     The BrendNaslovna
     */
    public void setBrendNaslovna(Integer brendNaslovna) {
        this.brendNaslovna = brendNaslovna;
    }

    /**
     *
     * @return
     *     The brendShow
     */
    public Integer getBrendShow() {
        return brendShow;
    }

    /**
     *
     * @param brendShow
     *     The BrendShow
     */
    public void setBrendShow(Integer brendShow) {
        this.brendShow = brendShow;
    }

    /**
     *
     * @return
     *     The brendSajt
     */
    public Integer getBrendSajt() {
        return brendSajt;
    }

    /**
     *
     * @param brendSajt
     *     The BrendSajt
     */
    public void setBrendSajt(Integer brendSajt) {
        this.brendSajt = brendSajt;
    }

    /**
     *
     * @return
     *     The brendSajtMasine
     */
    public Integer getBrendSajtMasine() {
        return brendSajtMasine;
    }

    /**
     *
     * @param brendSajtMasine
     *     The BrendSajtMasine
     */
    public void setBrendSajtMasine(Integer brendSajtMasine) {
        this.brendSajtMasine = brendSajtMasine;
    }

}
