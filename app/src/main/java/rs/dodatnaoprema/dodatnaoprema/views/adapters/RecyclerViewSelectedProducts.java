package rs.dodatnaoprema.dodatnaoprema.views.adapters;

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
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

public class RecyclerViewSelectedProducts extends RecyclerView.Adapter<RecyclerViewSelectedProducts.MyViewHolder> {
    private List<Article> products;
    private NetworkImageView productImg;

    TextView productName;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.subcategoryName);
            productImg = (NetworkImageView) view.findViewById(R.id.productImage);
        }
    }


    public RecyclerViewSelectedProducts(Context context, List<Article> products) {
        this.products = products;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewSelectedProducts.MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        productName.setText(products.get(position).getArtikalNaziv());
        ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();
        productImg.setImageUrl(products.get(position).getSlike().get(0).getSrednjaSlika(), mImageLoader);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }
}
