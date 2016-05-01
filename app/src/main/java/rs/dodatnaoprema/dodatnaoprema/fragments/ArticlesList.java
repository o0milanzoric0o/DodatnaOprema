package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.SubCategoryArticlesActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.RecyclerViewSelectedProducts;


public class ArticlesList extends Fragment {

    private CustomRecyclerView mRecyclerView;
    private static int firstVisibleInRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private SubCategoryArticlesActivity activity;
    private FrameLayout mHeader;
    private RecyclerViewSelectedProducts mAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        activity = (SubCategoryArticlesActivity) getActivity();

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

        mAdapter = new RecyclerViewSelectedProducts(getActivity(), activity.getArticlesList(), true, new RecyclerViewSelectedProducts.OnItemClickListener() {
            @Override
            public void onItemClick(Article item, View view) {
                ///Start Intent for Single Item Activity

                Log.logInfo("LALALA", "LIST");
            }
        });
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }
    public void updateFragment (List<Article> products){

         mAdapter.updateContent(products);
    }

}
