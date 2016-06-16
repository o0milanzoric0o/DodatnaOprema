package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

public class RecyclerViewSelectedProducts extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Article> products;
    private NetworkImageView productImg;

    private TextView productName;
    private TextView price;
    private TextView brandName;
    private TextView stockState;
    private TextView shortDescription;
    private ImageView saleIndicator;

    private Context context;
    private boolean list;
    private int existHeader;
    private final RecyclerViewSelectedProducts.OnItemClickListener listener;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.subcategoryName);
            brandName = (TextView) view.findViewById(R.id.brandName);

            price = (TextView) view.findViewById(R.id.productPrice);
            stockState = (TextView) view.findViewById(R.id.stockState);
            productImg = (NetworkImageView) view.findViewById(R.id.productImage);

            saleIndicator = (ImageView) view.findViewById(R.id.saleIndicator);
            saleIndicator.setColorFilter(ContextCompat.getColor(context, android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);


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

    class ViewHolderHeader extends RecyclerView.ViewHolder {

        public ViewHolderHeader(View itemView) {

            super(itemView);


        }
    }

    public RecyclerViewSelectedProducts(Context context, List<Article> products, boolean list, int header, OnItemClickListener listener) {
        this.products = products;
        this.context = context;
        this.list = list;
        this.existHeader = header;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (existHeader == 1) {
            if (viewType == TYPE_HEADER) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.empty_header, parent, false);
                return new ViewHolderHeader(itemView);
            }
        }
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof MyViewHolder) {

            ((MyViewHolder) holder).bind(products.get(position - existHeader), listener);
            holder.setIsRecyclable(false);

            productName.setText(products.get(position - existHeader).getArtikalNaziv().trim());
            brandName.setText(products.get(position - existHeader).getBrendIme().trim());
            price.append(" " + products.get(position - existHeader).getCenaSamoBrojFormat() + " " + products.get(position - existHeader).getCenaPrikazExt());
            if (products.get(position - existHeader).getStanje() > 0) {
                stockState.setText(context.getString(R.string.stock_state_available));
            } else {
                stockState.setText(context.getString(R.string.stock_state_sold));
            }

            ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();
            productImg.setImageUrl(products.get(position - existHeader).getSlike().get(0).getSrednjaSlika(), mImageLoader);

            if (list && products.get(position - existHeader).getArtikalKratakOpis().toString().length() > 0) {
                shortDescription.append(" " + products.get(position - existHeader).getArtikalKratakOpis().toString());
            }

            switch (products.get(position - existHeader).getArtikalNaAkciji()) {
                case 6:
                    saleIndicator.setVisibility(View.VISIBLE);
                    saleIndicator.setImageResource(R.drawable.ic_nav_sale);
                    break;
                case 7:
                    saleIndicator.setVisibility(View.VISIBLE);
                    saleIndicator.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_nav_new, null));
                    break;
                case 8:
                    saleIndicator.setVisibility(View.VISIBLE);
                    saleIndicator.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_nav_best, null));
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public int getItemCount() {

        return products.size() + existHeader;
    }

    public void updateContent(List<Article> articles) {

        this.products = articles;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Article item, View view);
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }
}
