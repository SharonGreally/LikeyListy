package com.likeylisty.app.database;

import android.provider.BaseColumns;

public class DatabaseDetails {

  private DatabaseDetails() {
  }

  public static final class Database implements BaseColumns {

    public static final String DATABASE_NAME = "likeylisty.db";
    public static final int DATABASE_VERSION = 1;
  }

  public static final class Item implements BaseColumns {

    public static final String TABLE_NAME = "item";

    public static final String COLUMN_ITEM_ID = BaseColumns._ID;
    public static final String COLUMN_ITEM_TEXT = "itemText";
    public static final String COLUMN_ITEM_STATUS = "itemStatus";
    public static final String COLUMN_ITEM_TIMESTAMP = "itemTimestamp";
  }

  public static final class List implements BaseColumns {

    public static final String TABLE_NAME = "list";
  }
}
