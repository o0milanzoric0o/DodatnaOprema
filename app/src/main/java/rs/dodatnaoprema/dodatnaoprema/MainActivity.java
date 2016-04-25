package rs.dodatnaoprema.dodatnaoprema;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.gcm.Config;
import rs.dodatnaoprema.dodatnaoprema.gcm.GcmIntentService;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.signin.AccountActivity;
import rs.dodatnaoprema.dodatnaoprema.signin.SignInActivity;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.ViewPagerAdapter;

// mirko svemirko komentar
public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // The following fields are added to support GCM
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    RelativeLayout mFourthButton;

    AppBarLayout mAppBar;

    TabLayout mTabLayout;
    private List<Category> mAllCategories = new ArrayList<>();
    private ViewPager mViewPager;
    private Intent intent;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // ImageButton icMore = (ImageButton) findViewById(R.id.toolbar_ic_more);
        ImageButton icCart = (ImageButton) findViewById(R.id.toolbar_btn_cart);
        icCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

        mFourthButton = (RelativeLayout) findViewById(R.id.fourth_round_button);

        //  icMore.setVisibility(View.GONE);

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

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    String token = intent.getStringExtra("token");

                    Toast.makeText(getApplicationContext(), "GCM registration token: " + token, Toast.LENGTH_LONG).show();

                } else if (intent.getAction().equals(Config.SENT_TOKEN_TO_SERVER)) {
                    // gcm registration id is stored in our server's MySQL

                    Toast.makeText(getApplicationContext(), "GCM registration token is stored in server!", Toast.LENGTH_LONG).show();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    Toast.makeText(getApplicationContext(), "Push notification is received!", Toast.LENGTH_LONG).show();
                } else if (intent.getAction().equals(Config.SET_USER_INFO)) {
                    // Update user email, name and photo
                    User user = MyApplication.getInstance().getPrefManager().getUser();
                    ((TextView) findViewById(R.id.id_user_email)).setText(user.getEmail());
                    ((TextView) findViewById(R.id.id_user_name)).setText(user.getName());
                    ((ImageView) findViewById(R.id.id_user_photo)).setImageURI(user.getPhoto());
                } else if (intent.getAction().equals(Config.CLEAR_USER_INFO)) {
                    // Update user email, name and photo
                    ((TextView) findViewById(R.id.id_user_email)).setText("");
                    ((TextView) findViewById(R.id.id_user_name)).setText(getString(R.string.user_name_unavailable));
                    ((ImageView) findViewById(R.id.id_user_photo)).setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.googleg_color));
                }
            }
        };

        if (checkPlayServices()) {
            registerGCM();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.SET_USER_INFO));

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.CLEAR_USER_INFO));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onDestroy();
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
            Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
            startActivity(intent);

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

    @SuppressWarnings("unchecked")
    public List<Category> getCategoriesList() {
        mAllCategories = (List<Category>) intent.getSerializableExtra("AllCategories");
        return mAllCategories;
    }

    @SuppressWarnings("unchecked")
    public List<Article> getProductsOnSale() {
        List<Article> mProductsOnSale;
        mProductsOnSale = (List<Article>) intent.getSerializableExtra(AppConfig.FIRST_TAB_ITEMS[0]);
        return mProductsOnSale;
    }

    @SuppressWarnings("unchecked")
    public List<Article> getNewProducts() {
        List<Article> mNewProducts;
        mNewProducts = (List<Article>) intent.getSerializableExtra(AppConfig.FIRST_TAB_ITEMS[1]);
        return mNewProducts;
    }

    @SuppressWarnings("unchecked")
    public List<Article> getBestSellingProducts() {
        List<Article> mBestSelling;
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


    // starting the service to register with GCM
    private void registerGCM() {
        Intent intent = new Intent(this, GcmIntentService.class);
        intent.putExtra("key", "register");
        startService(intent);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported. Google Play Services not installed!");
                Toast.makeText(getApplicationContext(), "This device is not supported. Google Play Services not installed!", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }
}
