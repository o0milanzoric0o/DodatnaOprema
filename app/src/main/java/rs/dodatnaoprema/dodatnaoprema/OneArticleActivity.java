package rs.dodatnaoprema.dodatnaoprema;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.springindicator.SpringIndicator;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.dialogs.CartItemAddConfirmationDialog;
import rs.dodatnaoprema.dodatnaoprema.dialogs.InfoDialog;
import rs.dodatnaoprema.dodatnaoprema.dialogs.NumberPickerDialog;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.fragments.OneArticleImageFragment;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.articles.ArticleSpec;
import rs.dodatnaoprema.dodatnaoprema.models.cart.ItemAddResponse;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.ViewPagerAdapterOneArticle;

public class OneArticleActivity extends BaseActivity implements OneArticleImageFragment.OnProductImageGalleryDraw, NumberPickerDialog.NumberPickerDialogListener {

    public int quantity;
    SpringIndicator springIndicator;
    TextView mTextViewBrendName;
    TextView mTextViewProductName;
    TextView mTextViewPrice;
    TextView mTextViewAboutPrice;
    RatingBar mRatingBar;
    TextView mTextViewYesNo;
    TextView mTextViewMin;
    TextView mTextViewId;
    TextView mTextViewCode;
    TextView mTextViewArticleCategory;
    TextView mTextViewSendQuestion;
    Toolbar mToolbar;
    TextView mTextView;
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

        //mImageView = (NetworkImageView) findViewById(R.id.img_one_product);
        springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        mTextViewBrendName = (TextView) findViewById(R.id.textView_brend_name);
        mTextViewProductName = (TextView) findViewById(R.id.textView_naziv);
        mTextViewPrice = (TextView) findViewById(R.id.textView_cena);
        mTextViewAboutPrice = (TextView) findViewById(R.id.textView_about_price);

        mRatingBar = (RatingBar) findViewById(R.id.ratingBar_stars);
        mTextViewYesNo = (TextView) findViewById(R.id.textView_yes_no);
        mTextViewMin = (TextView) findViewById(R.id.textView_min);
        mTextViewId = (TextView) findViewById(R.id.textView_id);
        mTextViewCode = (TextView) findViewById(R.id.textView_code);
        mTextViewArticleCategory = (TextView) findViewById(R.id.article_category);
        mTextViewSendQuestion = (TextView) findViewById(R.id.textView_question);

        mTextViewKorpa = (TextView) findViewById(R.id.textView_korpa);
        mTextViewKorpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // price exists
                if (mOneArticle.getArtikal().getStanje() == 0) { // no available quantity
                    InfoDialog infoDialog = InfoDialog.newInstance("Ne može se dodati u korpu", "Artikla nema na stanju.");
                    infoDialog.show(getSupportFragmentManager(), "InfoDialog");
                } else if (mOneArticle.getArtikal().getCenaPrikaz() == null || mOneArticle.getArtikal().getMozedaseKupi() == 0) {
                    // no price so can't add it to cart. Inform the user
                    InfoDialog infoDialog = InfoDialog.newInstance("Ne može se dodati u korpu", "Artikal se može naručiti samo telefonom.");
                    infoDialog.show(getSupportFragmentManager(), "InfoDialog");
                } else {
                    showNumberPicker(view);
                }
            }
        });

        mTextViewSendQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(intent);
            }

        });

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTextView = (TextView) findViewById(R.id.title);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageButton icSearch = (ImageButton) findViewById(R.id.toolbar_btn_search);
        icSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });

        fillViews(getIntent());
/*
        mViewPager = (ViewPager) findViewById(R.id.viewpager_one_article);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs_one_article);
        mTabLayout.setupWithViewPager(mViewPager);*/
    }


    private void fillViews(Intent intent) {
        quantity = 0;


        mOneArticle = (OneArticle) intent.getExtras().get(AppConfig.ABOUT_PRODUCT);


        if (mTextView != null && mOneArticle != null)
            mTextView.setText(mOneArticle.getArtikal().getArtikalNaziv());

        //mImageView.setImageUrl(mOneArticle.getArtikal().getSlike().get(0).getSrednjaSlika(), mImageLoader);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vp_gallery);
        ImageGalleryAdapter adapterViewPager = new ImageGalleryAdapter(getSupportFragmentManager());
        adapterViewPager.setImageCount(mOneArticle.getArtikal().getSlike().size());
        vpPager.setAdapter(adapterViewPager);
        if (adapterViewPager.getCount() > 0)
            springIndicator.setViewPager(vpPager);
        else {
            // No product images so hide spring indicator
            springIndicator.setVisibility(View.GONE);
        }

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
            mTextViewMin.setText(getString(R.string.min_quantity_txt, String.valueOf(mOneArticle.getArtikal().getMozedaseKupi()),String.valueOf(mOneArticle.getArtikal().getTipUnit())));

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

/*
        mNumberPicker = (NumberPicker)findViewById(R.id.numberPicker);
        mNumberPicker.setMinValue(mOneArticle.getArtikal().getMozedaseKupi());
        mNumberPicker.setMaxValue(9999);
*/
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs_one_article);
        if (mTabLayout != null) {
            mTabLayout.addTab(mTabLayout.newTab().setText("Opis"));
            mTabLayout.addTab(mTabLayout.newTab().setText("Specifikacije"));
            mTabLayout.addTab(mTabLayout.newTab().setText("Kako kupiti"));
            mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            mViewPager = (ViewPager) findViewById(R.id.viewpager_one_article);
            final ViewPagerAdapterOneArticle adapter = new ViewPagerAdapterOneArticle
                    (getSupportFragmentManager(), mTabLayout.getTabCount());
            mViewPager.setAdapter(adapter);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
            mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    }

    public String opis() {
        Object opisObject = mOneArticle.getArtikal().getOpisArtikliTekstovi();
        byte[] data = Base64.decode(opisObject.toString(), Base64.DEFAULT);

        return new String(data);
    }

    public int getArtikalId() {

        return mOneArticle.getArtikal().getArtikalId();
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
                    new PullWebContent<>(ItemAddResponse.class, url, mVolleySingleton);
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
                            progressDialog.hideDialog();
                            InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Nije uspelo dodavanje artikla.");
                            infoDialog.show(getSupportFragmentManager(), "InfoDialog");
                        }
                    }
                }

                @Override
                public void webRequestError(String error) {
                    progressDialog.hideDialog();
                    // Web request fail
                    // Create snackbar or something
                    InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Proverite internet konekciju.");
                    infoDialog.show(getSupportFragmentManager(), "InfoDialog");
                }
            });
            content.pullList();

        } else {
//            InfoDialog infoDialog = new InfoDialog(this);
//            infoDialog.setDialogMessage("Morate se ulogovati.");
//            infoDialog.setPositiveButtonListener(new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    // Handle the login action
//                    Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
//                    startActivity(intent);
//                }
//            });
//            infoDialog.create().show();
            // We are not logged in, but enable adding to cart anyway
            MyApplication.getInstance().getSessionManager().addItemOfflineCart(item_id, quantity, mOneArticle.getArtikal().getCenaPrikazBroj(), mOneArticle.getArtikal().getArtikalNaziv(), mOneArticle.getArtikal().getSlike(), mOneArticle.getArtikal().getCenaPrikazExt(), mOneArticle.getArtikal().getMinimalnaKolArt());
            // get total item quantity
            String totalQuantity = String.valueOf(MyApplication.getInstance().getSessionManager().getOfflineCartItemCount());
            // Update toolbar Icon
            Log.e("Korpa quant:", String.valueOf(quantity));
            Log.e("Korpa tot_quant:", totalQuantity);
            Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(updateToolbar);
            // Inform the user
            cartItemAddConfirmationDialog = new CartItemAddConfirmationDialog(mContext);

            cartItemAddConfirmationDialog.setDialogMessage(String.format("U korpi imate ukupno %1s artikala.", totalQuantity));
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
        }
    }

    public void showNumberPicker(View v) {
        // Create an instance of the dialog fragment and show it
        NumberPickerDialog aNumberPicker = NumberPickerDialog.newInstance(mOneArticle.getArtikal().getMinimalnaKolArt(), 999);
        //       aNumberPicker.setMinValue(mOneArticle.getArtikal().getMozedaseKupi());
        aNumberPicker.show(getSupportFragmentManager(), "NumberPickerDialog");
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

    @Override
    public String getProductImage(int position) {
        String url = null;
        if (mOneArticle != null && mOneArticle.getArtikal().getSlike().size() > position)
            url = mOneArticle.getArtikal().getSlike().get(position).getSrednjaSlika();
        return url;
    }

    @Override
    public ArrayList<String> getProductImgLargeUrls() {
        ArrayList<String> result = new ArrayList<>();

        if (mOneArticle != null && mOneArticle.getArtikal().getSlike().size() > 0) {
            int size = mOneArticle.getArtikal().getSlike().size();
            String url;
            for (int i = 0; i < size; i++) {
                url = mOneArticle.getArtikal().getSlike().get(i).getVelikaSlika();
                result.add(url);
            }
        }
        return result;
    }

    @Override
    public Integer getArtikalNaAkciji() {
        if (mOneArticle != null)
            return mOneArticle.getArtikal().getArtikalNaAkciji();
        return -1;
    }

    public List<ArticleSpec> getArticleSpecification() {
        return mOneArticle.getArtikal().getSpec();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onNumberPickerDialogPositiveClick(DialogFragment dialog) {
        quantity = ((NumberPickerDialog) dialog).getNumberPicked();
        // Try to add to cart
        addToCart(getArtikalId(), quantity);
    }

    @Override
    public void onNumberPickerDialogNegativeClick(DialogFragment dialog) {
        quantity = 0;
        mTextViewKorpa.setText(R.string.add_to_chart);
    }

    public static class ImageGalleryAdapter extends FragmentPagerAdapter {

        private int imageCount = 0;

        public ImageGalleryAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return imageCount;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return OneArticleImageFragment.newInstance(position);
        }

        public void setImageCount(int imageCount) {
            this.imageCount = imageCount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position + 1);
        }
    }
}