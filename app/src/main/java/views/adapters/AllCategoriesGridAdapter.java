package views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.categories.Child;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;


public class AllCategoriesGridAdapter extends BaseAdapter {

    private Context context;
    private List<Child> category;

    public AllCategoriesGridAdapter(Context context, List<Child> category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public Object getItem(int position) {
        return category.get(position);
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
        subCategoryName.setText(category.get(position).getKatsrblat());

        ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();

        NetworkImageView productImg = (NetworkImageView) row.findViewById(R.id.productImage);
        productImg.setImageUrl(category.get(position).getKategorijaArtikalaSlika(), mImageLoader);

        return row;
    }
}
