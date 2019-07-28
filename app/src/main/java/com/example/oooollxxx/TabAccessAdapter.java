package com.example.oooollxxx;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAccessAdapter extends FragmentPagerAdapter {
    public TabAccessAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 2:
                return new RequestFragment();
            case 1:
                return  new ChatFragment();
            case 0:
                return new FeedFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Feed";
            case 1:
                return  "Buyers";
            case 2:
                return "Chat";

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
