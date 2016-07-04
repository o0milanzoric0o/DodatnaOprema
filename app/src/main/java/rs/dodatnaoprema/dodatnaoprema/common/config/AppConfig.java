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
    public static final String URL_PARAM_SEARCH = "upitsearch";
    public static final String URL_PARAM_SORT_CONTROL = "sortKontrole";

    public static final String URL_VALUE_ALL_CATEGORIES = "sveKategorije";
    public static final String URL_VALUE_SEARCH = "searchAndr";
    public static final String URL_VALUE_INFO = "textZaInfoJSON";
    public static final int URL_VALUE_CONTACT = 41;
    public static final int URL_VALUE_HOW_TO_BUY = 42;
    public static final String URL_VALUE_CATEGORY_SPECIFICATION = "specPoKategorijiSamo";
    public static final String URL_VALUE_CURRENCY_RSD = "rsd";
    public static final String URL_VALUE_LANGUAGE_SRB_LAT = "srblat";
    public static final String URL_VALUE_CATEGORIES_BY_ID = "kategorijePoId";
    public static final String URL_VALUE_ARTICLES_BY_CATEGORY = "artikliPoKateg";
    public static final String URL_VALUE_ARTICLES_ON_SALE = "artNaAkciji";
    public static final String URL_VALUE_ARTICLE = "artikal";

    public static final String URL_PRODUCTS_OF_THE_WEEK = "http://masinealati.rs/cron/crongotovoMob/preporuka-nedelje-cron.json";
    public static final String URL_ALL_BRENDS = "http://masinealati.rs/parametri.php?action=listaBrendova";
    public static final String URL_YOU_MAY_ALSO_LIKE_CATEGORIES = "http://masinealati.rs/parametri.php?action=kategYouMayAlso&limit=10";

    public static final int URL_VALUE_ID_ARTICLES_ON_SALE = 6;
    public static final int URL_VALUE_ID_NEW_PRODUCTS = 7;
    public static final int URL_VALUE_ID_BEST_SEllING = 8;

    public static final String[] FIRST_TAB_ITEMS = {"Proizvodi na akciji", "Novi proizvodi", "Najprodavanije"};
    public static final String THE_PRODUCTS_OF_THE_WEEK = "Proizvodi nedelje";
    public static final String ALL_BRANDS = "Svi brendovi";
    public static final String YOU_MAY_ALSO_LIKE_CATEGORIES = "Preporucene kategorije";


    //Shared Preferences keys
    public static final String SALE = "SALE";
    public static final String NEW = "NEW";
    public static final String BEST = "BEST";
    public static final String ALL_CATEGORIES = "ALL_CATEGORIES";
    /**
     * History Shared Preferences
     */
    public static final String HISTORY_KEY = "History";
    public static final String HISTORY_ID_KEY = "HistoryID";

    /**
     * Login and register
     */
    public static final String URL_LOGIN_GET = "http://masinealati.rs/parametri.php?action=povuciPodatkeAndroidKorisnik&tag=%1$s&email=%2$s&p=%3$s";
    public static final String URL_REGISTER_GET = "http://masinealati.rs/parametri.php?action=registrujAndroid&tag=%1$s&email=%2$s&sifra=%3$s&komitentime=%4$s&komitentprezime=%5$s";
    public static final String URL_CHANGE_USER_DATA_GET = "http://direktnoizbaste.rs/parametri.php?action=izmeniPodatkeKomitent&id=%1$s&KomitentNaziv=%2$s&KomitentIme=%3$s" +
            "&KomitentPrezime=%4$s&KomitentAdresa=%5$s&KomitentPosBroj=%6$s&KomitentMesto=%7$s&KomitentTelefon=%8$s" +
            "&KomitentMobTel=%9$s&email=%10$s&KomitentUserName=%11$s&KomitentTipUsera=%12$s&KomitentFirma=%13$s" +
            "&KomitentMatBr=%14$s&KomitentPIB=%15$s&KomitentFirmaAdresa=%16$s";
    /**
     * Cart
     */
    public static final String URL_GET_CART = "http://masinealati.rs/parametri.php?action=korpaLista&userId=%1$s";
    public static final String URL_ADD_CART_ITEM = "http://masinealati.rs/parametri.php?action=dodajArtikalKorpa&id=%1$s&br=%2$s&userId=%3$s";
    public static final String URL_DELETE_CART_ITEM = "http://masinealati.rs/parametri.php?action=obrisiArtikalKorpa&id=%1$s&userId=%2$s";
    public static final String URL_DELETE_ALL_CART_ITEMS = "http://masinealati.rs/parametri.php?action=obrisiSveKorpa&userId=%1$s";
    public static final String URL_UPDATE_CART_ITEM_QUANTITY = "http://masinealati.rs/parametri.php?action=dodajArtikalKorpa&id=%1$s&br=%2$s&userId=%3$s";
    public static final String GET_CART = "Korpa";
    public static final String URL_COMPLETE_TRANSACTION = "http://masinealati.rs/parametri.php?action=kupovinaKorpa&userId=%1$s";


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

    /**
     * Sort options
     */
    public static final int SORT_ASCENDING = 2;
    // public static final int SORT_DESCENDING = 3;

    public static final String ABOUT_PRODUCT = "O proizvodu";

    /**
     * Shared preferences keys for FilterFragmentDialog
     */
    public static final String SELECTED_PRICES_KEY = "SELECTED_PRICE_RANGE";
    public static final String SELECTED_BRANDS_KEY = "SELECTED_BRANDS";

}
