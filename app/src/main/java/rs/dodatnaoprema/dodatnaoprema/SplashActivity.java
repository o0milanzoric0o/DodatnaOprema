package rs.dodatnaoprema.dodatnaoprema;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_on_sale.Article;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_on_sale.ArticlesOnSale;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;

public class SplashActivity extends AppCompatActivity {

    private List<Category> mAllCategories = new ArrayList<>();
    private List<Article> mProducts = new ArrayList<>();

    private int requestCounter = 0;
    private VolleySingleton mVolleySingleton;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);
        ImageView image = (ImageView) findViewById(R.id.img_logo);
        image.setBackgroundResource(R.drawable.chainsaw_splah_screen_logo);
        AnimationDrawable rocketAnimation = (AnimationDrawable) image.getBackground();
        rocketAnimation.start();

        mVolleySingleton = VolleySingleton.getsInstance(this);
        intent = new Intent(getApplicationContext(), MainActivity.class);

        getBestSellingProducts();
        getProductsOnSale();
        getNewProducts();
        //   getAllCategories();


    }

    private void response() {
        requestCounter++;
        final int numberOfRequests = 3;
        if (requestCounter == numberOfRequests) {
            startActivity(intent);
            finish();


        }
    }

  /*  private void getAllCategories() {
        PullWebContent<AllCategories> content = new PullWebContent<AllCategories>(this, AllCategories.class, UrlEndpoints.getRequestUrlAllCategories(), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<AllCategories>() {
            @Override
            public void webRequestSuccess(boolean success, AllCategories allCategories) {
                if (success) {
                    mAllCategories = allCategories.getKategorije();
                    intent.putExtra("AllCategories", (Serializable) mAllCategories);
                    response();
                }
            }

            @Override
            public void webRequestError(String error) {
                response();
            }
        });
        content.pullList();
    }*/
  private void getProductsOnSale() {
      PullWebContent<ArticlesOnSale> content =
              new PullWebContent<ArticlesOnSale>(this, ArticlesOnSale.class, UrlEndpoints.getRequestUrlSearchOnSale(AppConfig.URL_VALUE_ID_ARTICLES_ON_SALE, 0, 4), mVolleySingleton);
      content.setCallbackListener(new WebRequestCallbackInterface<ArticlesOnSale>() {
          @Override
          public void webRequestSuccess(boolean success, ArticlesOnSale articles) {
              if (success) {
                  mProducts = articles.getKategorije();
                  intent.putExtra(AppConfig.FIRST_TAB_ITEMS[0], (Serializable) mProducts);
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
                new PullWebContent<ArticlesOnSale>(this, ArticlesOnSale.class, UrlEndpoints.getRequestUrlSearchOnSale(AppConfig.URL_VALUE_ID_NEW_PRODUCTS, 0, 4), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesOnSale>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesOnSale articles) {
                if (success) {
                    mProducts = articles.getKategorije();
                    intent.putExtra(AppConfig.FIRST_TAB_ITEMS[1], (Serializable) mProducts);
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
                new PullWebContent<ArticlesOnSale>(this, ArticlesOnSale.class, UrlEndpoints.getRequestUrlSearchOnSale(AppConfig.URL_VALUE_ID_BEST_SEllING, 0, 4), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesOnSale>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesOnSale articles) {
                if (success) {
                    mProducts = articles.getKategorije();
                    intent.putExtra(AppConfig.FIRST_TAB_ITEMS[2], (Serializable) mProducts);
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