package com.a2task.a2task.adapter.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by 333da on 16.07.2016.
 */
public class AbstractTabFragment extends Fragment {
    public String title;

    protected View view;
    protected Context context;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
