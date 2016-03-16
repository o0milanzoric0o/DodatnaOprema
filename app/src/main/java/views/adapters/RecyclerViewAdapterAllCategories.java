package views.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.categories.Category;


public class RecyclerViewAdapterAllCategories extends RecyclerView.Adapter<RecyclerViewAdapterAllCategories.MyViewHolder> {

    private List<Category> allCategories;
    private TextView categoryName;

    private Context context;
    private RecyclerView mRecyclerView;

    private RecyclerViewAdapterSubcategories mAdapter;

    private GridLayoutManager mLayoutManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {

            super(view);

            categoryName = (TextView) view.findViewById(R.id.categoryName);

            mRecyclerView = (RecyclerView) view.findViewById(R.id.gridView);

            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mLayoutManager = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);

            } else {
                mLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
            }


            mRecyclerView.setLayoutManager(mLayoutManager);

        }
    }


    public RecyclerViewAdapterAllCategories(List<Category> allCategories, Context context) {
        this.allCategories = allCategories;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.four_products, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterAllCategories.MyViewHolder holder, int position) {

        Category category = allCategories.get(position);
        categoryName.setText(category.getKatsrblat());

        mAdapter = new RecyclerViewAdapterSubcategories(context, allCategories.get(position).getChild());
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public int getItemCount() {
        return allCategories.size();
    }


}
