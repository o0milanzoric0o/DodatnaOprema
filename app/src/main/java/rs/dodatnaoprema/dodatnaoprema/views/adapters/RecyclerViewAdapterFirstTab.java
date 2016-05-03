package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.MainActivity;
import rs.dodatnaoprema.dodatnaoprema.OneArticleActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.image_slider_with_dot_indicator.ImageSlider2Products;
import rs.dodatnaoprema.dodatnaoprema.customview.image_slider_with_dot_indicator.ImageSlider3Brands;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.articles.brands.Brand;
import rs.dodatnaoprema.dodatnaoprema.models.articles.products_of_the_week.Product;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;


public class RecyclerViewAdapterFirstTab extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    HashMap<String, List<Article>> items = new HashMap<>();
    ArrayList<Product> products_of_the_week;
    ArrayList<Brand> allBrands;
    private TextView categoryName;
    private ImageSlider3Brands imageViewPagerWDotIndicator_three_imgs;
    private ImageSlider2Products imageViewPagerWDotIndicator_two_imgs;
    private Context context;
    private RecyclerView mRecyclerView;

    private GridLayoutManager mLayoutManager;

    private RelativeLayout mFourthButton;
    private VolleySingleton mVolleySingleton;

    public RecyclerViewAdapterFirstTab(HashMap<String, List<Article>> items, Context context, ArrayList<Product> products_of_the_week, ArrayList<Brand> allBrands) {
        this.items = items;
        this.context = context;
        this.products_of_the_week = products_of_the_week;
        this.allBrands = allBrands;
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
        } else if (viewType == TYPE_FOOTER) {
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
            RecyclerViewSelectedProducts mAdapter = new RecyclerViewSelectedProducts(context, items.get(AppConfig.FIRST_TAB_ITEMS[position - 1]), false, new RecyclerViewSelectedProducts.OnItemClickListener() {
                @Override
                public void onItemClick(Article item, View view) {

                    //Start Intent for Single Item Activity

                    int itemID = item.getArtikalId();


                    Log.logInfo("LALALA", "RecyclerViewAdapterFirstTab");

                }
            });
            mRecyclerView.hasFixedSize();
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setAdapter(mAdapter);

        } else if (holder instanceof ViewHolderHeader) {
            imageViewPagerWDotIndicator_three_imgs.setAllBrands(allBrands);
            imageViewPagerWDotIndicator_two_imgs.setProductsOfTheWeek(products_of_the_week);

            mFourthButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFourthButton.setSelected(true);
                    ((MainActivity) context).viewAllCategories();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
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
            imageViewPagerWDotIndicator_three_imgs = (ImageSlider3Brands) itemView.findViewById(R.id.view_pager_dot_ind_0);
            imageViewPagerWDotIndicator_two_imgs = (ImageSlider2Products) itemView.findViewById(R.id.view_pager_dot_ind_1);

            mFourthButton = (RelativeLayout) itemView.findViewById(R.id.fourth_round_button);

        }
    }

    class ViewHolderFooter extends RecyclerView.ViewHolder {

        public ViewHolderFooter(View itemView) {
            super(itemView);
        }
    }

}