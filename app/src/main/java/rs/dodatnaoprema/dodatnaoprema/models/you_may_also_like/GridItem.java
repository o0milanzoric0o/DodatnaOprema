package rs.dodatnaoprema.dodatnaoprema.models.you_may_also_like;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.articles.Pictures;

/**
 * Created by 1 on 8/19/2016.
 */
public class GridItem implements Serializable{
    private Integer artikalId;
    private String artikalNaziv;
    private List<Pictures> slike = new ArrayList<Pictures>();


    public Integer getArtikalId() {
        return artikalId;
    }

    public String getArtikalNaziv() {
        return artikalNaziv;
    }

    /**
     * @return The slike
     */
    public List<Pictures> getSlike() {
        return slike;
    }

    public void setArtikalId(Integer artikalId) {
        this.artikalId = artikalId;
    }

    public void setArtikalNaziv(String artikalNaziv) {
        this.artikalNaziv = artikalNaziv;
    }

    /**
     * @return The slike
     */
    public void setSlike(List<Pictures> slike) {
        this.slike = slike;
    }


}
