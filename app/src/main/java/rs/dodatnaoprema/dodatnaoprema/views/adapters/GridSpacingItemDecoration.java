package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mSpacing;
    private int mHeaderNumber;

    public GridSpacingItemDecoration(int spanCount, int spacing, int headerNumber) {
        this.mSpanCount = spanCount;
        this.mSpacing = spacing;
        this.mHeaderNumber = headerNumber;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view) - mHeaderNumber;

        if (position >= 0) {
            int column = position % mSpanCount;
            outRect.left = column * mSpacing / mSpanCount;
            outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount;
            if (position < mSpanCount) {
                outRect.top = mSpacing;
            }
            outRect.bottom = mSpacing;
        } else {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
        }
    }
}
