package rs.dodatnaoprema.dodatnaoprema.customview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

public class LinearLayoutManagerAutoMeasure extends LinearLayoutManager {

    public LinearLayoutManagerAutoMeasure(Context context) {
        super(context);
    }

    public LinearLayoutManagerAutoMeasure(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public LinearLayoutManagerAutoMeasure(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean isAutoMeasureEnabled() {
        return true;
    }
}
