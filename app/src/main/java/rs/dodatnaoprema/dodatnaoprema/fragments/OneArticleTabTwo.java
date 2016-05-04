package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import rs.dodatnaoprema.dodatnaoprema.OneArticleActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;

/**
 * Created by Mirna on 4.5.2016..
 */
public class OneArticleTabTwo extends Fragment {


    private OneArticleActivity mActivity;

    public OneArticleTabTwo() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View mView = inflater.inflate(R.layout.one_article_tab_two, container, false);
        mActivity = (OneArticleActivity) getActivity();


        return mView;
    }

}
