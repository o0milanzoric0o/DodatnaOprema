package rs.dodatnaoprema.dodatnaoprema.signin;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.SessionManager;
import rs.dodatnaoprema.dodatnaoprema.fragments.AccountDetailsFragment;
import rs.dodatnaoprema.dodatnaoprema.fragments.LoginFragment;

public class AccountActivity extends FragmentActivity {
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            session = new SessionManager(getApplicationContext());

            //If the session is logged in move to MainActivity
            if (session.isLoggedIn()) {
                // Show user details fragment
                // Create a new Fragment to be placed in the activity layout
                AccountDetailsFragment accountDetailsFragment = new AccountDetailsFragment();

                // In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments
                accountDetailsFragment.setArguments(getIntent().getExtras());

                // Add the fragment to the 'fragment_container' FrameLayout
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, accountDetailsFragment).commit();
            } else {// Show login fragment

                // Create a new Fragment to be placed in the activity layout
                LoginFragment loginFragment = new LoginFragment();

                // In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments
                loginFragment.setArguments(getIntent().getExtras());

                // Add the fragment to the 'fragment_container' FrameLayout
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, loginFragment).commit();
            }
        }


    }
}
