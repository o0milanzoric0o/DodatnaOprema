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
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Child;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;


public class RecyclerViewSubCategories extends RecyclerView.Adapter<RecyclerViewSubCategories.MyViewHolder> {
    private List<Child> subcategories;
    private NetworkImageView subcategoryImg;
    private TextView subcategoryName;
    private TextView subcategoryCount;
    private Context context;
    private final RecyclerViewSubCategories.OnItemClickListener listener;


    class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View view) {

            super(view);
            subcategoryName = (TextView) view.findViewById(R.id.subcategoryText);
            subcategoryImg = (NetworkImageView) view.findViewById(R.id.subcategoryImage);
            subcategoryCount = (TextView) view.findViewById(R.id.subcategoryCount);

        }

        void bind(final Child item, final OnItemClickListener listener) {
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


    public RecyclerViewSubCategories(Context context, List<Child> subcategories, OnItemClickListener listener) {

        this.subcategories = subcategories;
        this.context = context;
        this.listener = listener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategory, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewSubCategories.MyViewHolder holder, int position) {

        holder.bind(subcategories.get(position), listener);
        holder.setIsRecyclable(false);

        subcategoryName.setText(subcategories.get(position).getKatIme().trim());
        ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();
        subcategoryImg.setImageUrl(subcategories.get(position).getKategorijaArtikalaSlika(), mImageLoader);

        if (subcategories.get(position).getDaLiImaPodKat() > 0)
            subcategoryCount.setText(". . .");
        else
            subcategoryCount.setText(String.valueOf(subcategories.get(position).getkolikoImaArt()));
    }


    @Override
    public int getItemCount() {
        return subcategories.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Child item, View view);
    }
}
