package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import rs.dodatnaoprema.dodatnaoprema.R;

public class SecondTab extends Fragment {
    ViewGroup drop_down;
    RelativeLayout dropdown_layout;
    ImageView dropdown_image;
    RotateAnimation rotateUp;
    RotateAnimation rotateDown;
    Animation slide_down;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.you_may_also_like_product, container, false);
        drop_down = (ViewGroup) mView.findViewById(R.id.flow_layout);
        dropdown_layout = (RelativeLayout) mView.findViewById(R.id.dropdown_layout);
        dropdown_image = (ImageView) mView.findViewById(R.id.img_drop_arrow);

        drop_down.setVisibility(ViewGroup.GONE);

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

        dropdown_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DODATNA OPREMA", "ON TOGGLE CLICK");
//                drop_down.setVisibility(ViewGroup.GONE);
                if (drop_down.getVisibility() != ViewGroup.GONE) {
                    dropdown_image.startAnimation(rotateDown);
                    drop_down.setVisibility(ViewGroup.GONE);
                } else {
                    drop_down.startAnimation(slide_down);
                    drop_down.setVisibility(ViewGroup.VISIBLE);

                    dropdown_image.startAnimation(rotateUp);
                }
            }
        });

        return mView;
    }
}
