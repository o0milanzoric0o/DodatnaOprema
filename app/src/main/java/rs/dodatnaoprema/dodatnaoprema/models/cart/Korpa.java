
package rs.dodatnaoprema.dodatnaoprema.models.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Korpa implements Serializable{

    @SerializedName("KorpaKolTempArt")
    @Expose
    private Integer korpaKolTempArt;
    @SerializedName("KorpaCenaPoArtKol")
    @Expose
    private Double korpaCenaPoArtKol;

    /**
     * @return The korpaKolTempArt
     */
    public Integer getKorpaKolTempArt() {
        return korpaKolTempArt;
    }

    /**
     * @param korpaKolTempArt The KorpaKolTempArt
     */
    public void setKorpaKolTempArt(Integer korpaKolTempArt) {
        this.korpaKolTempArt = korpaKolTempArt;
    }

    /**
     * @return The korpaCenaPoArtKol
     */
    public Double getKorpaCenaPoArtKol() {
        return korpaCenaPoArtKol;
    }

    /**
     * @param korpaCenaPoArtKol The KorpaCenaPoArtKol
     */
    public void setKorpaCenaPoArtKol(Double korpaCenaPoArtKol) {
        this.korpaCenaPoArtKol = korpaCenaPoArtKol;
    }

}
