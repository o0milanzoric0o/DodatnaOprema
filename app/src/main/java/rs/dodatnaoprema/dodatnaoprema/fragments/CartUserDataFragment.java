package rs.dodatnaoprema.dodatnaoprema.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import rs.dodatnaoprema.dodatnaoprema.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartUserDataFragment extends Fragment {

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

    public CartUserDataFragment() {
        // Required empty public constructor
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
        et_phone = (EditText)view.findViewById(R.id.et_phone);

        return view;
    }

}
