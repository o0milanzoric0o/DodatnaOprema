package rs.dodatnaoprema.dodatnaoprema.models.articles.brands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Brand implements Serializable {

    @SerializedName("BrendId")
    @Expose
    private Integer BrendId;
    @SerializedName("BrendIme")
    @Expose
    private String BrendIme;
    @SerializedName("BrendLink")
    @Expose
    private String BrendLink;
    @SerializedName("BrendSlikaMala")
    @Expose
    private String BrendSlikaMala;
    @SerializedName("BrendSlika172")
    @Expose
    private String BrendSlika172;
    @SerializedName("BrendActive")
    @Expose
    private String BrendActive;
    @SerializedName("BrendNaslovna")
    @Expose
    private Object BrendNaslovna;
    @SerializedName("BrendShow")
    @Expose
    private Integer BrendShow;
    @SerializedName("BrendSajt")
    @Expose
    private Integer BrendSajt;

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
     * @return The BrendLink
     */
    public String getBrendLink() {
        return BrendLink;
    }

    /**
     * @param BrendLink The BrendLink
     */
    public void setBrendLink(String BrendLink) {
        this.BrendLink = BrendLink;
    }

    /**
     * @return The BrendSlikaMala
     */
    public String getBrendSlikaMala() {
        return BrendSlikaMala;
    }

    /**
     * @param BrendSlikaMala The BrendSlikaMala
     */
    public void setBrendSlikaMala(String BrendSlikaMala) {
        this.BrendSlikaMala = BrendSlikaMala;
    }

    /**
     * @return The BrendSlika172
     */
    public String getBrendSlika172() {
        return BrendSlika172;
    }

    /**
     * @param BrendSlika172 The BrendSlika172
     */
    public void setBrendSlika172(String BrendSlika172) {
        this.BrendSlika172 = BrendSlika172;
    }

    /**
     * @return The BrendActive
     */
    public String getBrendActive() {
        return BrendActive;
    }

    /**
     * @param BrendActive The BrendActive
     */
    public void setBrendActive(String BrendActive) {
        this.BrendActive = BrendActive;
    }

    /**
     * @return The BrendNaslovna
     */
    public Object getBrendNaslovna() {
        return BrendNaslovna;
    }

    /**
     * @param BrendNaslovna The BrendNaslovna
     */
    public void setBrendNaslovna(Object BrendNaslovna) {
        this.BrendNaslovna = BrendNaslovna;
    }

    /**
     * @return The BrendShow
     */
    public Integer getBrendShow() {
        return BrendShow;
    }

    /**
     * @param BrendShow The BrendShow
     */
    public void setBrendShow(Integer BrendShow) {
        this.BrendShow = BrendShow;
    }

    /**
     * @return The BrendSajt
     */
    public Integer getBrendSajt() {
        return BrendSajt;
    }

    /**
     * @param BrendSajt The BrendSajt
     */
    public void setBrendSajt(Integer BrendSajt) {
        this.BrendSajt = BrendSajt;
    }

}
