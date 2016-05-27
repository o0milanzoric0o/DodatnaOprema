package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import java.io.Serializable;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.OneArticleActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.SubCategoryArticlesActivity;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSelectedProducts;


public class ArticlesList extends Fragment {

    private CustomRecyclerView mRecyclerView;
    private static int firstVisibleInRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private SubCategoryArticlesActivity activity;
    private FrameLayout mHeader;
    private RecyclerViewSelectedProducts mAdapter;

    private ScrollView mScrollView;

    private VolleySingleton mVolleySingleton;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        activity = (SubCategoryArticlesActivity) getActivity();

        mRecyclerView = (CustomRecyclerView) view.findViewById(R.id.recycler_view);
        // mHeader = (FrameLayout) view.findViewById(R.id.header);

        // mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.hasFixedSize();
        mRecyclerView.setNestedScrollingEnabled(false);

        mScrollView = (ScrollView) view.findViewById(R.id.scrollView);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        firstVisibleInRecyclerView = mLayoutManager.findFirstVisibleItemPosition();

        mVolleySingleton = VolleySingleton.getsInstance(this.getActivity());

       /* mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int currentFirstVisible = mLayoutManager.findFirstVisibleItemPosition();

                if (currentFirstVisible > firstVisibleInRecyclerView)
                    mHeader.setVisibility(View.VISIBLE);
                else
                    mHeader.setVisibility(View.GONE);

                firstVisibleInRecyclerView = currentFirstVisible;
            }
        });*/

        mAdapter = new RecyclerViewSelectedProducts(getActivity(), activity.getArticlesList(), true, new RecyclerViewSelectedProducts.OnItemClickListener() {
            @Override
            public void onItemClick(Article item, View view) {
                ///Start Intent for Single Item Activity
                int itemID = item.getArtikalId();

                PullWebContent<OneArticle> content =
                        new PullWebContent<OneArticle>(getActivity(), OneArticle.class, UrlEndpoints.getRequestUrlArticleById(itemID), mVolleySingleton);


                Log.logInfo("LALALA", String.valueOf(itemID));
                content.setCallbackListener(new WebRequestCallbackInterface<OneArticle>() {
                    @Override
                    public void webRequestSuccess(boolean success, OneArticle oneArticle) {
                        if (success) {
                            Log.logInfo("LALALA", "SUCCESS");
                            Intent intent = new Intent(getActivity(), OneArticleActivity.class);
                            intent.putExtra(AppConfig.ABOUT_PRODUCT, (Serializable) oneArticle);

                            //OneArticleActivity articleDetails = new OneArticleActivity();
                            startActivity(intent);


                            Log.logInfo("LALALA", oneArticle.getArtikal().getArtikalNaziv());

                        } else {
                            Log.logInfo("LALALA", "FAILED");
                        }
                    }

                    @Override
                    public void webRequestError(String error) {

                    }
                });

                Log.logInfo("LALALA", "LIST");
                content.pullList();
            }
        });
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }

    public void updateFragment(List<Article> products) {

        mAdapter.updateContent(products);
    }

    public void scrollToTop() {
        mScrollView.fullScroll(ScrollView.FOCUS_UP);    }

}
