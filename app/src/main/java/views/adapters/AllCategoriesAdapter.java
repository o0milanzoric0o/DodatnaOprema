package views.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import rs.dodatnaoprema.dodatnaoprema.customview.CustomGridView;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.categories.AllCategories;

public class AllCategoriesAdapter extends BaseAdapter {
    private AllCategories allCategories;
    private Context context;
    private TextView categoryName;
    private CustomGridView gridView;
    private AllCategoriesGridAdapter gridAdapter;

    public AllCategoriesAdapter(Context context, AllCategories allCategories) {
        this.allCategories = allCategories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return allCategories.getKategorije().size();
    }

    @Override
    public Object getItem(int position) {
        return allCategories.getKategorije().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.four_products, parent, false);
        } else {
            row = convertView;
        }
        categoryName = (TextView) row.findViewById(R.id.categoryName);
        categoryName.setText(allCategories.getKategorije().get(position).getKatsrblat());

        gridView = (CustomGridView) row.findViewById(R.id.gridView);
        gridAdapter = new AllCategoriesGridAdapter(context, allCategories.getKategorije().get(position));
        gridView.setAdapter(gridAdapter);


        return row;
    }
}
