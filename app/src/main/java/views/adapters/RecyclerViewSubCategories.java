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

/**
 * Created by Win 7 on 13.4.2016.
 */
public class RecyclerViewSubCategories extends RecyclerView.Adapter<RecyclerViewSubCategories.MyViewHolder> {
    private List<Child> subcategories;
    private NetworkImageView subcategoryImg;
    TextView subcategoryName;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            subcategoryName = (TextView) view.findViewById(R.id.subcategoryText);
            subcategoryImg = (NetworkImageView) view.findViewById(R.id.subcategoryImage);
        }


    }


    public RecyclerViewSubCategories(Context context, List<Child> subcategories) {
        this.subcategories = subcategories;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategory, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewSubCategories.MyViewHolder holder, int position) {

        subcategoryName.setText(subcategories.get(position).getKatIme());

        ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();

        subcategoryImg.setImageUrl(subcategories.get(position).getKategorijaArtikalaSlika(), mImageLoader);
    }


    @Override
    public int getItemCount() {
        return subcategories.size();
    }


}
