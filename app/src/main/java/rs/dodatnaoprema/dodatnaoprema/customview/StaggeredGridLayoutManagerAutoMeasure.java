package rs.dodatnaoprema.dodatnaoprema.customview;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

public class StaggeredGridLayoutManagerAutoMeasure extends StaggeredGridLayoutManager {
    public StaggeredGridLayoutManagerAutoMeasure(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public StaggeredGridLayoutManagerAutoMeasure(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override
    public boolean isAutoMeasureEnabled() {
        return true;
    }
}
