package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

/**
 * Created by Roman on 2016/04/16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

public class SwineburneAdapter extends SQLiteOpenHelper{
    //set table name
    public final static String TABLE_SWINEBURN = "swineBurne";

    private static final DbDetails dbDetails = new DbDetails();

    //set Collums
    public static final String COLUMN_NO_LOAD_LOSS= "no_load_loss";
    public static final String COLUMN_SHUNT_FIELD_LOSS = "chunt_field_loss";
    public static final String COLUMN_FULL_LOAD_LOSS= "full_load_loss";
    public static final String COLUMN_TOTAL_LOSS = "total_loss";
    public static final String COLUMN_OUTPUT = "output";
    public static final String COLUMN_INPUT = "input";
    public static final String COLUMN_EFFICIENCY = "efficiency";
    public static final String COLUMN_TUTORIAL_ID = "tutorial_id";

    private static final String DATABASE_NAME = dbDetails.getDATABASE_SwineBurne();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public SwineburneAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_SWINEBURN + "( "
            + COLUMN_TUTORIAL_ID +" integer primary key, "
            + COLUMN_NO_LOAD_LOSS + " text not null, "
            + COLUMN_SHUNT_FIELD_LOSS +" text not null, "
            + COLUMN_FULL_LOAD_LOSS +" text not null, "
            + COLUMN_TOTAL_LOSS +" text not null, "
            + COLUMN_OUTPUT +" text not null, "
            + COLUMN_INPUT +" text not null, "
            + COLUMN_EFFICIENCY +" text not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SwineburneAdapter.class.getName(),"upgraing the database from Version "+oldVersion
                +" to "+newVersion+" which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SWINEBURN);
        //call function to recreate the table
        onCreate(db);
    }
}
