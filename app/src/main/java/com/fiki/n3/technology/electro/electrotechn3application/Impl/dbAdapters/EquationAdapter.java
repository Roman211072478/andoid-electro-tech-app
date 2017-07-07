package com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters;

/**
 * Created by Roman on 2016/04/14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.DatabaseDetails.DbDetails;

public class EquationAdapter extends SQLiteOpenHelper{

    //set table name
    public final static String TABLE_EQUATION = "equation";

    //set Collums
    public static final String COLUMN_EQUATION_ID= "equation_id";
    public static final String COLUMN_FORMULA = "formula";
    public static final String COLUMN_EQUATION_TYPE= "equation_type";
    public static final String COLUMN_EQUATION_OUTPUT = "equation_output";

    private static final DbDetails dbDetails = new DbDetails();
    private static final String DATABASE_NAME = dbDetails.getDATABASE_Equation();
    private static final int DATABASE_VERSION = dbDetails.getDATABASE_VERSION();

    //Constructor
    public EquationAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    //DATABASE CREATION SQL
    private static final String CREATE_USER_TABLE = "create TABLE IF NOT EXISTS "
            + TABLE_EQUATION + "( "
            + COLUMN_EQUATION_ID + " integer primary key autoincrement , "
            + COLUMN_FORMULA +" text UNIQUE, "
            + COLUMN_EQUATION_TYPE +" text not null, "
            + COLUMN_EQUATION_OUTPUT +" text not null );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a database
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(EquationAdapter.class.getName(),"upgraing the database from Version "+oldVersion
                +" to "+newVersion+" which will destory old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUATION);
        //call function to recreate the table
        onCreate(db);
    }
}
