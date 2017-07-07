package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Roman on 2016/04/20.
 */
public class MyAdapter {
    private static final String DATABASE_NAME = "my.db";
    private static final String DATABASE_TABLE = "table";
    private static final int DATABASE_VERSION = 1;


    /**
     * Database queries
     */
    private static final String DATABASE_CREATE_STATEMENT = "some awesome create statement";

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_STATEMENT);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(MyAdapter.class.getName(), "upgraing the database from Version " + oldVersion
                    + " to " + newVersion + " which will destory old data");

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            //call function to recreate the table
            onCreate(db);
        }

    }

    DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    /**
     * Constructor - takes the provided context to allow for the database to be
     * opened/created.
     *
     * @param context the Context within which to work.
     */
    public MyAdapter(Context context) {
        mCtx = context;
    }


    public MyAdapter open() {
        try {
            mDbHelper = new DatabaseHelper(mCtx);
            mDb = mDbHelper.getWritableDatabase();
            if (mDb.isOpen() == false) {
                return null;
            }


        }
        catch(Exception ex){
            return null;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }
}
