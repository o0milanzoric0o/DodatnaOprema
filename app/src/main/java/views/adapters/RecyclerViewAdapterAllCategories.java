package views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomGridView;
import rs.dodatnaoprema.dodatnaoprema.models.categories.Category;


public class RecyclerViewAdapterAllCategories extends RecyclerView.Adapter<RecyclerViewAdapterAllCategories.MyViewHolder> {

    private List<Category> allCategories;
    private TextView categoryName;
    private GridView gridView;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            categoryName = (TextView) view.findViewById(R.id.categoryName);
            gridView = (CustomGridView) view.findViewById(R.id.gridView);
        }
    }


    public RecyclerViewAdapterAllCategories(List<Category> allCategories, Context context) {
        this.allCategories = allCategories;
        this.context=context;
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
        AllCategoriesGridAdapter gridAdapter = new AllCategoriesGridAdapter(context, category.getChild());
        gridView.setAdapter(gridAdapter);
    }


    @Override
    public int getItemCount() {
        return allCategories.size();
    }
}