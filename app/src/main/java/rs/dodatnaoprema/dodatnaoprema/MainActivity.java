package rs.dodatnaoprema.dodatnaoprema;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.gcm.QuickstartPreferences;
import rs.dodatnaoprema.dodatnaoprema.gcm.RegistrationIntentService;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.signin.SignInActivity;
import views.adapters.ViewPagerAdapter;

// mirko svemirko komentar
public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Category> mAllCategories = new ArrayList<>();
    private List<Article> mBestSelling = new ArrayList<>();
    private List<Article> mProductsOnSale = new ArrayList<>();
    private List<Article> mNewProducts = new ArrayList<>();
    private ViewPager mViewPager;

    RelativeLayout mFourthButton;

    AppBarLayout mAppBar;

    TabLayout mTabLayout;

    private Intent intent;

    // The following fields are added to support GCM
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageButton icMore = (ImageButton) findViewById(R.id.toolbar_ic_more);
        ImageButton icCart = (ImageButton) findViewById(R.id.toolbar_btn_cart);
        icCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

        mFourthButton = (RelativeLayout) findViewById(R.id.fourth_round_button);

        icMore.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeTabs();

        mAppBar = (AppBarLayout) findViewById(R.id.appBar);


        // GCM support
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    /**TODO token sent successfully*/
                    Log.i(TAG, getString(R.string.gcm_send_message));
                } else {
                    /**TODO token send failed*/
                    Log.i(TAG, getString(R.string.token_error_message));
                }
            }
        };


        // Registering BroadcastReceiver
        registerReceiver();

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
            Log.i(TAG,"YADAYADAYADA");
        }

        // FOR DEBUGGING
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }


    public void initializeTabs() {
        intent = getIntent();
        getIntentExtras();

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.first_tab)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.second_tab)));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    mAppBar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_dark));
                } else if (tab.getPosition() == 1) {
                    mAppBar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.primary));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            // Handle the login action
        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_chart) {

        } else if (id == R.id.nav_best) {

        } else if (id == R.id.nav_new) {

        } else if (id == R.id.nav_sale) {

        } else if (id == R.id.nav_how_to_buy) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<Category> getCategoriesList() {
        mAllCategories = (List<Category>) intent.getSerializableExtra("AllCategories");
        return mAllCategories;
    }

    public List<Article> getProductsOnSale() {
        mProductsOnSale = (List<Article>) intent.getSerializableExtra(AppConfig.FIRST_TAB_ITEMS[0]);
        return mProductsOnSale;
    }

    public List<Article> getNewProducts() {
        mNewProducts = (List<Article>) intent.getSerializableExtra(AppConfig.FIRST_TAB_ITEMS[1]);
        return mNewProducts;
    }

    public List<Article> getBestSellingProducts() {
        mBestSelling = (List<Article>) intent.getSerializableExtra(AppConfig.FIRST_TAB_ITEMS[2]);
        return mBestSelling;
    }

    private void getIntentExtras() {

        getProductsOnSale();
        getNewProducts();
        getBestSellingProducts();
        getCategoriesList();

    }

    public HashMap<String, List<Article>> getFirstTabItems() {

        HashMap<String, List<Article>> productsOnSale = new HashMap<>();
        productsOnSale.put(AppConfig.FIRST_TAB_ITEMS[0], getProductsOnSale());
        productsOnSale.put(AppConfig.FIRST_TAB_ITEMS[1], getNewProducts());
        productsOnSale.put(AppConfig.FIRST_TAB_ITEMS[2], getBestSellingProducts());
        return productsOnSale;

    }

    public void moveToNextTab() {

        mViewPager.setCurrentItem(mTabLayout.getSelectedTabPosition() + 1);

    }

    public void viewAllCategories() {

        intent = new Intent(getApplicationContext(), AllCategoriesActivity.class);
        intent.putExtra("SveKategorije", (Serializable) mAllCategories);

        startActivity(intent);
    }

    private void registerReceiver() {
        if (!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

}
