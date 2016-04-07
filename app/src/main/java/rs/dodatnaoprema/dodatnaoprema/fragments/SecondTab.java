package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.MainActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.ImageItem;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_filtered_by_category.ArticlesFilteredByCategory;
import rs.dodatnaoprema.dodatnaoprema.models.articles.articles_filtered_by_category.Artikli;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Child;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import views.adapters.GridViewAdapter;

public class SecondTab extends Fragment {
    private ViewGroup drop_down;
    private RelativeLayout dropdown_layout;
    private ScrollView flow_layout_scroll;
    private ImageView dropdown_image;
    private TextView dropdown_text;
    private RotateAnimation rotateUp;
    private RotateAnimation rotateDown;
    private Animation slide_down;
    private Animation slide_up;
    private View last_clicked_btn;
    private ViewGroup.LayoutParams param;

    private List<Artikli> mArticles;

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    VolleySingleton mVolleySingleton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.you_may_also_like_product, container, false);
        gridView = (GridView) mView.findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(mView.getContext(), R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        mVolleySingleton = VolleySingleton.getsInstance(getContext());
        mArticles = new ArrayList<>();

        final MainActivity mainActivity = (MainActivity) getActivity();

        drop_down = (ViewGroup) mView.findViewById(R.id.flow_layout);
        flow_layout_scroll = (ScrollView) mView.findViewById(R.id.flow_layout_scroll);
        dropdown_layout = (RelativeLayout) mView.findViewById(R.id.dropdown_layout);
        dropdown_image = (ImageView) mView.findViewById(R.id.img_drop_arrow);
        dropdown_text = (TextView) mView.findViewById(R.id.txt_drop);

        flow_layout_scroll.setVisibility(ViewGroup.GONE);
        List<Category> categories = mainActivity.getCategoriesList();

        param = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        //add button for showing all products
        Button btn = addNewButton("Svi proizvodi");
        dropdown_text.setText("Svi proizvodi");
        btn.setSelected(true);
        last_clicked_btn = btn;

        // creating buttons
        for (Category category : categories
                ) {
            for (Child child : category.getChild()
                    ) {
                addNewButton(child.getKatIme());
            }
        }

        rotateUp = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateUp.setDuration(300);
        rotateUp.setFillAfter(true);
        rotateUp.setFillEnabled(true);
        rotateUp.setInterpolator(new LinearInterpolator());

        rotateDown = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateDown.setDuration(300);
        rotateDown.setFillAfter(true);
        rotateDown.setFillEnabled(true);
        rotateDown.setInterpolator(new LinearInterpolator());

        slide_down = AnimationUtils.loadAnimation(container.getContext(),
                R.anim.slide_down);

        slide_up = AnimationUtils.loadAnimation(container.getContext(), R.anim.slide_up);

        dropdown_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flow_layout_scroll.getVisibility() != ViewGroup.GONE) {
                    flow_layout_scroll.startAnimation(slide_up);
                    flow_layout_scroll.setVisibility(ViewGroup.GONE);
                    dropdown_image.startAnimation(rotateDown);
                } else {
                    flow_layout_scroll.startAnimation(slide_down);
                    flow_layout_scroll.setVisibility(ViewGroup.VISIBLE);
                    dropdown_image.startAnimation(rotateUp);
                }
            }
        });

        return mView;
    }

    private Button addNewButton(String text) {
        Button btn = new Button(drop_down.getContext());
        btn.setLayoutParams(param);
        btn.setBackgroundResource(R.drawable.rounded_btn_normal);
        btn.setPadding(10, 0, 10, 0);

        btn.setText(text);
        btn.setAllCaps(false);
        btn.setTextColor(ContextCompat.getColor(drop_down.getContext(), R.color.btnTextColor));
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        btn.setMinHeight(80);
        btn.setMinimumHeight(80);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (last_clicked_btn != null)
                    last_clicked_btn.setSelected(false);
                v.setSelected(true);
                last_clicked_btn = v;

                // JECO OVDE MOZE DA SE PRAVI REQUEST

                // Set selection
                dropdown_text.setText(((Button) v).getText());
                // Roll up
                flow_layout_scroll.startAnimation(slide_up);
                flow_layout_scroll.setVisibility(ViewGroup.GONE);
                dropdown_image.startAnimation(rotateDown);

            }
        });
        drop_down.addView(btn);
        return btn;
    }

    // Prepare some dummy data for gridview
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, "Image#" + i));
        }
        return imageItems;
    }

    private List<Artikli> searchArticlesByCategory(int id, int from, int to, int sort) {
        PullWebContent<ArticlesFilteredByCategory> content = new PullWebContent<ArticlesFilteredByCategory>(getActivity(), ArticlesFilteredByCategory.class, UrlEndpoints.getRequestUrlSearchArticlesByCategory(id, from, to, AppConfig.URL_VALUE_CURRENCY_RSD, AppConfig.URL_VALUE_LANGUAGE_SRB_LAT, sort), mVolleySingleton);
        content.setCallbackListener(new WebRequestCallbackInterface<ArticlesFilteredByCategory>() {
            @Override
            public void webRequestSuccess(boolean success, ArticlesFilteredByCategory articlesFilteredByCategory) {
                if (success) {
                    mArticles = articlesFilteredByCategory.getArtikli();

                }
            }

            @Override
            public void webRequestError(String error) {

            }
        });
        content.pullList();

        return mArticles;
    }
}
