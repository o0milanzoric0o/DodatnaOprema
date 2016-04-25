package rs.dodatnaoprema.dodatnaoprema;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.FlipAnimation;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.fragments.ArticlesGrid;
import rs.dodatnaoprema.dodatnaoprema.fragments.ArticlesList;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSelectedProducts;

public class SubCategoryArticlesActivity extends BaseActivity {

    private List<Article> mArticles = new ArrayList<>();
    private VolleySingleton mVolleySingleton;

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private RecyclerViewSelectedProducts mAdapter;

    private boolean nextImgStateGrid = true;
    private Handler mHandler;

    private int mArticleId;


    public List<Article> getArticlesList() {
        return mArticles;
    }

    public int getmArticleId() {
        return mArticleId;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_category_articles_activity);

        Intent intent = getIntent();
        String mSubCategoryName = intent.getStringExtra("Artikli");
        mArticleId = intent.getIntExtra("ArtikalId", 0);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText(mSubCategoryName);
        Log.logInfo("NJNJ", String.valueOf(mArticles.size()));

        FrameLayout mFragmentHolder = (FrameLayout) findViewById(R.id.articles_content_list);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    }
    public void onCardClick(View view)
    {
        flipCard();
    }

    private void flipCard()
    {
        View rootLayout = (View) findViewById(R.id.main_activity_root);
        View cardFace = (View) findViewById(R.id.main_activity_card_face);
        View cardBack = (View) findViewById(R.id.main_activity_card_back);

        FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

        if (cardFace.getVisibility() == View.GONE)
        {
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

}




