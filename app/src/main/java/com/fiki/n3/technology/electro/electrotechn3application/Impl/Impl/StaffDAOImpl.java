package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.roman.andoirdmathlab.dto.StaffDTO;
import com.fiki.roman.andoirdmathlab.repository.Impl.StaffDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.dbAdapters.StaffAdapter;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public class StaffDAOImpl implements StaffDAO {
    //Private variables
    private SQLiteDatabase database;
    private StaffAdapter dbHelper;


    //Constructor
    public StaffDAOImpl(Context context)
    {
        dbHelper = new StaffAdapter(context);
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
        String selectQuery = "SELECT * FROM "+StaffAdapter.TABLE_STAFF;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }

    @Override
    public Long create(StaffDTO staffDTO) {
        /*
        before performing this action the id of loginId and roleId must first
        exist
        * */
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(StaffAdapter.COLUMN_ROLE_ID, staffDTO.getRoleId());
        values.put(StaffAdapter.COLUMN_LOGIN_ID, staffDTO.getLoginId());

        
            //Insert row
           Long result = database.insert(StaffAdapter.TABLE_STAFF, (String)(null), values);
       
        //Close database
        close();//Close connection
		return result;

    }

    @Override
    public int update(StaffDTO staffDTO) {
        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(StaffAdapter.COLUMN_ROLE_ID, staffDTO.getRoleId());
        values.put(StaffAdapter.COLUMN_LOGIN_ID, staffDTO.getLoginId());

       
            //updating row
          int result =  database.update(StaffAdapter.TABLE_STAFF, values, StaffAdapter.COLUMN_STAFF_ID +
                    " = ?", new String[]{String.valueOf(staffDTO.getStaffId())});
       
        //Close connection
        close();

return result;
    }

    @Override
    public int delete(Integer staffId) {
        open();
        int result = database.delete(StaffAdapter.TABLE_STAFF, StaffAdapter.COLUMN_STAFF_ID + " = ?",
                new String[]{String.valueOf(staffId)});
        close();
		return result;
    }

    @Override
    public int deleteTable() {
        open();
        database.delete(StaffAdapter.TABLE_STAFF, null, null);//return 1 if successful
        close();
        return 0;
    }

    @Override
    public StaffDTO findByIdStaffId(Integer staffId) {

        HashMap objectList = new HashMap();

        Integer roleId;
        Integer loginId;

        //open connection first
        open();

        Cursor cursor = database.query(StaffAdapter.TABLE_STAFF,
                new String[]{
                        StaffAdapter.COLUMN_ROLE_ID,
                        StaffAdapter.COLUMN_LOGIN_ID
                }
                ,StaffAdapter.COLUMN_STAFF_ID + " = ? ",
                new String[]{String.valueOf(staffId)},
                null,null,null,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }

        roleId = cursor.getInt(0);
        loginId  = cursor.getInt(1);

        StaffDTO staffDTO = new StaffDTO.Builder()
                .roleId(roleId)
                .loginId(loginId)
                .staffId(staffId)
                .build();


        close();
        return staffDTO;
    }

    @Override
    public StaffDTO findByLoginId(Integer loginId) {

        HashMap objectList = new HashMap();

		StaffDTO staffDTO = null;
        Integer roleId;
        Integer staffId;

        //open connection first
        open();

		try {
        Cursor cursor = database.query(StaffAdapter.TABLE_STAFF,
                new String[]{
                        StaffAdapter.COLUMN_STAFF_ID,
                        StaffAdapter.COLUMN_ROLE_ID
                }
                ,StaffAdapter.COLUMN_LOGIN_ID + " = ? ",
                new String[]{String.valueOf(loginId)},
                null,null,null,null);

        if(cursor != null)
        {
			 cursor.moveToFirst();
			
			staffId  = cursor.getInt(0);
			roleId = cursor.getInt(1);

         staffDTO = new StaffDTO.Builder()
                .roleId(roleId)
                .loginId(loginId)
                .staffId(staffId)
                .build();
           
        }


       
		}
		catch(Exception e)
		{
			  staffDTO = new StaffDTO.Builder()
                .roleId(-1)
                .loginId(-1)
                .staffId(-1)
                .build();
		}

        close();
        return staffDTO;
    }
    @Override
    public HashMap getList() {  //Contact object

        //cursor used to get rows from database
        Cursor cursor;

		 StaffDTO staffDTO = null;
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


                            staffDTO = new StaffDTO.Builder()
                                   .roleId(cursor.getInt(1))
                                   .loginId(cursor.getInt(2))
                                   .staffId(cursor.getInt(0))
                                   .build();

                           //Populate your hashMap
                           objectList.put(counter, new StaffDTO.Builder().copy(staffDTO).build());

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
