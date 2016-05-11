package rs.dodatnaoprema.dodatnaoprema.common.utils;


public class Conversions {

    public static double priceStringToFloat(String price) {
        price = price.replace(".", "");

        if (price.indexOf(',') != -1) {
            price = price.replace(",", ".");
            return Double.parseDouble(price);
        }
        return 0.0;
    }
}
