package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.OfflineCartItem;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

/**
 * ******************************
 * Created by milan on 7/20/2016.
 * ******************************
 */
public class CartViewOfflineAdapter extends ArrayAdapter<OfflineCartItem> {
    private List<OfflineCartItem> cartItems;
    private Context mContext = null;
    private ItemBtnClickListener itemBtnClickListener;
    private ChangeItemQuantityListener changeItemQuantityListener;

    public CartViewOfflineAdapter(Context context, List<OfflineCartItem> cartItems) {
        super(context, R.layout.cart_view_list_item, cartItems);
        mContext = context;
        this.cartItems = cartItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        MyViewHolder viewHolder;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.cart_view_list_item, parent, false);
            // configure view holder
            viewHolder = new MyViewHolder(rowView);
            rowView.setTag(viewHolder);
        }

        // fill data
        OfflineCartItem item = cartItems.get(position);
        final MyViewHolder holder = (MyViewHolder) rowView.getTag();
        holder.networkImageView.setImageUrl(item.getPictures().get(0).getMalaSlika(), VolleySingleton.getsInstance(mContext).getImageLoader());
        holder.firstLine.setText(item.getTitle());
        String item_price = item.getPrice();
        item_price += " " + item.getPrice_ext();
        holder.secondLine.setText(item_price);
        holder.imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemBtnClickListener != null)
                    itemBtnClickListener.onItemBtnClickListener(position);
            }
        });
        holder.mQuantityInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changeItemQuantityListener != null)
                    changeItemQuantityListener.onItemChangeQuantityInc(position, holder.mQuantityProgressBar);
            }
        });

        holder.mQuantityDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changeItemQuantityListener != null)
                    changeItemQuantityListener.onItemChangeQuantityDec(position, holder.mQuantityProgressBar);
            }
        });

        holder.mItemQuantity.setText(String.valueOf(item.getQuantity()));
        String item_total = String.valueOf(item.getTotal_price());
        item_total += " " + item.getPrice_ext();
        holder.mItemTotal.setText(item_total);
        return rowView;
    }

    public void setItemBtnClickListner(ItemBtnClickListener listener) {
        this.itemBtnClickListener = listener;
    }

    public void setChangeItemQuantityListener(ChangeItemQuantityListener changeItemQuantityListener) {
        this.changeItemQuantityListener = changeItemQuantityListener;
    }

    public interface ItemBtnClickListener {
        void onItemBtnClickListener(int position);
    }

    public interface ChangeItemQuantityListener {
        void onItemChangeQuantityInc(int position, ProgressBar progressBar);

        void onItemChangeQuantityDec(int position, ProgressBar progressBar);
    }

    public class MyViewHolder {
        public TextView firstLine, secondLine, mItemTotal, mItemQuantity;
        public TextView mQuantityInc, mQuantityDec;

        public NetworkImageView networkImageView;
        public ImageButton imgButton;
        public ProgressBar mQuantityProgressBar;

        public MyViewHolder(View view) {
            firstLine = (TextView) view.findViewById(R.id.firstLine);
            secondLine = (TextView) view.findViewById(R.id.secondLine);
            imgButton = (ImageButton) view.findViewById(R.id.btn_delete);
            networkImageView = (NetworkImageView) view.findViewById(R.id.productImage);
            mItemTotal = (TextView) view.findViewById(R.id.tv_item_total);
            mItemQuantity = (TextView) view.findViewById(R.id.tv_quantity);
            mQuantityInc = (TextView) view.findViewById(R.id.tv_quantity_inc);
            mQuantityDec = (TextView) view.findViewById(R.id.tv_quantity_dec);
            mQuantityProgressBar = (ProgressBar) view.findViewById(R.id.pb_spin);
        }
    }

}