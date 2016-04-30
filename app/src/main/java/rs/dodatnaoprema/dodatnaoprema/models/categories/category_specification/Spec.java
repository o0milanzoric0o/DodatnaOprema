package rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Spec {
    @SerializedName("IdSpecGrupe")
    @Expose
    private Integer IdSpecGrupe;
    @SerializedName("Grupe")
    @Expose
    private String Grupe;
    @SerializedName("OtvZarvSpecGrupe")
    @Expose
    private Integer OtvZarvSpecGrupe;
    @SerializedName("detalj")
    @Expose
    private List<Detail> detalj = new ArrayList<Detail>();

    /**
     *
     * @return
     * The IdSpecGrupe
     */
    public Integer getIdSpecGrupe() {
        return IdSpecGrupe;
    }

    /**
     *
     * @param IdSpecGrupe
     * The IdSpecGrupe
     */
    public void setIdSpecGrupe(Integer IdSpecGrupe) {
        this.IdSpecGrupe = IdSpecGrupe;
    }

    /**
     *
     * @return
     * The Grupe
     */
    public String getGrupe() {
        return Grupe;
    }

    /**
     *
     * @param Grupe
     * The Grupe
     */
    public void setGrupe(String Grupe) {
        this.Grupe = Grupe;
    }

    /**
     *
     * @return
     * The OtvZarvSpecGrupe
     */
    public Integer getOtvZarvSpecGrupe() {
        return OtvZarvSpecGrupe;
    }

    /**
     *
     * @param OtvZarvSpecGrupe
     * The OtvZarvSpecGrupe
     */
    public void setOtvZarvSpecGrupe(Integer OtvZarvSpecGrupe) {
        this.OtvZarvSpecGrupe = OtvZarvSpecGrupe;
    }

    /**
     *
     * @return
     * The detalj
     */
    public List<Detail> getDetalj() {
        return detalj;
    }

    /**
     *
     * @param detalj
     * The detalj
     */
    public void setDetalj(List<Detail> detalj) {
        this.detalj = detalj;
    }
}
