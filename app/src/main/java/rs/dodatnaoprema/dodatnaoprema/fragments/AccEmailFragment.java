package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccEmailFragment extends Fragment {

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private String email;
    private EditText ed_email;
    private Bundle args;

    public AccEmailFragment() {
        // Required empty public constructor
    }

    public static AccEmailFragment newInstance(Toolbar toolbar, FloatingActionButton fab, Bundle bundle) {
        AccEmailFragment f = new AccEmailFragment();
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
        View view = inflater.inflate(R.layout.fragment_acc_email, container, false);

        args = getArguments();

        ed_email = (EditText) view.findViewById(R.id.et_email);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO field validation
                email = ed_email.getText().toString();
                if (email.trim().length() > 0) {
                    args.putString("email", email);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    AccPassFragment accPassFragment = AccPassFragment.newInstance(toolbar, fab, args);
                    fragmentTransaction.replace(R.id.fragment_container, accPassFragment);
                    fragmentTransaction.commit();
                } else {
                    // show snackbar to enter credentials
                    Snackbar.make(fab, "Morate uneti email adresu.", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });

        accEmailToolbar();
        fab.setImageDrawable(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.ARROW_RIGHT, R.color.colorTextWhite));
        fab.show();

        return view;
    }

    private void accEmailToolbar() {
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
                        AccNameFragment accNameFragment = AccNameFragment.newInstance(toolbar, fab);
                        fragmentTransaction.replace(R.id.fragment_container, accNameFragment);
                        fragmentTransaction.commit();
                    }
                }
        );

        TextView tv = (TextView) toolbar.findViewById(R.id.tv_toolbar_txt);
        tv.setText("");
    }
}
