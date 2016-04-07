package rs.dodatnaoprema.dodatnaoprema.common.config;

public class AppConfig {
    /**
     * The default socket timeout in milliseconds
     */
    public static final int DEFAULT_TIMEOUT_MS = 3000;

    /**
     * The default number of retries
     */
    public static final int DEFAULT_MAX_RETRIES = 4;

    /**
     * The default backoff multiplier
     */
    public static final float DEFAULT_BACKOFF_MULT = 1f;

    /**
     * Urls for json docs
     */
    public static final String URL_MACHINES_AND_TOOLS = "http://masinealati.rs/parametri.php";

    public static final String URL_CHAR_QUESTION = "?";
    public static final String URL_CHAR_EQUAL = "=";
    public static final String URL_CHAR_AMPERSAND = "&";

    public static final String URL_PARAM_ACTION = "action";
    public static final String URL_PARAM_ID = "id";
    public static final String URL_PARAM_FROM = "od";
    public static final String URL_PARAM_TO = "do";
    public static final String URL_PARAM_CURRENCY = "valutasession";
    public static final String URL_PARAM_LANGUAGE = "jezik";
    public static final String URL_PARAM_BRAND = "brend";
    public static final String URL_PARAM_SORT_CONTROL = "sortKontrole";


    public static final String URL_VALUE_ALL_CATEGORIES = "sveKategorije";
    public static final String URL_VALUE_CURRENCY_RSD = "rsd";
    public static final String URL_VALUE_LANGUAGE_SRB_LAT = "srblat";
    public static final String URL_VALUE_CATEGORIES_BY_ID = "kategorijePoId";
    public static final String URL_VALUE_ARTICLES_BY_CATEGORY = "artikliPoKateg";
    public static final String URL_VALUE_ARTICLES_ON_SALE = "artNaAkciji";

    public static final int URL_VALUE_ID_ARTICLES_ON_SALE = 6;
    public static final int URL_VALUE_ID_NEW_PRODUCTS = 7;
    public static final int URL_VALUE_ID_BEST_SEllING = 8;

    public static final String[] FIRST_TAB_ITEMS = {"Proizvodi na akciji", "Novi proizvodi", "Najprodavanije"};


    public static final String URL_ALL_CATEGORIES = "http://masinealati.rs/parametri.php?action=sveKategorije";

    /**
     * Number of tabs
     */
    public static final int TAB_NUMBER = 2;
    /**
     * Scrolling speed
     * between 0 for no fling, and 1 for normal fling, or more for faster fling
     */
    public static final int FLING_SCALE_DOWN_FACTOR = 10;

    /**
     * Number of grid items
     */
    public static final int NUMBER_OF_ITEMS = 4;
    public static final int START_POSITION = 0;


}
