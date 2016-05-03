package rs.dodatnaoprema.dodatnaoprema;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewAllCategories;

public class AllCategoriesActivity extends BaseActivity {

    List<Category> allCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.all_categories_activity);
        Intent intent = getIntent();


        allCategories = (List<Category>) intent.getSerializableExtra("SveKategorije");

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText("Sve kategorije");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateRecyclerView();
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

    @Override
    protected void onResume() {
        super.onResume();

        populateRecyclerView();

    }

    public void populateRecyclerView() {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_categories);


        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        StaggeredGridLayoutManager mLayoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // use a linear layout manager
            mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        } else {
            mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        }
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerViewAllCategories mAdapter = new RecyclerViewAllCategories(this, allCategories, new RecyclerViewAllCategories.OnItemClickListener() {
            @Override
            public void onItemClick(Category item) {

                Intent intent = new Intent(getApplicationContext(), SubCategoriesActivity.class);
                intent.putExtra("Potkategorije", item);
                startActivity(intent);

            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }
    public void shortcutArticles(String subcategory, String id){
        Intent intent = new Intent(getApplicationContext(), SubCategoryArticlesActivity.class);
        intent.putExtra("Artikli", subcategory);
        intent.putExtra("ArtikalId", Integer.parseInt(id));
        startActivity(intent);
    }

}
