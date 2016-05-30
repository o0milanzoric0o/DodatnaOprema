package rs.dodatnaoprema.dodatnaoprema.customview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import static rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig.FLING_SCALE_DOWN_FACTOR;

public class CustomRecyclerView extends RecyclerView {
    private int FLING_FACTOR = FLING_SCALE_DOWN_FACTOR;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {

        velocityY *= getFlingFactor();
        // velocityX *= 0.7; for Horizontal recycler view. comment velocityY line not require for Horizontal Mode.

        return super.fling(velocityX, velocityY);
    }

    private int getFlingFactor() {

        return FLING_FACTOR;
    }

    public void setFlingFactor(int factor) {

        FLING_FACTOR = factor;
    }

}
