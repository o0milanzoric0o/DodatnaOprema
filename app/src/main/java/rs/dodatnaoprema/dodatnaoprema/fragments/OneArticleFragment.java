package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneArticleFragment extends Fragment {

    private ImageView mImageView;
    private TextView mTextViewProizvodjac;

    public OneArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_product,container,false);
        mImageView = (ImageView)  view.findViewById(R.id.img_one_product);
        mTextViewProizvodjac = (TextView) view.findViewById(R.id.textView_proizvodjac);

        OneArticle oneArticle= (OneArticle) getArguments().get(AppConfig.ABOUT_PRODUCT);
        mTextViewProizvodjac.setText(oneArticle.getArtikal().getBrendIme());
        return view;
    }

}
