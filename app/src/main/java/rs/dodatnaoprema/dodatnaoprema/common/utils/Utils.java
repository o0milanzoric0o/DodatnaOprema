package rs.dodatnaoprema.dodatnaoprema.common.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

/**
 * Created by 1 on 9/2/2016.
 */
public class Utils {
    public static Drawable getMaterialIconDrawable(Context context, MaterialDrawableBuilder.IconValue iconValue, int colorID){
        Drawable drawable = MaterialDrawableBuilder.with(context) // provide a context
                .setIcon(iconValue) // provide an icon
                .setColor(ContextCompat.getColor(context, colorID)) // set the icon color
                .setToActionbarSize() // set the icon size
                .build(); // Finally call build
        return drawable;
    }
}
