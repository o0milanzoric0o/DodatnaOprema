package views.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
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
    private final OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            categoryName = (TextView) view.findViewById(R.id.categoryText);
            productImg = (NetworkImageView) view.findViewById(R.id.categoryImage);
        }

        public void bind(final Category item, final OnItemClickListener listener) {
            //   ...
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                    itemView.setSelected(true);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            itemView.setSelected(false);
                        }
                    }, 1000);
                }
            });
        }
    }


    public RecyclerViewAllCategories(Context context, List<Category> categories, OnItemClickListener listener) {
        this.categories = categories;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAllCategories.MyViewHolder holder, int position) {
        holder.bind(categories.get(position), listener);

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


    public interface OnItemClickListener {
        void onItemClick(Category item);
    }

}
