package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import rs.dodatnaoprema.dodatnaoprema.OneArticleActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;

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

        final View mView = inflater.inflate(R.layout.web_view, container, false);
        mActivity = (OneArticleActivity) getActivity();

        LinearLayout.LayoutParams webViewParams =  new

                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mWebViewTab = (WebView) mView.findViewById(R.id.mWebViewTab);
        mWebViewTab.setLayoutParams(webViewParams);



        if (mWebViewTab != null) Log.logInfo("LALALA.........", "mWebViewTab != null");
        mWebViewTab.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + mActivity.opis(), "text/html", "UTF-8", null);

      //  mWebViewTab.loadDataWithBaseURL(null, mActivity.opis(), "text/html", "utf-8", null);
       // mWebViewTab.requestLayout();


        Log.logInfo("LALALA.........", "jjj>"+mActivity.opis().length());
        return mView;
    }

}
