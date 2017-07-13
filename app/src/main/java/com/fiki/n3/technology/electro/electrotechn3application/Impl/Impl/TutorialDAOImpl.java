package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.TutorialDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.TutorialAdapter;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/17.
 */
public class TutorialDAOImpl implements TutorialDAO {
    //Private variables
    private SQLiteDatabase database;
    private TutorialAdapter dbHelper;
   // private TutorialFactory factory = MachineEmfFactoryImpl.getInstance();


    //Constructor
    public TutorialDAOImpl(Context context)
    {
        dbHelper = new TutorialAdapter(context);
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
        String selectQuery = "SELECT * FROM "+TutorialAdapter.TABLE_TUTORIAL;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }


    @Override
    public Long create(TutorialDTO tutorial) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(TutorialAdapter.COLUMN_EQUATION_ID, tutorial.getEquationId());
        values.put(TutorialAdapter.COLUMN_SCREEN_ID, tutorial.getScreenId());

        values.put(TutorialAdapter.COLUMN_STAFF_ID, tutorial.getStaffId());
       
            //Insert row
        Long result =  database.insert(TutorialAdapter.TABLE_TUTORIAL, (String) (null), values);

        //Close database
        close();//Close connection
return result;


    }

    @Override
    public int update(TutorialDTO tutorial) {
        open();//open connection

        ContentValues values = new ContentValues();


        values.put(TutorialAdapter.COLUMN_EQUATION_ID, tutorial.getEquationId());
        values.put(TutorialAdapter.COLUMN_SCREEN_ID, tutorial.getScreenId());

        values.put(TutorialAdapter.COLUMN_STAFF_ID, tutorial.getStaffId());

       
            //updating row
         int result =  database.update(TutorialAdapter.TABLE_TUTORIAL, values, TutorialAdapter.COLUMN_TUTORIAL_ID+
                 " = ?", new String[]{String.valueOf(tutorial.getTutorialId() )} );
        
        //Close connection
        close();
        return result;

    }

    @Override
    public int delete(Integer tutorialId) {
        open();
        int result = database.delete(TutorialAdapter.TABLE_TUTORIAL, TutorialAdapter.COLUMN_TUTORIAL_ID + " = ?",
                new String[]{String.valueOf(tutorialId)});
        close();
return result;
    }

    @Override
    public int deleteTable() {
        open();
        database.delete(TutorialAdapter.TABLE_TUTORIAL, null, null);//return 1 if successful*/
        close();
        return 0;
    }

    @Override
    public TutorialDTO findByIdTutorialId(Integer tutorialId) {

        HashMap objectList = new HashMap();

        //Integer tutorialId;
        Integer equationId;
        String screenId;
        Integer staffId;

        TutorialDTO tutorialDTO = new TutorialDTO.Builder()
                .build();

        //open connection first
        open();

        try {
            Cursor cursor = database.query(TutorialAdapter.TABLE_TUTORIAL,
                    new String[]{
                            TutorialAdapter.COLUMN_EQUATION_ID,
                            TutorialAdapter.COLUMN_SCREEN_ID,
                            TutorialAdapter.COLUMN_STAFF_ID

                    }
                    , TutorialAdapter.COLUMN_TUTORIAL_ID + " = ? ",
                    new String[]{String.valueOf(tutorialId)},
                    null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                equationId = cursor.getInt(0);
                screenId = cursor.getString(1);
                staffId = cursor.getInt(2);

                tutorialDTO = new TutorialDTO.Builder()
                        .TutorialId(tutorialId)
                        .equationId(equationId)
                        .screenId(screenId)
                        .staffId(staffId)
                        .build();
            }


        }
        catch(Exception ex)
        {
            tutorialDTO = new TutorialDTO.Builder()
                    .TutorialId(-1)
                    .build();
        }

        close();
        return tutorialDTO;
    }



    @Override
    public HashMap getList() {


        //cursor used to get rows from database
        Cursor cursor;

        //using hashMap because List of objects dont want to work
        HashMap objectList = new HashMap();

        int counter = 0;//used for the keys

        try {
            cursor = getRows();//get data using method from this class

            int size = cursor.getCount();
            if (cursor == null || size == 0) {
                objectList.put("error", "");
            } else {
                if (cursor.moveToFirst()) {

                    do {


                        TutorialDTO tutorialDTO = new TutorialDTO.Builder()
                                .TutorialId(cursor.getInt(0))
                                .equationId(cursor.getInt(1))
                                .screenId(cursor.getString(2))
                                .staffId(cursor.getInt(3))
                                .build();

                        //Populate your hashMap
                        objectList.put(counter, new TutorialDTO.Builder().copy(tutorialDTO).build());

                        counter++;//Change key values for hashMap

                    } while (cursor.moveToNext());
                }
            }
        }
            catch(Exception e)
            {
                objectList.put("error","");
            }

        return objectList;
    }
}
