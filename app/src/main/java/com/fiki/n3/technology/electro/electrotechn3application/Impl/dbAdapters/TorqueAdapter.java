package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

/**
 * Created by Roman on 2016/04/16.
 */
public class TorqueAdapter extends SQLiteOpenHelper {


    //set table name
    public final static String TABLE_TORQUE = "torqueTable";
    private static final DbDetails dbDetails = new DbDetails();

    //set Collums
    public static final String COLUMN_ARMATURE_CONDUCTOR= "armature_Conductor";
    public static final String COLUMN_POLE_PAIRS = "pole_pairs";
    public static final String COLUMN_FLUX = "flux";
    public static final String COLUMN_ARMATURE_CURRENT = "armature_Current";
    public static final String COLUMN_PARALLEL_PATHS = "parallel_paths";
    public static final String COLUMN_TORQUE = "torque";
    public static final String COLUMN_TUTORIAL_ID = "tutorial_id";

    private static final String DATABASE_NAME = dbDetails.getDATABASE_Torque();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public TorqueAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_TORQUE + "( "
            + COLUMN_TUTORIAL_ID +" integer primary key,  "
            + COLUMN_ARMATURE_CONDUCTOR + " text not null, "
            + COLUMN_POLE_PAIRS +" text not null, "
            + COLUMN_FLUX +" text not null, "
            + COLUMN_ARMATURE_CURRENT +" text not null, "
            + COLUMN_PARALLEL_PATHS +" text not null, "
            + COLUMN_TORQUE +" text not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TorqueAdapter.class.getName(),"upgraing the database from Version "+oldVersion
                +" to "+newVersion+" which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TORQUE);
        //call function to recreate the table
        onCreate(db);
    }


}
