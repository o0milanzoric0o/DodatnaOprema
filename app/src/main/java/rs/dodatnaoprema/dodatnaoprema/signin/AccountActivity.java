package rs.dodatnaoprema.dodatnaoprema.signin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import rs.dodatnaoprema.dodatnaoprema.Interface.SignInCallbackInterface;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.application.SessionManager;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.dialogs.InfoDialog;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.fcm.MyPreferenceManager;
import rs.dodatnaoprema.dodatnaoprema.fragments.AccDetailsFragment;
import rs.dodatnaoprema.dodatnaoprema.fragments.AccNameFragment;
import rs.dodatnaoprema.dodatnaoprema.fragments.LoginFragment;
import rs.dodatnaoprema.dodatnaoprema.fragments.SignInFragment;
import rs.dodatnaoprema.dodatnaoprema.models.User;

public class AccountActivity extends AppCompatActivity implements SignInCallbackInterface {
    ProgressDialogCustom progressDialog;
    MyPreferenceManager prefs;
    private SessionManager session;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialogCustom(this);
        progressDialog.setCancelable(false);

        prefs = MyApplication.getInstance().getPrefManager();

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
                MyApplication.hideKeyboard(this);
                AccDetailsFragment accDetailsFragment = AccDetailsFragment.newInstance(toolbar, fab);

                // In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments
                accDetailsFragment.setArguments(getIntent().getExtras());

                // Add the fragment to the 'fragment_container' FrameLayout
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, accDetailsFragment).commit();
            } else {// Show login fragment

//                // Create a new Fragment to be placed in the activity layout
//                LoginFragment loginFragment = new LoginFragment();
//
//                // In case this activity was started with special instructions from an
//                // Intent, pass the Intent's extras to the fragment as arguments
//                loginFragment.setArguments(getIntent().getExtras());
//
//                // Add the fragment to the 'fragment_container' FrameLayout
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.fragment_container, loginFragment).commit();
                // Show sign in fragment
                MyApplication.hideKeyboard(this);
                SignInFragment signInFragment = SignInFragment.newInstance(toolbar, fab);
                signInFragment.setArguments(getIntent().getExtras());
                // Add the fragment to the 'fragment_container' FrameLayout
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, signInFragment).commit();
            }
        }
    }

    @Override
    public void onHaveAccClick() {
        MyApplication.hideKeyboard(this);
        LoginFragment loginFragment = LoginFragment.newInstance(toolbar, fab);
        loginFragment.setArguments(getIntent().getExtras());
        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, loginFragment).commit();
    }

    @Override
    public void onCreateNewAccClick() {
        MyApplication.hideKeyboard(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Show signIn fragment
        AccNameFragment accNameFragment = AccNameFragment.newInstance(toolbar, fab);
        fragmentTransaction.replace(R.id.fragment_container, accNameFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateAccClick(String name, String last_name, String email, String pass) {
        registerUser(name, last_name, email, pass);
    }

    @Override
    public void onLogInClick(String email, String pass) {
        checkLogin(email, pass);
    }

    @Override
    public void onForgotPassClick(String email) {

    }

    @Override
    public void onChangeUserData() {
        if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
            updateUserData();
        } else {
            showSnack("Morate se prvo prijaviti.");
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

                        // collect user data
                        JSONObject jObj_cart_count = jObj.getJSONObject("artUkorpi");
                        int cart_count = jObj_cart_count.getInt("ukupnaKolicina");

                        MyApplication.getInstance().getSessionManager().setCartItemCount(cart_count);
                        // Inform the rest of the change
                        Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
                        LocalBroadcastManager.getInstance(AccountActivity.this).sendBroadcast(updateToolbar);

                        // Update Navigation Drawer from main activity
                        Intent loginSuccess = new Intent(Config.SET_USER_INFO);
                        LocalBroadcastManager.getInstance(AccountActivity.this).sendBroadcast(loginSuccess);
                        MyApplication.hideKeyboard(AccountActivity.this);
                        // Launching  user account fragment
                        Fragment accountDetailsFragment = AccDetailsFragment.newInstance(toolbar, fab);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack
                        fragmentTransaction.replace(R.id.fragment_container, accountDetailsFragment);
                        //transaction.addToBackStack(null);
                        MyApplication.hideKeyboard(AccountActivity.this);
                        // Commit the transaction
                        fragmentTransaction.commit();

                    } else {
                        // login error
                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(AccountActivity.this,
//                                errorMsg, Toast.LENGTH_LONG).show();
                        Snackbar.make(fab, errorMsg, Snackbar.LENGTH_LONG)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AccountActivity.this,
                        error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Post params to login url
                Map<String, String> params = new HashMap<>();

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

    /*
function to register user details in mysql database
 */
    private void registerUser(final String name, final String last_name, final String email,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        progressDialog.showDialog("Registracija...");
        String url = "";
        try {
            //String email_enc = URLEncoder.encode(email, "utf-8");
            //String pass_enc = URLEncoder.encode(password, "utf-8");
            String name_enc = URLEncoder.encode(name, "utf-8");
            String last_name_enc = URLEncoder.encode(last_name, "utf-8");
            url = String.format(AppConfig.URL_REGISTER_GET, "register", email, password, name_enc, last_name_enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.hideDialog();

                try {
                    Log.logInfo("REGISTER_URL", response);
                    JSONObject jObj = new JSONObject(response);
//{"error_msg":"","tag":"register","error":false,"uid":33,"user":{"KomitentIme":"Milan","KomitentPrezime":"Milan","KomitentUserName":"a","email":"a@b.com","created_at":"2016-01-14 19:03:33"}} */

                    boolean success = jObj.getBoolean("success");
                    if (success) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("KomitentIme");
                        String last_name = user.getString("KomitentPrezime");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");


                        AlertDialog alertDialog = new AlertDialog.Builder(AccountActivity.this).create();
                        alertDialog.setTitle("Registracija novog korisnika");
                        alertDialog.setMessage("Registracija uspešna!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.OK),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        MyApplication.hideKeyboard(AccountActivity.this);
                                        // Launch login activity
                                        // Show login fragment
                                        // Create new fragment and transaction
                                        Fragment loginFragment = LoginFragment.newInstance(toolbar, fab);
                                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                                        // Replace whatever is in the fragment_container view with this fragment,
                                        // and add the transaction to the back stack
                                        transaction.replace(R.id.fragment_container, loginFragment);
                                        //transaction.addToBackStack(null);

                                        // Commit the transaction
                                        transaction.commit();
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        if (errorMsg.equals("")) errorMsg = "Greška u komunikaciji sa serverom!";
                        //Toast.makeText(getApplicationContext(),
                        //        errorMsg, Toast.LENGTH_LONG).show();
                        Snackbar.make(fab, errorMsg, Snackbar.LENGTH_LONG)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AccountActivity.this,
                        error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<>();
                params.put("action", "registrujAndroid");
                params.put("tag", "register");
                params.put("komitentime", name);
                params.put("komitentprezime", last_name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
        Log.logInfo("REGISTER_URL", strReq.getUrl());
    }

    private void showSnack(String msg) {
        Snackbar.make(fab, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void updateUserData() {
        String tag_string_req = "req_upate_user_data";
        progressDialog.showDialog(getString(R.string.progress_update_user_data));
        User user = MyApplication.getInstance().getPrefManager().getUser();
        final String p1 = user.getId();
        final String p2 = user.getGeneral_name();
        final String p3 = user.getName();
        final String p4 = user.getLast_name();
        final String p5 = user.getAddress();
        final String p6 = user.getZip_code();
        final String p7 = user.getCity();
        final String p8 = user.getPhone();
        final String p9 = user.getMobile();
        final String p10 = user.getEmail();
        final String p11 = user.getUserName();
        final String p12 = String.valueOf(user.getUserType());
        final String p13 = user.getUserFirmName();
        final String p14 = user.getFirmID();
        final String p15 = user.getFirmPIB();
        final String p16 = user.getFirmAddress();

        String url = String.format(AppConfig.URL_CHANGE_USER_DATA_GET, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);

                    boolean success = jObj.getBoolean("success");

                    if (success) {
                        InfoDialog infoDialog = InfoDialog.newInstance("", "Uspešno promenjeni podaci.");
                        infoDialog.show(getSupportFragmentManager(), "InfoDialog");
                    } else {
                        InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Nije uspela izmena podataka.");
                        infoDialog.show(getSupportFragmentManager(), "InfoDialog");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Nije uspelo slanje podataka.");
                    infoDialog.show(getSupportFragmentManager(), "InfoDialog");
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hideDialog();
                InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Proverite internet konekciju.");
                infoDialog.show(getSupportFragmentManager(), "InfoDialog");
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Post params
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", p1);
                params.put("KomitentNaziv", p2);
                params.put("KomitentIme", p3);
                params.put("KomitentPrezime", p4);
                params.put("KomitentAdresa", p5);
                params.put("KomitentPosBroj", p6);
                params.put("KomitentMesto", p7);
                params.put("KomitentTelefon", p8);
                params.put("KomitentMobTel", p9);
                params.put("email", p10);
                params.put("KomitentUserName", p11);
                params.put("KomitentTipUsera", p12);
                params.put("KomitentFirma", p13);
                params.put("KomitentMatBr", p14);
                params.put("KomitentPIB", p15);
                params.put("KomitentFirmaAdresa", p16);
                return params;
            }

        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_TIMEOUT_MS, AppConfig.DEFAULT_MAX_RETRIES, AppConfig.DEFAULT_BACKOFF_MULT));
        // Adding request to  queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
