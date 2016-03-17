package rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Child {

    @SerializedName("KategorijaArtikalaId")
    @Expose
    private Integer KategorijaArtikalaId;
    @SerializedName("ParentKategorijaArtikalaId")
    @Expose
    private Integer ParentKategorijaArtikalaId;
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

    /**
     * @return The KategorijaArtikalaId
     */
    public Integer getKategorijaArtikalaId() {
        return KategorijaArtikalaId;
    }


    /**
     * @return The ParentKategorijaArtikalaId
     */
    public Integer getParentKategorijaArtikalaId() {
        return ParentKategorijaArtikalaId;
    }


    /**
     * @return The Katsrblat
     */
    public String getKatsrblat() {
        return Katsrblat;
    }


    /**
     * @return The KategorijeVidljivZaMP
     */
    public Integer getKategorijeVidljivZaMP() {
        return KategorijeVidljivZaMP;
    }


    /**
     * @return The KategorijaArtikalaSlika
     */
    public String getKategorijaArtikalaSlika() {
        return KategorijaArtikalaSlika;
    }


    /**
     * @return The KategorijaArtikalaLink
     */
    public String getKategorijaArtikalaLink() {
        return KategorijaArtikalaLink;
    }


    /**
     * @return The KategorijaArtikalaActiveMasine
     */
    public Integer getKategorijaArtikalaActiveMasine() {
        return KategorijaArtikalaActiveMasine;
    }
    

}