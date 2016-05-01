package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.SubCategoryArticlesActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSelectedProducts;


public class ArticlesGrid extends Fragment {

    private CustomRecyclerView mRecyclerView;
    private RecyclerViewSelectedProducts mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        SubCategoryArticlesActivity activity = (SubCategoryArticlesActivity) getActivity();

        mRecyclerView = (CustomRecyclerView) view.findViewById(R.id.recycler_view);

        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerViewSelectedProducts(getActivity(), activity.getArticlesList(), false, new RecyclerViewSelectedProducts.OnItemClickListener() {
            @Override
            public void onItemClick(Article item, View view) {

                //Start Intent for Single Item Activity

                Log.logInfo("LALALA", "GRID");
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
    public void updateFragment (List<Article> products){

        mAdapter.updateContent(products);
    }

}
