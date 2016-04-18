package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.fragments.FirstTab;
import rs.dodatnaoprema.dodatnaoprema.fragments.SecondTab;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FirstTab tab1 = new FirstTab();
                return tab1;
            case 1:
                SecondTab tab2 = new SecondTab();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return AppConfig.TAB_NUMBER;
    }
}
