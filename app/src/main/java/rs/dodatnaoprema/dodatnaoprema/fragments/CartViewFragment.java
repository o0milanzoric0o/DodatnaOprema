package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.dialogs.CartItemDeleteConfirmationDialog;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Cart;
import rs.dodatnaoprema.dodatnaoprema.models.cart.ItemDeleteResponse;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.CartViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartViewFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.MultiChoiceModeListener, CartViewAdapter.ItemBtnClickListener {

    private Cart mCart;
    private CartViewAdapter mAdapter;
    private ListView listView;
    private CartItemDeleteConfirmationDialog cartItemDeleteConfirmationDialog;
    private VolleySingleton mVolleySingleton;
    private int item_position;

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

        mVolleySingleton = VolleySingleton.getsInstance(getActivity());

        cartItemDeleteConfirmationDialog = new CartItemDeleteConfirmationDialog(getActivity());
        cartItemDeleteConfirmationDialog.setPositiveButtonListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete item from cart
                // check if logged in
                if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
                    // Load user data and get UserId
                    User user = MyApplication.getInstance().getPrefManager().getUser();
                    String user_id = user.getId();

                    int item_id = mCart.getArtikli().get(item_position).getArtikalId();
                    // get item id
                    String url = String.format(AppConfig.URL_DELETE_CART_ITEM, item_id ,user_id);

                    PullWebContent<ItemDeleteResponse> content =
                            new PullWebContent<ItemDeleteResponse>(getActivity(), ItemDeleteResponse.class, url, mVolleySingleton);
                    content.setCallbackListener(new WebRequestCallbackInterface<ItemDeleteResponse>() {
                        @Override
                        public void webRequestSuccess(boolean success, ItemDeleteResponse resp) {
                            if (success) {
                                if (resp.getSuccess()){
                                    // item is successfully deleted
                                    // remove from the list (this is offline removal)
                                    mCart.getArtikli().remove(item_position);
                                    mAdapter.notifyDataSetChanged();
                                    if (mCart.getArtikli().size()==0){
                                        // No more items in the cart
                                        // Show EmptyCartFragment
                                        // Launching  empty cart fragment
                                        Toast.makeText(getActivity(), "Removed last item", Toast.LENGTH_SHORT).show();
                                        Fragment emptyCartFragment = new EmptyCartFragment();
                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();

                                        // Replace whatever is in the container view with this fragment
                                        transaction.add(R.id.container, emptyCartFragment);
                                        transaction.commit();
                                    }
                                }else{
                                    /**TODO  couldn't delete, God knows why**/
                                }
                            }
                        }

                        @Override
                        public void webRequestError(String error) {
                            // Web request fail
                            // Create snackbar or something
                            /**TODO Inform the user there was a connection failure...**////
                        }
                    });
                    content.pullList();

                } else {

                }
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
        item_position = position;
        cartItemDeleteConfirmationDialog.create().show();
    }
}
