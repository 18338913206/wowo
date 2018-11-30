package com.example.rw17;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyVPAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> titleList;
    public MyVPAdapter(FragmentManager fm,List<Fragment> list,List<String> titleList) {
        super(fm);
        this.list=list;
        this.titleList=titleList;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
