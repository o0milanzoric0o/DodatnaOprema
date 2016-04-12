package views.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

public class RecyclerViewAllCategories extends RecyclerView.Adapter<RecyclerViewAllCategories.MyViewHolder> {

    private List<Category> categories;
    private NetworkImageView productImg;
    TextView categoryName;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            categoryName = (TextView) view.findViewById(R.id.categoryText);
            productImg = (NetworkImageView) view.findViewById(R.id.categoryImage);
        }
    }


    public RecyclerViewAllCategories(Context context, List<Category> categories) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAllCategories.MyViewHolder holder, int position) {

        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;
            sglp.setFullSpan(position == 0);
            holder.itemView.setLayoutParams(sglp);
        }
        categoryName.setText(categories.get(position).getKatsrblat());

        ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();

        productImg.setImageUrl(categories.get(position).getKategorijaArtikalaSlika(), mImageLoader);
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

}
