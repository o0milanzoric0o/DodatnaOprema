package rs.dodatnaoprema.dodatnaoprema;

/**
 * Created by 1 on 3/29/2016.
 */
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.AllCategories;
import rs.dodatnaoprema.dodatnaoprema.models.categories.all_categories.Category;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;

public class SplashActivity extends AppCompatActivity {

    private List<Category> mAllCategories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);
        ImageView image = (ImageView)findViewById(R.id.img_logo);
        image.setBackgroundResource(R.drawable.chainsaw_splah_screen_logo);
        AnimationDrawable rocketAnimation = (AnimationDrawable) image.getBackground();
        rocketAnimation.start();

        PullWebContent<AllCategories> content = new PullWebContent<AllCategories>(this, AllCategories.class, UrlEndpoints.getRequestUrlAllCategories());
        content.setCallbackListener(new WebRequestCallbackInterface<AllCategories>() {
            @Override
            public void webRequestSuccess(boolean success, AllCategories allCategories) {
                if (success) {
                    // specify an adapter
                    mAllCategories = allCategories.getKategorije();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("AllCategories", (Serializable) mAllCategories);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void webRequestError(String error) {
            }
        });
        content.pullCategoriesList();

    }
}