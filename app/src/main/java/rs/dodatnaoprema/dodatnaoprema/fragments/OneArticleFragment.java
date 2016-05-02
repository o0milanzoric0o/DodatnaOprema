package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneArticleFragment extends Fragment {

    private NetworkImageView mImageView;
    private TextView mTextViewProizvodjac;
    private TextView mTextViewProductName;
    private TextView mTextViewPrice;

    public OneArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_product,container,false);
        mImageView = (NetworkImageView)  view.findViewById(R.id.img_one_product);
        mTextViewProizvodjac = (TextView) view.findViewById(R.id.textView_proizvodjac);
        mTextViewProductName = (TextView) view.findViewById(R.id.textView_naziv);
        mTextViewPrice= (TextView) view.findViewById(R.id.textView_cena);

        OneArticle oneArticle= (OneArticle) getArguments().get(AppConfig.ABOUT_PRODUCT);
        ImageLoader mImageLoader = VolleySingleton.getsInstance(getActivity()).getImageLoader();
        mImageView.setImageUrl(oneArticle.getArtikal().getSlike().get(0).getSrednjaSlika(), mImageLoader);
        mTextViewProizvodjac.setText(oneArticle.getArtikal().getBrendIme());
        mTextViewProductName.setText(oneArticle.getArtikal().getArtikalNaziv());
        mTextViewPrice.setText(oneArticle.getArtikal().getCenaSamoBrojFormat()+" "+oneArticle.getArtikal().getCenaPrikazExt());
        return view;
    }

}