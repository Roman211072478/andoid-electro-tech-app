package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

/**
 * Created by Roman on 2016/04/14.
 */

public class ContactDetailsAdapter extends SQLiteOpenHelper {

    //set table name
    public final static String TABLE_CONTACT_DETAILS = "contact_details";

    //set Collums
    public static final String COLUMN_STAFF_ID= "staff_id";
    public static final String COLUMN_EMAIL_ADDRES = "email_address";
    public static final String COLUMN_CELL_NUMBER= "cell_number";

    private static final DbDetails dbDetails = new DbDetails();
    private static final String DATABASE_NAME = dbDetails.getDATABASE_ContactDetails();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public ContactDetailsAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_CONTACT_DETAILS + "( "
            + COLUMN_STAFF_ID + " integer primary key, "
            + COLUMN_EMAIL_ADDRES +" text not null, "
            + COLUMN_CELL_NUMBER +" text );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ContactDetailsAdapter.class.getName(),"upgraing the database from Version "+oldVersion
                +" to "+newVersion+" which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT_DETAILS);
        //call function to recreate the table
        onCreate(db);
    }

}
