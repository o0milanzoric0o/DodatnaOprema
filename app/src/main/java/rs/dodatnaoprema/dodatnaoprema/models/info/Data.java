package rs.dodatnaoprema.dodatnaoprema.models.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("IdKategHead")
    @Expose
    private Object idKategHead;
    @SerializedName("ParentKategHead")
    @Expose
    private Integer parentKategHead;
    @SerializedName("LinkKategHead")
    @Expose
    private String linkKategHead;
    @SerializedName("AktivanKategHead")
    @Expose
    private Integer aktivanKategHead;
    @SerializedName("MestoKategHead")
    @Expose
    private Integer mestoKategHead;
    @SerializedName("NaslovKategHead")
    @Expose
    private String naslovKategHead;
    @SerializedName("DaliImaPodKat")
    @Expose
    private Object daliImaPodKat;
    @SerializedName("OpisKategHeadTekst")
    @Expose
    private String opisKategHeadTekst;

    /**
     * @return The idKategHead
     */
    public Object getIdKategHead() {
        return idKategHead;
    }


    /**
     * @return The parentKategHead
     */
    public Integer getParentKategHead() {
        return parentKategHead;
    }


    /**
     * @return The linkKategHead
     */
    public String getLinkKategHead() {
        return linkKategHead;
    }


    /**
     * @return The aktivanKategHead
     */
    public Integer getAktivanKategHead() {
        return aktivanKategHead;
    }


    /**
     * @return The mestoKategHead
     */
    public Integer getMestoKategHead() {
        return mestoKategHead;
    }


    /**
     * @return The naslovKategHead
     */
    public String getNaslovKategHead() {
        return naslovKategHead;
    }


    /**
     * @return The daliImaPodKat
     */
    public Object getDaliImaPodKat() {
        return daliImaPodKat;
    }


    /**
     * @return The opisKategHeadTekst
     */
    public String getOpisKategHeadTekst() {
        return opisKategHeadTekst;
    }

}
