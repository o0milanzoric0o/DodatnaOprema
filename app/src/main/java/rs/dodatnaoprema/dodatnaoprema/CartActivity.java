package rs.dodatnaoprema.dodatnaoprema;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import junit.framework.Assert;

import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.dialogs.CartDeleteAllConfirmationDialog;
import rs.dodatnaoprema.dodatnaoprema.dialogs.InfoDialog;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.fragments.CartViewFragment;
import rs.dodatnaoprema.dodatnaoprema.fragments.CartViewOfflineFragment;
import rs.dodatnaoprema.dodatnaoprema.fragments.EmptyCartFragment;
import rs.dodatnaoprema.dodatnaoprema.models.OfflineCart;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Cart;
import rs.dodatnaoprema.dodatnaoprema.models.cart.ItemDeleteAllResponse;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;

public class CartActivity extends BaseActivity {
    private FrameLayout mContainer;
    private VolleySingleton mVolleySingleton;
    private Context mContext;
    private CartDeleteAllConfirmationDialog cartDeleteАllConfirmationDialog;
    private ProgressDialogCustom progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mContext = this;

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);

        ImageButton icSearch = (ImageButton) findViewById(R.id.toolbar_btn_search);
        icSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });

        progressDialog = new ProgressDialogCustom(CartActivity.this);
        progressDialog.setCancelable(false);

        Assert.assertNotNull(mTextView);
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
                progressDialog.showDialog("Učitavanje...");

                // Load user data and get UserId
                User user = MyApplication.getInstance().getPrefManager().getUser();
                String user_id = user.getId();

                String url = String.format(AppConfig.URL_GET_CART, user_id);

                PullWebContent<Cart> content =
                        new PullWebContent<>(Cart.class, url, mVolleySingleton);
                content.setCallbackListener(new WebRequestCallbackInterface<Cart>() {
                    @Override
                    public void webRequestSuccess(boolean success, Cart cart) {
                        if (success) {
                            // Update cart items count
                            // Set cart item count
                            int count = (cart.getArtikli().size() > 0 ? cart.getUkupnaKolicina() : 0);
                            MyApplication.getInstance().getSessionManager().setCartItemCount(count);

                            updateCartToolbarIcon();
                            // Update Navigation Drawer from main activity
                            Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
                            LocalBroadcastManager.getInstance(CartActivity.this).sendBroadcast(updateToolbar);
                            if (cart.getArtikli().size() > 0)
                                showCartContentFragment(cart);
                            else
                                showEmptyCartFragment();
                        }
                        progressDialog.hideDialog();
                    }

                    @Override
                    public void webRequestError(String error) {
                        progressDialog.hideDialog();
                    }
                });
                content.pullList();
            } else {
                // Show offline cart content
                Integer offlineCartItemCount = MyApplication.getInstance().getSessionManager().getOfflineCartItemCount();
                if (offlineCartItemCount > 0) {
                    showOfflineCartContent();
                } else {
                    showEmptyCartFragment();
                }
            }
        }

        cartDeleteАllConfirmationDialog = new CartDeleteAllConfirmationDialog(mContext);
        cartDeleteАllConfirmationDialog.setPositiveButtonListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Create web request to delete all cart items.
                // check if logged in
                if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
                    // Load user data and get UserId
                    progressDialog.showDialog("Učitavanje...");
                    User user = MyApplication.getInstance().getPrefManager().getUser();
                    String user_id = user.getId();

                    String url = String.format(AppConfig.URL_DELETE_ALL_CART_ITEMS, user_id);

                    PullWebContent<ItemDeleteAllResponse> content =
                            new PullWebContent<>(ItemDeleteAllResponse.class, url, mVolleySingleton);
                    content.setCallbackListener(new WebRequestCallbackInterface<ItemDeleteAllResponse>() {
                        @Override
                        public void webRequestSuccess(boolean success, ItemDeleteAllResponse resp) {
                            progressDialog.hideDialog();
                            if (success) {
                                if (resp.getSuccess()) {
                                    // items are successfully deleted
                                    // show empty cart fragment
                                    // Set cart item count
                                    MyApplication.getInstance().getSessionManager().setCartItemCount(0);
                                    // notify application to update toolbar icon
                                    Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
                                    LocalBroadcastManager.getInstance(CartActivity.this).sendBroadcast(updateToolbar);
                                    //show emty cart fragment
                                    showEmptyCartFragment();
                                } else {
                                    InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Nije uspelo brisanje artikla.");
                                    infoDialog.show(getSupportFragmentManager(), "InfoDialog");
                                }
                            }
                        }

                        @Override
                        public void webRequestError(String error) {
                            progressDialog.hideDialog();
                            // Web request fail
                            // Create snackbar or something
                            InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Proverite internet konekciju.");
                            infoDialog.show(getSupportFragmentManager(), "InfoDialog");
                        }
                    });
                    content.pullList();

                } else {
                    // Clear offline cart
                    OfflineCart offlineCart = MyApplication.getInstance().getPrefManager().loadOfflineCart();
                    if (offlineCart != null) {
                        offlineCart.clearCart();
                        MyApplication.getInstance().getSessionManager().setOfflineCartItemCount(offlineCart.getTotalQuantity());
                    }
                    // notify application to update toolbar icon
                    Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
                    LocalBroadcastManager.getInstance(CartActivity.this).sendBroadcast(updateToolbar);
                    //show emty cart fragment
                    showEmptyCartFragment();
                }
            }
        });
        cartDeleteАllConfirmationDialog.setNegativeButtonListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });
    }

    private void showEmptyCartFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        EmptyCartFragment emptyCartFragment = new EmptyCartFragment();
        fragmentTransaction.replace(R.id.container, emptyCartFragment);
        fragmentTransaction.commit();
    }

    private void showCartContentFragment(Cart cart) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CartViewFragment cartViewFragment = CartViewFragment.newInstance(cart);
        fragmentTransaction.replace(R.id.container, cartViewFragment);
        fragmentTransaction.commit();
    }

    private void showOfflineCartContent() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        OfflineCart offlineCart = MyApplication.getInstance().getPrefManager().loadOfflineCart();
        CartViewOfflineFragment cartViewOfflineFragment = CartViewOfflineFragment.newInstance(offlineCart);
        fragmentTransaction.replace(R.id.container, cartViewOfflineFragment);
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
    }

    @Override
    protected void updateCartToolbarIcon() {
        // Display badge over cart icon if there are some items in the cart
        ImageButton icCart = (ImageButton) findViewById(R.id.toolbar_btn_cart);
        TextView tvItemCount = (TextView) findViewById(R.id.badge_textView);
        int itemCount = MyApplication.getInstance().getSessionManager().getCartItemCount();
        Assert.assertNotNull(tvItemCount);
        Assert.assertNotNull(icCart);
        icCart.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_delete_white_24dp));
        if (itemCount == 0) {
            tvItemCount.setVisibility(View.GONE);
            icCart.setVisibility(View.GONE);
        } else {
            tvItemCount.setText(String.valueOf(itemCount));
        }
        icCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDeleteАllConfirmationDialog.create().show();
            }
        });
    }
}
