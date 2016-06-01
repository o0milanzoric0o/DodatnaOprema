package rs.dodatnaoprema.dodatnaoprema;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.fragments.CartViewFragment;
import rs.dodatnaoprema.dodatnaoprema.fragments.EmptyCartFragment;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Artikli;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Cart;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;

public class CartActivity extends AppCompatActivity {
    private FrameLayout mContainer;
    private VolleySingleton mVolleySingleton;
    private List<Artikli> mArtikli = new ArrayList<>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mVolleySingleton = VolleySingleton.getsInstance(this);
        mContainer = (FrameLayout) findViewById(R.id.container);
        if (mContainer != null) {

            // pull cart content and show it

            // check if logged in
            if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {

                // Load user data and get UserId
                User user = MyApplication.getInstance().getPrefManager().getUser();
                String user_id = user.getId();

                String url = String.format(AppConfig.URL_GET_CART, user_id);

                PullWebContent<Cart> content =
                        new PullWebContent<Cart>(this, Cart.class, url, mVolleySingleton);
                content.setCallbackListener(new WebRequestCallbackInterface<Cart>() {
                    @Override
                    public void webRequestSuccess(boolean success, Cart cart) {
                        if (success) {
                            if (cart.getArtikli().size() > 0)
                                showCartContentFragment(cart);
                            else
                                showEmptyCartFragment();
                        }
                    }

                    @Override
                    public void webRequestError(String error) {

                    }
                });
                content.pullList();
            } else {
                showEmptyCartFragment();
            }
        }
    }

    private void showEmptyCartFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        EmptyCartFragment emptyCartFragment = new EmptyCartFragment();
        fragmentTransaction.add(R.id.container, emptyCartFragment);
        fragmentTransaction.commit();
    }

    private void showCartContentFragment(Cart cart) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CartViewFragment cartViewFragment = CartViewFragment.newInstance(cart);
        fragmentTransaction.add(R.id.container, cartViewFragment);
        fragmentTransaction.commit();
    }
}
