package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

/**
 * Created by Roman on 2016/04/16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

public class MachineEmfAdapter extends SQLiteOpenHelper{


    //set table name
    public final static String TABLE_MachineEmf = "machineEmf";
    private static final DbDetails dbDetails = new DbDetails();

    //set Collums
    public static final String COLUMN_ARMATURE_CCONDUCTORS= "armature_conductors";
    public static final String COLUMN_FLUX = "flux";
    public static final String COLUMN_PARALLEL_PATHS= "parallel_paths";
    public static final String COLUMN_POLE_PAIRS = "pole_pairs";
    public static final String COLUMN_SPEED = "speed";
    public static final String COLUMN_EMF = "emf";
    public static final String COLUMN_TUTORIAL_ID = "tutorial_id";
    public static final String COLUMN_ID = "id";

    private static final String DATABASE_NAME = dbDetails.getDATABASE_MachineEmf();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public MachineEmfAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_MachineEmf + "( "
            + COLUMN_TUTORIAL_ID + " integer  primary key , "
            + COLUMN_ARMATURE_CCONDUCTORS +" text not null, "
            + COLUMN_FLUX +" text not null, "
            + COLUMN_PARALLEL_PATHS +" text not null, "
            + COLUMN_POLE_PAIRS +" text not null, "
            + COLUMN_EMF +" text not null, "
            + COLUMN_SPEED +" text not null );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MachineEmfAdapter.class.getName(),"upgraing the database from Version "+oldVersion
                +" to "+newVersion+" which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MachineEmf);
        //call function to recreate the table
        onCreate(db);
    }



}
