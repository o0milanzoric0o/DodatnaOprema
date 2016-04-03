package views.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_on_sale.Article;


public class RecyclerViewAdapterFirstTab extends RecyclerView.Adapter<RecyclerViewAdapterFirstTab.MyViewHolder> {

    HashMap<String,List<Article>> items = new HashMap<>();
    private TextView categoryName;

    private Context context;
    private RecyclerView mRecyclerView;

    private RecyclerViewSelectedProducts mAdapter;

    private GridLayoutManager mLayoutManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {

            super(view);

            categoryName = (TextView) view.findViewById(R.id.categoryName);

            mRecyclerView = (RecyclerView) view.findViewById(R.id.gridView);

            int spacing = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_space);
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4, spacing, 0));
                mLayoutManager = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
                mLayoutManager.setAutoMeasureEnabled(true);

            } else {
                mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, 0));
                mLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
                mLayoutManager.setAutoMeasureEnabled(true);

            }


            mRecyclerView.setLayoutManager(mLayoutManager);

        }
    }


    public RecyclerViewAdapterFirstTab(HashMap<String,List<Article>> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.four_products, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterFirstTab.MyViewHolder holder, int position) {
        String category="";

      //  Category category = items.
        categoryName.setText(AppConfig.FIRST_TAB_ITEMS[position]);

        mAdapter = new RecyclerViewSelectedProducts(context, items.get(AppConfig.FIRST_TAB_ITEMS[position]));

       mRecyclerView.hasFixedSize();

        mRecyclerView.setNestedScrollingEnabled(false);

        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public int getItemCount() {
        return AppConfig.FIRST_TAB_ITEMS.length;
    }


}
