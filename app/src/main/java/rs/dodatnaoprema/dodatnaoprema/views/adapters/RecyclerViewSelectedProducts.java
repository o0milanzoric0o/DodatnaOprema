package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.content.Context;
import android.os.Handler;
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

    private TextView productName;
    private TextView price;
    private TextView brandName;
    private TextView stockState;
    private TextView shortDescription;

    private Context context;
    private boolean list;
    private final RecyclerViewSelectedProducts.OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.subcategoryName);
            brandName = (TextView) view.findViewById(R.id.brandName);
            price = (TextView) view.findViewById(R.id.productPrice);
            stockState = (TextView) view.findViewById(R.id.stockState);
            productImg = (NetworkImageView) view.findViewById(R.id.productImage);

            if (list) {
                shortDescription = (TextView) view.findViewById(R.id.short_description_txt);
            }
        }

        public void bind(final Article item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(item, itemView);
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

    public RecyclerViewSelectedProducts(Context context, List<Article> products, boolean list, OnItemClickListener listener) {
        this.products = products;
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (list) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_list, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_grid, parent, false);
        }

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewSelectedProducts.MyViewHolder holder, int position) {

        holder.bind(products.get(position), listener);
        holder.setIsRecyclable(false);

        productName.setText(products.get(position).getArtikalNaziv().trim());
        brandName.setText(products.get(position).getBrendIme().trim());
        price.append(" " + products.get(position).getCenaSamoBrojFormat() + " " + products.get(position).getCenaPrikazExt());
        if (products.get(position).getStanje() > 0) {
            stockState.setText(context.getString(R.string.stock_state_available));
        } else {
            stockState.setText(context.getString(R.string.stock_state_sold));
        }

        ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();
        productImg.setImageUrl(products.get(position).getSlike().get(0).getSrednjaSlika(), mImageLoader);

        if (list && products.get(position).getArtikalKratakOpis().toString().length() > 0) {
            shortDescription.append(" " + products.get(position).getArtikalKratakOpis().toString());
        }
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateContent(List<Article> articles) {

        this.products = articles;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Article item, View view);
    }
}
