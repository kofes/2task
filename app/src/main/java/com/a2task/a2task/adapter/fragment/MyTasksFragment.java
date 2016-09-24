package com.a2task.a2task.adapter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a2task.a2task.R;

/**
 * Created by 333da on 16.07.2016.
 */
public class MyTasksFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_example;

    public static MyTasksFragment getInstance(Context context) {

        Bundle args = new Bundle();
        MyTasksFragment fragment = new MyTasksFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_my_tasks));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
