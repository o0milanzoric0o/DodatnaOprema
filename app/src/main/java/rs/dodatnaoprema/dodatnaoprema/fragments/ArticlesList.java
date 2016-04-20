package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.SubCategoryArticlesActivity;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_filtered_by_category.ArticlesFilteredByCategory;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSelectedProducts;


public class ArticlesList extends Fragment {

    private VolleySingleton mVolleySingleton;
    private CustomRecyclerView mRecyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);

        SubCategoryArticlesActivity activity = (SubCategoryArticlesActivity) getActivity();

        mRecyclerView = (CustomRecyclerView) view.findViewById(R.id.recycler_view);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mVolleySingleton = VolleySingleton.getsInstance(getActivity());
        searchArticlesByCategory(activity.getmArticleId(), 1, 100, 2);


        return view;
    }

    private void searchArticlesByCategory(int id, int from, int to, int sort) {
        PullWebContent<ArticlesFilteredByCategory> content = new PullWebContent<ArticlesFilteredByCategory>(getActivity(), ArticlesFilteredByCategory.class, UrlEndpoints.getRequestUrlSearchArticlesByCategory(id, from, to, AppConfig.URL_VALUE_CURRENCY_RSD, AppConfig.URL_VALUE_LANGUAGE_SRB_LAT, sort), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesFilteredByCategory>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesFilteredByCategory articlesFilteredByCategory) {
                if (success) {
                    RecyclerViewSelectedProducts mAdapter = new RecyclerViewSelectedProducts(getActivity(), articlesFilteredByCategory.getArtikli());
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
