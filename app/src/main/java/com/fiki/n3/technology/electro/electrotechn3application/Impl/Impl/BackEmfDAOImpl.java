package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;
import com.fiki.math.lab.mathlab.factories.BackEmfFactory;
import com.fiki.math.lab.mathlab.factories.Impl.BackEmfFactoryImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.BackEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.BackEmfAdapter;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/14.
 */
public class BackEmfDAOImpl implements BackEmfDAO {

    //Private variables
    private SQLiteDatabase database;
    private BackEmfAdapter dbHelper;
    private BackEmfFactory factory = BackEmfFactoryImpl.getInstance();


    //Constructor
    public BackEmfDAOImpl(Context context)
    {
        dbHelper = new BackEmfAdapter(context);
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
        String selectQuery = "SELECT * FROM "+BackEmfAdapter.TABLE_BACKEMF;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }


    @Override
    public Long create(BackEmf backEmf, Integer tutorialId) {

			open();

            ContentValues values = new ContentValues();

           
            values.put(BackEmfAdapter.COLUMN_ARMATURE_CURRENT, backEmf.getArmatureCurrent());
            values.put(BackEmfAdapter.COLUMN_BACK_EMF, backEmf.result());
            values.put(BackEmfAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(BackEmfAdapter.COLUMN_RESISTANCE, backEmf.getResistance());
            values.put(BackEmfAdapter.COLUMN_VOLTS, backEmf.getVolts());

            //Insert row
            Long returnValue = database.insert(BackEmfAdapter.TABLE_BACKEMF, (String)(null), values);//returns -1 when error or the primary id

        //Close database
        close();//Close connection
        return returnValue;

    }

    @Override
    public int update(BackEmf backEmf,Integer tutorialId) {
      
            open();//open connection

            ContentValues values = new ContentValues();

            //settings URL
            values.put(BackEmfAdapter.COLUMN_ARMATURE_CURRENT, backEmf.getArmatureCurrent());
            values.put(BackEmfAdapter.COLUMN_BACK_EMF, backEmf.result());
            // values.put(BackEmfAdapter.COLUMN_TUTORIAL_ID, tutorialId);
            values.put(BackEmfAdapter.COLUMN_RESISTANCE, backEmf.getResistance());
            values.put(BackEmfAdapter.COLUMN_VOLTS, backEmf.getVolts());
            //updating row
            int returnEffected = database.update(BackEmfAdapter.TABLE_BACKEMF, values, BackEmfAdapter.COLUMN_TUTORIAL_ID+
                    " = ?", new String[]{String.valueOf(tutorialId )} );
        

        //Close connection
        close();
        return returnEffected;
    }

    @Override
    public int delete(Integer tutorialId) {
        
            open();
      /*  int effectedReturn = database.delete(BackEmfAdapter.TABLE_BACKEMF, null, null);//return 1 if successful*/
            int effectedReturn = database.delete(BackEmfAdapter.TABLE_BACKEMF, BackEmfAdapter.COLUMN_TUTORIAL_ID + " = ?",
                    new String[]{String.valueOf(tutorialId)});

            close();
        return effectedReturn;

    }

    @Override
    public int deleteTable() {
        open();
       int effectedReturn = database.delete(BackEmfAdapter.TABLE_BACKEMF, null, null);//return 1 if successful

        close();
        return effectedReturn;

    }

    @Override
    public BackEmf findById(Integer tutorialId) {
        double armatureCurrent;
        double emf;
        double resistance;
        double volts;
		BackEmf backEmf = null;
        //open connection first
        open();
		Cursor cursor = null;

        try {
         cursor = database.query(BackEmfAdapter.TABLE_BACKEMF,
                new String[]{
                        BackEmfAdapter.COLUMN_ARMATURE_CURRENT, BackEmfAdapter.COLUMN_BACK_EMF,
                        BackEmfAdapter.COLUMN_RESISTANCE, BackEmfAdapter.COLUMN_VOLTS
                }
                ,BackEmfAdapter.COLUMN_TUTORIAL_ID + " = ? ",
                new String[]{String.valueOf(tutorialId)},
                null,null,null,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
			armatureCurrent = Double.parseDouble(cursor.getString(0));
			resistance  = Double.parseDouble(cursor.getString(2));
			volts  = Double.parseDouble(cursor.getString(3));
			emf  = Double.parseDouble(cursor.getString(1)) ;
			backEmf =  factory.createBackEmf(armatureCurrent,resistance,volts,emf);
        }
		}catch(Exception e)
		{
			backEmf =  factory.createBackEmf(-1,-1,-1,-1);
		}

         close();


        //Object
        


        return backEmf;
    }

    @Override
    public HashMap getList() {

        //Contact object
        BackEmf backEmf;
        BackEmfDTO backEmfDTO;
        //cursor used to get rows from database
        Cursor cursor;

        //using hashMap because List of objects dont want to work
        HashMap objectList = new HashMap();

        int counter = 0;//used for the keys

           try {
               cursor = getRows();//get data using method from this class

               int size = cursor.getCount();
               if (cursor == null || size == 0) {
                   objectList.put("error", "yes");
               } else {
                   if (cursor.moveToFirst()) {

                       do {

                           backEmf = new BackEmf.Builder()
                                   .backEmf(Double.parseDouble(cursor.getString(4)))
                                   .armatureCurrent(Double.parseDouble(cursor.getString(1)))
                                   .resistance(Double.parseDouble(cursor.getString(2)))
                                   .volts(Double.parseDouble(cursor.getString(3)))
                                   .build();


                           backEmfDTO = new BackEmfDTO.Builder()
                                   .tutorialId(new Integer(cursor.getInt(0)))
                                   .backEmf(backEmf)
                                   .build();
                           //Populate your hashMap
                           objectList.put(counter, new BackEmfDTO.Builder().copy(backEmfDTO).build());

                           counter++;//Change key values for hashMap

                       } while (cursor.moveToNext());
                   }
               }
           }
           catch(Exception e )
        {
            objectList.put("error","yes");
        }
       

        return objectList;

    }
}
