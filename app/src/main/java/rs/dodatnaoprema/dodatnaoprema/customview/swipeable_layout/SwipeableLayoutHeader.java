package rs.dodatnaoprema.dodatnaoprema.customview.swipeable_layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * ******************************
 * Created by Win 7 on 8.4.2016.
 * ******************************
 */
public class SwipeableLayoutHeader extends FrameLayout implements SwipeRefreshTrigger, SwipeTrigger {

    public SwipeableLayoutHeader(Context context) {
        this(context, null);
    }

    public SwipeableLayoutHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeableLayoutHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onSwipe(int y, boolean isComplete) {
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onReset() {
    }
}
