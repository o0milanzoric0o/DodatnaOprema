package rs.dodatnaoprema.dodatnaoprema;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_on_sale.ArticlesOnSale;
import rs.dodatnaoprema.dodatnaoprema.models.articles.brands.AllBrands;
import rs.dodatnaoprema.dodatnaoprema.models.articles.brands.Brand;
import rs.dodatnaoprema.dodatnaoprema.models.articles.products_of_the_week.Product;
import rs.dodatnaoprema.dodatnaoprema.models.articles.products_of_the_week.ProductsOfTheWeek;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.AllCategories;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.models.categories.you_may_also_like_categories.YMALCategories;
import rs.dodatnaoprema.dodatnaoprema.models.categories.you_may_also_like_categories.YMALCategory;
import rs.dodatnaoprema.dodatnaoprema.models.info.Data;
import rs.dodatnaoprema.dodatnaoprema.models.info.Info;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;

public class SplashActivity extends AppCompatActivity {

    Intent intent;
    private List<Category> mAllCategories = new ArrayList<>();
    private List<Article> mArticles = new ArrayList<>();
    private Data contact;
    private List<Product> mProducts = new ArrayList<>();
    private List<Brand> mBrands = new ArrayList<>();
    private List<YMALCategory> mYMALCategories = new ArrayList<>();
    private int requestCounter = 0;
    private VolleySingleton mVolleySingleton;
    private boolean skipLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);
        ImageView image = (ImageView) findViewById(R.id.img_logo);
        if (image != null) {
            image.setBackgroundResource(R.drawable.chainsaw_splah_screen_logo);
            AnimationDrawable rocketAnimation = (AnimationDrawable) image.getBackground();
            rocketAnimation.start();
        }
        mVolleySingleton = VolleySingleton.getsInstance(this);

        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                // let's see what's inside
                Log.e("SplashActivity", "Key: " + key + " Value: " + value);
            }
        }


        // [END handle_data_extras]

        intent = new Intent(getApplicationContext(), MainActivity.class);
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("articleID")) {
            intent.putExtra("articleID", getIntent().getExtras().getString("articleID"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            skipLoading = true;
        }

        intent.putExtra("Kljuc", "vrednost kljuca");

        if (!skipLoading) {
            getBestSellingProducts();
            getProductsOnSale();
            getNewProducts();
            getProductsOfTheWeek();
            getAllBrands();
            getYMALCategories();
            getAllCategories();
            getInfoContact();
            getInfoHowToBuy();
        }else{
            // show main activity...
            startActivity(intent);
            finish();
        }

    }

    private void response() {
        requestCounter++;
        final int numberOfRequests = 9;
        if (requestCounter == numberOfRequests) {
            startActivity(intent);
            finish();
        }
    }

    private void getAllCategories() {
        PullWebContent<AllCategories> content = new PullWebContent<>(AllCategories.class, UrlEndpoints.getRequestUrlAllCategories(), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<AllCategories>() {
            @Override
            public void webRequestSuccess(boolean success, AllCategories allCategories) {
                if (success) {
                    mAllCategories = allCategories.getKategorije();
                    // intent.putExtra("AllCategories", (Serializable) mAllCategories);
                    SharedPreferencesUtils.putArrayListCategories(getApplicationContext(), AppConfig.ALL_CATEGORIES, mAllCategories);

                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }

    private void getInfoContact() {
        final PullWebContent<Info> content = new PullWebContent<>(Info.class, UrlEndpoints.getRequestUrlInfo(AppConfig.URL_VALUE_CONTACT), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<Info>() {
            @Override
            public void webRequestSuccess(boolean success, Info info) {
                if (success) {
                    contact = info.getPodaci();
                    SharedPreferencesUtils.putString(getApplicationContext(), "INFO_CONTACT", contact.getOpisKategHeadTekst());
                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }

    private void getInfoHowToBuy() {
        final PullWebContent<Info> content = new PullWebContent<>(Info.class, UrlEndpoints.getRequestUrlInfo(AppConfig.URL_VALUE_HOW_TO_BUY), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<Info>() {
            @Override
            public void webRequestSuccess(boolean success, Info info) {
                if (success) {
                    contact = info.getPodaci();
                    SharedPreferencesUtils.putString(getApplicationContext(), "INFO_HOW_TO_BUY", contact.getOpisKategHeadTekst());
                    response();

                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }

    private void getProductsOnSale() {
        PullWebContent<ArticlesOnSale> content =
                new PullWebContent<>(ArticlesOnSale.class, UrlEndpoints.getRequestUrlSearchOnSaleAll(AppConfig.URL_VALUE_ID_ARTICLES_ON_SALE), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesOnSale>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesOnSale articles) {
                if (success) {
                    mArticles = articles.getKategorije();
                    SharedPreferencesUtils.putArrayListArticle(getApplicationContext(), AppConfig.SALE, mArticles);
                    // intent.putExtra(AppConfig.FIRST_TAB_ITEMS[0], (Serializable) mArticles);
                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }

    private void getNewProducts() {
        PullWebContent<ArticlesOnSale> content =
                new PullWebContent<>(ArticlesOnSale.class, UrlEndpoints.getRequestUrlSearchOnSaleAll(AppConfig.URL_VALUE_ID_NEW_PRODUCTS), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesOnSale>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesOnSale articles) {
                if (success) {
                    mArticles = articles.getKategorije();
                    SharedPreferencesUtils.putArrayListArticle(getApplicationContext(), AppConfig.NEW, mArticles);
                    // intent.putExtra(AppConfig.FIRST_TAB_ITEMS[1], (Serializable) mArticles);
                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }


    private void getBestSellingProducts() {
        PullWebContent<ArticlesOnSale> content =
                new PullWebContent<>(ArticlesOnSale.class, UrlEndpoints.getRequestUrlSearchOnSaleAll(AppConfig.URL_VALUE_ID_BEST_SEllING), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesOnSale>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesOnSale articles) {
                if (success) {
                    mArticles = articles.getKategorije();
                    SharedPreferencesUtils.putArrayListArticle(getApplicationContext(), AppConfig.BEST, mArticles);
                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }

    private void getProductsOfTheWeek() {
        PullWebContent<ProductsOfTheWeek> content =
                new PullWebContent<>(ProductsOfTheWeek.class, AppConfig.URL_PRODUCTS_OF_THE_WEEK, mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ProductsOfTheWeek>() {
            @Override
            public void webRequestSuccess(boolean success, ProductsOfTheWeek articles) {
                if (success) {
                    mProducts = articles.getArtikli();
                    //intent.putExtra(AppConfig.THE_PRODUCTS_OF_THE_WEEK, (Serializable) mProducts);
                    SharedPreferencesUtils.putArrayListProducts(getApplicationContext(), AppConfig.THE_PRODUCTS_OF_THE_WEEK, mProducts);

                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }

    private void getAllBrands() {
        PullWebContent<AllBrands> content =
                new PullWebContent<>(AllBrands.class, AppConfig.URL_ALL_BRENDS, mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<AllBrands>() {
            @Override
            public void webRequestSuccess(boolean success, AllBrands allBrands) {
                if (success) {
                    mBrands = allBrands.getBrand();
                    // intent.putExtra(AppConfig.ALL_BRANDS, (Serializable) mBrands);
                    SharedPreferencesUtils.putArrayListBrands(getApplicationContext(), AppConfig.ALL_BRANDS, mBrands);
                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }

    private void getYMALCategories() { // get YOU MAY ALSO LIKE CATEGORIES
        PullWebContent<YMALCategories> content =
                new PullWebContent<>(YMALCategories.class, AppConfig.URL_YOU_MAY_ALSO_LIKE_CATEGORIES, mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<YMALCategories>() {
            @Override
            public void webRequestSuccess(boolean success, YMALCategories ymalCategories) {
                if (success) {
                    mYMALCategories = ymalCategories.getKategorije();
                    // intent.putExtra(AppConfig.YOU_MAY_ALSO_LIKE_CATEGORIES, (Serializable) mYMALCategories);
                    SharedPreferencesUtils.putArrayListYAML(getApplicationContext(), AppConfig.YOU_MAY_ALSO_LIKE_CATEGORIES, mYMALCategories);
                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }

}