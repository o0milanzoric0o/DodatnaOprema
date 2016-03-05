package rs.dodatnaoprema.dodatnaoprema.network;

import rs.dodatnaoprema.dodatnaoprema.models.categories.SveKategorije;


public interface WebRequestCallbackInterface {

    void webRequestSuccess(boolean success, SveKategorije allCategories);

    void webRequestError(String error);
}
