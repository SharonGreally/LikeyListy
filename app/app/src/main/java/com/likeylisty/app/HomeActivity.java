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

import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.likeylisty.app.database.DatabaseDetails;
import com.likeylisty.app.item.ItemAdapter;
import com.likeylisty.app.database.DatabaseHelper;
import com.likeylisty.app.item.Item;

public class HomeActivity extends Activity {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private EditText itemTextInput;
    private Button itemAddButton, displayListButton, hideListButton;
    private ItemAdapter adapter;
    private String itemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        RecyclerView itemListView = findViewById(R.id.item_list_view);
        itemListView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(this, getAllItems());
        itemListView.setAdapter(adapter);

        itemTextInput = findViewById(R.id.item_text_input);
        itemAddButton = findViewById(R.id.item_add_button);
        displayListButton = findViewById(R.id.display_button);
        hideListButton = findViewById(R.id.hide_button);
        ConstraintLayout cl1 = (ConstraintLayout) findViewById(R.id.itemListCluster);

        cl1.setVisibility(View.INVISIBLE);

        displayListButton.setOnClickListener(v -> {
            cl1.setVisibility(View.VISIBLE);
        });

        hideListButton.setOnClickListener(v -> {
            cl1.setVisibility(View.INVISIBLE);
        });

        itemAddButton.setOnClickListener(v -> {
            itemText = itemTextInput.getText().toString();
            dbHelper.createItem(itemText);
            System.out.println("---------- " + itemText);
            adapter.swapCursor(getAllItems());
            itemTextInput.getText().clear();
        });
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
