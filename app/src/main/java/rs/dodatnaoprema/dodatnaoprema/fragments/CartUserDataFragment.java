package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rs.dodatnaoprema.dodatnaoprema.MainActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.application.MyApplication;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.dialogs.ProgressDialogCustom;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.dialogs.InfoDialog;
import rs.dodatnaoprema.dodatnaoprema.fcm.Config;
import rs.dodatnaoprema.dodatnaoprema.models.OfflineCart;
import rs.dodatnaoprema.dodatnaoprema.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartUserDataFragment extends Fragment {

    private static String PARAM1 = "param1";
    private EditText et_name;
    private EditText et_last_name;
    private EditText et_email;
    private EditText et_address;
    private EditText et_city;
    private EditText et_zip;
    private EditText et_mobile;
    private EditText et_phone;
    private EditText et_comment;
    private Button btn_next;
    private TextView tv_total;

    private String mtotal_price;
    private ProgressDialogCustom progressDialog;

    public CartUserDataFragment() {
        // Required empty public constructor
    }

    public static CartUserDataFragment newInstance(String total_price) {

        CartUserDataFragment f = new CartUserDataFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM1, total_price);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mtotal_price = (String) getArguments().getSerializable(PARAM1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_user_data, container, false);
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_last_name = (EditText) view.findViewById(R.id.et_last_name);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_address = (EditText) view.findViewById(R.id.et_shipping_address);
        et_city = (EditText) view.findViewById(R.id.et_city);
        et_zip = (EditText) view.findViewById(R.id.et_zip);
        et_mobile = (EditText) view.findViewById(R.id.et_mobile);
        et_comment = (EditText) view.findViewById(R.id.et_comment);
        et_phone = (EditText) view.findViewById(R.id.et_phone);
        tv_total = (TextView) view.findViewById(R.id.tv_total);
        btn_next = (Button) view.findViewById(R.id.btn_cart_buy);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    //user.setUserName();
//                    user.setUserType();
//                    user.setUserFirmName();
//                    user.setFirmID();
//                    user.setFirmPIB();
//                    user.setFirmAddress();
                    if (MyApplication.getInstance().getSessionManager().isLoggedIn()) {
                        // Make web requests to Change user data, and finish transaction
                        updateUserData();
                    } else {
                        completeTransactionLoggedOff();
                    }

                } else {
                    // show snackbar to enter credentials
                    Snackbar.make(v, "Morate uneti sve podatke!", Snackbar.LENGTH_LONG)
                            .show();
                }

            }

        });

        progressDialog = new ProgressDialogCustom(getContext());
        progressDialog.setCancelable(false);

        // Load user data and fill text views
        User user = MyApplication.getInstance().getPrefManager().getUser();

        if (user != null) {
            if (!user.getName().equals("null")) {
                et_name.setText(user.getName());
            }
            if (!user.getLast_name().equals("null")) {
                et_last_name.setText(user.getLast_name());
            }
            if (!user.getEmail().equals("null")) {
                et_email.setText(user.getEmail());
            }
            if (!user.getAddress().equals("null")) {
                et_address.setText(user.getAddress());
            }
            if (!user.getCity().equals("null")) {
                et_city.setText(user.getCity());
            }
            if (!user.getZip_code().equals("null")) {
                et_zip.setText(user.getZip_code());
            }
            if (!user.getMobile().equals("null")) {
                et_mobile.setText(user.getMobile());
            }
            if (!user.getPhone().equals("null")) {
                et_phone.setText(user.getPhone());
            }
        }
        tv_total.setText(mtotal_price);

        return view;
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


                        completeTransaction();
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Nije uspelo slanje podataka.");
                    infoDialog.show(getFragmentManager(), "InfoDialog");
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hideDialog();
                InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Proverite internet konekciju.");
                infoDialog.show(getFragmentManager(), "InfoDialog");
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

    private void completeTransaction() {
        String tag_string_req = "req_complete_transaction";
        progressDialog.showDialog(getString(R.string.progress_completing_transaction));
        User user = MyApplication.getInstance().getPrefManager().getUser();
        final String p1 = user.getId();

        String url = String.format(AppConfig.URL_COMPLETE_TRANSACTION, p1);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.hideDialog();
                Log.logInfo("COMPLETE TRANSACTION REQUEST", response);
                try {

                    JSONObject jObj = new JSONObject(response);

                    boolean success = jObj.getBoolean("success");

                    if (success) {
                        // Inform the user to check his email to confirm the order
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setTitle(getString(R.string.order_success_title));
                        alertDialog.setMessage(getString(R.string.order_success_message));
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        // Clear cart in user session on the phone
                                        // Set cart item count
                                        MyApplication.getInstance().getSessionManager().setCartItemCount(0);
                                        // notify application to update toolbar icon
                                        Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
                                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(updateToolbar);
                                        // Close cart activity...
                                        // Get back to home activity
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                });
                        alertDialog.show();
                    } else {
                        InfoDialog infoDialog = InfoDialog.newInstance("Greška", jObj.getString("error_msg"));
                        infoDialog.show(getFragmentManager(), "InfoDialog");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Nije uspelo pravljenje narudžbe.");
                    infoDialog.show(getFragmentManager(), "InfoDialog");
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hideDialog();
                InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Proverite internet konekciju.");
                infoDialog.show(getFragmentManager(), "InfoDialog");
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Post params
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", p1);
                return params;
            }

        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_TIMEOUT_MS, AppConfig.DEFAULT_MAX_RETRIES, AppConfig.DEFAULT_BACKOFF_MULT));
        // Adding request to  queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void completeTransactionLoggedOff() {
        // prepare json object that contains the data to send
        JSONObject jsonObject = new JSONObject();
        JSONArray cartItems = MyApplication.getInstance().getSessionManager().getCartItemsJsonArray();

        try {
            jsonObject.accumulate("KomitentIme", et_name.getText());
            jsonObject.accumulate("KomitentPrezime", et_last_name.getText());
            jsonObject.accumulate("KomitentAdresa", et_address.getText());
            jsonObject.accumulate("KomitentPosBroj", et_zip.getText());
            jsonObject.accumulate("KomitentMesto", et_city.getText());
            jsonObject.accumulate("KomitentTelefon", et_phone.getText());
            jsonObject.accumulate("KomitentMobTel", et_mobile.getText());
            jsonObject.accumulate("email", et_email.getText());
            jsonObject.accumulate("napomena", et_comment.getText());
            jsonObject.accumulate("valutaId", 1);
            jsonObject.accumulate("jezikId", 1);
            jsonObject.accumulate("korpa", cartItems);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String tag_string_req = "req_complete_tans_offline_user";
        progressDialog.showDialog(getString(R.string.progress_completing_transaction));

        String url = String.format(AppConfig.URL_COMPLETE_TRANSACTION_UNREGISTERED_USER);

        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                progressDialog.hideDialog();
                Log.logInfo("COMPLETE TRANSACTION REQUEST UNREG", response.toString());
                try {
                    boolean success = response.getBoolean("success");

                    if (success) {
                        // Inform the user to check his email to confirm the order
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setTitle(getString(R.string.order_success_title));
                        alertDialog.setMessage(getString(R.string.order_success_message));
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        // Clear offline cart
                                        OfflineCart offlineCart = MyApplication.getInstance().getPrefManager().loadOfflineCart();
                                        if (offlineCart != null) {
                                            offlineCart.clearCart();
                                            MyApplication.getInstance().getPrefManager().saveOfflineCart(offlineCart);
                                            MyApplication.getInstance().getSessionManager().setOfflineCartItemCount(offlineCart.getTotalQuantity());
                                        }
                                        // notify application to update toolbar icon
                                        Intent updateToolbar = new Intent(Config.UPDATE_CART_TOOLBAR_ICON);
                                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(updateToolbar);

                                        // Close cart activity...
                                        // Get back to home activity
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                });
                        alertDialog.show();
                    } else {
                        InfoDialog infoDialog = InfoDialog.newInstance("Greška", response.getString("error_msg"));
                        infoDialog.show(getFragmentManager(), "InfoDialog");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Nije uspelo pravljenje narudžbe.");
                    infoDialog.show(getFragmentManager(), "InfoDialog");
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hideDialog();
                InfoDialog infoDialog = InfoDialog.newInstance("Greška", "Proverite internet konekciju.");
                infoDialog.show(getFragmentManager(), "InfoDialog");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_TIMEOUT_MS * 2, AppConfig.DEFAULT_MAX_RETRIES * 0, AppConfig.DEFAULT_BACKOFF_MULT));
        // Adding request to  queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
}
