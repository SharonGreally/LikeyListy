package com.likeylisty.app.item;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.likeylisty.app.R;
import com.likeylisty.app.database.DatabaseDetails;
import com.likeylisty.app.database.DatabaseHelper;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context context;
    private Cursor cursor;
    
    public ItemAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public CheckBox itemCheckBox;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemCheckBox = itemView.findViewById(R.id.item_checkbox);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        String itemText = cursor.getString(cursor.getColumnIndex(DatabaseDetails.Item.COLUMN_ITEM_TEXT));
        int itemStatus = cursor.getInt(cursor.getColumnIndex(DatabaseDetails.Item.COLUMN_ITEM_STATUS));
        int itemId = cursor.getInt(cursor.getColumnIndex(DatabaseDetails.Item.COLUMN_ITEM_ID));

        holder.itemCheckBox.setText(itemText);
        holder.itemCheckBox.setOnCheckedChangeListener(null);
        holder.itemCheckBox.setChecked(toBoolean(itemStatus));
        holder.itemCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                dbHelper.updateItemStatus(itemId, itemStatus);
            }
        });
    }

    public boolean toBoolean(int num) {
        if (num == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }

        cursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
