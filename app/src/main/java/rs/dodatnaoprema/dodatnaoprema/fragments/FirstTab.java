package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.MainActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BitmapDecoder;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomProgressDialog;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.customview.ImageViewPagerWDotIndicator;
import rs.dodatnaoprema.dodatnaoprema.customview.swipeable_layout.OnLoadMoreListener;
import rs.dodatnaoprema.dodatnaoprema.customview.swipeable_layout.SwipeableLayout;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import views.adapters.RecyclerViewAdapterFirstTab;

public class FirstTab extends Fragment implements OnLoadMoreListener {

    private CustomProgressDialog mProgressDialog;
    private ImageViewPagerWDotIndicator imageViewPagerWDotIndicator;

    private ScrollView mScrollView;
    private SwipeableLayout mSwipeableLayout;

    private MainActivity mainActivity;

    ArrayList<Bitmap> bitmaps;
    List<Category> categories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            int h = 200;
            int w = 320;

            bitmaps = new ArrayList<>();
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.agm_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.bosch_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.dremel_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.makita_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.marker_srafovi_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.skill_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.stanley_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.villager_172, w, h));
            bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.wolfcraft_172, w, h));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.first_tab, container, false);
        mainActivity = (MainActivity) getActivity();

        mSwipeableLayout= (SwipeableLayout) mView.findViewById(R.id.swipeToLoadLayout);
        mSwipeableLayout.setOnLoadMoreListener(this);

        CustomRecyclerView mRecyclerView = (CustomRecyclerView) mView.findViewById(R.id.recycler_view);
        mScrollView = (ScrollView) mView.findViewById(R.id.scrollView);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        imageViewPagerWDotIndicator = (ImageViewPagerWDotIndicator) mView.findViewById(R.id.view_pager_dot_ind);

        RecyclerViewAdapterFirstTab mAdapter = new RecyclerViewAdapterFirstTab(mainActivity.getFirstTabItems(), getActivity(), bitmaps);
        mRecyclerView.setAdapter(mAdapter);
        return mView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("Bitmaps", bitmaps);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        for (int i = 0; i < bitmaps.size(); i++) {
            bitmaps.get(i).recycle();
            bitmaps.set(i, null);
        }
        Log.logInfo("DODATNA OP", "onDestroyView...");
        super.onDestroyView();
    }


    @Override
    public void onLoadMore() {
        mSwipeableLayout.setLoadingMore(true);
        mSwipeableLayout.setLoadingMore(false);
        mainActivity.moveToNextTab();
    }


}
