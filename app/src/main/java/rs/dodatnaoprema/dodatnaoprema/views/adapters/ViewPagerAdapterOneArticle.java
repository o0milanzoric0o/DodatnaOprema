package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.fragments.OneArticleTabOne;
import rs.dodatnaoprema.dodatnaoprema.fragments.OneArticleTabTwo;

/**
 * Created by Mirna on 4.5.2016..
 */
public class ViewPagerAdapterOneArticle extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerAdapterOneArticle(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                OneArticleTabOne tab1 = new OneArticleTabOne();
                Log.logInfo("LALALA.....", "1");
                return tab1;
            case 1:
                OneArticleTabTwo tab2 = new OneArticleTabTwo();
                Log.logInfo("LALALA.....", "2");
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
