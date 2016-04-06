package rs.dodatnaoprema.dodatnaoprema.fragments;


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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.MainActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Child;

public class SecondTab extends Fragment {
    ViewGroup drop_down;
    RelativeLayout dropdown_layout;
    ImageView dropdown_image;
    TextView dropdown_text;
    RotateAnimation rotateUp;
    RotateAnimation rotateDown;
    Animation slide_down;
    Animation slide_up;
    View last_clicked_btn;
    ViewGroup.LayoutParams param;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.you_may_also_like_product, container, false);
        final MainActivity mainActivity = (MainActivity) getActivity();

        drop_down = (ViewGroup) mView.findViewById(R.id.flow_layout);
        dropdown_layout = (RelativeLayout) mView.findViewById(R.id.dropdown_layout);
        dropdown_image = (ImageView) mView.findViewById(R.id.img_drop_arrow);
        dropdown_text = (TextView) mView.findViewById(R.id.txt_drop);

        drop_down.setVisibility(ViewGroup.GONE);
        List<Category> categories = mainActivity.getCategoriesList();

        param = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        //add button for showing all products
        Button btn =  addNewButton("Svi proizvodi");
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
                if (drop_down.getVisibility() != ViewGroup.GONE) {
                    drop_down.startAnimation(slide_up);
                    drop_down.setVisibility(ViewGroup.GONE);
                    dropdown_image.startAnimation(rotateDown);
                } else {
                    drop_down.startAnimation(slide_down);
                    drop_down.setVisibility(ViewGroup.VISIBLE);
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

                // Set selection
                dropdown_text.setText(((Button) v).getText());
                // Roll up
                drop_down.startAnimation(slide_up);
                drop_down.setVisibility(ViewGroup.GONE);
                dropdown_image.startAnimation(rotateDown);

            }
        });
        drop_down.addView(btn);
        return btn;
    }
}
