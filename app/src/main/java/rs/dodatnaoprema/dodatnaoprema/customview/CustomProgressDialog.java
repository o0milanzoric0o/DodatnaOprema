package rs.dodatnaoprema.dodatnaoprema.customview;


import android.app.ProgressDialog;
import android.content.Context;

public class CustomProgressDialog extends ProgressDialog {

    public CustomProgressDialog(Context context) {
        super(context);
    }
    /*
   function to show dialog
   */
    public void showDialog(String msg) {
        if (!isShowing()) {
            setMessage(msg);
            show();
        }
    }

    /*
    function to hide dialog
     */
    public void hideDialog() {
        if (isShowing())
            dismiss();
    }
}
