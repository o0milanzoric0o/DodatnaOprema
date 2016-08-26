package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import rs.dodatnaoprema.dodatnaoprema.OneArticleActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.dialogs.CartItemDeleteConfirmationDialog;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.models.OfflineCart;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.CartViewOfflineAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartViewOfflineFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.MultiChoiceModeListener, CartViewOfflineAdapter.ItemBtnClickListener, CartViewOfflineAdapter.ChangeItemQuantityListener {

    private OfflineCart mOfflineCart;
    private CartViewOfflineAdapter mAdapter;
    private ListView listView;
    private Button mBtnBuy;
    private TextView mTotal;
    private TextView mShipping;
    private TextView mPrice;
    //private String mtot;

    private CartItemDeleteConfirmationDialog cartItemDeleteConfirmationDialog;
    private VolleySingleton mVolleySingleton;
    private int item_position;

    public CartViewOfflineFragment() {
        // Required empty public constructor
    }

    public static CartViewOfflineFragment newInstance(OfflineCart offlineCart) {

        CartViewOfflineFragment f = new CartViewOfflineFragment();
        Bundle args = new Bundle();
        args.putSerializable(AppConfig.GET_CART, offlineCart);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mOfflineCart = (OfflineCart) getArguments().getSerializable(AppConfig.GET_CART);

        mVolleySingleton = VolleySingleton.getsInstance(getActivity());

        cartItemDeleteConfirmationDialog = new CartItemDeleteConfirmationDialog(getActivity());
        cartItemDeleteConfirmationDialog.setPositiveButtonListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Delete item
                Integer item_id = mOfflineCart.getItems().get(item_position).getItem_id();
                mOfflineCart.removeItem(item_id);
                pullCartContent();
            }
        });
        cartItemDeleteConfirmationDialog.setNegativeButtonListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing...
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_view, container, false);

        listView = (ListView) view.findViewById(R.id.list_view_cart);
        mBtnBuy = (Button) view.findViewById(R.id.btn_cart_buy);
        mTotal = (TextView) view.findViewById(R.id.tv_total);
        mShipping = (TextView) view.findViewById(R.id.tv_shipping);
        mPrice = (TextView) view.findViewById(R.id.tv_price);

        mBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch fragment to fill user data
                Fragment userDataFragment = CartUserDataFragment.newInstance(mTotal.getText().toString());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the container view with this fragment
                transaction.add(R.id.container, userDataFragment);
                transaction.commit();
            }
        });

        mAdapter = new CartViewOfflineAdapter(getContext(), mOfflineCart.getItems());
        mAdapter.setItemBtnClickListner(this);
        mAdapter.setChangeItemQuantityListener(this);

        listView.setOnItemClickListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);

        listView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();


        //**TODO shipping is hardcoded right now, fix it in the future

        String tot = mOfflineCart.getTotalPrice(200) + " " + mOfflineCart.getPrice_ext();
        String price = mOfflineCart.getTotalPrice(0) + " " + mOfflineCart.getPrice_ext();
        String shipping = String.valueOf(200) + " " + mOfflineCart.getPrice_ext();

        mTotal.setText(tot);
        mPrice.setText(price);
        mShipping.setText(shipping);

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
        int itemID = mOfflineCart.getItems().get(position).getItem_id();
        viewArticle(itemID);
    }

    @Override
    public void onItemBtnClickListener(int position) {
        item_position = position;
        cartItemDeleteConfirmationDialog.create().show();
    }

    @Override
    public void onItemChangeQuantityInc(int position, ProgressBar progressBar) {
        Integer old_quantity = mOfflineCart.getItems().get(position).getQuantity();
        Integer new_quantity = ++old_quantity;
        setNewQauntity(position, new_quantity);
    }

    @Override
    public void onItemChangeQuantityDec(int position, ProgressBar progressBar) {
        Integer old_quantity = mOfflineCart.getItems().get(position).getQuantity();
        Integer new_quantity = --old_quantity;
        Integer min_quantity = mOfflineCart.getItems().get(position).getMinQuantity();
        if (new_quantity >= min_quantity)
            setNewQauntity(position, new_quantity);
        else
            Toast.makeText(getActivity(), "Minimalna količina: " + String.valueOf(min_quantity), Toast.LENGTH_SHORT).show();
    }

    private void pullCartContent() {
        MyApplication.getInstance().getPrefManager().saveOfflineCart(mOfflineCart);
        MyApplication.getInstance().getSessionManager().setOfflineCartItemCount(mOfflineCart.getTotalQuantity());
        mAdapter.notifyDataSetChanged();
        // Update toolbar icon
        Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(updateToolbar);

        // Update total price
        //**TODO shipping is hardcoded right now, fix it in the future

        String tot = mOfflineCart.getTotalPrice(200) + " " + mOfflineCart.getPrice_ext();
        String price = mOfflineCart.getTotalPrice(0) + " " + mOfflineCart.getPrice_ext();
        String shipping = String.valueOf(200) + " " + mOfflineCart.getPrice_ext();

        mTotal.setText(tot);
        mPrice.setText(price);
        mShipping.setText(shipping);

        //String offline_price = mOfflineCart.getTotalPrice(0);

        if (Float.valueOf(mOfflineCart.getTotalPrice(0)) == 0.0f) {
            //show emty cart fragment
            Fragment emptyCartFragment = new EmptyCartFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the container view with this fragment
            transaction.replace(R.id.container, emptyCartFragment);
            transaction.commit();
        }
    }

    private void setNewQauntity(int position, int quantity) {
        Integer item_id = mOfflineCart.getItems().get(position).getItem_id();
        mOfflineCart.updateItemQuantity(item_id, quantity);
        pullCartContent();
    }

    public void viewArticle(int itemID) {

        final ProgressDialogCustom progressDialog = new ProgressDialogCustom(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.showDialog("Učitavanje...");

        PullWebContent<OneArticle> content =
                new PullWebContent<>(OneArticle.class, UrlEndpoints.getRequestUrlArticleById(itemID), mVolleySingleton);

        content.setCallbackListener(new WebRequestCallbackInterface<OneArticle>() {
            @Override
            public void webRequestSuccess(boolean success, OneArticle oneArticle) {
                if (success) {
                    Intent intent = new Intent(getContext(), OneArticleActivity.class);
                    intent.putExtra(AppConfig.ABOUT_PRODUCT, oneArticle);
                    startActivity(intent);
                    progressDialog.hideDialog();
                } else {
                    progressDialog.hideDialog();
                }
            }

            @Override
            public void webRequestError(String error) {
                progressDialog.hideDialog();

            }
        });

        content.pullList();
    }
}