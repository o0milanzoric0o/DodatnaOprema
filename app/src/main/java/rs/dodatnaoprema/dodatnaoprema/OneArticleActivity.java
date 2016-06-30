package rs.dodatnaoprema.dodatnaoprema;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.dialogs.CartItemAddConfirmationDialog;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.cart.ItemAddResponse;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.ViewPagerAdapterOneArticle;

public class OneArticleActivity extends BaseActivity {

    public int quantity;
    private NetworkImageView mImageView;
    private TextView mTextViewKorpa;
    private ViewPager mViewPager;
    private OneArticle mOneArticle;
    private Context mContext;

    private CartItemAddConfirmationDialog cartItemAddConfirmationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_article);

        mContext = OneArticleActivity.this;

        mImageView = (NetworkImageView) findViewById(R.id.img_one_product);
        TextView mTextViewBrendName = (TextView) findViewById(R.id.textView_brend_name);
        TextView mTextViewProductName = (TextView) findViewById(R.id.textView_naziv);
        TextView mTextViewPrice = (TextView) findViewById(R.id.textView_cena);
        TextView mTextViewAboutPrice = (TextView) findViewById(R.id.textView_about_price);

        RatingBar mRatingBar = (RatingBar) findViewById(R.id.ratingBar_stars);
        TextView mTextViewYesNo = (TextView) findViewById(R.id.textView_yes_no);
        TextView mTextViewMin = (TextView) findViewById(R.id.textView_min);
        TextView mTextViewId = (TextView) findViewById(R.id.textView_id);
        TextView mTextViewCode = (TextView) findViewById(R.id.textView_code);
        TextView mTextViewArticleCategory = (TextView) findViewById(R.id.article_category);

        mTextViewKorpa = (TextView) findViewById(R.id.textView_korpa);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        quantity = 0;


        mOneArticle = (OneArticle) getIntent().getExtras().get(AppConfig.ABOUT_PRODUCT);
        ImageLoader mImageLoader = VolleySingleton.getsInstance(this).getImageLoader();


        if (mTextView != null) mTextView.setText(mOneArticle.getArtikal().getArtikalNaziv());

        mImageView.setImageUrl(mOneArticle.getArtikal().getSlike().get(0).getSrednjaSlika(), mImageLoader);
        if (mTextViewBrendName != null)
            mTextViewBrendName.setText(getResources().getString(R.string.brend_txt, mOneArticle.getArtikal().getBrendIme()));
        if (mTextViewProductName != null)
            mTextViewProductName.setText(mOneArticle.getArtikal().getArtikalNaziv());
        if (mTextViewPrice != null)
            mTextViewPrice.setText(getResources().getString(R.string.cena_txt, mOneArticle.getArtikal().getCenaSamoBrojFormat() + " " + mOneArticle.getArtikal().getCenaPrikazExt()));
        if (mTextViewAboutPrice != null)
            mTextViewAboutPrice.setText(getString(R.string.price_by, mOneArticle.getArtikal().getTipUnitCelo()));


        Integer i = mOneArticle.getArtikal().getOcenaut();
        if (mRatingBar != null) mRatingBar.setRating(i);
        if (mTextViewYesNo != null) {
            if (mOneArticle.getArtikal().getStanje() == 1)
                mTextViewYesNo.setText(getString(R.string.text, "ima na stanju"));
            else mTextViewYesNo.setText(getString(R.string.text, "nema na stanju"));
        }
        if (mTextViewMin != null)
            mTextViewMin.setText(getString(R.string.min_quantity_txt, String.valueOf(mOneArticle.getArtikal().getMozedaseKupi())));

        if (mTextViewId != null)
            mTextViewId.append(" " + mOneArticle.getArtikal().getArtikalId());
        if (mTextViewCode != null)
            mTextViewCode.append(" " + mOneArticle.getArtikal().getCodeVendor());

        SpannableString content = new SpannableString(getString(R.string.articles_category_link, mOneArticle.getArtikal().getKategorijaArtiklaNaziv()));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        if (mTextViewArticleCategory != null) {
            mTextViewArticleCategory.setText(content);
            // mTextViewArticleCategory.setText(getString(R.string.articles_category_link, mOneArticle.getArtikal().getKategorijaArtiklaNaziv()));
            mTextViewArticleCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SubCategoryArticlesActivity.class);
                    intent.putExtra("Artikli", mOneArticle.getArtikal().getKategorijaArtiklaNaziv());
                    intent.putExtra("ArtikalId", mOneArticle.getArtikal().getKategorijaArtikalId());
                    startActivity(intent);
                }
            });
        }


        //Object opisObject = mOneArticle.getArtikal().getOpisArtikliTekstovi();
        // byte[] data = Base64.decode(opisObject.toString(), Base64.DEFAULT);
        //String opisText = new String(data);
        //  mTextViewAbout.setText("Opis artikla");

        //  mWebView.loadDataWithBaseURL(null, opisText, "text/html", "utf-8", null);

        Log.logInfo("LALALA.....", ".............");
/*
        mNumberPicker = (NumberPicker)findViewById(R.id.numberPicker);
        mNumberPicker.setMinValue(mOneArticle.getArtikal().getMozedaseKupi());
        mNumberPicker.setMaxValue(9999);
*/
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs_one_article);
        if (mTabLayout != null) {
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
/*
        mViewPager = (ViewPager) findViewById(R.id.viewpager_one_article);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs_one_article);
        mTabLayout.setupWithViewPager(mViewPager);*/
    }

    public String opis() {
        Object opisObject = mOneArticle.getArtikal().getOpisArtikliTekstovi();
        byte[] data = Base64.decode(opisObject.toString(), Base64.DEFAULT);

        return new String(data);
    }

    public int getArtikalId() {

        return mOneArticle.getArtikal().getArtikalId();
    }

    public void morePics(View v) {
        //selected_item.xml
        Toast.makeText(this.getApplicationContext(), "wqeqeqeqe", Toast.LENGTH_LONG).show();
        mImageView.setSelected(true);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mImageView.setSelected(false);
            }
        }, 1000);
    }


    public void addToCart(int item_id, final int quantity) {
        if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {

            final ProgressDialogCustom progressDialog = new ProgressDialogCustom(OneArticleActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.showDialog("Učitavanje...");

            // Load user data and get UserId
            User user = MyApplication.getInstance().getPrefManager().getUser();
            String user_id = user.getId();

            // int item_id = mCart.getArtikli().get(item_position).getArtikalId();
            // get item id
            String url = String.format(AppConfig.URL_ADD_CART_ITEM, item_id, quantity, user_id);
            VolleySingleton mVolleySingleton = VolleySingleton.getsInstance(this);
            PullWebContent<ItemAddResponse> content =
                    new PullWebContent<>(this, ItemAddResponse.class, url, mVolleySingleton);
            content.setCallbackListener(new WebRequestCallbackInterface<ItemAddResponse>() {
                @Override
                public void webRequestSuccess(boolean success, ItemAddResponse resp) {
                    if (success) {
                        progressDialog.hideDialog();
                        if (resp.getSuccess()) {
                            // item is successfully added to cart
                            // update one item display
                            mTextViewKorpa.setText(getString(R.string.quantity_txt, quantity));
                            // update toolbar cart icon
                            MyApplication.getInstance().getSessionManager().setCartItemCount(resp.getUkupnaKolicina());
                            Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
                            LocalBroadcastManager.getInstance(mContext).sendBroadcast(updateToolbar);
                            // Inform the user
                            cartItemAddConfirmationDialog = new CartItemAddConfirmationDialog(mContext);

                            cartItemAddConfirmationDialog.setDialogMessage(String.format("U korpi imate ukupno %1s artikala.", String.valueOf(resp.getUkupnaKolicina())));
                            cartItemAddConfirmationDialog.setPositiveButtonListener(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Launch cart view activity
                                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                                    startActivity(intent);
                                    // close one article activity
                                    finish();
                                }
                            });

                            cartItemAddConfirmationDialog.setNegativeButtonListener(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do nothing, just let the dialog close...
                                }
                            });

                            cartItemAddConfirmationDialog.create().show();

                        } else {
                            /**TODO  couldn't add to cart, God knows why**/
                            progressDialog.hideDialog();
                        }
                    }
                }

                @Override
                public void webRequestError(String error) {
                    progressDialog.hideDialog();
                    // Web request fail
                    // Create snackbar or something
                    /**TODO Inform the user there was a connection failure...**////
                }
            });
            content.pullList();

        }
    }

    public void addToCart(View v) {

        final NumberPicker aNumberPicker = new NumberPicker(mContext, null, R.style.number_picker);
        aNumberPicker.setMaxValue(9999);
        aNumberPicker.setMinValue(mOneArticle.getArtikal().getMozedaseKupi());
        aNumberPicker.setLayoutParams(new NumberPicker.LayoutParams(NumberPicker.LayoutParams.WRAP_CONTENT, NumberPicker.LayoutParams.WRAP_CONTENT));
        aNumberPicker.setOrientation(LinearLayout.VERTICAL);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle("Odaberite količinu");

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Izaberi",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                quantity = aNumberPicker.getValue();
                                // Try to add to cart
                                addToCart(getArtikalId(), quantity);
                            }
                        })
                .setNegativeButton("Otkaži",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                quantity = 0;
                                mTextViewKorpa.setText(R.string.add_to_chart);
                                dialog.cancel();
                            }
                        });
        //AlertDialog alertDialog = alertDialogBuilder.create();

        Dialog d = alertDialogBuilder.setView(aNumberPicker).create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        d.show();
        d.getWindow().setAttributes(lp);
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