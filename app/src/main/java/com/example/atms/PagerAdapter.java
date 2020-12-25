package com.example.atms;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;


    public PagerAdapter(@NonNull FragmentManager fm,int numberOfTabs) {
        super(fm);
        this.numOfTabs=numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Available();
            case 1:
                return new NotAvailable();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
