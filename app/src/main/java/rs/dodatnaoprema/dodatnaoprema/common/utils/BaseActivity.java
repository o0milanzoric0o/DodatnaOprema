package rs.dodatnaoprema.dodatnaoprema.common.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

import java.io.Serializable;

import rs.dodatnaoprema.dodatnaoprema.CartActivity;
import rs.dodatnaoprema.dodatnaoprema.MainActivity;
import rs.dodatnaoprema.dodatnaoprema.OffersActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.gcm.Config;


public class BaseActivity extends AppCompatActivity {
    private BroadcastReceiver mUpdateToolbarBroadcastReceiver;

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mUpdateToolbarBroadcastReceiver,
                new IntentFilter(Config.UPDATE_CART_TOOLBAR_ICON));
        updateCartToolbarIcon();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUpdateToolbarBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.UPDATE_CART_TOOLBAR_ICON)) {
                    updateCartToolbarIcon();
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pop_up_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {

            case R.id.item_home:
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case R.id.item_signing:
                Toast.makeText(this, "Clicked: Menu No. 2 - SubMenu No .1", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item_sale:
                intent = new Intent(this, OffersActivity.class);
                intent.putExtra("Artikli", AppConfig.FIRST_TAB_ITEMS[0]);
                intent.putExtra("AllCategories", (Serializable) SharedPreferencesUtils.getArrayListArticle(this, "SALE"));
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mUpdateToolbarBroadcastReceiver);

    }

    private void updateCartToolbarIcon() {
        // Display badge over cart icon if there are some items in the cart
        ImageButton icCart = (ImageButton) findViewById(R.id.toolbar_btn_cart);
        TextView tvItemCount = (TextView) findViewById(R.id.badge_textView);
        int itemCount = MyApplication.getInstance().getSessionManager().getCartItemCount();
        Assert.assertNotNull(tvItemCount);
        Assert.assertNotNull(icCart);
        if (itemCount == 0) {
            tvItemCount.setVisibility(View.GONE);
        } else {
            tvItemCount.setText(String.valueOf(itemCount));
        }
        icCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }
}
