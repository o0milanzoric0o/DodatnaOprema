package rs.dodatnaoprema.dodatnaoprema;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.fragments.OneArticleTabOne;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.ViewPagerAdapterOneArticle;

public class OneArticleActivity extends AppCompatActivity {

    private NetworkImageView mImageView;
    private TextView mTextViewBrendName;
    private TextView mTextViewProductName;
    private TextView mTextViewPrice;
    private TextView mTextViewAboutPrice;

    private TextView mTextViewStars;
    private TextView mTextViewYesNo;
    private TextView mTextViewMin;

  //  private TextView mTextViewAbout;
    private WebView mWebView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private OneArticle mOneArticle;

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

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //  mTextViewAbout = (TextView) findViewById(R.id.textView_about);
        mWebView = (WebView) findViewById(R.id.webView);

        mOneArticle= (OneArticle) getIntent().getExtras().get(AppConfig.ABOUT_PRODUCT);
        ImageLoader mImageLoader = VolleySingleton.getsInstance(this).getImageLoader();


        mTextView.setText(mOneArticle.getArtikal().getArtikalNaziv());

        mImageView.setImageUrl(mOneArticle.getArtikal().getSlike().get(0).getSrednjaSlika(), mImageLoader);
        mTextViewBrendName.setText(mOneArticle.getArtikal().getBrendIme());
        mTextViewProductName.setText(mOneArticle.getArtikal().getArtikalNaziv());
        mTextViewPrice.setText(mOneArticle.getArtikal().getCenaSamoBrojFormat()+" "+mOneArticle.getArtikal().getCenaPrikazExt());
        mTextViewAboutPrice.setText("cena po: "+ mOneArticle.getArtikal().getTipUnitCelo());

        mTextViewStars.setText("ocena: "+ mOneArticle.getArtikal().getOcenaut());

        if (mOneArticle.getArtikal().getStanje() == 1)
            mTextViewYesNo.setText("ima na stanju");
        else
            mTextViewYesNo.setText("nema na stanju");

        mTextViewMin.setText("Minimalna količina za narudžbinu: " + String.valueOf(mOneArticle.getArtikal().getMozedaseKupi()));



        Object  opisObject = mOneArticle.getArtikal().getOpisArtikliTekstovi();
        byte[] data = Base64.decode(opisObject.toString(), Base64.DEFAULT);
        String opisText = new String(data);
      //  mTextViewAbout.setText("Opis artikla");

        mWebView.loadDataWithBaseURL(null, opisText, "text/html", "utf-8", null);

        Log.logInfo("LALALA.....", ".............");



        mTabLayout = (TabLayout) findViewById(R.id.tabs_one_article);
        mTabLayout.addTab(mTabLayout.newTab().setText("Opis"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Kako kupiti"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_one_article);
        final ViewPagerAdapterOneArticle adapter = new ViewPagerAdapterOneArticle
                (getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public String opis()
    {
        Object  opisObject = mOneArticle.getArtikal().getOpisArtikliTekstovi();
        byte[] data = Base64.decode(opisObject.toString(), Base64.DEFAULT);
        String opisText = new String(data);

        return opisText;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}