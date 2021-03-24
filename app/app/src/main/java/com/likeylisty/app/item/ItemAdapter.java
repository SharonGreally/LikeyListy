package com.likeylisty.app.item;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.likeylisty.app.R;
import com.likeylisty.app.database.DatabaseDetails;
import com.likeylisty.app.database.DatabaseHelper;
import com.likeylisty.app.refreshAdapter;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

  private Context context;
  private Cursor cursor;
  private refreshAdapter refreshAdap;


  public ItemAdapter(Context context, Cursor cursor, refreshAdapter refreshAdap) {
    this.context = context;
    this.cursor = cursor;
    this.refreshAdap = refreshAdap;
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder {

    public CheckBox itemCheckBox;
    public ImageButton itemMenu;

    public ItemViewHolder(@NonNull View itemView) {
      super(itemView);

      itemCheckBox = itemView.findViewById(R.id.item_checkbox);
      itemMenu = itemView.findViewById(R.id.item_menu);
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

    String itemText = cursor
        .getString(cursor.getColumnIndex(DatabaseDetails.Item.COLUMN_ITEM_TEXT));
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
        refreshAdap.refreshAdapter();
      }
    });



    holder.itemMenu.setOnClickListener(v -> {
      PopupMenu popup = new PopupMenu(context, v);
      popup.inflate(R.menu.pop_menu);
      popup.show();
      popup.setOnMenuItemClickListener(item -> {
        switch (item.getItemId()) {
          case R.id.edit_item:

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Enter New Note:");
            builder.setMessage("Old note: " + itemText);

            final EditText input = new EditText(context);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                String itemText = "";
                itemText = input.getText().toString();

                DatabaseHelper dbHelper = new DatabaseHelper(context);
                dbHelper.updateItem(itemId, itemText);
                refreshAdap.refreshAdapter();

              }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
              }
            });
            builder.show();


            return true;
          case R.id.delete_item:
            DatabaseHelper dbHelper = new DatabaseHelper(context);
            dbHelper.deleteItem(itemId);
            refreshAdap.refreshAdapter();
            return true;
          default:
            return false;
        }
      });
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
