package rs.dodatnaoprema.dodatnaoprema.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import rs.dodatnaoprema.dodatnaoprema.customview.swipeable_layout.SwipeableLayoutFooter;

/**
 * ******************************
 * Created by Win 7 on 8.4.2016.
 * ******************************
 */
public class FirstTabFooter extends SwipeableLayoutFooter {
    private TextView tvLoadMore;
    private ImageView ivSuccess;

    // private FirstTabFooter footer;

    //  private int mFooterHeight;
    public FirstTabFooter(Context context) {
        this(context, null);
    }

    public FirstTabFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FirstTabFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //  mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height_classic);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //  footer= (FirstTabFooter) findViewById(R.id.swipe_load_more_footer);
        // tvLoadMore = (TextView) findViewById(R.id.footer_text);
        //  ivSuccess = (ImageView) findViewById(R.id.arrow_up);

    }

    @Override
    public void onPrepare() {

        //ivSuccess.setVisibility(GONE);
    }

    @Override
    public void onSwipe(int y, boolean isComplete) {
        //  if (!isComplete) {
        //    ivSuccess.setVisibility(GONE);
        //   if (-y >= mFooterHeight) {
        // tvLoadMore.setText("RELEASE TO LOAD MORE");
        //  } else {
        //  tvLoadMore.setText("SWIPE TO LOAD MORE");
        //  }
        //  }
    }

    @Override
    public void onLoadMore() {

        //footer.setVisibility(GONE);
        //tvLoadMore.setText("LOADING MORE");
    }

    @Override
    public void onRelease() {
        // footer.setVisibility(GONE);

    }

    @Override
    public void complete() {
        // ivSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void onReset() {
        //ivSuccess.setVisibility(GONE);
    }
}
