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
public class OneArticleTabOne extends Fragment {

    private WebView mWebViewTab;
    private OneArticleActivity mActivity;

    public OneArticleTabOne() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View mView = inflater.inflate(R.layout.one_article_tab_one, container, false);
        mActivity = (OneArticleActivity) getActivity();

        mWebViewTab = (WebView) mView.findViewById(R.id.mWebViewTab);

        if (mWebViewTab != null) Log.logInfo("LALALA.........", "mWebViewTab != null");

        mWebViewTab.loadDataWithBaseURL(null, mActivity.opis(), "text/html", "utf-8", null);

        Log.logInfo("LALALA.........", "jjj>"+mActivity.opis().length());
        return mView;
    }

}
