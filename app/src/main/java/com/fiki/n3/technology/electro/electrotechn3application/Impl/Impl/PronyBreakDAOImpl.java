package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.PronyBreakEfficiency;
import com.fiki.math.lab.mathlab.factories.Impl.ProneBreakEfficiencyFactoryImpl;
import com.fiki.math.lab.mathlab.factories.ProneBreakEfficiencyFactory;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.PronyBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.PronyBreakAdapter;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public class PronyBreakDAOImpl implements PronyBreakDAO {
    //Private variables
    private SQLiteDatabase database;
    private PronyBreakAdapter dbHelper;
    private ProneBreakEfficiencyFactory factory = ProneBreakEfficiencyFactoryImpl.getInstance();


    //Constructor
    public PronyBreakDAOImpl(Context context)
    {
        dbHelper = new PronyBreakAdapter(context);
    }

    //open database
    public void open()
    {
            database =  dbHelper.getWritableDatabase();
       
    }
    //Close database
    public void close()
    {
        database.close();
    }


    private Cursor getRows() {
        //Sql query
        String selectQuery = "SELECT * FROM "+PronyBreakAdapter.TABLE_PRONY_BREAK;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }

    @Override
    public Long create(PronyBreakEfficiency pronyBreakEfficiency, Integer tutorialId) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(PronyBreakAdapter.COLUMN_ARM_LENGTH, pronyBreakEfficiency.getArmsLength());
        values.put(PronyBreakAdapter.COLUMN_CURRENT, pronyBreakEfficiency.getCurrent());
        values.put(PronyBreakAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(PronyBreakAdapter.COLUMN_PRONY_EFFICIENCY, pronyBreakEfficiency.result());
        values.put(PronyBreakAdapter.COLUMN_TENSION, pronyBreakEfficiency.getTension());
        values.put(PronyBreakAdapter.COLUMN_VOLTS, pronyBreakEfficiency.getVolts());
        values.put(PronyBreakAdapter.COLUMN_SPEED, pronyBreakEfficiency.getSpeed());
        values.put(PronyBreakAdapter.COLUMN_WEIGHT, pronyBreakEfficiency.getWeight());

       
            //Insert row
           Long result =  database.insert(PronyBreakAdapter.TABLE_PRONY_BREAK, (String)(null), values);
        
        
        //Close database
        close();//Close connection

			return result;
    }

    @Override
    public int update(PronyBreakEfficiency pronyBreakEfficiency, Integer tutorialId) {
        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(PronyBreakAdapter.COLUMN_WEIGHT, pronyBreakEfficiency.getWeight());
        values.put(PronyBreakAdapter.COLUMN_VOLTS, pronyBreakEfficiency.getVolts());
        values.put(PronyBreakAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(PronyBreakAdapter.COLUMN_TENSION, pronyBreakEfficiency.getTension());
        values.put(PronyBreakAdapter.COLUMN_PRONY_EFFICIENCY, pronyBreakEfficiency.result());
        values.put(PronyBreakAdapter.COLUMN_ARM_LENGTH, pronyBreakEfficiency.getArmsLength());
        values.put(PronyBreakAdapter.COLUMN_CURRENT, pronyBreakEfficiency.getCurrent());
        values.put(PronyBreakAdapter.COLUMN_SPEED, pronyBreakEfficiency.getSpeed());


        
            //updating row
           int result =  database.update(PronyBreakAdapter.TABLE_PRONY_BREAK, values, PronyBreakAdapter.COLUMN_TUTORIAL_ID+
                    " = ?", new String[]{String.valueOf(tutorialId )} );
       
        //Close connection
        close();
	return result;

    }

    @Override
    public int delete(Integer tutorialId) {
        open();
        int result = database.delete(PronyBreakAdapter.TABLE_PRONY_BREAK, PronyBreakAdapter.COLUMN_TUTORIAL_ID + " = ?",
                new String[]{String.valueOf(tutorialId)});
        close();
return result;
    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(PronyBreakAdapter.TABLE_PRONY_BREAK,null,null);
        close();
        return result;
    }

    @Override
    public PronyBreakEfficiency findByIdTutorialId(Integer tutorialId) {

        double speed;
        double volts;
        double current;
        double weight;
        double tension;
        double pronyEfficiency;
        double armsLength;
		PronyBreakEfficiency pronyBreakObject = null;

        //open connection first
        open();

		try{
        Cursor cursor = database.query(PronyBreakAdapter.TABLE_PRONY_BREAK,
                new String[]{
                        PronyBreakAdapter.COLUMN_SPEED,
                        PronyBreakAdapter.COLUMN_VOLTS,
                        PronyBreakAdapter.COLUMN_CURRENT,
                        PronyBreakAdapter.COLUMN_WEIGHT,
                        PronyBreakAdapter.COLUMN_TENSION,
                        PronyBreakAdapter.COLUMN_PRONY_EFFICIENCY,
                        PronyBreakAdapter.COLUMN_ARM_LENGTH
                }
                ,PronyBreakAdapter.COLUMN_TUTORIAL_ID + " = ? ",
                new String[]{String.valueOf(tutorialId)},
                null,null,null,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
			 speed = Double.parseDouble(cursor.getString(0));
         volts = Double.parseDouble(cursor.getString(1));
         current = Double.parseDouble(cursor.getString(2));
         weight = Double.parseDouble(cursor.getString(3));
         tension = Double.parseDouble(cursor.getString(4));
         pronyEfficiency = Double.parseDouble(cursor.getString(5));
         armsLength = Double.parseDouble(cursor.getString(6));

        //Object
         pronyBreakObject =  factory.createProneBreakEfficiency(
                speed,
                volts,
                current,
                weight,
                tension,
                pronyEfficiency,
                armsLength
                );
        }

        
		}
		catch(Exception e)
		{
			pronyBreakObject =  factory.createProneBreakEfficiency(
                -1,
                -1,
                -1,
                -1,
                -1,
                -1,
                -1
                );
		}

        close();
        return pronyBreakObject;
    }

    @Override
    public HashMap getList() {
        //Contact object
        PronyBreakEfficiency pronyBreakEfficiency;
        //cursor used to get rows from database
        Cursor cursor;

        //using hashMap because List of objects dont want to work
        HashMap objectList = new HashMap();

        int counter = 0;//used for the keys
        
             try {
                 cursor = getRows();//get data using method from this class

                 int size = cursor.getCount();
                 if (cursor == null || size == 0) {

                     objectList.put("error", "no record");
                 } else {
                     if (cursor.moveToFirst()) {

                         do {


                             pronyBreakEfficiency = new PronyBreakEfficiency.Builder()
                                     .id(cursor.getInt(0))
                                     .speed(Double.parseDouble(cursor.getString(1)))
                                     .volts(Double.parseDouble(cursor.getString(2)))
                                     .current(Double.parseDouble(cursor.getString(3)))
                                     .weight(Double.parseDouble(cursor.getString(4)))
                                     .tension(Double.parseDouble(cursor.getString(5)))
                                     .pronyBreakEfficiency(Double.parseDouble(cursor.getString(6)))
                                     .armsLength(Double.parseDouble(cursor.getString(7)))
                                     .build();


                             //Populate your hashMap  maybe instead of the counter use the tutorial id=> cursor.getString(0)
                             objectList.put(counter/*cursor.getString(0)*/, new PronyBreakEfficiency.Builder().copy(pronyBreakEfficiency).build());

                             counter++;//Change key values for hashMap

                         } while (cursor.moveToNext());
                     }
                 }
             }
			catch(Exception e)
			{
				objectList.put("error","db error");
			}
       

        return objectList;
    }
}
