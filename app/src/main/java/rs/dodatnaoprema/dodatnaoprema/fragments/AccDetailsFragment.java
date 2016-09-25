package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import rs.dodatnaoprema.dodatnaoprema.Interface.SignInCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Utils;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccDetailsFragment extends Fragment {
    EditText et_name;
    EditText et_last_name;
    EditText et_address;
    EditText et_city;
    EditText et_zip;
    EditText et_email;
    EditText et_mobile;
    EditText et_phone;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private SignInCallbackInterface mCallback;

    public AccDetailsFragment() {
        // Required empty public constructor
    }

    public static AccDetailsFragment newInstance(Toolbar toolbar, FloatingActionButton fab) {
        AccDetailsFragment f = new AccDetailsFragment();
        // TODO do we need to pass some args here?
        f.fab = fab;
        f.toolbar = toolbar;
        //Bundle args = new Bundle();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_details, container, false);
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_last_name = (EditText) view.findViewById(R.id.et_last_name);
        et_address = (EditText) view.findViewById(R.id.et_shipping_address);
        et_city = (EditText) view.findViewById(R.id.et_city);
        et_zip = (EditText) view.findViewById(R.id.et_zip);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_mobile = (EditText) view.findViewById(R.id.et_mobile);
        et_phone = (EditText) view.findViewById(R.id.et_phone);
        // Load user data and fill text views
        User user = MyApplication.getInstance().getPrefManager().getUser();
        if (user.getEmail() != null) {
            et_email.setText(user.getEmail());
        }
        if (user.getName() != null) {
            et_name.setText(user.getName());
        }
        if (user.getLast_name() != null) {
            et_last_name.setText(user.getLast_name());
        }
        if (user.getAddress() != null) {
            et_address.setText(user.getAddress());
        }
        if (user.getCity() != null) {
            et_city.setText(user.getCity());
        }
        if (user.getZip_code() != null) {
            et_zip.setText(user.getZip_code());
        }
        if (user.getMobile() != null) {
            et_mobile.setText(user.getMobile());
        }
        if (user.getPhone() != null) {
            et_phone.setText(user.getPhone());
        }

        if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
            //status.setText(getString(R.string.text_logged_in));
        } else
            //status.setText(getString(R.string.text_logged_out));

            if (user.getPhoto() != null) {
                //imageView.setImageURI(user.getPhoto());
            }

        accDetailsToolbar();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate fields
                if (et_name.getText().toString().trim().length() > 0
                        & et_last_name.getText().toString().trim().length() > 0
                        & et_email.getText().toString().trim().length() > 0
                        & et_address.getText().toString().trim().length() > 0
                        & et_city.getText().toString().trim().length() > 0
                        & et_zip.getText().toString().trim().length() > 0
                        & et_mobile.getText().toString().trim().length() > 0
                        & et_phone.getText().toString().trim().length() > 0) {
                    // Change User Object
                    User user = MyApplication.getInstance().getPrefManager().getUser();
                    if (user == null) user = new User();
                    //user.setGeneral_name();
                    user.setName(et_name.getText().toString().trim());
                    user.setLast_name(et_last_name.getText().toString().trim());
                    user.setAddress(et_address.getText().toString().trim());
                    user.setZip_code(et_zip.getText().toString().trim());
                    user.setCity(et_city.getText().toString().trim());
                    user.setPhone(et_phone.getText().toString().trim());
                    user.setMobile(et_mobile.getText().toString().trim());
                    user.setEmail(et_email.getText().toString().trim());
                    MyApplication.getInstance().getPrefManager().storeUser(user);
                    // Update user data
                    mCallback.onChangeUserData();
                } else {
                    // show snackbar to enter credentials
                    Snackbar.make(fab, "Morate popuniti sva polja.", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });

        fab.setImageDrawable(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.CONTENT_SAVE_SETTINGS, R.color.colorTextWhite));
        fab.show();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SignInCallbackInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SignInCallbackInterface");
        }
    }

    private void accDetailsToolbar() {
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.CLOSE, R.color.colorTextWhite));
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                }
        );


        TextView tv = (TextView) toolbar.findViewById(R.id.tv_toolbar_txt);
        tv.setText("Odjavi se");
        tv.setVisibility(View.VISIBLE);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.hideKeyboard(getActivity());
                //Log out
                MyApplication.getInstance().getSessionManager().setLogin(false);
                // Switch to Login Fragment
                Fragment loginFragment = LoginFragment.newInstance(toolbar, fab);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // clear the cart since we logged off
                MyApplication.getInstance().getSessionManager().setCartItemCount(0);
                // inform about cart update
                Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(updateToolbar);


                // Update Navigation Drawer from main activity
                Intent signOut = new Intent(Config.CLEAR_USER_INFO);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(signOut);
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, loginFragment);
                //transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });
    }
}
