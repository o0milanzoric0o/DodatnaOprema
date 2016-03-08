package rs.dodatnaoprema.dodatnaoprema.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
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

import java.util.ArrayList;

import rs.dodatnaoprema.dodatnaoprema.R;

/**
 * Created by 1 on 3/8/2016.
 */
public class ImageViewPagerWDotIndicator extends RelativeLayout{

    private ImageView mImageView = null;
    private LinearLayout mDotsLayout = null;
    private ViewPager mViewPager = null;
    private ViewPagerAdapter mAdapter = null;
    private ArrayList<Bitmap> mBitmapList = null;
    private Context mcontext;
    private int dotsCount;
    private ImageView[] dots;

    public ImageViewPagerWDotIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;

        // Get attributes
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ImageViewPagerWDotIndicatorOptions, 0, 0);
        //String titleText = a.getString(R.styleable.ColorOptionsView_titleText);
        //int valueColor = a.getColor(R.styleable.ColorOptionsView_valueColor,
        //       android.R.color.holo_blue_light);

        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.dot_indicator_view_pager, this, true);

        // Get views
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mImageView = (ImageView) findViewById(R.id.img_pager_item);
        mDotsLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int dotsCount = mAdapter.getCount();
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(mcontext, R.drawable.nonselecteditem_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(mcontext, R.drawable.selecteditem_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setBitmapList(ArrayList<Bitmap> bitmapList){
        mBitmapList = bitmapList;
        mAdapter = new ViewPagerAdapter(mcontext, mBitmapList);
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(mcontext);
            dots[i].setImageDrawable(ContextCompat.getDrawable(mcontext, R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            mDotsLayout.addView(dots[i], params);
        }


        dots[0].setImageDrawable(ContextCompat.getDrawable(mcontext, R.drawable.selecteditem_dot));
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;
        private ArrayList<Bitmap> mbitmapArray;

        public ViewPagerAdapter(Context mContext, ArrayList<Bitmap> mbitmapArray) {
            this.mContext = mContext;
            this.mbitmapArray = mbitmapArray;
        }

        @Override
        public int getCount() {
            return mbitmapArray.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.view_pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
            imageView.setImageResource(mbitmapArray.get(position).getRowBytes());

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
