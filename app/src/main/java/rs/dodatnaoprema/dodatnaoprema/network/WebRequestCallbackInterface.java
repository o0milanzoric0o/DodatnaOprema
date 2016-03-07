package rs.dodatnaoprema.dodatnaoprema.network;

/**
 * Created by 1 on 3/7/2016.
 */
public interface WebRequestCallbackInterface<T> {
    void webRequestSuccess(boolean success, T t);

    void webRequestError(String error);
}
