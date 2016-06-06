package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Artikli;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

/**
 * Created by milan on 5/30/2016.
 */
public class CartViewAdapter extends ArrayAdapter<Artikli> {
    private List<Artikli> artikliList;
    private Context mContext = null;
    private ItemBtnClickListener itemBtnClickListener;

    public CartViewAdapter(Context context, List<Artikli> artikliList) {
        super(context, R.layout.cart_view_list_item, artikliList);
        mContext = context;
        this.artikliList = artikliList;
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
        Artikli artikal = artikliList.get(position);
        MyViewHolder holder = (MyViewHolder) rowView.getTag();
        holder.networkImageView.setImageUrl(artikal.getSlike().get(0).getMalaSlika(), VolleySingleton.getsInstance(mContext).getImageLoader());
        holder.firstLine.setText(artikal.getArtikalNaziv());
        holder.secondLine.setText(artikal.getCenaSamoBrojFormat());
        holder.imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemBtnClickListener != null)
                    itemBtnClickListener.onItemBtnClickListener(position);
            }
        });
        return rowView;
    }

    public void setItemBtnClickListner(ItemBtnClickListener listener) {
        this.itemBtnClickListener = listener;
    }

    public interface ItemBtnClickListener {
        public void onItemBtnClickListener(int position);
    }

    public class MyViewHolder {
        public TextView firstLine, secondLine;
        public CheckBox checkBox;
        public NetworkImageView networkImageView;
        public ImageButton imgButton;

        public MyViewHolder(View view) {
            firstLine = (TextView) view.findViewById(R.id.firstLine);
            secondLine = (TextView) view.findViewById(R.id.secondLine);
            checkBox = (CheckBox) view.findViewById(R.id.cb_item);
            imgButton = (ImageButton) view.findViewById(R.id.btn_delete);
            networkImageView = (NetworkImageView) view.findViewById(R.id.productImage);
        }
    }

}
