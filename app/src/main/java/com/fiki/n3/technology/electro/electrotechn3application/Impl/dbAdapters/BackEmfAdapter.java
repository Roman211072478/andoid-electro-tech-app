package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

/**
 * Created by Roman on 2016/04/14.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

public class BackEmfAdapter extends SQLiteOpenHelper{


    private SQLiteOpenHelper sqLiteOpenHelper;
    //set table name
    public final static String TABLE_BACKEMF = "backEmf";



    //set Collums
    public static final String COLUMN_ARMATURE_CURRENT= "armature_Current";
    public static final String COLUMN_RESISTANCE = "resistance";
    public static final String COLUMN_VOLTS= "volts";
    public static final String COLUMN_BACK_EMF = "back_emf";
    public static final String COLUMN_TUTORIAL_ID = "tutorial_id";

    private static final DbDetails dbDetails = new DbDetails();
    private static final String DATABASE_NAME = dbDetails.getDATABASE_BackEmf();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();


    //Constructor
    public BackEmfAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_BACKEMF + "( "
            + COLUMN_TUTORIAL_ID +" integer primary key, "
            + COLUMN_ARMATURE_CURRENT + " text not null, "
            + COLUMN_RESISTANCE +" text not null, "
            + COLUMN_VOLTS +" text not null, "
            + COLUMN_BACK_EMF +" text not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BackEmfAdapter.class.getName(),"upgraing the database from Version "+oldVersion
                +" to "+newVersion+" which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BACKEMF);
        //call function to recreate the table
        onCreate(db);
    }


}
