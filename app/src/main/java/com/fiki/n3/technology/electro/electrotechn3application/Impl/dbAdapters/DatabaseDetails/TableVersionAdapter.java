package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Roman on 2016/06/15.
 */
public class TableVersionAdapter extends SQLiteOpenHelper {

    //set table name
    public final static String TABLE_NAME = "table_version";

    //set Collums
    public static final String COLUMN_VERSION = "version";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_ID= "version_id";


    private static final String DATABASE_NAME = "table_version.db";
    private static final int DATABASE_VERSION = 1;

    //Constructor
    public TableVersionAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_NAME + "( "
            + COLUMN_ID +" integer primary key, "
            + COLUMN_TYPE + " text not null UNIQUE, "
            + COLUMN_VERSION +" integer not null"
            + ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TableVersionAdapter.class.getName(), "upgraing the database from Version " + oldVersion
                + " to " + newVersion + " which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //call function to recreate the table
        onCreate(db);
    }
}
