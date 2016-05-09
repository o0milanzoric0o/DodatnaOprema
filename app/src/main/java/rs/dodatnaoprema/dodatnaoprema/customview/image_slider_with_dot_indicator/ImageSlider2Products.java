package rs.dodatnaoprema.dodatnaoprema.customview.image_slider_with_dot_indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import github.chenupt.springindicator.SpringIndicator;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.articles.products_of_the_week.Product;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.pagetransformers.ExperimentalPageTransformer;

/**
 * Created by 1 on 3/8/2016.
 */
public class ImageSlider2Products extends RelativeLayout {

    private final Handler mhandler = new Handler();
    private LinearLayout mDotsLayout = null;
    private SpringIndicator mSpringIndicator = null;
    private ViewPager mViewPager = null;
    private ViewPagerAdapter mAdapter = null;
    private ArrayList<Product> mProductsOfTheWeek = null;
    private Context mcontext;
    private int mdotsCount;
    private ImageView[] mdots;
    private int slideInterval;
    private Runnable mRunnable;

    public ImageSlider2Products(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;

        // Get attributes
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ImageSlider, 0, 0);
        slideInterval = a.getInteger(R.styleable.ImageSlider_slideInterval, 6000);
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.dot_indicator_view_pager_double_version, this, true);

        // Get rs.dodatnaoprema.dodatnaoprema.views
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        //mImageView = (ImageView) findViewById(R.id.img_pager_item);
        mDotsLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int dotsCount = mAdapter.getCount();
                for (int i = 0; i < dotsCount; i++) {
                    mdots[i].setImageDrawable(ContextCompat.getDrawable(mcontext, R.drawable.nonselecteditem_dot));
                }

                mdots[position].setImageDrawable(ContextCompat.getDrawable(mcontext, R.drawable.selecteditem_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setPageTransformer(true, new ExperimentalPageTransformer());

    }

    public void setProductsOfTheWeek(ArrayList<Product> productsOfTheWeek) {
        this.mProductsOfTheWeek = productsOfTheWeek;
        mAdapter = new ViewPagerAdapter(mcontext, mProductsOfTheWeek);
        mdotsCount = mAdapter.getCount();
        mdots = new ImageView[mdotsCount];

        for (int i = 0; i < mdotsCount; i++) {
            mdots[i] = new ImageView(mcontext);
            mdots[i].setImageDrawable(ContextCompat.getDrawable(mcontext, R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            mDotsLayout.addView(mdots[i], params);
        }


        mdots[0].setImageDrawable(ContextCompat.getDrawable(mcontext, R.drawable.selecteditem_dot));

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mRunnable = new Runnable() {
            int item_count = 0;

            @Override
            public void run() {
                item_count = mViewPager.getCurrentItem();
                item_count++;
                if (item_count == mdotsCount) {
                    item_count = 0;
                }
                mViewPager.setCurrentItem(item_count, true); // set current item with smooth scroll
                mhandler.postDelayed(this, slideInterval);
            }
        };
        mhandler.postDelayed(mRunnable, slideInterval);
    }

    @Override
    protected void finalize() throws Throwable {
        mhandler.removeCallbacks(mRunnable);
        super.finalize();
    }

    private class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;
        private ArrayList<Product> mProductArray;

        public ViewPagerAdapter(Context mContext, ArrayList<Product> mProductArray) {
            this.mContext = mContext;
            this.mProductArray = mProductArray;
        }

        @Override
        public int getCount() {
            return mProductArray.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView;

            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_pager_item_double_version, container, false);
            NetworkImageView imageView_left = (NetworkImageView) itemView.findViewById(R.id.img_pager_item_1);
            NetworkImageView imageView_middle = (NetworkImageView) itemView.findViewById(R.id.img_pager_item_2);

            ImageLoader mImageLoader = VolleySingleton.getsInstance(mContext).getImageLoader();
            imageView_left.setImageUrl(mProductArray.get(position - 1 >= 0 ? position - 1 : mProductArray.size() - 1).getSrednjaSlika(), mImageLoader);
            imageView_middle.setImageUrl(mProductArray.get(position).getSrednjaSlika(), mImageLoader);
            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);

        }
    }

}
