package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.roman.andoirdmathlab.dto.TableVersionDTO;
import com.fiki.roman.andoirdmathlab.repository.Impl.TableVersionDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.dbAdapters.DatabaseDetails.TableVersionAdapter;

import java.util.HashMap;

/**
 * Created by Roman on 2016/06/15.
 */
public class TableVersionDAOImpl implements TableVersionDAO {

    //Private variables
    private SQLiteDatabase database;
    private TableVersionAdapter dbHelper;

    //Constructor
    public TableVersionDAOImpl(Context context)
    {
        dbHelper = new TableVersionAdapter(context);
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
        String selectQuery = "SELECT * FROM "+TableVersionAdapter.TABLE_NAME;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }

    @Override
    public Long create(TableVersionDTO dto) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(TableVersionAdapter.COLUMN_ID, dto.getId());
        values.put(TableVersionAdapter.COLUMN_TYPE, dto.getType());
        values.put(TableVersionAdapter.COLUMN_VERSION, dto.getVersion());

        //Insert row
        Long result =  database.insert(TableVersionAdapter.TABLE_NAME, (String)(null), values);

        //Close database
        close();//Close connection
        return result;
    }

    @Override
    public int update(TableVersionDTO dto) {
        open();//open connection
        ContentValues values = new ContentValues();

        //settings URL
        values.put(TableVersionAdapter.COLUMN_VERSION, dto.getVersion());
        values.put(TableVersionAdapter.COLUMN_TYPE, dto.getType());

        //updating row
        int result = database.update(TableVersionAdapter.TABLE_NAME, values, TableVersionAdapter.COLUMN_TYPE+
                " = ?", new String[]{String.valueOf(dto.getType() )} );

        //Close connection
        close();

        return result;
    }

    @Override
    public int delete(Integer id) {
        open();
        int result =  database.delete(TableVersionAdapter.TABLE_NAME, TableVersionAdapter.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        close();
        return result;
    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(TableVersionAdapter.TABLE_NAME,null,null);
        close();
        return result;

    }

    @Override
    public TableVersionDTO findById(String value) {

        TableVersionDTO object =null;

        Integer version_id;
        Integer version;
        //open connection first
        open();

        try{
            Cursor cursor = database.query(TableVersionAdapter.TABLE_NAME,
                    new String[]{
                            TableVersionAdapter.COLUMN_ID,
                            TableVersionAdapter.COLUMN_VERSION
                    }
                    ,TableVersionAdapter.COLUMN_TYPE + " = ? ",
                    new String[]{String.valueOf(value)},
                    null,null,null,null);

            if(cursor != null)
            {
                cursor.moveToFirst();
                version_id = new Integer(cursor.getInt(0));
                version = new Integer(cursor.getInt(1));
                //Object
                object =  new TableVersionDTO.Builder()
                .id(version_id)
                .version(version)
                .build();
            }
        }
        catch(Exception e)
        {
            object =  new TableVersionDTO.Builder()
                    .version(-1)
                    .id(-1)
                    .build();
        }
        close();
        return object;
    }

    @Override
    public HashMap getList() {
        //Contact object
        TableVersionDTO object;
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


                        object = new TableVersionDTO.Builder()
                                .version(new Integer(cursor.getInt(2)))
                                .id(new Integer(cursor.getInt(0)))
                                .build();

                        //Populate your hashMap  maybe instead of the counter use the tutorial id=> cursor.getString(0)
                        objectList.put(counter/*cursor.getString(0)*/,new TableVersionDTO.Builder().copy(object).build());

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
