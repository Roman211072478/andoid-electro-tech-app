package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.RopeBreakMethod;
import com.fiki.math.lab.mathlab.factories.Impl.RopeBreakMethodFactoryImpl;
import com.fiki.math.lab.mathlab.factories.RopeBreakMethodFactory;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RopeBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.RopeBreakAdapter;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public class RopeBreakDAOImpl implements RopeBreakDAO {
    //Private variables
    private SQLiteDatabase database;
    private RopeBreakAdapter dbHelper;

    private RopeBreakMethodFactory factory = RopeBreakMethodFactoryImpl.getInstance();


    //Constructor
    public RopeBreakDAOImpl(Context context)
    {
        dbHelper = new RopeBreakAdapter(context);
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
        String selectQuery = "SELECT * FROM "+RopeBreakAdapter.TABLE_ROPE_BREAK;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }

    @Override
    public Long create(RopeBreakMethod ropeBreakMethod, Integer tutorialId) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(RopeBreakAdapter.COLUMN_RADIUS, ropeBreakMethod.getRadius());
        values.put(RopeBreakAdapter.COLUMN_CURRENT, ropeBreakMethod.getCurrent());
        values.put(RopeBreakAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(RopeBreakAdapter.COLUMN_ROPE_EFFICIENCY, ropeBreakMethod.result());
        values.put(RopeBreakAdapter.COLUMN_TENSION, ropeBreakMethod.getTension());
        values.put(RopeBreakAdapter.COLUMN_VOLTS, ropeBreakMethod.getVolts());
        values.put(RopeBreakAdapter.COLUMN_SPEED, ropeBreakMethod.getSpeed());
        values.put(RopeBreakAdapter.COLUMN_WEIGHT, ropeBreakMethod.getWeight());

        
            //Insert row
          Long result =   database.insert(RopeBreakAdapter.TABLE_ROPE_BREAK, (String)(null), values);
       
        //Close database
        close();//Close connection

return result;
    }

    @Override
    public int update(RopeBreakMethod ropeBreakMethod, Integer tutorialId) {
        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(RopeBreakAdapter.COLUMN_WEIGHT, ropeBreakMethod.getWeight());
        values.put(RopeBreakAdapter.COLUMN_VOLTS, ropeBreakMethod.getVolts());
        values.put(RopeBreakAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(RopeBreakAdapter.COLUMN_TENSION, ropeBreakMethod.getTension());
        values.put(RopeBreakAdapter.COLUMN_ROPE_EFFICIENCY, ropeBreakMethod.result());
        values.put(RopeBreakAdapter.COLUMN_RADIUS, ropeBreakMethod.getRadius());
        values.put(RopeBreakAdapter.COLUMN_CURRENT, ropeBreakMethod.getCurrent());
        values.put(RopeBreakAdapter.COLUMN_SPEED, ropeBreakMethod.getSpeed());


            //updating row
           int result =  database.update(RopeBreakAdapter.TABLE_ROPE_BREAK, values, RopeBreakAdapter.COLUMN_TUTORIAL_ID+
                    " = ?", new String[]{String.valueOf(tutorialId )} );
        
        //Close connection
        close();
return result;

    }

    @Override
    public int delete(Integer tutorialId) {
        open();
       int result =  database.delete(RopeBreakAdapter.TABLE_ROPE_BREAK, RopeBreakAdapter.COLUMN_TUTORIAL_ID + " = ?",
                new String[]{String.valueOf(tutorialId)});
        close();
		return result;

    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(RopeBreakAdapter.TABLE_ROPE_BREAK,null,null);
        close();
        return result;
    }

    @Override
    public RopeBreakMethod findByIdTutorialId(Integer tutorialId) {

        double speed;
        double volts;
        double current;
        double weight;
        double tension;
        double ropeBreak;
        double radius;
		
		RopeBreakMethod ropeBreakMethod = null;

        //open connection first
        open();

		try{
        Cursor cursor = database.query(RopeBreakAdapter.TABLE_ROPE_BREAK,
                new String[]{
                        RopeBreakAdapter.COLUMN_SPEED,
                        RopeBreakAdapter.COLUMN_VOLTS,
                        RopeBreakAdapter.COLUMN_CURRENT,
                        RopeBreakAdapter.COLUMN_WEIGHT,
                        RopeBreakAdapter.COLUMN_TENSION,
                        RopeBreakAdapter.COLUMN_ROPE_EFFICIENCY,
                        RopeBreakAdapter.COLUMN_RADIUS
                }
                ,RopeBreakAdapter.COLUMN_TUTORIAL_ID + " = ? ",
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
			ropeBreak = Double.parseDouble(cursor.getString(5));
			radius = Double.parseDouble(cursor.getString(6));

			//Object
			  ropeBreakMethod =  factory.createRopeBreakMethod(
					 speed,
					 radius,
					 volts,
					 current,
					 weight,
					 tension,
					 ropeBreak
			 );
        }

        
		}
		catch(Exception e)
		{
			ropeBreakMethod =  factory.createRopeBreakMethod(
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
        return ropeBreakMethod;
    }

    @Override
    public HashMap getList() {
        //Contact object
        RopeBreakMethod ropeBreakMethod;
        //cursor used to get rows from database
        Cursor cursor;

        //using hashMap because List of objects dont want to work
        HashMap objectList = new HashMap();

        int counter = 0;//used for the keys
        try{

            cursor = getRows();//get data using method from this class
			
			int size = cursor.getCount();
            if(cursor == null  || size == 0)
            {
                
                objectList.put("error","yes");
            }

            else{
            if(cursor.moveToFirst())
            {

                do{


                    ropeBreakMethod = new RopeBreakMethod.Builder()

                            .id(cursor.getInt(0))
                            .speed(Double.parseDouble(cursor.getString(1)))
                            .volts(Double.parseDouble(cursor.getString(2)))
                            .current(Double.parseDouble(cursor.getString(3)))
                            .weight(Double.parseDouble(cursor.getString(4)))
                            .tension(Double.parseDouble(cursor.getString(5)))
                            .RopeBreakEfficiency(Double.parseDouble(cursor.getString(6)))
                            .radius(Double.parseDouble(cursor.getString(7)))
                            .build();

                    //Populate your hashMap  maybe instead of the counter use the tutorial id=> cursor.getString(0)
                    objectList.put(counter/*cursor.getString(0)*/,new RopeBreakMethod.Builder().copy(ropeBreakMethod).build());

                    counter++;//Change key values for hashMap

                }while(cursor.moveToNext());
            }
			}
        }
        catch(Exception e)
        {

        }

        return objectList;
    }
}
