package rs.dodatnaoprema.dodatnaoprema;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.FlipAnimation;
import rs.dodatnaoprema.dodatnaoprema.fragments.ArticlesGrid;
import rs.dodatnaoprema.dodatnaoprema.fragments.ArticlesList;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_filtered_by_category.ArticlesFilteredByCategory;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;

public class SubCategoryArticlesActivity extends BaseActivity {

    private List<Article> mArticles = new ArrayList<>();
    private RelativeLayout mFooter;
    private VolleySingleton mVolleySingleton;

    private boolean nextImgStateGrid = true;

    private int mArticleId;
    private int sortOption = 0;
    private boolean addedFragments = false;


    public List<Article> getArticlesList() {
        return mArticles;
    }

    public int getmArticleId() {
        return mArticleId;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategory_articles_activity);

        Intent intent = getIntent();
        String mSubCategoryName = intent.getStringExtra("Artikli");
        mArticleId = intent.getIntExtra("ArtikalId", 0);

        Spinner mSpinner = (Spinner) findViewById(R.id.spinner_sort);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.sort_options));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0 && sortOption != 0) {
                    sortOption = 0;
                    searchArticlesByCategory(getmArticleId(), 0, 100, AppConfig.SORT_ASCENDING);
                } else if (position == 1 && sortOption != 1) {
                    sortOption = 1;
                    searchArticlesByCategory(getmArticleId(), 0, 100, AppConfig.SORT_DESCENDING);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mVolleySingleton = VolleySingleton.getsInstance(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText(mSubCategoryName);

      //  FrameLayout mFragmentHolder = (FrameLayout) findViewById(R.id.articles_content_list);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final ImageButton listGridChangeBtn = (ImageButton) findViewById(R.id.list_grid_change_btn);

        if (listGridChangeBtn != null) {
            listGridChangeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (nextImgStateGrid) {
                        listGridChangeBtn.setImageResource(R.drawable.ic_reorder_black_24dp);
                        nextImgStateGrid = false;

                    } else {
                        listGridChangeBtn.setImageResource(R.drawable.ic_view_module_black_24dp);
                        nextImgStateGrid = true;

                    }
                    flipCard();

                }
            });
        }

        mFooter = (RelativeLayout) findViewById(R.id.filter_layout);
        mFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFooter.setSelected(true);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mFooter.setSelected(false);
                    }
                }, 1000);

                Intent intent = new Intent(getApplicationContext(), SubCategorySpecificationActivity.class);
                intent.putExtra("NumberOfArticles", mArticles.size());
                intent.putExtra("KategorijaID", mArticleId);
                startActivity(intent);
            }
        });

        searchArticlesByCategory(getmArticleId(), 0, 100, AppConfig.SORT_ASCENDING);

    }

    private void flipCard() {
        View rootLayout = findViewById(R.id.main_activity_root);
        View cardFace = findViewById(R.id.articles_content_list);
        View cardBack = findViewById(R.id.articles_content_grid);

        FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

        if (cardFace.getVisibility() == View.GONE) {
            flipAnimation.reverse();
        }
        rootLayout.startAnimation(flipAnimation);
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

    private void searchArticlesByCategory(int id, int from, final int to, int sort) {

        PullWebContent<ArticlesFilteredByCategory> content = new PullWebContent<>(this, ArticlesFilteredByCategory.class, UrlEndpoints.getRequestUrlSearchArticlesByCategory(id, from, to, AppConfig.URL_VALUE_CURRENCY_RSD, AppConfig.URL_VALUE_LANGUAGE_SRB_LAT, sort), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesFilteredByCategory>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesFilteredByCategory articlesFilteredByCategory) {
                if (success) {

                    mArticles = articlesFilteredByCategory.getArtikli();

                    if (!addedFragments) {

                        addedFragments = true;

                        getFragmentManager()
                                .beginTransaction()
                                .add(R.id.articles_content_list, new ArticlesList())
                                // Add this transaction to the back stack, allowing users to press Back
                                // to get to the front of the card.
                                .addToBackStack(null)
                                // Commit the transaction.
                                .commit();
                        getFragmentManager()
                                .beginTransaction()
                                .add(R.id.articles_content_grid, new ArticlesGrid())
                                // Add this transaction to the back stack, allowing users to press Back
                                // to get to the front of the card.
                                .addToBackStack(null)
                                // Commit the transaction.
                                .commit();
                    } else {

                        ((ArticlesList) getFragmentManager().findFragmentById(R.id.articles_content_list)).updateFragment(mArticles);
                        ((ArticlesGrid) getFragmentManager().findFragmentById(R.id.articles_content_grid)).updateFragment(mArticles);
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




