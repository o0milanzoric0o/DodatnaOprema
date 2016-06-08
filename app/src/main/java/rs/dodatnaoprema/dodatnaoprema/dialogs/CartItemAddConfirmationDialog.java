package rs.dodatnaoprema.dodatnaoprema.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import rs.dodatnaoprema.dodatnaoprema.R;


/**
 * Created by 1 on 1/28/2016.
 */
public class CartItemAddConfirmationDialog {
    AlertDialog.Builder builder;

    public CartItemAddConfirmationDialog(Context context) {
        builder = new AlertDialog.Builder(context);

        // Use the Builder class for convenient dialog construction
        builder.setTitle(R.string.add_confirmation_title);
        builder.setMessage(R.string.add_confirmation_question);
    }

    public void setPositiveButtonListener(DialogInterface.OnClickListener listener) {
        builder.setPositiveButton(R.string.go_to_cart, listener);
    }

    public void setNegativeButtonListener(DialogInterface.OnClickListener listener) {
        builder.setNegativeButton(R.string.continue_shopping, listener);
    }

    public Dialog create() {
        // Create the AlertDialog object and return
        return builder.create();
    }
}
