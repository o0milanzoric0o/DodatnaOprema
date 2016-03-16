package rs.dodatnaoprema.dodatnaoprema.network;
import static  rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_MACHINES_AND_TOOLS;
import static  rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_CHAR_QUESTION;
import static  rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_PARAM_ACTION;
import static  rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_CHAR_EQUAL;
import static  rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.URL_VALUE_ALL_CATEGORIES;





public class Endpoints {

    public static String getRequestUrlAllCategories() {
        // http://masinealati.rs/parametri.php?action=sveKategorije
        return URL_MACHINES_AND_TOOLS
                + URL_CHAR_QUESTION
                + URL_PARAM_ACTION + URL_CHAR_EQUAL
                + URL_VALUE_ALL_CATEGORIES;

    }
}
