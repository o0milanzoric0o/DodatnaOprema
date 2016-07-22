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
import rs.dodatnaoprema.dodatnaoprema.dialogs.InfoDialog;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.models.User;
import rs.dodatnaoprema.dodatnaoprema.models.cart.Cart;
import rs.dodatnaoprema.dodatnaoprema.models.cart.ItemDeleteResponse;
import rs.dodatnaoprema.dodatnaoprema.models.cart.ItemUpdateResponse;
import rs.dodatnaoprema.dodatnaoprema.models.one_article.OneArticle;
import rs.dodatnaoprema.dodatnaoprema.network.PullWebContent;
import rs.dodatnaoprema.dodatnaoprema.network.UrlEndpoints;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.views.adapters.CartViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartViewFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.MultiChoiceModeListener, CartViewAdapter.ItemBtnClickListener, CartViewAdapter.ChangeItemQuantityListener {

    private Cart mCart;
    private CartViewAdapter mAdapter;
    private ListView listView;
    private Button mBtnBuy;
    private TextView mTotal;

    private CartItemDeleteConfirmationDialog cartItemDeleteConfirmationDialog;
    private VolleySingleton mVolleySingleton;
    private int item_position;
    private ProgressDialogCustom progressDialog;

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

        progressDialog = new ProgressDialogCustom(getActivity());
        progressDialog.setCancelable(false);

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
                    String url = String.format(AppConfig.URL_DELETE_CART_ITEM, item_id, user_id);

                    PullWebContent<ItemDeleteResponse> content =
                            new PullWebContent<>(ItemDeleteResponse.class, url, mVolleySingleton);
                    content.setCallbackListener(new WebRequestCallbackInterface<ItemDeleteResponse>() {
                        @Override
                        public void webRequestSuccess(boolean success, ItemDeleteResponse resp) {
                            if (success) {
                                if (resp.getSuccess()) {
                                    // item is successfully deleted
                                    // so pull cart content again...
                                    pullCartContent();

                                } else {
                                    InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Nije uspelo brisanje artikla.");
                                    infoDialog.show(getFragmentManager(),"InfoDialog");
                                }
                            }
                        }

                        @Override
                        public void webRequestError(String error) {
                            // Web request fail
                            // Create snackbar or something
                            InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Proverite internet konekciju.");
                            infoDialog.show(getFragmentManager(),"InfoDialog");
                        }
                    });
                    content.pullList();

                } else {

                }
                cartItemDeleteConfirmationDialog.setNegativeButtonListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                });
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

        mAdapter = new CartViewAdapter(getContext(), mCart.getArtikli());
        mAdapter.setItemBtnClickListner(this);
        mAdapter.setChangeItemQuantityListener(this);

        listView.setOnItemClickListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);

        listView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        String tot = String.valueOf(mCart.getUkupnaCena()) + " " + mCart.getCenaPrikazExt();

        mTotal.setText(tot);

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
        int itemID = mCart.getArtikli().get(position).getArtikalId();
        viewArticle(itemID);

    }

    @Override
    public void onItemBtnClickListener(int position) {
        item_position = position;
        cartItemDeleteConfirmationDialog.create().show();
    }

    @Override
    public void onItemChangeQuantityInc(int position, ProgressBar progressBar) {
        Integer old_quantity = mCart.getArtikli().get(position).getKorpa().getKorpaKolTempArt();
        Integer new_quantity = ++old_quantity;
        setNewQauntity(position, new_quantity, progressBar);
    }

    @Override
    public void onItemChangeQuantityDec(int position, ProgressBar progressBar) {
        Integer old_quantity = mCart.getArtikli().get(position).getKorpa().getKorpaKolTempArt();
        Integer new_quantity = --old_quantity;
        Integer min_quantity = mCart.getArtikli().get(position).getMinimalnaKolArt();
        if (new_quantity >= min_quantity)
            setNewQauntity(position, new_quantity, progressBar);
        else
            Toast.makeText(getActivity(), "Minimalna količina: " + String.valueOf(min_quantity), Toast.LENGTH_SHORT).show();
    }

    private void pullCartContent() {
        if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
            // Load user data and get UserId
            User user = MyApplication.getInstance().getPrefManager().getUser();
            String user_id = user.getId();
            String url = String.format(AppConfig.URL_GET_CART, user_id);
            progressDialog.showDialog("Učitavanje...");

            PullWebContent<Cart> content =
                    new PullWebContent<>(Cart.class, url, mVolleySingleton);
            content.setCallbackListener(new WebRequestCallbackInterface<Cart>() {
                @Override
                public void webRequestSuccess(boolean success, Cart cart) {
                    if (success) {

                        mCart = cart;


                        if (cart.getArtikli().size() == 0) {
                            // No more items in the cart
                            // Show EmptyCartFragment
                            // Launching  empty cart fragment
                            MyApplication.getInstance().getSessionManager().setCartItemCount(0);


                            Toast.makeText(getActivity(), "Removed last item", Toast.LENGTH_SHORT).show();
                            Fragment emptyCartFragment = new EmptyCartFragment();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();

                            // Replace whatever is in the container view with this fragment
                            transaction.replace(R.id.container, emptyCartFragment);
                            transaction.commit();
                        } else {
                            // update toolbar cart item count
                            MyApplication.getInstance().getSessionManager().setCartItemCount(mCart.getUkupnaKolicina());

                            mAdapter = new CartViewAdapter(getContext(), mCart.getArtikli());
                            mAdapter.setItemBtnClickListner(CartViewFragment.this);
                            mAdapter.setChangeItemQuantityListener(CartViewFragment.this);
                            listView.setAdapter(mAdapter);
                        }

                        //mUpdateToolbarIconCallback.UpdateCartToolbarIcon();
                        // Update Navigation Drawer from main activity
                        Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(updateToolbar);

                        // Update total price
                        String tot = String.valueOf(mCart.getUkupnaCena()) + " " + mCart.getCenaPrikazExt();
                        mTotal.setText(tot);

                    }
                    progressDialog.hideDialog();
                }

                @Override
                public void webRequestError(String error) {
                    progressDialog.hideDialog();
                }
            });
            content.pullList();
        }
    }

    private void setNewQauntity(int position, int quantity, final ProgressBar progressBar) {
        if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
            // Load user data and get UserId
            User user = MyApplication.getInstance().getPrefManager().getUser();
            String user_id = user.getId();

            int item_id = mCart.getArtikli().get(position).getArtikalId();
            // get item id
            String url = String.format(AppConfig.URL_UPDATE_CART_ITEM_QUANTITY, item_id, quantity, user_id);

            progressBar.setVisibility(View.VISIBLE);
            PullWebContent<ItemUpdateResponse> content =
                    new PullWebContent<>(ItemUpdateResponse.class, url, mVolleySingleton);
            content.setCallbackListener(new WebRequestCallbackInterface<ItemUpdateResponse>() {
                @Override
                public void webRequestSuccess(boolean success, ItemUpdateResponse resp) {
                    progressBar.setVisibility(View.GONE);
                    if (success) {
                        if (resp.getSuccess()) {
                            // item is successfully updated
                            // so pull cart content again...
                            pullCartContent();

                        } else {
                            InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Nije uspelo dodavanje artikla.");
                            infoDialog.show(getFragmentManager(),"InfoDialog");
                        }
                    }
                }

                @Override
                public void webRequestError(String error) {
                    progressBar.setVisibility(View.GONE);
                    // Web request fail
                    // Create snackbar or something
                    InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Proverite internet konekciju.");
                    infoDialog.show(getFragmentManager(),"InfoDialog");

                }
            });
            content.pullList();

        } else {
            // not logged
        }
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
