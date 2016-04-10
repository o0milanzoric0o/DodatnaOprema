package rs.dodatnaoprema.dodatnaoprema.customview.swipeable_layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class SwipeableLayoutFooter extends FrameLayout implements SwipeLoadMoreTrigger, SwipeTrigger {

    public SwipeableLayoutFooter(Context context) {
        this(context, null);
    }

    public SwipeableLayoutFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeableLayoutFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onLoadMore() {
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
