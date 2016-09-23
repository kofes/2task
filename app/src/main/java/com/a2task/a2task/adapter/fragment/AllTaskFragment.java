package com.a2task.a2task.adapter.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a2task.a2task.R;
import com.a2task.a2task.adapter.RemindListAdapter;
import com.a2task.a2task.databases.DataBaseNote;
import com.a2task.a2task.dto.RemindDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 333da on 16.07.2016.
 */
public class AllTaskFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_history;

    DataBaseNote dbNote;

    public static AllTaskFragment getInstance(Context context) {

        Bundle args = new Bundle();
        AllTaskFragment fragment = new AllTaskFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_all_tasks));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));

        List<RemindDTO> taskList = createData();

        rv.setAdapter(new RemindListAdapter(taskList));

        return view;
    }

    private List<RemindDTO> createData() {
        dbNote = new DataBaseNote(context);

        SQLiteDatabase database = dbNote.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        List<RemindDTO> data = new ArrayList<>();

        Cursor cursor = database.query(DataBaseNote.TABLE_TASK, null, null, null, null, null, null);
        if(cursor != null)
        if(cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DataBaseNote.KEY_ID);
            int titleIndex = cursor.getColumnIndex(DataBaseNote.KEY_TITLE);
            int textIndex = cursor.getColumnIndex(DataBaseNote.KEY_NOTE);
            int typeIndex = cursor.getColumnIndex(DataBaseNote.KEY_TYPE);
            int typeDate = cursor.getColumnIndex(DataBaseNote.KEY_DATE);
            do {
                data.add(new RemindDTO(
                        cursor.getInt(idIndex),
                        cursor.getString(titleIndex),
                        cursor.getString(textIndex),
                        cursor.getString(typeIndex),
                        cursor.getString(typeDate)
                ));
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();

        dbNote.close();
        return data;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new RemindListAdapter(createData()));

    }
}
