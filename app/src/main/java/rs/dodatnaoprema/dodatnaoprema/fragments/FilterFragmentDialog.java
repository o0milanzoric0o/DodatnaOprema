package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.SubCategoryArticlesActivity;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;
import rs.dodatnaoprema.dodatnaoprema.customview.MultiSelectionSpinner;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Brendovus;
import rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification.CategorySpecification;
import rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification.Spec;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSubcategorySpecification;

public class FilterFragmentDialog extends DialogFragment implements AdapterView.OnItemSelectedListener, MultiSelectionSpinner.OnMultipleItemsSelectedListener {

    private RecyclerView mRecyclerView;

    private List<Spec> specifications = new ArrayList<>();
    private List<String> brandNames = new ArrayList<>();

    private int numberOfSelectedBrands = 0;

    private Spinner priceOptions;

    private int priceOptionSelected = 0;

    private double down = 0.0;
    private double up = Double.MAX_VALUE;

    private VolleySingleton mVolleySingleton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Filter_DIALOG);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.subcategory_specification_activity, container, false);

        TextView mTextView = (TextView) root.findViewById(R.id.title);
        mTextView.setText(getResources().getString(R.string.filter_txt));

        List<Brendovus> mBrands = ((SubCategoryArticlesActivity) getActivity()).getBrands();

        for (Brendovus brands : mBrands) {

            brandNames.add(brands.getBrendIme());
        }

        final MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) root.findViewById(R.id.multiSelectionSpinnerBrands);
        if (multiSelectionSpinner != null) {
            if (brandNames.size() > 0) {
                multiSelectionSpinner.setItems(brandNames);
                multiSelectionSpinner.setListener(this);
            } else {
                multiSelectionSpinner.setVisibility(View.GONE);
            }

            multiSelectionSpinner.setSelection(SharedPreferencesUtils.getArrayList(getActivity(), AppConfig.SELECTED_BRANDS_KEY));
        }

        mVolleySingleton = VolleySingleton.getsInstance(getActivity());

        TextView mTextViewResults = (TextView) root.findViewById(R.id.searchResultsNumber);
        if (mTextViewResults != null) {
            Log.logInfo("SEARCH", "Broj rezultata dialog: "+((SubCategoryArticlesActivity) getActivity()).getNumberOfResults());
            mTextViewResults.setText(getString(R.string.txt_search_results, ((SubCategoryArticlesActivity) getActivity()).getNumberOfResults()));
        }


        priceOptions = (Spinner) root.findViewById(R.id.pricesFilter);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.addAll(getResources().getStringArray(R.array.price_options));

        if (priceOptions != null) {

            priceOptions.setAdapter(dataAdapter);
            priceOptions.setOnItemSelectedListener(this);
            priceOptions.setSelection(SharedPreferencesUtils.getInt(getActivity(), AppConfig.SELECTED_PRICES_KEY));

        }

        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_specifications);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Button btnApply = (Button) root.findViewById(R.id.applyBtn);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((SubCategoryArticlesActivity) getActivity()).filterArticles(down, up);
                SharedPreferencesUtils.putInt(getActivity(), AppConfig.SELECTED_PRICES_KEY, priceOptionSelected);

                if (priceOptionSelected != 0) {
                    ((SubCategoryArticlesActivity) getActivity()).setFiltered(true);
                }

                if (numberOfSelectedBrands > 0) {
                    ((SubCategoryArticlesActivity) getActivity()).setFiltered(true);
                }
                dismiss();
            }
        });

        Button btnReset = (Button) root.findViewById(R.id.resetBtn);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset spinner with prices
                if (priceOptions != null) priceOptions.setSelection(0);

                //delete saved filter's options
                SharedPreferencesUtils.clearSharedPreferences(getActivity(), AppConfig.SELECTED_PRICES_KEY);
                SharedPreferencesUtils.clearSharedPreferences(getActivity(), AppConfig.SELECTED_BRANDS_KEY);

                //reset spinner with brands
                if (multiSelectionSpinner != null) multiSelectionSpinner.resetSpinner();

                //reset spinner with specifications
                RecyclerViewSubcategorySpecification mAdapter = new RecyclerViewSubcategorySpecification(getActivity(), specifications);
                mRecyclerView.setAdapter(mAdapter);

                //set activity filter indicator to false
                ((SubCategoryArticlesActivity) getActivity()).setFiltered(false);
            }
        });

        ImageButton exitFragmentBtn = (ImageButton) root.findViewById(R.id.exit_btn);
        exitFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close dialog
                dismiss();

            }
        });

        subCategoriesSpecifications(((SubCategoryArticlesActivity) getActivity()).getClickedSubcategoryId());

        return root;
    }


    private void subCategoriesSpecifications(int id) {

        PullWebContent<CategorySpecification> content = new PullWebContent<>(getActivity(), CategorySpecification.class, UrlEndpoints.getRequestUrlCategorySpecification(id), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<CategorySpecification>() {
            @Override
            public void webRequestSuccess(boolean success, CategorySpecification categorySpecification) {
                if (success) {

                    if (categorySpecification.getSpec().size() > 0) {

                        specifications = categorySpecification.getSpec();
                        RecyclerViewSubcategorySpecification mAdapter = new RecyclerViewSubcategorySpecification(getActivity(), specifications);
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

        if (position == 0) {
            priceOptionSelected = 0;
            down = 0.0;
            up = Double.MAX_VALUE;
        } else if (position == 1) {
            priceOptionSelected = 1;
            down = 0.0;
            up = 500.0;
        } else if (position == 2) {
            priceOptionSelected = 2;
            down = 500.0;
            up = 1000.0;
        } else if (position == 3) {
            priceOptionSelected = 3;
            down = 1000.0;
            up = 2000.0;
        } else if (position == 4) {
            priceOptionSelected = 4;
            down = 2000.0;
            up = 5000.0;
        } else if (position == 5) {
            priceOptionSelected = 5;
            down = 5000.0;
            up = 10000.0;
        } else if (position == 6) {
            priceOptionSelected = 6;
            down = 10000.0;
            up = 20000.0;
        } else if (position == 7) {
            priceOptionSelected = 7;
            down = 20000.0;
            up = 50000.0;
        } else if (position == 8) {
            priceOptionSelected = 8;
            down = 50000.0;
            up = Double.MAX_VALUE;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {

        SharedPreferencesUtils.putArrayList(getActivity(), AppConfig.SELECTED_BRANDS_KEY, new ArrayList<>(strings));
        numberOfSelectedBrands = strings.size();

    }
}
