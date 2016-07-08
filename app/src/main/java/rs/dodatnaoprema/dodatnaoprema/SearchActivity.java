package rs.dodatnaoprema.dodatnaoprema;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.fragments.ArticlesList;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.search.Search;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;


public class SearchActivity extends BaseActivity {

    private VolleySingleton mVolleySingleton;
    private List<Article> mArticles = new ArrayList<>();
    private List<String> searchHistory = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        ImageButton searchBtn = (ImageButton) findViewById(R.id.list_grid_change_btn);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        if (mTextView != null) mTextView.setText(getString(R.string.search_title));

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ImageButton icSearch = (ImageButton) findViewById(R.id.toolbar_btn_search);
        icSearch.setVisibility(View.GONE);

        final EditText searchTxt = (EditText) findViewById(R.id.searh_text);

        searchTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.logInfo("SEARCH", searchTxt.getText().toString());
                    search(searchTxt.getText().toString());
                    return true;
                }
                return false;
            }
        });

        mVolleySingleton = VolleySingleton.getsInstance(this);
    }

    private void search(String keyWord) {

        final ProgressDialogCustom progressDialog = new ProgressDialogCustom(SearchActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.showDialog("Učitavanje...");

        PullWebContent<Search> content = new PullWebContent<>(Search.class, UrlEndpoints.getSearchResults(keyWord), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<Search>() {
            @Override
            public void webRequestSuccess(boolean success, Search searchResults) {
                if (success) {

                    Log.logInfo("SEARCH", searchResults.getSuccess().toString());
                    mArticles = searchResults.getArtikli();

                    Log.logInfo("ARTIKLI", "" + mArticles.size());
                    if (mArticles.size() > 0) {

                        getFragmentManager()
                                .beginTransaction()
                                .add(R.id.articles_content_list, new ArticlesList())
                                // Add this transaction to the back stack, allowing users to press Back
                                // to get to the front of the card.
                                .addToBackStack(null)
                                // Commit the transaction.
                                .commit();

                    } else {

                        updateList(mArticles);
                    }
                } else {
                    // noResults();
                }
                removeKeyboard();
                progressDialog.hideDialog();
            }


            @Override
            public void webRequestError(String error) {
                removeKeyboard();
                progressDialog.hideDialog();
            }
        });
        content.pullList();

    }

    public List<Article> getArticlesList() {
        return mArticles;
    }

    public void updateList(List<Article> articles) {

        Log.logInfo("SORT", "" + articles.size());
        // setNumberOfResults(articles.size());
        ((ArticlesList) getFragmentManager().findFragmentById(R.id.articles_content_list)).updateFragment(articles);

        //  if (articles.size() == 0)
        // noSearchResults();
    }

    public void removeKeyboard() {

        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /*  private void noResults() {
          mHeader.setVisibility(View.GONE);
          mFooter.setVisibility(View.GONE);
          cardBack.setVisibility(View.GONE);
          cardFace.setVisibility(View.GONE);

          msgNoResults.setVisibility(View.VISIBLE);
          msgNoResults.setText(getString(R.string.msg_no_articles, mSubCategoryName));
      }

      private void noSearchResults() {
          mHeader.setVisibility(View.GONE);
          cardBack.setVisibility(View.GONE);
          cardFace.setVisibility(View.GONE);

          msgNoResults.setVisibility(View.VISIBLE);
          msgNoResults.setText(getString(R.string.msg_no_search_results));
      }
  */
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

