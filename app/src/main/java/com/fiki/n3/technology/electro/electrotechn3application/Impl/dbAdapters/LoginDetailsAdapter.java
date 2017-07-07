package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

/**
 * Created by Roman on 2016/04/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

public class LoginDetailsAdapter extends SQLiteOpenHelper{

    //set table name
    public final static String TABLE_LOGIN_DETAILS= "loginDetails";

    private static final DbDetails dbDetails = new DbDetails();

    //set Collums
    public static final String COLUMN_LOGIN_ID= "login_id";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD= "password";
    public static final String COLUMN_DISPLAY_NAME = "displayName";


    private static final String DATABASE_NAME = dbDetails.getDATABASE_LoginDetails();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public LoginDetailsAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_LOGIN_DETAILS + "( "
            + COLUMN_LOGIN_ID + " integer primary key autoincrement, "
            + COLUMN_ROLE_ID + " integer, "
            + COLUMN_USERNAME +" text not null UNIQUE, "
            + COLUMN_PASSWORD +" text not null, "
            + COLUMN_DISPLAY_NAME +" text not null UNIQUE );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LoginDetailsAdapter.class.getName(),"upgraing the database from Version "+oldVersion
                +" to "+newVersion+" which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN_DETAILS);
        //call function to recreate the table
        onCreate(db);
    }

}
