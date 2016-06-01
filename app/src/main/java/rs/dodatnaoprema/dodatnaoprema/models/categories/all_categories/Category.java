package rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.models.User;


public class Category implements Serializable{

    @SerializedName("KategorijaArtikalaId")
    @Expose
    private Integer KategorijaArtikalaId;
    @SerializedName("ParentKategorijaArtikalaId")
    @Expose
    private Object ParentKategorijaArtikalaId;
    @SerializedName("KatIme")
    @Expose
    private String KatIme;
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
    @SerializedName("child")
    @Expose
    private List<Child> child = new ArrayList<>();

    /**
     * @return The KategorijaArtikalaId
     */
    public Integer getKategorijaArtikalaId() {
        return KategorijaArtikalaId;
    }

    /**
     * @return The ParentKategorijaArtikalaId
     */
    public Object getParentKategorijaArtikalaId() {
        return ParentKategorijaArtikalaId;
    }

    /**
     * @return The Katsrblat
     */
    public String getKatsrblat() {
        return KatIme;
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

    /**
     * @return The child
     */
    public List<Child> getChild() {

        List<Child> visibleSubcategories = new ArrayList<>();

        if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
            // jeste logovan
            User user = MyApplication.getInstance().getPrefManager().getUser();

            if (user != null) {
                if (Integer.parseInt(user.getUserType()) > 2) {
                    return child;
                }
            }
        }
        for (Child subcategory : child
                ) {
            if (subcategory.getKategorijeVidljivZaMP() == 1) visibleSubcategories.add(subcategory);

        }
        return visibleSubcategories;

    }


}