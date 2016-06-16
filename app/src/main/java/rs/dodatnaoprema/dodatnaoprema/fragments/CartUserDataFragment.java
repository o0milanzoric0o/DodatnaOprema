package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.dodatnaoprema.dodatnaoprema.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartUserDataFragment extends Fragment {


    public CartUserDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_user_data, container, false);
    }

}
