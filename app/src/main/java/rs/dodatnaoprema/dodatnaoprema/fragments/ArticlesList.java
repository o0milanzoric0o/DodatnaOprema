package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.SubCategoryArticlesActivity;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_filtered_by_category.ArticlesFilteredByCategory;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSelectedProducts;


public class ArticlesList extends Fragment {

    private VolleySingleton mVolleySingleton;
    private CustomRecyclerView mRecyclerView;
    private static int firstVisibleInRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FrameLayout mHeader;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        SubCategoryArticlesActivity activity = (SubCategoryArticlesActivity) getActivity();

        mRecyclerView = (CustomRecyclerView) view.findViewById(R.id.recycler_view);
        mHeader = (FrameLayout) view.findViewById(R.id.header);

        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        firstVisibleInRecyclerView = mLayoutManager.findFirstVisibleItemPosition();

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
        mVolleySingleton = VolleySingleton.getsInstance(getActivity());
        searchArticlesByCategory(activity.getmArticleId(), 1, 100, 2);


        return view;
    }

    private void searchArticlesByCategory(int id, int from, int to, int sort) {

        PullWebContent<ArticlesFilteredByCategory> content = new PullWebContent<>(getActivity(), ArticlesFilteredByCategory.class, UrlEndpoints.getRequestUrlSearchArticlesByCategory(id, from, to, AppConfig.URL_VALUE_CURRENCY_RSD, AppConfig.URL_VALUE_LANGUAGE_SRB_LAT, sort), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesFilteredByCategory>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesFilteredByCategory articlesFilteredByCategory) {
                if (success) {
                    RecyclerViewSelectedProducts mAdapter = new RecyclerViewSelectedProducts(getActivity(), articlesFilteredByCategory.getArtikli(), true, new RecyclerViewSelectedProducts.OnItemClickListener() {
                        @Override
                        public void onItemClick(Article item, View view) {
                            ///Start Intent for Single Item Activity

                            Log.logInfo("LALALA", "LIST");
                        }
                    });
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void webRequestError(String error) {

            }
        });
        content.pullList();

    }

}
