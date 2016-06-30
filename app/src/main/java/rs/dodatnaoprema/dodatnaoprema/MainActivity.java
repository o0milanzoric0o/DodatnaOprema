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
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.application.SessionManager;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.articles.brands.Brand;
import rs.dodatnaoprema.dodatnaoprema.models.articles.products_of_the_week.Product;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.models.categories.you_may_also_like_categories.YMALCategory;
import rs.dodatnaoprema.dodatnaoprema.signin.AccountActivity;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.ViewPagerAdapter;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";


    AppBarLayout mAppBar;

    TabLayout mTabLayout;
    private List<Category> mAllCategories = new ArrayList<>();
    private ViewPager mViewPager;
    private Intent intent;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // ImageButton icMore = (ImageButton) findViewById(R.id.toolbar_ic_more);

        updateCartToolbarIcon();

        //  icMore.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                session = MyApplication.getInstance().getSessionManager();
                if (session.isLoggedIn()) {
                    setUserDrawerInfo();
                } else {
                    clearUserDrawerInfo();
                }
            }
        };
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeTabs();

        mAppBar = (AppBarLayout) findViewById(R.id.appBar);


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.SET_USER_INFO)) {
                    setUserDrawerInfo();
                } else if (intent.getAction().equals(Config.CLEAR_USER_INFO)) {
                    clearUserDrawerInfo();
                } else if (intent.getAction().equals(Config.UPDATE_CART_TOOLBAR_ICON)) {
                    updateCartToolbarIcon();
                }
            }
        };


        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]

        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        Log.d(TAG, "Subscribed to " + "news" + " topic");
        // [END subscribe_topics]

        Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());
    }

    private void updateCartToolbarIcon() {
        // Display badge over cart icon if there are some items in the cart
        ImageButton icCart = (ImageButton) findViewById(R.id.toolbar_btn_cart);
        TextView tvItemCount = (TextView) findViewById(R.id.badge_textView);
        session = MyApplication.getInstance().getSessionManager();
        int itemCount = session.getCartItemCount();
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

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.SET_USER_INFO));

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.CLEAR_USER_INFO));

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.UPDATE_CART_TOOLBAR_ICON));


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

    private void setUserDrawerInfo() {
        // Update user email, name and photo
        User user = MyApplication.getInstance().getPrefManager().getUser();
        ((TextView) findViewById(R.id.id_user_email)).setText(user.getEmail());
        TextView tv_user_name = (TextView) findViewById(R.id.id_user_name);
        tv_user_name.setText(user.getName());
        tv_user_name.setOnClickListener(null);
        ((ImageView) findViewById(R.id.id_user_photo)).setImageURI(user.getPhoto());
    }

    private void clearUserDrawerInfo() {
        // Update user email, name and photo
        ((TextView) findViewById(R.id.id_user_email)).setText("");
        TextView tv_user_name_unavailable = (TextView) findViewById(R.id.id_user_name);
        tv_user_name_unavailable.setText(getString(R.string.user_name_unavailable));
        tv_user_name_unavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the login action
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(intent);
                // close drawer
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        ((ImageView) findViewById(R.id.id_user_photo)).setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.googleg_color));
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
            Intent intent = new Intent(this, OffersActivity.class);
            intent.putExtra("Artikli", AppConfig.FIRST_TAB_ITEMS[2]);
            intent.putExtra("AllCategories", (Serializable) getBestSellingProducts());
            startActivityOneArticle(intent);

        } else if (id == R.id.nav_new) {

            Intent intent = new Intent(this, OffersActivity.class);
            intent.putExtra("Artikli", AppConfig.FIRST_TAB_ITEMS[1]);
            intent.putExtra("AllCategories", (Serializable) getNewProducts());
            startActivityOneArticle(intent);


        } else if (id == R.id.nav_sale) {
            articlesOnSale();

        } else if (id == R.id.nav_how_to_buy) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<Category> getCategoriesList() {
        // mAllCategories = (List<Category>) intent.getSerializableExtra("AllCategories");
        mAllCategories = SharedPreferencesUtils.getArrayListCategories(this, AppConfig.ALL_CATEGORIES);
        return mAllCategories;
    }

    public List<Article> getProductsOnSale() {
        List<Article> mProductsOnSale;
        mProductsOnSale = SharedPreferencesUtils.getArrayListArticle(this, AppConfig.SALE);
        return mProductsOnSale;
    }

    public List<Article> getNewProducts() {
        List<Article> mNewProducts;
        mNewProducts = SharedPreferencesUtils.getArrayListArticle(this, AppConfig.NEW);
        return mNewProducts;
    }

    public List<Article> getBestSellingProducts() {
        List<Article> mBestSelling;
        mBestSelling = SharedPreferencesUtils.getArrayListArticle(this, AppConfig.BEST);
        return mBestSelling;
    }

    public ArrayList<Product> getProductsOfTheWeek() {
        List<Product> products;
        products = SharedPreferencesUtils.getArrayListProducts(this, AppConfig.THE_PRODUCTS_OF_THE_WEEK);
        return new ArrayList<>(products);
    }

    public ArrayList<Brand> getAllBrands() {
        List<Brand> brands;
        brands = SharedPreferencesUtils.getArrayListBrands(this, AppConfig.ALL_BRANDS);

        return new ArrayList<>(brands);
    }

    public List<YMALCategory> getYMALCategories() {
        List<YMALCategory> ymalCategories;
        ymalCategories = SharedPreferencesUtils.getArrayListYAML(this, AppConfig.YOU_MAY_ALSO_LIKE_CATEGORIES);
        return ymalCategories;
    }

    private void getIntentExtras() {

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

    public void articlesOnSale() {

        Intent intent = new Intent(this, OffersActivity.class);
        intent.putExtra("Artikli", AppConfig.FIRST_TAB_ITEMS[0]);
        intent.putExtra("AllCategories", (Serializable) getProductsOnSale());
        startActivityOneArticle(intent);
    }

    public void startActivityOneArticle(Intent intent) {
        startActivity(intent);
    }
}
