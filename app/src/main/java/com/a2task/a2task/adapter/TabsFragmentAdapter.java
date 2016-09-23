package com.a2task.a2task.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.a2task.a2task.adapter.fragment.AbstractTabFragment;
import com.a2task.a2task.adapter.fragment.MyTasksFragment;
import com.a2task.a2task.adapter.fragment.AllTaskFragment;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by 333da on 16.07.2016.
 */
public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        tabs = new HashMap<>();
        tabs.put(0, AllTaskFragment.getInstance(context));
        tabs.put(1, MyTasksFragment.getInstance(context));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

}
