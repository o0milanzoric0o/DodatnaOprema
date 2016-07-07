package rs.dodatnaoprema.dodatnaoprema.common.utils;

import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
import rs.dodatnaoprema.dodatnaoprema.QuestionActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.SearchActivity;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.fragments.InfoFragmentDialog;


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
                    setSearchButton();
                }
            }
        };
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (getApplicationContext() instanceof QuestionActivity) {

            menu.findItem(R.id.item_instructions).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pop_up_menu, menu);
        if (getApplicationContext() instanceof QuestionActivity) {

        }

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
            case R.id.item_instructions:
                Bundle args = new Bundle();
                args.putString("mTitle", getString(R.string.how_to_buy));
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                InfoFragmentDialog frag = new InfoFragmentDialog();
                frag.setArguments(args);
                frag.show(ft, "txn_tag");
            case R.id.item_question:
                intent = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mUpdateToolbarBroadcastReceiver);

    }

    protected void updateCartToolbarIcon() {
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
            tvItemCount.setVisibility(View.VISIBLE);
        }
        icCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void setSearchButton() {
        ImageButton icSearch = (ImageButton) findViewById(R.id.toolbar_btn_search);
        icSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });
    }

}
