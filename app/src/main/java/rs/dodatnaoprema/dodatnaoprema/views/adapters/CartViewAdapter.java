package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Artikli;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

/**
 * Created by milan on 5/30/2016.
 */
public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.MyViewHolder> {
    private List<Artikli> artikliList;
    private Context mContext = null;

    public CartViewAdapter(List<Artikli> artikliList) {
        this.artikliList = artikliList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_view_list_item, parent, false);

        mContext = itemView.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Artikli artikal = artikliList.get(position);
        holder.networkImageView.setImageUrl(artikal.getSlikaMain(), VolleySingleton.getsInstance(mContext).getImageLoader());
        holder.firstLine.setText(artikal.getArtikalNaziv());
        holder.secondLine.setText(artikal.getCenaPrikaz());
    }

    @Override
    public int getItemCount() {
        return artikliList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView firstLine, secondLine;
        public CheckBox checkBox;
        public NetworkImageView networkImageView;

        public MyViewHolder(View view) {
            super(view);
            firstLine = (TextView) view.findViewById(R.id.firstLine);
            secondLine = (TextView) view.findViewById(R.id.secondLine);
            checkBox = (CheckBox) view.findViewById(R.id.cb_item);
            networkImageView = (NetworkImageView) view.findViewById(R.id.productImage);
        }
    }

}
