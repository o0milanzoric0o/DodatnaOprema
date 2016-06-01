package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Cart;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartViewFragment extends Fragment {

    private Cart mCart;

    public CartViewFragment() {
        // Required empty public constructor
    }


    public static CartViewFragment newInstance(Cart cart) {
        CartViewFragment f = new CartViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(AppConfig.GET_CART, cart);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_view, container, false);
    }

}
