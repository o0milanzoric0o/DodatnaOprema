package rs.dodatnaoprema.dodatnaoprema;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Child;
import rs.dodatnaoprema.dodatnaoprema.models.categories.categories_by_id.BreadCrump;
import rs.dodatnaoprema.dodatnaoprema.models.categories.categories_by_id.CategoriesByID;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSubCategories;

public class SubCategoriesActivity extends BaseActivity implements Serializable {

    private VolleySingleton mVolleySingleton;
    private int existSubcategory = 0;
    private ViewGroup pathList;

    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategories_activity);

        Intent intent = getIntent();
        // this is unchecked, but guaranteed to work
        List<Child> subCategories = (List<Child>) intent.getSerializableExtra("Potkategorije");
        List<BreadCrump> breadCrumpList = (List<BreadCrump>) intent.getSerializableExtra("breadCrump");
        String title = intent.getStringExtra("Title");
        pathList = (ViewGroup) findViewById(R.id.flow_layout_path);

        if (breadCrumpList.size() > 0) {
            pathList.addView(addNewButton("Sve kategorije", "0"));
            if (breadCrumpList.size() > 1) {
                pathList.addView(addSeparator());
            }
            for (int i = 0; i < breadCrumpList.size() - 1; i++) {
                pathList.addView(addNewButton(breadCrumpList.get(i).getIme(), breadCrumpList.get(i).getIdBc().toString()));

                if (breadCrumpList.size() > 0 && i < breadCrumpList.size() - 2) {
                    pathList.addView(addSeparator());
                }
            }
        }


        mVolleySingleton = VolleySingleton.getsInstance(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);

        if (mTextView != null) mTextView.setText(title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton icSearch = (ImageButton) findViewById(R.id.toolbar_btn_search);
        icSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });

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


                getCategoriesById(item.getKategorijaArtikalaId(), item);


            }
        });
        if (mRecyclerView != null) mRecyclerView.setAdapter(mAdapter);

    }

    private void getCategoriesById(int id, final Child item) {

        final ProgressDialogCustom progressDialog = new ProgressDialogCustom(SubCategoriesActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.showDialog("Učitavanje...");

        PullWebContent<CategoriesByID> content =
                new PullWebContent<>(CategoriesByID.class, UrlEndpoints.getRequestUrlCategoriesById(id), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<CategoriesByID>() {
            @Override
            public void webRequestSuccess(boolean success, CategoriesByID categoriesByID) {
                if (success) {
                    existSubcategory = categoriesByID.getKategorije().size();

                    if (existSubcategory == 0) {
                        setHistory(item);
                        Intent intent = new Intent(getApplicationContext(), SubCategoryArticlesActivity.class);
                        intent.putExtra("Artikli", item.getKatIme());
                        intent.putExtra("ArtikalId", item.getKategorijaArtikalaId());
                        intent.putExtra("breadCrump", (Serializable) categoriesByID.getBreadCrump());
                        startActivity(intent);
                        progressDialog.hideDialog();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), SubCategoriesActivity.class);
                        intent.putExtra("Potkategorije", (Serializable) categoriesByID.getKategorije());
                        intent.putExtra("breadCrump", (Serializable) categoriesByID.getBreadCrump());
                        intent.putExtra("Title", item.getKatIme());
                        startActivity(intent);
                        progressDialog.hideDialog();
                    }
                }
            }

            @Override
            public void webRequestError(String error) {
                progressDialog.hideDialog();
            }
        });
        content.pullList();
    }

    private void getCategories(int id, final String categoryName) {

        final ProgressDialogCustom progressDialog = new ProgressDialogCustom(SubCategoriesActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.showDialog("Učitavanje...");

        PullWebContent<CategoriesByID> content =
                new PullWebContent<>(CategoriesByID.class, UrlEndpoints.getRequestUrlCategoriesById(id), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<CategoriesByID>() {
            @Override
            public void webRequestSuccess(boolean success, CategoriesByID categoriesByID) {
                if (success) {

                    Intent intent = new Intent(getApplicationContext(), SubCategoriesActivity.class);
                    intent.putExtra("Potkategorije", (Serializable) categoriesByID.getKategorije());
                    intent.putExtra("breadCrump", (Serializable) categoriesByID.getBreadCrump());
                    intent.putExtra("Title", categoryName);
                    startActivity(intent);
                    progressDialog.hideDialog();

                }
            }

            @Override
            public void webRequestError(String error) {
                progressDialog.hideDialog();
            }
        });
        content.pullList();
    }


    public void setHistory(Child item) {

        ArrayList<String> mHistory;
        ArrayList<String> mHistoryID;

        mHistory = SharedPreferencesUtils.getArrayList(getApplication(), AppConfig.HISTORY_KEY);
        mHistoryID = SharedPreferencesUtils.getArrayList(getApplication(), AppConfig.HISTORY_ID_KEY);

        if (mHistory != null && mHistory.contains(item.getKatIme())) { //check if subcategory shortcut exists
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
        if (mHistory.size() >= 10) { // limit size of history array to 10 items
            SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_KEY, new ArrayList<>(mHistory.subList(0, 10)));
            SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_ID_KEY, new ArrayList<>(mHistoryID.subList(0, 10)));

        } else {
            SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_KEY, mHistory);
            SharedPreferencesUtils.putArrayList(getApplication(), AppConfig.HISTORY_ID_KEY, mHistoryID);
        }

    }


    private TextView addNewButton(final String subcategory, final String id) {

        RecyclerView.LayoutParams param = new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);

        SpannableString content = new SpannableString(subcategory);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        final TextView tv = new TextView(pathList.getContext());

        tv.setLayoutParams(param);
        tv.setPadding(2, 2, 2, 2);
        tv.setGravity(Gravity.CENTER);
        tv.setClickable(true);
        tv.setMaxLines(1);
        tv.setEllipsize(TextUtils.TruncateAt.END);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subcategory.equalsIgnoreCase("Sve kategorije")) {
                    Intent intent = new Intent(getApplicationContext(), AllCategoriesActivity.class);
                    // Not going to put allCategories in an intent, better get it from shared prefs
                    //intent.putExtra("SveKategorije", (Serializable) mAllCategories);
                    startActivity(intent);
                } else {
                    getCategories(Integer.parseInt(id), subcategory);
                }
            }
        });

        tv.setText(content);
        tv.setAllCaps(false);
        tv.setTextColor(ContextCompat.getColor(pathList.getContext(), R.color.primary_dark));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        tv.setMinHeight(10);
        tv.setMaxWidth(300);
        tv.setMinimumHeight(10);


        return tv;
    }

    private TextView addSeparator() {

        RecyclerView.LayoutParams param = new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);

        final TextView tv = new TextView(pathList.getContext());

        tv.setLayoutParams(param);
        tv.setPadding(2, 2, 2, 2);
        tv.setGravity(Gravity.CENTER);
        tv.setMaxLines(1);
        tv.setEllipsize(TextUtils.TruncateAt.END);

        tv.setText(">");
        tv.setAllCaps(false);
        tv.setTextColor(ContextCompat.getColor(pathList.getContext(), R.color.btnTextColor));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        tv.setMinHeight(10);
        tv.setMaxWidth(300);
        tv.setMinimumHeight(10);


        return tv;
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
