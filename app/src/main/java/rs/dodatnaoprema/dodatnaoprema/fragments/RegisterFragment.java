package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.application.SessionManager;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    TextView tvLogin;
    TextInputLayout fullName;
    TextInputLayout emailRegister;
    TextInputLayout passwordRegister;
    EditText etFullName;
    EditText etLastName;
    EditText etEmailRegister;
    EditText etPasswordRegister;
    Button registerButton;

    SessionManager session;

    private ProgressDialogCustom pDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

//initializing Views
        registerButton = (Button) view.findViewById(R.id.register_button);
        fullName = (TextInputLayout) view.findViewById(R.id.fullname_registerlayout);
        emailRegister = (TextInputLayout) view.findViewById(R.id.email_registerlayout);
        passwordRegister = (TextInputLayout) view.findViewById(R.id.password_registerlayout);
        etFullName = (EditText) view.findViewById(R.id.fullname_register);
        etLastName = (EditText) view.findViewById(R.id.lastname_register);
        etEmailRegister = (EditText) view.findViewById(R.id.email_register);
        etPasswordRegister = (EditText) view.findViewById(R.id.password_register);
        tvLogin = (TextView) view.findViewById(R.id.tv_signin);

        //setting toolbar
//        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolBar);

        tvLogin.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        // Progress dialog
        pDialog = new ProgressDialogCustom(getActivity());
        pDialog.setCancelable(false);

        // Session manager
        session = MyApplication.getInstance().getSessionManager();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_signin:
                // Show login fragment
                // Create new fragment and transaction
                Fragment loginFragment = new LoginFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, loginFragment);
                //transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                break;
            case R.id.register_button:
                String name = etFullName.getText().toString();
                String last_name = etLastName.getText().toString();
                String email = etEmailRegister.getText().toString();
                String password = etPasswordRegister.getText().toString();
                /* TODO  Make better filed validation. Name can't contain spaces and/or UTF-8 letters.*/
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !last_name.isEmpty()) {
                    registerUser(name, last_name, email, password);
                } else {
                    showSnack("Unesite podatke!");
                }
                break;
        }
    }

    /*
    function to register user details in mysql database
     */
    private void registerUser(final String name, final String last_name, final String email,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.showDialog("Registracija...");
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
                pDialog.hideDialog();

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


                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setTitle("Registracija novog korisnika");
                        alertDialog.setMessage("Registracija uspešna!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.OK),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Launch login activity
                                        // Show login fragment
                                        // Create new fragment and transaction
                                        Fragment loginFragment = new LoginFragment();
                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();

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
                        showSnack(errorMsg);
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
                pDialog.hideDialog();
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
        Snackbar.make(registerButton, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
