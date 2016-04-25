package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.application.SessionManager;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.gcm.MyPreferenceManager;
import rs.dodatnaoprema.dodatnaoprema.models.User;


public class LoginFragment extends Fragment implements View.OnClickListener {
    Button registerHere;
    Button signIn;
    TextInputLayout emailLogin;
    TextInputLayout passwordLogin;
    EditText etEmailLogin;
    EditText etPasswordLogin;

    ProgressDialogCustom progressDialog;
    MyPreferenceManager prefs;
    private SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        registerHere = (Button) view.findViewById(R.id.registerhere_button);
        signIn = (Button) view.findViewById(R.id.signin_button);
        emailLogin = (TextInputLayout) view.findViewById(R.id.email_loginlayout);
        passwordLogin = (TextInputLayout) view.findViewById(R.id.password_loginlayout);
        etEmailLogin = (EditText) view.findViewById(R.id.email_login);
        etPasswordLogin = (EditText) view.findViewById(R.id.password_login);
        //setting onclick listeners
        registerHere.setOnClickListener(this);
        signIn.setOnClickListener(this);

        // Progress dialog
        progressDialog = new ProgressDialogCustom(getActivity());
        progressDialog.setCancelable(false);


        prefs = MyApplication.getInstance().getPrefManager();
        session = MyApplication.getInstance().getSessionManager();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //on clicking register button move to Register Activity
            case R.id.registerhere_button:
                // Show register fragment
                // Create new fragment and transaction
                Fragment registerFragment = new RegisterFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, registerFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                break;

            //on clicking the signin button check for the empty field then call the checkLogin() function
            case R.id.signin_button:
                String email = etEmailLogin.getText().toString();
                String password = etPasswordLogin.getText().toString();

                // Check for empty data
                if (email.trim().length() > 0 && password.trim().length() > 0) {
                    // login user
                    // Make a call to server to login user

                    checkLogin(email, password);
                } else {
                    // show snackbar to enter credentials
                    Snackbar.make(v, "Unesite podatke!", Snackbar.LENGTH_LONG)
                            .show();
                }
                break;
        }
    }

    /**
     * function to verify login details
     */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        progressDialog.showDialog("Povezivanje...");

        String url = String.format(AppConfig.URL_LOGIN_GET, "login", email, password);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);

                    //String userId= jObj.getString("uid");

                    boolean success = jObj.getBoolean("success");

                    if (success) {
                        // user successfully logged in
                        // Create login session
                        String uid = jObj.getString("uid");

                        session.setLogin(true);

                        // collect user data
                        JSONObject jObj_user_data = jObj.getJSONObject("user");

                        // Save user data
                        User user = new User();

                        user.setId(uid);
                        user.setGeneral_name(jObj_user_data.getString("KomitentNaziv"));
                        user.setName(jObj_user_data.getString("KomitentIme"));
                        user.setLast_name(jObj_user_data.getString("KomitentPrezime"));
                        user.setAddress(jObj_user_data.getString("KomitentAdresa"));
                        user.setZip_code(jObj_user_data.getString("KomitentPosBroj"));
                        user.setCity(jObj_user_data.getString("KomitentMesto"));
                        user.setPhone(jObj_user_data.getString("KomitentTelefon"));
                        user.setMobile(jObj_user_data.getString("KomitentMobTel"));
                        user.setEmail(jObj_user_data.getString("KomitentEmail"));
                        user.setUserName(jObj_user_data.getString("KomitentUserName"));
                        user.setUserType(String.valueOf(jObj_user_data.getInt("KomitentTipUsera")));
                        user.setUserFirmName(jObj_user_data.getString("KomitentFirma"));
                        user.setFirmID(jObj_user_data.getString("KomitentMatBr"));
                        user.setFirmPIB(jObj_user_data.getString("KomitentPIB"));
                        user.setFirmAddress(jObj_user_data.getString("KomitentFirmaAdresa"));
                        prefs.storeUser(user);
                        // Launching  user account fragment
                        /**TODO
                         * Launch fragment to display user data
                         */

                    } else {
                        // login error
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getActivity(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Post params to login url
                Map<String, String> params = new HashMap<String, String>();

                params.put("action", "povuciPodatkeAndroidKorisnik");
                params.put("tag", "login");
                params.put("email", email);
                params.put("password", password);
                return params;
            }

        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));

        // Adding request to  queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
