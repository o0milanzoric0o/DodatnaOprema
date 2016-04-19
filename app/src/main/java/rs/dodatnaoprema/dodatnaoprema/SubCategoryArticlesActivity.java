package rs.dodatnaoprema.dodatnaoprema;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_filtered_by_category.ArticlesFilteredByCategory;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSelectedProducts;

public class SubCategoryArticlesActivity extends BaseActivity {

    private List<Article> mArticles = new ArrayList<>();
    private VolleySingleton mVolleySingleton;

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private RecyclerViewSelectedProducts mAdapter;

    private boolean nextImgStateGrid = true;

    private void searchArticlesByCategory(int id, int from, int to, int sort) {
        PullWebContent<ArticlesFilteredByCategory> content = new PullWebContent<ArticlesFilteredByCategory>(this, ArticlesFilteredByCategory.class, UrlEndpoints.getRequestUrlSearchArticlesByCategory(id, from, to, AppConfig.URL_VALUE_CURRENCY_RSD, AppConfig.URL_VALUE_LANGUAGE_SRB_LAT, sort), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesFilteredByCategory>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesFilteredByCategory articlesFilteredByCategory) {
                if (success) {
                    mArticles = articlesFilteredByCategory.getArtikli();
                    // gridAdapter.updateContent(mArticles);
                    mRecyclerView.setAdapter(mAdapter);

                }
            }

            @Override
            public void webRequestError(String error) {

            }
        });
        content.pullList();

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_category_articles_activity);

        Intent intent = getIntent();
        String mSubCategoryName = intent.getStringExtra("Artikli");
        int mArticleId = intent.getIntExtra("ArtikalId", 0);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText(mSubCategoryName);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageButton listGridChangeBtn = (ImageButton) findViewById(R.id.list_grid_change_btn);
        listGridChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextImgStateGrid) {
                    listGridChangeBtn.setImageResource(R.drawable.ic_view_module_black_24dp);
                    nextImgStateGrid = false;
                } else {
                    listGridChangeBtn.setImageResource(R.drawable.ic_reorder_black_24dp);
                    nextImgStateGrid = true;
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
