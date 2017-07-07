package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

/**
 * Created by Roman on 2016/04/16.
 */
public class RopeBreakAdapter extends SQLiteOpenHelper {


    //set table name
    public final static String TABLE_ROPE_BREAK = "ropeBreak";

    private static final DbDetails dbDetails = new DbDetails();

    //set Collums
    public static final String COLUMN_TUTORIAL_ID= "tutorial_id";
    public static final String COLUMN_SPEED = "speed";
    public static final String COLUMN_VOLTS= "volts";
    public static final String COLUMN_CURRENT = "current";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_TENSION= "tension";
    public static final String COLUMN_ROPE_EFFICIENCY = "rope_efficiency";
    public static final String COLUMN_RADIUS = "radius";

    private static final String DATABASE_NAME = dbDetails.getDATABASE_RopeBreak();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public RopeBreakAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_ROPE_BREAK + "( "
            + COLUMN_TUTORIAL_ID + " integer primary key, "
            + COLUMN_SPEED +" text not null, "
            + COLUMN_VOLTS +" text not null, "
            + COLUMN_CURRENT +" text not null, "
            + COLUMN_WEIGHT +" text not null, "
            + COLUMN_TENSION +" text not null, "
            + COLUMN_ROPE_EFFICIENCY +" text not null, "
            + COLUMN_RADIUS +" text not null );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RopeBreakAdapter.class.getName(),"upgraing the database from Version "+oldVersion
                +" to "+newVersion+" which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROPE_BREAK);
        //call function to recreate the table
        onCreate(db);
    }

}
