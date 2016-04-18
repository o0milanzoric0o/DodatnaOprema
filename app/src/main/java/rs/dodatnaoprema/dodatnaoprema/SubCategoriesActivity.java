package rs.dodatnaoprema.dodatnaoprema;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Child;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSubCategories;

public class SubCategoriesActivity extends BaseActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategories_activity);

        Intent intent = getIntent();
        List<Child> subCategories = new ArrayList<>();
        Category item = (Category) intent.getSerializableExtra("Potkategorije");
        subCategories = item.getChild();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText(item.getKatsrblat());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_subcategories);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager mLayoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // use a linear layout manager
            mLayoutManager = new GridLayoutManager(this, 6, GridLayoutManager.VERTICAL, false);
        } else {
            mLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        }
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerViewSubCategories mAdapter = new RecyclerViewSubCategories(this, subCategories, new RecyclerViewSubCategories.OnItemClickListener() {
            @Override
            public void onItemClick(Child item, final View view) {

                ArrayList<String> mHistory;
                mHistory = SharedPreferencesUtils.getArrayList(getApplication(), AppConfig.HISTORY_KEY);

                if (mHistory.contains(item.getKatIme())) {
                    mHistory.remove(item.getKatIme());
                    mHistory.add(0, item.getKatIme());
                } else {
                    mHistory.add(0, item.getKatIme());
                }

                SharedPreferencesUtils.clearSharedPreferences(getApplication(), AppConfig.HISTORY_KEY);
                if (mHistory.size() >= 4) {
                    SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_KEY, new ArrayList<String>(mHistory.subList(0, 4)));
                } else {
                    SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_KEY, mHistory);
                }

                Intent intent = new Intent(getApplicationContext(), SubCategoryArticlesActivity.class);
                intent.putExtra("Artikli", item.getKatIme());
                intent.putExtra("ArtikalId", item.getKategorijaArtikalaId());
                startActivity(intent);
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
