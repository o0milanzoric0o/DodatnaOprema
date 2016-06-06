package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.dialogs.CartItemDeleteConfirmationDialog;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Cart;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.CartViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartViewFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.MultiChoiceModeListener, CartViewAdapter.ItemBtnClickListener {

    private Cart mCart;
    private CartViewAdapter mAdapter;
    private ListView listView;
    private CartItemDeleteConfirmationDialog cartItemDeleteConfirmationDialog;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mCart = (Cart) getArguments().getSerializable(AppConfig.GET_CART);

        cartItemDeleteConfirmationDialog = new CartItemDeleteConfirmationDialog(getActivity());
        cartItemDeleteConfirmationDialog.setPositiveButtonListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        cartItemDeleteConfirmationDialog.setNegativeButtonListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_view, container, false);

        listView = (ListView) view.findViewById(R.id.list_view_cart);

        mAdapter = new CartViewAdapter(getContext(), mCart.getArtikli());
        mAdapter.setItemBtnClickListner(this);

        listView.setOnItemClickListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);

        listView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        return view;
    }


    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(), "Clicked on: " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemBtnClickListener(int position) {
        cartItemDeleteConfirmationDialog.create().show();
    }
}
