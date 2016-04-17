package rs.dodatnaoprema.dodatnaoprema.customview;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rs.dodatnaoprema.dodatnaoprema.AllCategoriesActivity;
import rs.dodatnaoprema.dodatnaoprema.R;

public class DeleteHistoryDialog extends DialogFragment {

    private Button no;
    private Button yes;
    private boolean clickedNo = false;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.history_delete_dialog, null);

        no = (Button) view.findViewById(R.id.cancelBtn);
        yes = (Button) view.findViewById(R.id.deleteBtn);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = getActivity().getSharedPreferences("Kliknuo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.remove("Kliknuo");
                editor.apply();
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
