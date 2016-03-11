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
    public static final String URL_ALL_CATEGORIES = "http://masinealati.rs/parametri.php?action=sveKategorije";


}
