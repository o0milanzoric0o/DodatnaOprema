package views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Child;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

public class RecyclerViewAllCategories extends RecyclerView.Adapter<RecyclerViewAllCategories.MyViewHolder>{

    private List<Child> subcategories;
    private List<Child> displaySubcategories;
    private NetworkImageView productImg;
    TextView subCategoryName;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            subCategoryName = (TextView) view.findViewById(R.id.subcategoryName);
            productImg = (NetworkImageView) view.findViewById(R.id.productImage);        }
    }


    public RecyclerViewAllCategories(Context context, List<Child> subcategories) {
        this.displaySubcategories = subcategories.subList(0,4);
        this.subcategories=subcategories;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAllCategories.MyViewHolder holder, int position) {

        subCategoryName.setText(subcategories.get(position).getKatIme());

        ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();

        productImg.setImageUrl(subcategories.get(position).getKategorijaArtikalaSlika(), mImageLoader);
    }


    @Override
    public int getItemCount() {
        return displaySubcategories.size();
    }

}
