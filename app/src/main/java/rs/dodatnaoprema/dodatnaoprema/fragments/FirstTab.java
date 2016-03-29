package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BitmapDecoder;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomProgressDialog;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.customview.ImageViewPagerWDotIndicator;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.AllCategories;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import views.adapters.RecyclerViewAdapterAllCategories;

public class FirstTab extends Fragment {

    private CustomProgressDialog mProgressDialog;
    private ImageViewPagerWDotIndicator imageViewPagerWDotIndicator;
    private rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView mRecyclerView;

    ArrayList<Bitmap> bitmaps;
    List<Category> categories;

    private List<Category> mAllCategories = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.first_tab, container, false);

        mRecyclerView = (CustomRecyclerView) mView.findViewById(R.id.recycler_view);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);


//        imageViewPagerWDotIndicator = (ImageViewPagerWDotIndicator) mView.findViewById(R.id.view_pager_dot_ind);
//
//        int h = 200;
//        int w = 320;
//
//        bitmaps = new ArrayList<>();
//        bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.abc1, w, h));
//        bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.abc2, w, h));
//        bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.abc3, w, h));
//        bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.abc4, w, h));
//        bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.abc5, w, h));
//        bitmaps.add(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), R.drawable.abc6, w, h));
//        imageViewPagerWDotIndicator.setBitmapList(bitmaps);


        // Progress dialog
        mProgressDialog = new CustomProgressDialog(getActivity());
        mProgressDialog.showDialog(getResources().getString(R.string.progress_dialog_message));

        PullWebContent<AllCategories> content = new PullWebContent<AllCategories>(getActivity(), AllCategories.class, UrlEndpoints.getRequestUrlAllCategories());

        content.setCallbackListener(new WebRequestCallbackInterface<AllCategories>() {
            @Override
            public void webRequestSuccess(boolean success, AllCategories allCategories) {
                if (success) {
                    // specify an adapter
                    mAllCategories = allCategories.getKategorije();
                    RecyclerViewAdapterAllCategories mAdapter = new RecyclerViewAdapterAllCategories(mAllCategories, getActivity());
                    setCategoriesList(mAllCategories);
                    mRecyclerView.setAdapter(mAdapter);
                    mProgressDialog.hideDialog();
                }
            }

            @Override
            public void webRequestError(String error) {
                mProgressDialog.hideDialog();
            }
        });
        content.pullCategoriesList();


        return mView;
    }


    @Override
    public void onDestroyView() {
/*        for (int i = 0; i < bitmaps.size(); i++) {
            bitmaps.get(i).recycle();
            bitmaps.set(i, null);
        }
        Log.logInfo("DODATNA OP", "onDestroyView...");*/
        super.onDestroyView();
    }

    private void setCategoriesList(List<Category> categories) {
        this.categories = categories;
    }

}
