package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

/**
 * Created by Roman on 2016/04/16.
 */
public class TutorialAdapter extends SQLiteOpenHelper {

    //set table name
    public final static String TABLE_TUTORIAL = "tutorial";
    private static final DbDetails dbDetails = new DbDetails();

    //set Collums
    public static final String COLUMN_TUTORIAL_ID= "tutorial_id";
    public static final String COLUMN_EQUATION_ID = "equation_id";
    public static final String COLUMN_SCREEN_ID= "screen_id";
    public static final String COLUMN_STAFF_ID = "staff_id";

    private static final String DATABASE_NAME = dbDetails.getDATABASE_Tutorial();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public TutorialAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_TUTORIAL + "( "
            + COLUMN_TUTORIAL_ID +" integer primary key autoincrement, "
            + COLUMN_EQUATION_ID + " integer not null, "
            + COLUMN_SCREEN_ID +" text not null , "
            + COLUMN_STAFF_ID +" integer not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TutorialAdapter.class.getName(), "upgraing the database from Version " + oldVersion
                + " to " + newVersion + " which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUTORIAL);
        //call function to recreate the table
        onCreate(db);
    }

}
