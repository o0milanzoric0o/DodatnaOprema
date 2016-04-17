package views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.common.utils.ObjectSerializer;
import rs.dodatnaoprema.dodatnaoprema.customview.DeleteHistoryDialog;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

public class RecyclerViewAllCategories extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Category> categories;
    private NetworkImageView productImg;
    TextView categoryName;
    private Context context;
    private final OnItemClickListener listener;

    private ArrayList<String> mHistory;
    private ViewGroup historyList;

    private int existHistory;

    ImageButton deleteBtn;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

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

    class ViewHolderHeader extends RecyclerView.ViewHolder {

        public ViewHolderHeader(View itemView) {

            super(itemView);
            historyList = (ViewGroup) itemView.findViewById(R.id.flow_layout_history);
            deleteBtn = (ImageButton) itemView.findViewById(R.id.img_delete);

        }
    }


    public RecyclerViewAllCategories(Context context, List<Category> categories, OnItemClickListener listener) {

        this.categories = categories;
        this.context = context;
        this.listener = listener;
        SharedPreferences prefs = context.getSharedPreferences("Kliknuo", Context.MODE_PRIVATE);
        try {
            this.mHistory = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString("Kliknuo", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.existHistory = (mHistory.size() != 0) ? 1 : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (existHistory == 1) {
            if (viewType == TYPE_ITEM) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.category, parent, false);

                return new MyViewHolder(itemView);
            } else if (viewType == TYPE_HEADER) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.all_categories_header, parent, false);
                return new ViewHolderHeader(itemView);
            }
            throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category, parent, false);

            return new MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {

            ((MyViewHolder) holder).bind(categories.get(position - existHistory), listener);

            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                //setting grid layout appearance
                ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
                StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;

                sglp.setFullSpan(position - existHistory == 0);

                holder.itemView.setLayoutParams(sglp);}

                categoryName.setText(categories.get(position - existHistory).getKatsrblat());
                ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();
                productImg.setImageUrl(categories.get(position - existHistory).getKategorijaArtikalaSlika(), mImageLoader);

        } else if (holder instanceof ViewHolderHeader) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;

            sglp.setFullSpan(position==0);
            Log.logInfo("Niz", String.valueOf(mHistory.size()));

            holder.itemView.setLayoutParams(sglp);
            if (existHistory == 1) {
                for (String subcategory : mHistory
                        ) {
                    Log.logInfo("Niz", subcategory);
                    addNewButton(subcategory);
                }
            }
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteHistoryDialog dialog = new DeleteHistoryDialog();
                    dialog.setCancelable(false);
                    dialog.show(((Activity) context).getFragmentManager(), "Dialog");

                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return categories.size() + existHistory;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    public interface OnItemClickListener {
        void onItemClick(Category item);
    }

    private Button addNewButton(String subcategory) {

        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btn = new Button(historyList.getContext());
        btn.setLayoutParams(param);
        btn.setBackgroundResource(R.drawable.history_btn);
        btn.setPadding(5, 0, 5, 0);
        btn.setClickable(true);

        btn.setText(subcategory);
        btn.setAllCaps(false);
        btn.setTextColor(ContextCompat.getColor(historyList.getContext(), R.color.btnTextColor));
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        btn.setMinHeight(80);
        btn.setMinimumHeight(80);

        historyList.addView(btn);
        return btn;
    }

}
