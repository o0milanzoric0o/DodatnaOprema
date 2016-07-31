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

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.articles.brands.Brand;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.pagetransformers.ExperimentalPageTransformer;

/**
 * ******************************
 * Created by milan on 5/1/2016.
 * ******************************
 */
public class ImageSlider3Brands extends RelativeLayout {

    private final Handler mhandler = new Handler();
    private LinearLayout mDotsLayout = null;
    private ViewPager mViewPager = null;
    private ViewPagerAdapter mAdapter = null;
    private ArrayList<Brand> mAllBrands = new ArrayList<>();
    private Context mcontext;
    private int mdotsCount;
    private ImageView[] mdots;
    private int slideInterval;
    private Runnable mRunnable;

    public ImageSlider3Brands(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;

        // Get attributes
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ImageSlider, 0, 0);
        //String titleText = a.getString(R.styleable.ColorOptionsView_titleText);
        //int valueColor = a.getColor(R.styleable.ColorOptionsView_valueColor,
        //       android.R.color.holo_blue_light);
        slideInterval = a.getInteger(R.styleable.ImageSlider_slideInterval, 6000);
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.dot_indicator_view_pager_tripple_version, this, true);

        // Get rs.dodatnaoprema.dodatnaoprema.views
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        //mImageView = (ImageView) findViewById(R.id.img_pager_item);
        mDotsLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mhandler.removeCallbacks(mRunnable);
                mhandler.postDelayed(mRunnable, slideInterval);
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

    public void setAllBrands(ArrayList<Brand> allBrands) {
        this.mAllBrands.clear();
        // Show only brands with BrendSajt == 2
        for (int i = 0; i < allBrands.size(); i++) {
            if (allBrands.get(i).getBrendSajtMasine() == 1 && allBrands.get(i).getBrendNaslovna() == 1)
                mAllBrands.add(allBrands.get(i));
        }

        mAdapter = new ViewPagerAdapter(mcontext, mAllBrands);
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

        if (mdots.length >= 1)
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
                // mhandler.postDelayed(this, slideInterval);
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
        private ArrayList<Brand> mBrandArray;

        public ViewPagerAdapter(Context mContext, ArrayList<Brand> mBrandArray) {
            this.mContext = mContext;
            this.mBrandArray = mBrandArray;
        }

        @Override
        public int getCount() {
            return mBrandArray.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(position);
            //return 0.8f;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView;
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_pager_item_tripple_version, container, false);
            NetworkImageView imageView_left = (NetworkImageView) itemView.findViewById(R.id.img_pager_item_1);
            NetworkImageView imageView_middle = (NetworkImageView) itemView.findViewById(R.id.img_pager_item_2);
            NetworkImageView imageView_right = (NetworkImageView) itemView.findViewById(R.id.img_pager_item_3);
            ImageLoader mImageLoader = VolleySingleton.getsInstance(mContext).getImageLoader();
            imageView_left.setImageUrl(mBrandArray.get(position - 1 >= 0 ? position - 1 : mBrandArray.size() - 1).getBrendSlika172(), mImageLoader);
            imageView_middle.setImageUrl(mBrandArray.get(position).getBrendSlika172(), mImageLoader);

            imageView_right.setImageUrl(mBrandArray.get(position + 1 < mBrandArray.size() ? position + 1 : 0).getBrendSlika172(), mImageLoader);
            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);

        }
    }
}
