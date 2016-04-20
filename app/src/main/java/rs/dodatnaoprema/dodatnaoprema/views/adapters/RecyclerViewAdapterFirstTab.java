package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.MainActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.customview.ImageViewPagerWDotIndicator;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;


public class RecyclerViewAdapterFirstTab extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    HashMap<String, List<Article>> items = new HashMap<>();
    private TextView categoryName;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private ImageViewPagerWDotIndicator imageViewPagerWDotIndicator_three_imgs;
    private ImageViewPagerWDotIndicator imageViewPagerWDotIndicator_two_imgs;

    ArrayList<Bitmap> bitmaps;

    private Context context;
    private RecyclerView mRecyclerView;

    private GridLayoutManager mLayoutManager;

    private RelativeLayout mFourthButton;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {

            super(view);

            categoryName = (TextView) view.findViewById(R.id.categoryName);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.gridView);

            int spacing = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_space);
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4, spacing, 0));
                mLayoutManager = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
                mLayoutManager.setAutoMeasureEnabled(true);

            } else {
                mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, 0));
                mLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
                mLayoutManager.setAutoMeasureEnabled(true);

            }

            mRecyclerView.setLayoutManager(mLayoutManager);

        }
    }

    class ViewHolderHeader extends RecyclerView.ViewHolder {

        public ViewHolderHeader(View itemView) {
            super(itemView);
            imageViewPagerWDotIndicator_three_imgs = (ImageViewPagerWDotIndicator) itemView.findViewById(R.id.view_pager_dot_ind_0);
            imageViewPagerWDotIndicator_two_imgs = (ImageViewPagerWDotIndicator) itemView.findViewById(R.id.view_pager_dot_ind_1);

            mFourthButton = (RelativeLayout) itemView.findViewById(R.id.fourth_round_button);

        }
    }
    class ViewHolderFooter extends RecyclerView.ViewHolder {

        public ViewHolderFooter(View itemView) {
            super(itemView);
        }
    }


    public RecyclerViewAdapterFirstTab(HashMap<String, List<Article>> items, Context context, ArrayList<Bitmap> bitmaps) {
        this.items = items;
        this.context = context;
        this.bitmaps=bitmaps;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.four_products, parent, false);

            return new MyViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.first_tab_header, parent, false);
            return new ViewHolderHeader(itemView);
        }
        else if (viewType == TYPE_FOOTER){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_footer, parent, false);
            return new ViewHolderFooter(itemView);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);

        if (holder instanceof MyViewHolder) {

            categoryName.setText(AppConfig.FIRST_TAB_ITEMS[position - 1]);
            RecyclerViewSelectedProducts mAdapter = new RecyclerViewSelectedProducts(context, items.get(AppConfig.FIRST_TAB_ITEMS[position - 1]));
            mRecyclerView.hasFixedSize();
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setAdapter(mAdapter);

        } else if (holder instanceof ViewHolderHeader) {
            imageViewPagerWDotIndicator_three_imgs.setBitmapList(bitmaps);
            imageViewPagerWDotIndicator_two_imgs.setBitmapList(bitmaps);

            mFourthButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFourthButton.setSelected(true);
                    ((MainActivity)context).viewAllCategories();
                   new Handler().postDelayed(new Runnable()
                    {
                        public void run()
                        {
                            mFourthButton.setSelected(false);
                        }
                    }, 1000);
                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return AppConfig.FIRST_TAB_ITEMS.length + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        if (isPositionFooter(position))
            return TYPE_FOOTER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == (getItemCount() - 1);
    }

}
