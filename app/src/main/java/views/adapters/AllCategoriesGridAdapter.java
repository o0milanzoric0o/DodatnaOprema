package views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.categories.Category;


public class AllCategoriesGridAdapter extends BaseAdapter {

    private Context context;
    private Category category;

    public AllCategoriesGridAdapter(Context context, Category category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public int getCount() {
        Log.d("Lala",""+category.getChild().size());
        return category.getChild().size();
    }

    @Override
    public Object getItem(int position) {
        return category.getChild().get(position);
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
            row = inflater.inflate(R.layout.product, null);
        } else {
            row = convertView;
        }
        TextView subCategoryName = (TextView) row.findViewById(R.id.subcategoryName);
        subCategoryName.setText(category.getChild().get(position).getKatsrblat());

        return row;
    }
}
