package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import rs.dodatnaoprema.dodatnaoprema.AllCategoriesActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;

public class DeleteHistoryDialog extends DialogFragment {

    private Button no;
    private Button yes;
    private boolean clickedNo = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.history_delete_dialog, null);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        no = (Button) view.findViewById(R.id.cancelBtn);
        yes = (Button) view.findViewById(R.id.deleteBtn);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.clearSharedPreferences(getActivity(), AppConfig.HISTORY_KEY);
                dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedNo = true;
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (!clickedNo) {
            AllCategoriesActivity activity = (AllCategoriesActivity) getActivity();
            activity.populateRecyclerView();
        }
    }

}
