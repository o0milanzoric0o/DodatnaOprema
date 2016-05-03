package rs.dodatnaoprema.dodatnaoprema;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification.CategorySpecification;
import rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification.Spec;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSubcategorySpecification;

public class SubCategorySpecificationActivity extends BaseActivity {

    private int numberOfResults;
    private int subCategoryId;

    private RecyclerView mRecyclerView;
    private List<Spec> specifications = new ArrayList<>();

    private VolleySingleton mVolleySingleton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategory_specification_activity);

        Intent intent = getIntent();
        numberOfResults = intent.getIntExtra("NumberOfArticles", 0);
        subCategoryId = intent.getIntExtra("KategorijaID", 0);

        mVolleySingleton = VolleySingleton.getsInstance(this);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText("" + subCategoryId);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_specifications);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);


        subCategoriesSpecifications(subCategoryId);


    }

    private void subCategoriesSpecifications(int id) {

        PullWebContent<CategorySpecification> content = new PullWebContent<>(this, CategorySpecification.class, UrlEndpoints.getRequestUrlCategorySpecification(id), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<CategorySpecification>() {
            @Override
            public void webRequestSuccess(boolean success, CategorySpecification categorySpecification) {
                if (success) {

                    if (categorySpecification.getSpec().size() > 0) {

                        specifications = categorySpecification.getSpec();
                        RecyclerViewSubcategorySpecification mAdapter = new RecyclerViewSubcategorySpecification(getApplicationContext(), specifications);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                }
            }

            @Override
            public void webRequestError(String error) {

            }
        });
        content.pullList();

    }

}
