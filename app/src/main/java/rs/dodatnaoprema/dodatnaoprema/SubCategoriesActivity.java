package rs.dodatnaoprema.dodatnaoprema;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_on_sale.ArticlesOnSale;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Child;
import rs.dodatnaoprema.dodatnaoprema.models.categories.categories_by_id.CategoriesByID;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSubCategories;

public class SubCategoriesActivity extends BaseActivity {

    private VolleySingleton mVolleySingleton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategories_activity);

        Intent intent = getIntent();
        List<Child> subCategories = (List<Child>) intent.getSerializableExtra("Potkategorije");
        String title = intent.getStringExtra("Title");

        //subCategories = item.getChild();

        mVolleySingleton = VolleySingleton.getsInstance(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        if (mTextView != null) mTextView.setText(title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CustomRecyclerView mRecyclerView = (CustomRecyclerView) findViewById(R.id.recycler_view_subcategories);

        if (mRecyclerView != null) {
            mRecyclerView.setFlingFactor(1);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setHasFixedSize(true);
        }

        GridLayoutManager mLayoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // use a linear layout manager
            mLayoutManager = new GridLayoutManager(this, 6, GridLayoutManager.VERTICAL, false);
        } else {
            mLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        }
        mLayoutManager.setAutoMeasureEnabled(true);
        if (mRecyclerView != null) mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerViewSubCategories mAdapter = new RecyclerViewSubCategories(this, subCategories, new RecyclerViewSubCategories.OnItemClickListener() {
            @Override
            public void onItemClick(Child item, final View view) {

                ArrayList<String> mHistory;
                ArrayList<String> mHistoryID;

                mHistory = SharedPreferencesUtils.getArrayList(getApplication(), AppConfig.HISTORY_KEY);
                mHistoryID = SharedPreferencesUtils.getArrayList(getApplication(), AppConfig.HISTORY_ID_KEY);

                if (mHistory.contains(item.getKatIme())) { //check if subcategory shortcut exists
                    // move existing shortcut to the beginning of the array
                    mHistory.remove(item.getKatIme());
                    mHistoryID.remove(String.valueOf(item.getKategorijaArtikalaId()));

                    mHistory.add(0, item.getKatIme());
                    mHistoryID.add(0, String.valueOf(item.getKategorijaArtikalaId()));
                } else {
                    // create new subcategory shortcut at the beginning of the array
                    mHistory.add(0, item.getKatIme());
                    mHistoryID.add(0, String.valueOf(item.getKategorijaArtikalaId()));
                }

                //clear existing history
                SharedPreferencesUtils.clearSharedPreferences(getApplication(), AppConfig.HISTORY_KEY);
                SharedPreferencesUtils.clearSharedPreferences(getApplication(), AppConfig.HISTORY_ID_KEY);

                //
                if (mHistory.size() >= 4) { // limit size of history array to 4 items
                    SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_KEY, new ArrayList<>(mHistory.subList(0, 4)));
                    SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_ID_KEY, new ArrayList<>(mHistoryID.subList(0, 4)));

                } else {
                    SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_KEY, mHistory);
                    SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_ID_KEY, mHistoryID);
                }

                Intent intent = new Intent(getApplicationContext(), SubCategoryArticlesActivity.class);
                intent.putExtra("Artikli", item.getKatIme());
                intent.putExtra("ArtikalId", item.getKategorijaArtikalaId());
                startActivity(intent);

            }
        });
        if (mRecyclerView != null) mRecyclerView.setAdapter(mAdapter);

    }

    private void getCategoriesById(int id) {
        PullWebContent<CategoriesByID> content =
                new PullWebContent<CategoriesByID>(this, CategoriesByID.class, UrlEndpoints.getRequestUrlCategoriesById(id), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<CategoriesByID>() {
            @Override
            public void webRequestSuccess(boolean success, CategoriesByID articles) {
                if (success) {
                    // articles.getKategorije()
                }
            }

            @Override
            public void webRequestError(String error) {

            }
        });
        content.pullList();
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
