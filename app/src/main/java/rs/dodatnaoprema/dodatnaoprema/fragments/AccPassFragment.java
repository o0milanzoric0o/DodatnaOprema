package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AccPassFragment extends Fragment {

    private TextView tv_passToggle;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private EditText ed_pass;
    private String name;
    private String last_name;
    private String email;
    private String pass;
    private Bundle args;


    private SignInCallbackInterface mCallback;

    public AccPassFragment() {
        // Required empty public constructor
    }

    public static AccPassFragment newInstance(Toolbar toolbar, FloatingActionButton fab, Bundle bundle) {
        AccPassFragment f = new AccPassFragment();
        // TODO do we need to pass some args here?
        f.fab = fab;
        f.toolbar = toolbar;
        f.setArguments(bundle);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_acc_pass, container, false);
        ed_pass = (EditText) view.findViewById(R.id.et_password);

        tv_passToggle = (TextView) view.findViewById(R.id.tv_pass_toggle_label);
        tv_passToggle.setTag(0);
        tv_passToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((Integer) view.getTag() == 0) {
                    tv_passToggle.setText(R.string.pass_toggle_label_hide);
                    tv_passToggle.setTag(1);
                    ed_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ed_pass.setSelection(ed_pass.getText().length());
                } else if ((Integer) view.getTag() == 1) {
                    tv_passToggle.setText(R.string.pass_toggle_label_show);
                    tv_passToggle.setTag(0);
                    ed_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ed_pass.setSelection(ed_pass.getText().length());
                }
            }
        });


        args = getArguments();
        name = args.getString("name");
        last_name = args.getString("last_name");
        email = args.getString("email");

        // Inflate the layout for this fragment
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO validate password
                pass = ed_pass.getText().toString();
                if (pass.trim().length() >= 8) {
                    mCallback.onCreateAccClick(name, last_name, email, pass);
                } else {
                    // show snackbar to enter credentials
                    Snackbar.make(fab, "Šifra nema dovoljno karaktera.", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });

        accPassToolbar();
        fab.setImageDrawable(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.ARROW_RIGHT, R.color.colorTextWhite));
        fab.show();

        // Inflate the layout for this fragment
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

    private void accPassToolbar() {
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.ARROW_LEFT, R.color.colorTextWhite));
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyApplication.hideKeyboard(getActivity());
                        //showSignInFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Show signIn fragment
                        AccEmailFragment accEmailFragment = AccEmailFragment.newInstance(toolbar, fab, args);
                        fragmentTransaction.replace(R.id.fragment_container, accEmailFragment);
                        fragmentTransaction.commit();
                    }
                }
        );

        TextView tv = (TextView) toolbar.findViewById(R.id.tv_toolbar_txt);
        tv.setText("");
    }
}
