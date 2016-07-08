package rs.dodatnaoprema.dodatnaoprema.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import rs.dodatnaoprema.dodatnaoprema.R;

/**
 * ******************************
 * Created by milan on 7/8/2016.
 * ******************************
 */
public class InfoDialog {
    AlertDialog.Builder builder;

    private String dialogMessage;

    public InfoDialog(Context context) {
        builder = new AlertDialog.Builder(context);

        // Use the Builder class for convenient dialog construction
        builder.setTitle(R.string.info_title);
        builder.setMessage(dialogMessage);
    }

    public void setDialogMessage(String msg) {
        dialogMessage = msg;
        builder.setMessage(dialogMessage);
    }

    public void setPositiveButtonListener(DialogInterface.OnClickListener listener) {
        builder.setPositiveButton(R.string.OK, listener);
    }


    public Dialog create() {
        // Create the AlertDialog object and return
        return builder.create();
    }
}
