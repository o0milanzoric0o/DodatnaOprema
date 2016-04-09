package views.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BitmapDecoder;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.ImageViewPagerWDotIndicator;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;


public class RecyclerViewAdapterFirstTab extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    HashMap<String, List<Article>> items = new HashMap<>();
    private TextView categoryName;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private ImageViewPagerWDotIndicator imageViewPagerWDotIndicator;

    ArrayList<Bitmap> bitmaps;

    private Context context;
    private RecyclerView mRecyclerView;

    private RecyclerViewSelectedProducts mAdapter;

    private GridLayoutManager mLayoutManager;

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
            imageViewPagerWDotIndicator = (ImageViewPagerWDotIndicator) itemView.findViewById(R.id.view_pager_dot_ind);


        }
    }
    class ViewHolderFooter extends RecyclerView.ViewHolder {

        public ViewHolderFooter(View itemView) {
            super(itemView);
        }
    }


    public RecyclerViewAdapterFirstTab(HashMap<String, List<Article>> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.four_products, parent, false);

            return new MyViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dot_indicator, parent, false);
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

        if (holder instanceof MyViewHolder) {
            String category = "";

            //  Category category = items.
            categoryName.setText(AppConfig.FIRST_TAB_ITEMS[position - 1]);
            mAdapter = new RecyclerViewSelectedProducts(context, items.get(AppConfig.FIRST_TAB_ITEMS[position - 1]));
            mRecyclerView.hasFixedSize();
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setAdapter(mAdapter);
        } else if (holder instanceof ViewHolderHeader) {
            //cast holder to VHHeader and set data for header.
            int h = 200;
            int w = 320;

            bitmaps = new ArrayList<>();
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), R.drawable.agm_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), R.drawable.bosch_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), R.drawable.dremel_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), R.drawable.makita_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), R.drawable.marker_srafovi_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), R.drawable.skill_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), R.drawable.stanley_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), R.drawable.villager_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), R.drawable.wolfcraft_172, w, h));
            imageViewPagerWDotIndicator.setBitmapList(bitmaps);
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

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        for (int i = 0; i < bitmaps.size(); i++) {
            bitmaps.get(i).recycle();
            bitmaps.set(i, null);
        }
        Log.logInfo("DODATNA OP", "onDestroyView...");
        super.onDetachedFromRecyclerView(recyclerView);
    }
}
