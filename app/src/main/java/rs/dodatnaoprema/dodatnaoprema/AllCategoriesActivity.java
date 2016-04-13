package rs.dodatnaoprema.dodatnaoprema;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import views.adapters.RecyclerViewAllCategories;

/**
 * Created by Win 7 on 12.4.2016.
 */
public class AllCategoriesActivity extends AppCompatActivity {

    private AppBarLayout mAppBar;
    private List<Category> allCategories;
    private StaggeredGridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.all_categories_activity);
        Intent intent = getIntent();
        allCategories = new ArrayList<>();
        allCategories = (List<Category>) intent.getSerializableExtra("SveKategorije");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sve kategorije");

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_categories);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

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

                Intent intent = new Intent(getApplicationContext(),SubCategoriesActivity.class);
                intent.putExtra("Potkategorije", (Serializable) item);
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
