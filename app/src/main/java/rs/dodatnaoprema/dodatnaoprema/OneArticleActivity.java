package rs.dodatnaoprema.dodatnaoprema;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

public class OneArticleActivity extends AppCompatActivity {

    private NetworkImageView mImageView;
    private TextView mTextViewProizvodjac;
    private TextView mTextViewProductName;
    private TextView mTextViewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_article);

        mImageView = (NetworkImageView)  findViewById(R.id.img_one_product);
        mTextViewProizvodjac = (TextView) findViewById(R.id.textView_proizvodjac);
        mTextViewProductName = (TextView) findViewById(R.id.textView_naziv);
        mTextViewPrice= (TextView) findViewById(R.id.textView_cena);

        OneArticle oneArticle= (OneArticle) getIntent().getExtras().get(AppConfig.ABOUT_PRODUCT);
        ImageLoader mImageLoader = VolleySingleton.getsInstance(this).getImageLoader();

        mImageView.setImageUrl(oneArticle.getArtikal().getSlike().get(0).getSrednjaSlika(), mImageLoader);
        mTextViewProizvodjac.setText(oneArticle.getArtikal().getBrendIme());
        mTextViewProductName.setText(oneArticle.getArtikal().getArtikalNaziv());
        mTextViewPrice.setText(oneArticle.getArtikal().getCenaSamoBrojFormat()+" "+oneArticle.getArtikal().getCenaPrikazExt());

    }



}
