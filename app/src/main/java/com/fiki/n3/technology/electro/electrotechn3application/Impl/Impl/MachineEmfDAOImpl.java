package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.MachineEmf;
import com.fiki.math.lab.mathlab.factories.Impl.MachineEmfFactoryImpl;
import com.fiki.math.lab.mathlab.factories.MachineEmfFactory;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.MachineEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.MachineEmfAdapter;
import com.fiki.n3.technology.electro.electrotechn3application.dto.MachineEmfDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public class MachineEmfDAOImpl implements MachineEmfDAO {

    //Private variables
    private SQLiteDatabase database;
    private MachineEmfAdapter dbHelper;
    private MachineEmfFactory factory = MachineEmfFactoryImpl.getInstance();


    //Constructor
    public MachineEmfDAOImpl(Context context)
    {
        dbHelper = new MachineEmfAdapter(context);
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
        String selectQuery = "SELECT * FROM "+MachineEmfAdapter.TABLE_MachineEmf;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }

    @Override
    public Long create(MachineEmf machineEmf, Integer tutorialId) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(MachineEmfAdapter.COLUMN_ARMATURE_CCONDUCTORS, machineEmf.getArmatureConductors());
        values.put(MachineEmfAdapter.COLUMN_EMF, machineEmf.result());
        values.put(MachineEmfAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(MachineEmfAdapter.COLUMN_PARALLEL_PATHS, machineEmf.getParrelPaths());
        values.put(MachineEmfAdapter.COLUMN_POLE_PAIRS, machineEmf.getPolePairs());
        values.put(MachineEmfAdapter.COLUMN_FLUX, machineEmf.getFlux());
        values.put(MachineEmfAdapter.COLUMN_SPEED, machineEmf.getspeedRevolution());

       
            //Insert row
            Long result = database.insert(MachineEmfAdapter.TABLE_MachineEmf, (String)(null), values);
       
        //Close database
        close();//Close connection

		return result;
    }

    @Override
    public int update(MachineEmf machineEmf, Integer tutorialId) {
        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(MachineEmfAdapter.COLUMN_ARMATURE_CCONDUCTORS, machineEmf.getArmatureConductors());
        values.put(MachineEmfAdapter.COLUMN_EMF, machineEmf.result());
        values.put(MachineEmfAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(MachineEmfAdapter.COLUMN_PARALLEL_PATHS, machineEmf.getParrelPaths());
        values.put(MachineEmfAdapter.COLUMN_POLE_PAIRS, machineEmf.getPolePairs());
        values.put(MachineEmfAdapter.COLUMN_FLUX, machineEmf.getFlux());
        values.put(MachineEmfAdapter.COLUMN_SPEED, machineEmf.getspeedRevolution());


           //updating row
            int result = database.update(MachineEmfAdapter.TABLE_MachineEmf, values, MachineEmfAdapter.COLUMN_TUTORIAL_ID+
                    " = ?", new String[]{String.valueOf(tutorialId )} );
        
        //Close connection
        close();
		return result;


    }

    @Override
    public int  delete(Integer tutorialId) {
        open();
        //int result = database.delete(MachineEmfAdapter.TABLE_MachineEmf, null, null);//return 1 if successful
        int result = database.delete(MachineEmfAdapter.TABLE_MachineEmf, MachineEmfAdapter.COLUMN_TUTORIAL_ID + " = ?",
                new String[]{String.valueOf(tutorialId)});
        close();

		return result;
    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(MachineEmfAdapter.TABLE_MachineEmf,null,null);
        close();
        return result;
    }

    @Override
    public MachineEmf findByIdTutorialId(Integer tutorialId) {

        double armatureConductors;
        double emf;
        double flux;
        double parallelPaths;
        double speed;
        double polePairs;
        //open connection first
        open();

		MachineEmf machineEmf = null;
		try{
        Cursor cursor = database.query(MachineEmfAdapter.TABLE_MachineEmf,
                new String[]{
                        MachineEmfAdapter.COLUMN_ARMATURE_CCONDUCTORS,
                        MachineEmfAdapter.COLUMN_FLUX,
                        MachineEmfAdapter.COLUMN_PARALLEL_PATHS,
                        MachineEmfAdapter.COLUMN_POLE_PAIRS,
                        MachineEmfAdapter.COLUMN_SPEED,
                        MachineEmfAdapter.COLUMN_EMF
                }
                ,MachineEmfAdapter.COLUMN_TUTORIAL_ID + " = ? ",
                new String[]{String.valueOf(tutorialId)},
                null,null,null,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
			 armatureConductors = Double.parseDouble(cursor.getString(0));
			flux  = Double.parseDouble(cursor.getString(1));
			parallelPaths  = Double.parseDouble(cursor.getString(2));
			polePairs  = Double.parseDouble(cursor.getString(3)) ;
			speed  = Double.parseDouble(cursor.getString(4));
			emf  = Double.parseDouble(cursor.getString(5)) ;
			
			 machineEmf =  factory.createMachineEmf(armatureConductors, flux, parallelPaths,
                polePairs, speed, emf);
        }

       


        //Object
        
		}
		catch(Exception e)
		{
			 machineEmf =  factory.createMachineEmf(-1, -1, -1,
                -1, -1, -1);
		}
        close();

        return machineEmf;
    }

    @Override
    public HashMap getList() {
        //Contact object
        MachineEmf machineEmf;
        MachineEmfDTO machineEmfDTO;
        //cursor used to get rows from database
        Cursor cursor;

        //using hashMap because List of objects dont want to work
        HashMap objectList = new HashMap();

        int counter = 0;//used for the keys
        

            try {
                cursor = getRows();//get data using method from this class

                int size = cursor.getCount();
                if (cursor == null || size == 0) {

                    objectList.put("error", "no records");
                } else {
                    if (cursor.moveToFirst()) {

                        do {


                            machineEmf = new MachineEmf.Builder()
                                    .armatureConductors(Double.parseDouble(cursor.getString(1)))
                                    .flux(Double.parseDouble(cursor.getString(2)))
                                    .parallelPaths(Double.parseDouble(cursor.getString(3)))
                                    .polePairs(Double.parseDouble(cursor.getString(4)))
                                    .speedRevolution(Double.parseDouble(cursor.getString(6)))
                                    .emf(Double.parseDouble(cursor.getString(5)))
                                    .build();

                            machineEmfDTO =  new MachineEmfDTO.Builder()
                                    .machineEmf(machineEmf)
                                    .tutorialId(new Integer(cursor.getInt(0)))
                                    .build();

                            //Populate your hashMap  maybe instead of the counter use the tutorial id=> cursor.getString(0)
                            objectList.put(counter/*cursor.getString(0)*/, new MachineEmfDTO.Builder().copy(machineEmfDTO).build());

                            counter++;//Change key values for hashMap

                        } while (cursor.moveToNext());
                    }
                }

            }		catch(Exception e)
			{
				objectList.put("error","db error");
			}
			
        

        return objectList;
    }
}
