package rs.dodatnaoprema.dodatnaoprema;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.MultiSelectionSpinner;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Brendovus;
import rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification.CategorySpecification;
import rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification.Spec;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSubcategorySpecification;

public class SubCategorySpecificationActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, MultiSelectionSpinner.OnMultipleItemsSelectedListener {

    private RecyclerView mRecyclerView;

    private List<Spec> specifications = new ArrayList<>();
    private List<String> brandNames = new ArrayList<>();

    private VolleySingleton mVolleySingleton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategory_specification_activity);

        Intent intent = getIntent();
        int numberOfResults = intent.getIntExtra("NumberOfArticles", 0);
        int subCategoryId = intent.getIntExtra("KategorijaID", 0);
        List<Brendovus> mBrands = (List<Brendovus>) intent.getSerializableExtra("Brendovi");

        for (Brendovus brands : mBrands) {

            brandNames.add(brands.getBrendIme());
        }

        Log.logInfo("BRENDOVI", "" + mBrands.size());

        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.multiSelectionSpinnerBrands);
        if (multiSelectionSpinner != null) {
            multiSelectionSpinner.setItems(brandNames);
            multiSelectionSpinner.setListener(this);
        }

        mVolleySingleton = VolleySingleton.getsInstance(this);

        TextView mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText("Filter");

       // Log.logInfo("BROJ",""+numberOfResults);
        TextView mTextViewResults = (TextView) findViewById(R.id.searchResultsNumber);
        if (mTextViewResults != null) {
            Log.logInfo("BROJ",""+numberOfResults);
            mTextViewResults.setText(getResources().getString(R.string.txt_search_results)+" "+String.valueOf(numberOfResults));
        }


        Spinner priceOptions = (Spinner) findViewById(R.id.pricesFilter);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.addAll(getResources().getStringArray(R.array.price_options));
        if (priceOptions != null) {
            priceOptions.setAdapter(dataAdapter);
            priceOptions.setOnItemSelectedListener(this);
        }

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {

    }
}
