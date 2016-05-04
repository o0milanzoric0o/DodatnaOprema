package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.gcm.Config;
import rs.dodatnaoprema.dodatnaoprema.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountDetailsFragment extends Fragment implements View.OnClickListener {
    Button signOut;
    TextView title;
    TextView status;
    TextView details;
    ImageView imageView;

    public AccountDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_details, container, false);
        signOut = (Button) view.findViewById(R.id.sign_out_button);
        signOut.setOnClickListener(this);
        imageView = (ImageView) view.findViewById(R.id.user_photo);
        title = (TextView) view.findViewById(R.id.title_text);
        status = (TextView) view.findViewById(R.id.status);
        details = (TextView) view.findViewById(R.id.detail);

        // Load user data and fill text views
        User user = MyApplication.getInstance().getPrefManager().getUser();
        if (user.getEmail() != null) {
            title.setText(user.getEmail());
        }

        if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
            status.setText(getString(R.string.text_logged_in));
        } else
            status.setText(getString(R.string.text_logged_out));

        if (user.getPhoto() != null) {
            imageView.setImageURI(user.getPhoto());
        }

        String det = "";
        if (user.getName() != null)
            det += user.getName();
        if (user.getLast_name() != null)
            det += " " + user.getLast_name() + "\n";
        else det += "\n";
        if (user.getAddress() != null)
            det += user.getAddress();

        details.setText(det);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_out_button:
                //Log out
                MyApplication.getInstance().getSessionManager().setLogin(false);
                // Switch to Login Fragment
                Fragment loginFragment = new LoginFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Update Navigation Drawer from main activity
                Intent signOut = new Intent(Config.CLEAR_USER_INFO);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(signOut);
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, loginFragment);
                //transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                break;
        }
    }
}
