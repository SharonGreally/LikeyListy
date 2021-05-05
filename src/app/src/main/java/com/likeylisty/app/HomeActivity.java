package com.likeylisty.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.likeylisty.app.database.DatabaseDetails;
import com.likeylisty.app.database.DatabaseHelper;
import com.likeylisty.app.item.ItemAdapter;

public class HomeActivity extends Activity implements refreshAdapter {

  private SQLiteDatabase db;
  private DatabaseHelper dbHelper;
  private EditText itemTextInput;
  private Button itemAddButton, displayButton;
  private ItemAdapter adapter;
  private String itemText;
  private Toolbar appActionBar;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    dbHelper = new DatabaseHelper(this);
    db = dbHelper.getWritableDatabase();

    RecyclerView itemListView = findViewById(R.id.item_list_view);
    itemListView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new ItemAdapter(this, getAllItems(), this);
    itemListView.setAdapter(adapter);

    itemTextInput = findViewById(R.id.item_text_input);
    itemAddButton = findViewById(R.id.item_add_button);
    displayButton = findViewById(R.id.display_button);
    ConstraintLayout cl1 = (ConstraintLayout) findViewById(R.id.itemListCluster);

    cl1.setVisibility(View.INVISIBLE);
    displayButton.setText("Show List");

    displayButton.setOnClickListener(v -> {
      if (cl1.isShown()) {
        cl1.setVisibility(View.INVISIBLE);
        displayButton.setText("Show List");
      }
      else{
        cl1.setVisibility(View.VISIBLE);
        displayButton.setText("Hide List");
      }
     });

    itemAddButton.setOnClickListener(v -> {
      itemText = itemTextInput.getText().toString();
      dbHelper.createItem(itemText);
      refreshAdapter();

      itemTextInput.getText().clear();
    });

    appActionBar = findViewById(R.id.appActionBar);
    setActionBar(appActionBar);
  }

  @SuppressLint("ResourceType")
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.toolbar_menu, menu);
    return true;
  }

  @SuppressLint("NonConstantResourceId")
  @Override
  public boolean onOptionsItemSelected(MenuItem selection) {
    switch (selection.getItemId()) {
      case R.id.access_settings:
        Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(settingsIntent);

//      case R.id.create_new_list:
//        return true;

    }
    return super.onOptionsItemSelected(selection);
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

  @Override
  public void refreshAdapter() {
    adapter.swapCursor(getAllItems());

  }
}
