package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

/**
 * Created by Roman on 2016/04/16.
 */
public class RoleAdapter extends SQLiteOpenHelper {

    //set table name
    public final static String TABLE_ROLE = "role";
    private static final DbDetails dbDetails = new DbDetails();

    //set Collums
    public static final String COLUMN_ROLE_ID= "role_id";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_DESCRIPTION= "description";

    private static final String DATABASE_NAME = dbDetails.getDATABASE_Role();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public RoleAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_ROLE + "( "
            + COLUMN_ROLE_ID +" integer primary key autoincrement, "
            + COLUMN_ROLE + " text not null UNIQUE, "
            + COLUMN_DESCRIPTION +" text);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RoleAdapter.class.getName(), "upgraing the database from Version " + oldVersion
                + " to " + newVersion + " which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROLE);
        //call function to recreate the table
        onCreate(db);
    }

}
