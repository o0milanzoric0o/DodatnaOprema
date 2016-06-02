package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Cart;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.CartViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartViewFragment extends Fragment {

    private Cart mCart;
    private CartViewAdapter mAdapter;
    private RecyclerView recyclerView;

    public CartViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mCart = (Cart) getArguments().getSerializable(AppConfig.GET_CART);
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
        View view = inflater.inflate(R.layout.fragment_cart_view, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_cart);

        mAdapter = new CartViewAdapter(mCart.getArtikli());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        return view;
    }

}
