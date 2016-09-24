package com.a2task.a2task.adapter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.a2task.a2task.R;
import com.a2task.a2task.databases.DataBaseNote;
import com.a2task.a2task.dto.RemindDTO;

import java.util.List;

/**
 * Created by 333da on 16.07.2016.
 */
public class RemindListAdapter extends RecyclerView.Adapter<RemindListAdapter.RemindViewHoldre> {

    private List<RemindDTO> data;

    public RemindListAdapter(List<RemindDTO> data) {
        this.data = data;
    }

    @Override
    public RemindViewHoldre onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remind_item, parent, false);
        return new RemindViewHoldre(view);
    }

    @Override
    public void onBindViewHolder(final RemindViewHoldre holder, int position) {
        holder.id = data.get(position).getID();
        holder.title.setTitle(data.get(position).getTitle());


        holder.text_note.setText(data.get(position).getText());

        holder.type.setText(data.get(position).getType());
        holder.date.setText(data.get(position).getDate());

        switch (data.get(position).getType()) {
            case "birthday":
                holder.type.setTextColor(Color.RED);
                break;
            case "task":
                holder.type.setTextColor(Color.BLUE);
                break;
            case "idea":
                holder.type.setTextColor(Color.GREEN);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  static class RemindViewHoldre extends RecyclerView.ViewHolder {
        CardView cardView;
        Toolbar title;
        TextView text_note;
        TextView type;
        TextView date;
        int id;
        DataBaseNote dbNote;

        public RemindViewHoldre(final View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (Toolbar) itemView.findViewById(R.id.card_toolbar);

            dbNote = new DataBaseNote(itemView.getContext());
            title.inflateMenu(R.menu.menu_cardview);
            title.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getItemId() == R.id.actionDeleteItem) {
                        SQLiteDatabase database = dbNote.getWritableDatabase();

                        ContentValues contentValues = new ContentValues();
//
                        database.delete(DataBaseNote.TABLE_TASK, DataBaseNote.KEY_ID +" = " + id, null);
                        dbNote.close();
                    }
                    return false;
                }
            });


            text_note = (TextView) itemView.findViewById(R.id.text_note);
            type = (TextView) itemView.findViewById(R.id.type);
            date = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
