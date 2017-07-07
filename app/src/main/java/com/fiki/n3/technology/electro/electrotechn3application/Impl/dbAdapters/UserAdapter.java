package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

/**
 * Created by Roman on 2016/04/16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

public class UserAdapter extends SQLiteOpenHelper{

    //set table name
    public final static String TABLE_USER = "users";
    private static final DbDetails dbDetails = new DbDetails();

    //set Collums
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_LOGIN_ID = "login_id";
    public static final String COLUMN_CURRENT_TUTORIAL_ID = "tutorial_id";

    private static final String DATABASE_NAME = dbDetails.getDATABASE_User();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public UserAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_USER + "( "
            + COLUMN_USER_ID +" integer primary key autoincrement, "
            + COLUMN_ROLE_ID + " integer not null, "
            + COLUMN_LOGIN_ID + " integer not null UNIQUE, "
            + COLUMN_CURRENT_TUTORIAL_ID +" integer );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(UserAdapter.class.getName(),"upgraing the database from Version "+oldVersion
                +" to "+newVersion+" which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        //call function to recreate the table
        onCreate(db);
    }
}
