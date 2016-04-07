package rs.dodatnaoprema.dodatnaoprema.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import rs.dodatnaoprema.dodatnaoprema.R;

/**
 * Created by milan on 4/7/2016.
 */
public class CustomGridLayout extends RelativeLayout {
    private int paddingVertical;
    private int paddingHorizontal;

    public CustomGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.FlowLayout, 0, 0);
        paddingVertical = a.getInteger(R.styleable.FlowLayout_paddingVertical, 10);
        paddingHorizontal = a.getInteger(R.styleable.FlowLayout_paddingHorizontal, 10);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Figure out available size for laying children
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        int lineHeight = 0;
        // 100 is a dummy number, widthMeasureSpec should always be EXACTLY for FlowLayout
        int availableWidth = resolveSize(100, widthMeasureSpec);   // Take available width, that is set exactly
        // So don't use wrap_content for layout_width
        int wantedHeight = 0;

        // Assume all children are same width
        int number_of_children = 0;
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            } else {
                number_of_children++; // find the number of visible children
            }
        }
        if (number_of_children > 0) {
            View child = getChildAt(0); // get the first child, assume all have the same width
            // let the child measure itself
            child.measure(
                    getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(),
                            child.getLayoutParams().width),
                    getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(),
                            child.getLayoutParams().height));
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            int empty_space = (availableWidth - getPaddingLeft() - getPaddingRight() + paddingHorizontal) % (childWidth + paddingHorizontal);
            int max_num = (availableWidth - getPaddingLeft() - getPaddingRight() + paddingHorizontal) / (childWidth + paddingHorizontal);
            int new_child_width = 0;
            int columns = 0;
            if (empty_space >= childWidth/2){
                // shrink and fit in one more child
                columns = max_num + 1;
            }else{
                // expand
                columns = max_num;
            }
            new_child_width = (availableWidth - getPaddingLeft() - getPaddingRight() + paddingHorizontal)/columns - paddingHorizontal;

            child.getLayoutParams().width = new_child_width;

        }


        // lineheight is the height of current line, should be the height of the heightest view
//        lineHeight = Math.max(childHeight, lineHeight);
//        if (childWidth + childLeft + getPaddingRight() > availableWidth) {
//            // wrap this line
//            childLeft = getPaddingLeft();
//            childTop += paddingVertical + lineHeight;
//            lineHeight = childHeight;
//        }
//        childLeft += childWidth + paddingHorizontal;
//
//        wantedHeight += childTop + lineHeight + getPaddingBottom();
//        setMeasuredDimension(availableWidth, resolveSize(wantedHeight + 20, heightMeasureSpec));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
