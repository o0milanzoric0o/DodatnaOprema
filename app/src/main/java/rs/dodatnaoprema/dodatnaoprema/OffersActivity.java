package rs.dodatnaoprema.dodatnaoprema;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSelectedProducts;

public class OffersActivity extends BaseActivity {

    private CustomRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerViewSelectedProducts mAdapter;

    private List<Article> articles = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers_activity);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);

        Intent intent = getIntent();
        String mSubCategoryName = intent.getStringExtra("Artikli");
        articles = (List<Article>) intent.getSerializableExtra("AllCategories");
        if (mTextView != null) mTextView.setText(mSubCategoryName);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (CustomRecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerViewSelectedProducts(this, articles, true, new RecyclerViewSelectedProducts.OnItemClickListener() {
            @Override
            public void onItemClick(Article item, View view) {
                ///Start Intent for Single Item Activity
            }
        });
        mRecyclerView.setAdapter(mAdapter);

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
