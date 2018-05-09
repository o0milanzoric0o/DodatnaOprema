package rs.dodatnaoprema.dodatnaoprema.customview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

public class GridLayoutManagerAutoMeasure extends GridLayoutManager {
    public GridLayoutManagerAutoMeasure(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public GridLayoutManagerAutoMeasure(Context context, int spanCount) {
        super(context, spanCount);
    }

    public GridLayoutManagerAutoMeasure(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public boolean isAutoMeasureEnabled() {
        return true;
    }
}
