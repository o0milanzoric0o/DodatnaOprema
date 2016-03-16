package rs.dodatnaoprema.dodatnaoprema.network;

import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_CHAR_AMPERSAND;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_CHAR_EQUAL;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_CHAR_QUESTION;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_MACHINES_AND_TOOLS;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_ACTION;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_BRAND;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_CURRENCY;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_FROM;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_ID;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_LANGUAGE;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_SORT_CONTROL;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_TO;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_ALL_CATEGORIES;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_ARTICLES_BY_CATEGORY;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_CATEGORIES_BY_ID;


public class Endpoints {

    public static String getRequestUrlAllCategories() {

        // http://masinealati.rs/parametri.php?action=sveKategorije

        return URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL
                + URL_VALUE_ALL_CATEGORIES;

    }

    public static String getRequestUrlCategoriesById(int id) {

        // http://masinealati.rs/parametri.php?action=kategorijePoId&id=6

        return URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL
                + URL_VALUE_CATEGORIES_BY_ID
                + URL_CHAR_AMPERSAND
                + URL_PARAM_ID + URL_CHAR_EQUAL + id;

    }

    public static String getRequestUrlSearchArticlesByCategory(int id, int from, int to, String currency, String language, int brand, int sort) {

        //http://masinealati.rs/parametri.php?action=artikliPoKateg&id=1623&od=2&do=5&valutasession=rsd&jezik=srblat&brend=35&sortKontrole=2

        return URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL + URL_VALUE_ARTICLES_BY_CATEGORY
                + URL_CHAR_AMPERSAND
                + URL_PARAM_ID + URL_CHAR_EQUAL + id
                + URL_CHAR_AMPERSAND
                + URL_PARAM_FROM + from
                + URL_CHAR_AMPERSAND
                + URL_PARAM_TO + to
                + URL_CHAR_AMPERSAND
                + URL_PARAM_CURRENCY + currency
                + URL_CHAR_AMPERSAND
                + URL_PARAM_LANGUAGE + language
                + URL_CHAR_AMPERSAND
                + URL_PARAM_BRAND + brand
                + URL_CHAR_AMPERSAND
                + URL_PARAM_SORT_CONTROL + sort;
    }
}
