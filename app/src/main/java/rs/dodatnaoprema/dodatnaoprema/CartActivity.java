package rs.dodatnaoprema.dodatnaoprema;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import rs.dodatnaoprema.dodatnaoprema.fragments.EmptyCartFragment;

public class CartActivity extends AppCompatActivity {
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mContainer = (FrameLayout) findViewById(R.id.container);
        if (mContainer != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            EmptyCartFragment emptyCartFragment = new EmptyCartFragment();
            fragmentTransaction.add(R.id.container, emptyCartFragment);
            fragmentTransaction.commit();
        }
    }
}
