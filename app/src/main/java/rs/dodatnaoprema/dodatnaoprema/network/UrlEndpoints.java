package rs.dodatnaoprema.dodatnaoprema.network;


import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;

import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_CHAR_AMPERSAND;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_CHAR_EQUAL;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_CHAR_QUESTION;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_MACHINES_AND_TOOLS;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_ACTION;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_FROM;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_ID;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_SORT_CONTROL;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_TO;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_ALL_CATEGORIES;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_ARTICLE;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_ARTICLES_BY_CATEGORY;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_ARTICLES_ON_SALE;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_CATEGORIES_BY_ID;
import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_CATEGORY_SPECIFICATION;


public class UrlEndpoints {


    public static String getRequestUrlAllCategories() {

        // http://masinealati.rs/parametri.php?action=sveKategorije
        String url = URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL
                + URL_VALUE_ALL_CATEGORIES;

        return getFullUrl(url);

    }

    public static String getRequestUrlCategoriesById(int id) {

        // http://masinealati.rs/parametri.php?action=kategorijePoId&id=6

        String url = URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL + URL_VALUE_CATEGORIES_BY_ID
                + URL_CHAR_AMPERSAND
                + URL_PARAM_ID + URL_CHAR_EQUAL + id;

        return getFullUrl(url);
    }

    public static String getRequestUrlSearchArticlesByCategory(int id, int from, int to, String currency, String language, int sort) {

        //http://masinealati.rs/parametri.php?action=artikliPoKateg&id=1623&od=2&do=5&valutasession=rsd&jezik=srblat&brend=35&sortKontrole=2

        String url = URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL + URL_VALUE_ARTICLES_BY_CATEGORY
                + URL_CHAR_AMPERSAND
                + URL_PARAM_ID + URL_CHAR_EQUAL + id
                + URL_CHAR_AMPERSAND
                + URL_PARAM_FROM + URL_CHAR_EQUAL + from
                + URL_CHAR_AMPERSAND
                + URL_PARAM_TO + URL_CHAR_EQUAL + to
                + URL_CHAR_AMPERSAND
                //    + URL_PARAM_CURRENCY + URL_CHAR_EQUAL + currency
                //    + URL_CHAR_AMPERSAND
                //    + URL_PARAM_LANGUAGE + URL_CHAR_EQUAL + language
                //    + URL_CHAR_AMPERSAND
                + URL_PARAM_SORT_CONTROL + URL_CHAR_EQUAL + sort;

        return getFullUrl(url);


    }

    public static String getRequestUrlSearchOnSale(int id, int from, int to) {

        // http://masinealati.rs/parametri.php?action=artNaAkciji&id=6&od=1&do=2
        // http://masinealati.rs/parametri.php?action=artNaAkciji&id=7&od=0&do=5
        // http://masinealati.rs/parametri.php?action=artNaAkciji&id=8&od=0&do=25

        String url = URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL + URL_VALUE_ARTICLES_ON_SALE
                + URL_CHAR_AMPERSAND
                + URL_PARAM_ID + URL_CHAR_EQUAL + id
                + URL_CHAR_AMPERSAND
                + URL_PARAM_FROM + URL_CHAR_EQUAL + from
                + URL_CHAR_AMPERSAND
                + URL_PARAM_TO + URL_CHAR_EQUAL + to;

        return getFullUrl(url);
    }

    public static String getRequestUrlSearchOnSaleAll(int id) {

        // http://masinealati.rs/parametri.php?action=artNaAkciji&id=6

        String url = URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL + URL_VALUE_ARTICLES_ON_SALE
                + URL_CHAR_AMPERSAND
                + URL_PARAM_ID + URL_CHAR_EQUAL + id;
        return getFullUrl(url);
    }

    public static String getRequestUrlCategorySpecification(int id) {

        // http://masinealati.rs/parametri.php?action=specPoKategorijiSamo&id=1623

        String url = URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL + URL_VALUE_CATEGORY_SPECIFICATION
                + URL_CHAR_AMPERSAND
                + URL_PARAM_ID + URL_CHAR_EQUAL + id;
        return url;
    }


    public static String getRequestUrlArticleById(int id) {

        // http://masinealati.rs/parametri.php?action=artikal&id=641
        String url = URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL + URL_VALUE_ARTICLE
                + URL_CHAR_AMPERSAND
                + URL_PARAM_ID + URL_CHAR_EQUAL + id;

        return getFullUrl(url);
    }

    private static String getFullUrl(String url) {
        if (!SharedPreferencesUtils.getUserId().equals("")) {

            return url + "&userId=" + SharedPreferencesUtils.getUserId();

        } else return url;

    }
}
