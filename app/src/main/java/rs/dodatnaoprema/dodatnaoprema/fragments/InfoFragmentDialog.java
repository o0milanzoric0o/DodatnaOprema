package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;

public class InfoFragmentDialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Filter_DIALOG);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.info_fragment, container, false);
        byte[] data;

        LinearLayout.LayoutParams webViewParams = new

                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final WebView mWebViewTab = (WebView) root.findViewById(R.id.mWebViewTab);
        mWebViewTab.setVisibility(View.GONE);
        mWebViewTab.setLayoutParams(webViewParams);
        String title = getArguments().getString("mTitle");
        if (title!=null&&title.equals(getString(R.string.how_to_buy))) {
            data = Base64.decode(SharedPreferencesUtils.getString(getActivity(), "INFO_HOW_TO_BUY"), Base64.DEFAULT);
        } else {
            data = Base64.decode(SharedPreferencesUtils.getString(getActivity(), "INFO_CONTACT"), Base64.DEFAULT);
        }

        mWebViewTab.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mWebViewTab.setVisibility(View.VISIBLE);
            }
        });
        mWebViewTab.loadDataWithBaseURL(null, new String(data), "text/html", "UTF-8", null);

        //  mWebViewTab.loadDataWithBaseURL(null, mActivity.opis(), "text/html", "utf-8", null);
        // mWebViewTab.requestLayout();


        // Log.logInfo("LALALA.........", "jjj>"+mActivity.opis().length());



        TextView mTextView = (TextView) root.findViewById(R.id.title);
        mTextView.setText(title);
        ImageButton exitFragmentBtn = (ImageButton) root.findViewById(R.id.exit_btn);
        exitFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close dialog
                dismiss();

            }
        });
        return root;
    }
}
