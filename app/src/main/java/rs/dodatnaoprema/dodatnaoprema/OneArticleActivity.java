package rs.dodatnaoprema.dodatnaoprema;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

public class OneArticleActivity extends AppCompatActivity {

    private NetworkImageView mImageView;
    private TextView mTextViewBrendName;
    private TextView mTextViewProductName;
    private TextView mTextViewPrice;
    private TextView mTextViewAboutPrice;

    private TextView mTextViewStars;
    private TextView mTextViewYesNo;
    private TextView mTextViewMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_article);

        mImageView = (NetworkImageView)  findViewById(R.id.img_one_product);
        mTextViewBrendName = (TextView) findViewById(R.id.textView_brend_name);
        mTextViewProductName = (TextView) findViewById(R.id.textView_naziv);
        mTextViewPrice= (TextView) findViewById(R.id.textView_cena);
        mTextViewAboutPrice = (TextView) findViewById(R.id.textView_about_price);

        mTextViewStars = (TextView) findViewById(R.id.textView_stars);
        mTextViewYesNo = (TextView) findViewById(R.id.textView_yes_no);
        mTextViewMin = (TextView) findViewById(R.id.textView_min);


        OneArticle oneArticle= (OneArticle) getIntent().getExtras().get(AppConfig.ABOUT_PRODUCT);
        ImageLoader mImageLoader = VolleySingleton.getsInstance(this).getImageLoader();

        mImageView.setImageUrl(oneArticle.getArtikal().getSlike().get(0).getSrednjaSlika(), mImageLoader);
        mTextViewBrendName.setText(oneArticle.getArtikal().getBrendIme());
        mTextViewProductName.setText(oneArticle.getArtikal().getArtikalNaziv());
        mTextViewPrice.setText(oneArticle.getArtikal().getCenaSamoBrojFormat()+" "+oneArticle.getArtikal().getCenaPrikazExt());
        mTextViewAboutPrice.setText("cena po: "+ oneArticle.getArtikal().getTipUnitCelo());

        mTextViewStars.setText("ocena: "+ oneArticle.getArtikal().getOcenaut());

        if (oneArticle.getArtikal().getStanje() == 1)
            mTextViewYesNo.setText("ima na stanju");
        else
            mTextViewYesNo.setText("nema na stanju");

        mTextViewMin.setText("Minimalna količina za narudžbinu: " + oneArticle.getArtikal().getMozedasekupi());

    }



}
