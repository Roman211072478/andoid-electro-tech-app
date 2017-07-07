package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.Torque;
import com.fiki.math.lab.mathlab.factories.Impl.TorqueFactoryImpl;
import com.fiki.math.lab.mathlab.factories.TorqueFactory;
import com.fiki.roman.andoirdmathlab.repository.Impl.TorqueDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.dbAdapters.TorqueAdapter;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public class TorqueDAOImpl implements TorqueDAO {
    //Private variables
    private SQLiteDatabase database;
    private TorqueAdapter dbHelper;
    private TorqueFactory factory = TorqueFactoryImpl.getInstance();


    //Constructor
    public TorqueDAOImpl(Context context)
    {
        dbHelper = new TorqueAdapter(context);
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
        String selectQuery = "SELECT * FROM "+TorqueAdapter.TABLE_TORQUE;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }

    @Override
    public Long create(Torque torque, Integer tutorialId) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(TorqueAdapter.COLUMN_ARMATURE_CONDUCTOR, torque.getArmatureConductors());
        values.put(TorqueAdapter.COLUMN_ARMATURE_CURRENT, torque.getArmatureCurrent());
        values.put(TorqueAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(TorqueAdapter.COLUMN_PARALLEL_PATHS, torque.getParallelPaths());
        values.put(TorqueAdapter.COLUMN_POLE_PAIRS, torque.getPolePairs());
        values.put(TorqueAdapter.COLUMN_FLUX, torque.getFlux());
        values.put(TorqueAdapter.COLUMN_TORQUE, torque.result());

        
            //Insert row
         Long result =  database.insert(TorqueAdapter.TABLE_TORQUE, (String)(null), values);
       
        //Close database
        close();//Close connection
return result;

    }

    @Override
    public int update(Torque torque, Integer tutorialId) {
        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(TorqueAdapter.COLUMN_ARMATURE_CONDUCTOR, torque.getArmatureConductors());
        values.put(TorqueAdapter.COLUMN_TORQUE, torque.result());
        values.put(TorqueAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(TorqueAdapter.COLUMN_PARALLEL_PATHS, torque.getParallelPaths());
        values.put(TorqueAdapter.COLUMN_POLE_PAIRS, torque.getPolePairs());
        values.put(TorqueAdapter.COLUMN_FLUX, torque.getFlux());
        values.put(TorqueAdapter.COLUMN_ARMATURE_CURRENT, torque.getArmatureCurrent());


            //updating row
          int result = database.update(TorqueAdapter.TABLE_TORQUE, values, TorqueAdapter.COLUMN_TUTORIAL_ID+
                    " = ?", new String[]{String.valueOf(tutorialId )} );
       
        //Close connection
        close();

return result;
    }

    @Override
    public int delete(Integer tutorialId) {
        open();
       int result =  database.delete(TorqueAdapter.TABLE_TORQUE, TorqueAdapter.COLUMN_TUTORIAL_ID + " = ?",
                new String[]{String.valueOf(tutorialId)});
        close();
return result;
    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(TorqueAdapter.TABLE_TORQUE,null,null);
        close();
        return result;

    }

    @Override
    public Torque findByIdTutorialId(Integer tutorialId) {

        double armatureConductors;
        double polePairs;
        double flux;
        double armatureCurrent;
        double parallelPaths;
        double torque;
		
		Torque torqueObject =null;


        //open connection first
        open();

		try{
        Cursor cursor = database.query(TorqueAdapter.TABLE_TORQUE,
                new String[]{
                        TorqueAdapter.COLUMN_ARMATURE_CONDUCTOR,
                        TorqueAdapter.COLUMN_POLE_PAIRS,
                        TorqueAdapter.COLUMN_FLUX,
                        TorqueAdapter.COLUMN_ARMATURE_CURRENT,
                        TorqueAdapter.COLUMN_PARALLEL_PATHS,
                        TorqueAdapter.COLUMN_TORQUE
                }
                ,TorqueAdapter.COLUMN_TUTORIAL_ID + " = ? ",
                new String[]{String.valueOf(tutorialId)},
                null,null,null,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
			 armatureConductors = Double.parseDouble(cursor.getString(0));
			polePairs  = Double.parseDouble(cursor.getString(1));
			flux  = Double.parseDouble(cursor.getString(2));
			armatureCurrent  = Double.parseDouble(cursor.getString(3));
			parallelPaths  = Double.parseDouble(cursor.getString(4));
			torque  = Double.parseDouble(cursor.getString(5)) ;

			
			//Object
			 torqueObject =  factory.createTorque(
					armatureConductors,
					polePairs,
					flux,
					armatureCurrent,
					parallelPaths,
					torque
					);
        }

       
		}
		catch(Exception e)
		{
			torqueObject =  factory.createTorque(
					-1,
					-1,
					-1,
					-1,
					-1,
					-1
					);
		}

		close();

        return torqueObject;
    }

    @Override
    public HashMap getList() {
        //Contact object
        Torque torque;

        //cursor used to get rows from database
        Cursor cursor;

        //using hashMap because List of objects dont want to work
        HashMap objectList = new HashMap();

        int counter = 0;//used for the keys
    

             try {
            cursor = getRows();//get data using method from this class

            int size = cursor.getCount();
            if(cursor == null  || size == 0)
            {
               
                objectList.put("error","");
            }

            else{
            if(cursor.moveToFirst())
            {
                do{
                    torque = new Torque.Builder()
                            .id(cursor.getInt(cursor.getColumnIndexOrThrow("tutorial_id")))
                            .armatureConductors(Double.parseDouble(cursor.getString(1)))
                            .polePairs(Double.parseDouble(cursor.getString(2)))
                            .flux(Double.parseDouble(cursor.getString(3)))
                            .armatureCurrent(Double.parseDouble(cursor.getString(4)))
                            .parallelPaths(Double.parseDouble(cursor.getString(5)))
                            .torque(Double.parseDouble(cursor.getString(6)))
                            .build();

                    /*Populate your hashMap  maybe instead of the counter use the tutorial id=> cursor.getString(0)*/
                    objectList.put(counter/*cursor.getString(0)*/,new Torque.Builder().copy(torque).build());

                    counter++;//Change key values for hashMap

                }while(cursor.moveToNext());
            }
			}
			 }catch(Exception e )
			 {
				 objectList.put("error","");
			 }
       

        return objectList;
    }
}
