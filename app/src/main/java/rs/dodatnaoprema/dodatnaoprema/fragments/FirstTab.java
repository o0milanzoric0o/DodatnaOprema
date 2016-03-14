package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomProgressDialog;
import rs.dodatnaoprema.dodatnaoprema.customview.ImageViewPagerWDotIndicator;
import rs.dodatnaoprema.dodatnaoprema.models.categories.AllCategories;
import rs.dodatnaoprema.dodatnaoprema.network.PullAllCategories;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import views.adapters.RecyclerViewAdapterAllCategories;

public class FirstTab extends Fragment {

    private CustomProgressDialog mProgressDialog;
    private ImageViewPagerWDotIndicator imageViewPagerWDotIndicator;
    private RecyclerView mRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.first_tab, container, false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);

        imageViewPagerWDotIndicator = (ImageViewPagerWDotIndicator) mView.findViewById(R.id.view_pager_dot_ind);

        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.abc1));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.abc2));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.abc3));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.abc4));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.abc5));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.abc6));
        imageViewPagerWDotIndicator.setBitmapList(bitmaps);

        // Progress dialog
        mProgressDialog = new CustomProgressDialog(getActivity());
        mProgressDialog.showDialog(getResources().getString(R.string.progress_dialog_message));

        PullAllCategories pal = new PullAllCategories(getActivity());
        pal.setCallbackListener(new WebRequestCallbackInterface<AllCategories>() {
            @Override
            public void webRequestSuccess(boolean success, AllCategories allCategories) {
                if (success) {

                    mRecyclerView.setHasFixedSize(true);

                    // use a linear layout manager
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    mRecyclerView.setLayoutManager(mLayoutManager);

                    // specify an adapter
                    RecyclerViewAdapterAllCategories mAdapter = new RecyclerViewAdapterAllCategories(allCategories.getKategorije(),getActivity());
                    mRecyclerView.setAdapter(mAdapter);
                    mProgressDialog.hideDialog();
                }
            }

            @Override
            public void webRequestError(String error) {
                mProgressDialog.hideDialog();
            }
        });
        pal.pullCategoriesList(AppConfig.URL_ALL_CATEGORIES);

        return mView;
    }

}
