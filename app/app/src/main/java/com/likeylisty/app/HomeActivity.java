package com.likeylisty.app;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.likeylisty.app.database.DatabaseDetails;
import com.likeylisty.app.item.ItemAdapter;
import com.likeylisty.app.database.DatabaseHelper;
import com.likeylisty.app.item.Item;

public class HomeActivity extends Activity {

    private SQLiteDatabase db;
    private EditText itemTextInput;
    private Button itemAddButton;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        RecyclerView itemListView = findViewById(R.id.item_list_view);
        itemListView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(this, getAllItems());
        itemListView.setAdapter(adapter);

        itemTextInput = findViewById(R.id.item_text_input);
        itemAddButton = findViewById(R.id.item_add_button);

        String itemText = itemTextInput.getText().toString();

        itemAddButton.setOnClickListener(v -> dbHelper.createItem(itemText));

        adapter.swapCursor(getAllItems());

        itemTextInput.getText().clear();
    }

    public Cursor getAllItems() {
        return db.query(
                DatabaseDetails.Item.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DatabaseDetails.Item.COLUMN_ITEM_TIMESTAMP + " DESC"
        );
    }
}
