package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.apmem.tools.layouts.FlowLayout;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;

public class SecondTab extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.you_may_also_like_product, container, false);

        FlowLayout flowLayout =(FlowLayout) mView.findViewById(R.id.flow_layout);
        Button btn1 = new Button(container.getContext());
        Button btn2 = new Button(container.getContext());
        Button btn3 = new Button(container.getContext());
        Button btn4 = new Button(container.getContext());
        Button btn5 = new Button(container.getContext());
        Log.logInfo("DODATNA OPREMA","Adding buttons");
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        flowLayout.addView(btn1, lp);
        flowLayout.addView(btn2, lp);
        flowLayout.addView(btn3, lp);
        flowLayout.addView(btn4, lp);
        flowLayout.addView(btn5, lp);
        return mView;
    }
}
