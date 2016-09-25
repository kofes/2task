package com.a2task.a2task;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.a2task.a2task.databases.DataBaseNote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class NewTaskActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int LAYOUT = R.layout.activity_new_note;

    private DataBaseNote dbNote;

    private Toolbar toolbar;

    Button btnOK, btnCancle;
    Button deleteAll;
    EditText etTitle, etText, etDate;
    RadioGroup rgType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initToolbar();

        dbNote = new DataBaseNote(this);
//        fillDataBase();

        btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);

        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnCancle.setOnClickListener(this);

        deleteAll = (Button) findViewById(R.id.deleteAll);
        deleteAll.setOnClickListener(this);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etText = (EditText) findViewById(R.id.etText);
        etDate = (EditText) findViewById(R.id.editDate);

        rgType = (RadioGroup) findViewById(R.id.rgType);

    }

    private void fillDataBase(){
        String info = "";
        String tableName = DataBaseNote.TABLE_TASK;

        SQLiteDatabase database = dbNote.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        try {
            JSONArray JSONinfo = new JSONArray(info);
            for(int i = 0;i < JSONinfo.length();i++){
                JSONObject jsonData = JSONinfo.getJSONObject(i);

                Iterator<?> keys = jsonData.keys();

                while( keys.hasNext() ) {
                    String key = (String)keys.next();
                    contentValues.put(key, jsonData.getString(key));
                }
                database.insert(tableName, null, contentValues);
                dbNote.close();
                finish();

            }
        }
        catch (JSONException e) {

        }
    }

    @Override
    public void onClick(View v) {

        //to control database
        SQLiteDatabase database = dbNote.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {

            case R.id.btnOK:
                contentValues.put(DataBaseNote.KEY_TITLE, etTitle.getText().toString());
                contentValues.put(DataBaseNote.KEY_NOTE, etText.getText().toString());
                contentValues.put(DataBaseNote.KEY_DOIT, false);
                contentValues.put(DataBaseNote.KEY_DATE, etDate.getText().toString());

                switch (rgType.getCheckedRadioButtonId()) {
                    case R.id.rIdea:
                        contentValues.put(DataBaseNote.KEY_TYPE, "idea");
                        break;
                    case R.id.rBirthday:
                        contentValues.put(DataBaseNote.KEY_TYPE, "birthday");
                        break;
                    case R.id.rTask:
                        contentValues.put(DataBaseNote.KEY_TYPE, "task");
                        break;
                }
                database.insert(DataBaseNote.TABLE_TASK, null, contentValues);
                dbNote.close();
                finish();
                break;
            case R.id.btnCancle:
                dbNote.close();
                finish();
                break;
            case R.id.deleteAll:
                database.delete(DataBaseNote.TABLE_TASK, null, null);
                dbNote.close();
                finish();
                break;
        }
        dbNote.close();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar_new_note);
        toolbar.setTitle(R.string.new_task);
    }
}
