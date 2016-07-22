package rs.dodatnaoprema.dodatnaoprema.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.customview.NumberPicker;

/**
 * ******************************
 * Created by milan on 7/21/2016.
 * ******************************
 */
public class NumberPickerDialog extends DialogFragment {
    // Use this instance of the interface to deliver action events
    NumberPickerDialogListener mListener;
    private NumberPicker numberPicker;

    public static NumberPickerDialog newInstance(int min, int max) {
        NumberPickerDialog frag = new NumberPickerDialog();
        Bundle args = new Bundle();
        args.putInt("min", min);
        args.putInt("max", max);
        frag.setArguments(args);
        return frag;
    }

    public int getNumberPicked() {
        return numberPicker.getValue();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog_view = inflater.inflate(R.layout.number_picker_dialog_layout, null);
        numberPicker = (NumberPicker) dialog_view.findViewById(R.id.number_picker);
        numberPicker.setMinValue(getArguments().getInt("min"));
        numberPicker.setMaxValue(getArguments().getInt("max"));
        builder.setView(dialog_view).setTitle("Odaberite količinu").setCancelable(false)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        // call callback method
                        mListener.onNumberPickerDialogPositiveClick(NumberPickerDialog.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        mListener.onNumberPickerDialogNegativeClick(NumberPickerDialog.this);
                        NumberPickerDialog.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NumberPickerDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NumberPickerDialogListener {
        void onNumberPickerDialogPositiveClick(DialogFragment dialog);

        void onNumberPickerDialogNegativeClick(DialogFragment dialog);
    }
}