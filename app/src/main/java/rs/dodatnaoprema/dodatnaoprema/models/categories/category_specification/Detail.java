package rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("IdSpecVrednostiVre")
    @Expose
    private Integer IdSpecVrednostiVre;
    @SerializedName("IdSpecVrednostiImeVre")
    @Expose
    private String IdSpecVrednostiImeVre;

    /**
     * @return The IdSpecVrednostiVre
     */
    public Integer getIdSpecVrednostiVre() {
        return IdSpecVrednostiVre;
    }

    /**
     * @param IdSpecVrednostiVre The IdSpecVrednostiVre
     */
    public void setIdSpecVrednostiVre(Integer IdSpecVrednostiVre) {
        this.IdSpecVrednostiVre = IdSpecVrednostiVre;
    }

    /**
     * @return The IdSpecVrednostiImeVre
     */
    public String getIdSpecVrednostiImeVre() {
        return IdSpecVrednostiImeVre;
    }

    /**
     * @param IdSpecVrednostiImeVre The IdSpecVrednostiImeVre
     */
    public void setIdSpecVrednostiImeVre(String IdSpecVrednostiImeVre) {
        this.IdSpecVrednostiImeVre = IdSpecVrednostiImeVre;
    }

}

