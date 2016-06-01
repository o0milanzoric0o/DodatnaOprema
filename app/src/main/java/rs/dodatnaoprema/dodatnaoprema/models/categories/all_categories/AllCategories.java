package rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.models.User;


public class AllCategories implements Serializable {

    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
    @SerializedName("kategorije")
    @Expose
    private List<Category> kategorije = new ArrayList<>();

    /**
     * @return The tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @return The error
     */
    public Integer getError() {
        return error;
    }

    /**
     * @return The errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @return The kategorije
     */
    public List<Category> getKategorije() {

        List<Category> visibleCategories = new ArrayList<>();

        if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
            // jeste logovan
            User user = MyApplication.getInstance().getPrefManager().getUser();

            if (user != null) {
                if (Integer.parseInt(user.getUserType()) > 2) {
                    return kategorije;
                }
            }
        }
        for (Category category : kategorije
                ) {
            if (category.getKategorijeVidljivZaMP() == 1) visibleCategories.add(category);

        }
        return visibleCategories;
    }


}