package rs.dodatnaoprema.dodatnaoprema;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.fragments.CartViewFragment;
import rs.dodatnaoprema.dodatnaoprema.fragments.EmptyCartFragment;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Cart;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;

public class CartActivity extends BaseActivity {
    private FrameLayout mContainer;
    private VolleySingleton mVolleySingleton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText("Korpa");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageButton icCart = (ImageButton) findViewById(R.id.toolbar_btn_cart);
        icCart.setVisibility(View.GONE);
        icCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
//                startActivity(intent);
            }
        });
    }
}
