package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.LoginDetails;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.LoginDetailsDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.LoginDetailsAdapter;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/15.
 */
public class LoginDetailsDAOImpl implements LoginDetailsDAO {
    //Private variables
    private SQLiteDatabase database;
    private LoginDetailsAdapter dbHelper;


    //Constructor
    public LoginDetailsDAOImpl(Context context)
    {
        dbHelper = new LoginDetailsAdapter(context);
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
        String selectQuery = "SELECT * FROM "+LoginDetailsAdapter.TABLE_LOGIN_DETAILS;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }


    @Override
    public Long create(LoginDetails loginDetails) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(LoginDetailsAdapter.COLUMN_USERNAME, loginDetails.getUsername());
        values.put(LoginDetailsAdapter.COLUMN_PASSWORD, loginDetails.getPassword());
        values.put(LoginDetailsAdapter.COLUMN_DISPLAY_NAME, loginDetails.getDisplayName());
        values.put(LoginDetailsAdapter.COLUMN_ROLE_ID, loginDetails.getRoleId());

            //Insert row
         Long result = database.insert(LoginDetailsAdapter.TABLE_LOGIN_DETAILS, (String)(null), values);
       
        //Close database
        close();//Close connection

        return result;
    }

    @Override
    public int update(LoginDetails loginDetails ) {
        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(LoginDetailsAdapter.COLUMN_PASSWORD, loginDetails.getPassword());
        values.put(LoginDetailsAdapter.COLUMN_USERNAME, loginDetails.getUsername());
        values.put(LoginDetailsAdapter.COLUMN_DISPLAY_NAME, loginDetails.getDisplayName());
        values.put(LoginDetailsAdapter.COLUMN_ROLE_ID, loginDetails.getRoleId());
        
            //updating row
            int result = database.update(LoginDetailsAdapter.TABLE_LOGIN_DETAILS, values, LoginDetailsAdapter.COLUMN_LOGIN_ID+
                    " = ?", new String[]{String.valueOf(loginDetails.getLoginId() )} );
       
        //Close connection
        close();
        return result;
    }

    @Override
    public int delete(LoginDetails loginDetails) {

        open();
        int result = database.delete(LoginDetailsAdapter.TABLE_LOGIN_DETAILS, LoginDetailsAdapter.COLUMN_LOGIN_ID + " = ?",
                new String[]{String.valueOf(loginDetails.getLoginId())});
        close();

        return result;
    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(LoginDetailsAdapter.TABLE_LOGIN_DETAILS,null,null);
        close();
        return result;
    }


    @Override
    public LoginDetails findByUserName(String username) {

        //open connection first
        open();
        //Object
        LoginDetails loginDetails = new LoginDetails();
        Cursor cursor = null;
        try {
             cursor = database.query(LoginDetailsAdapter.TABLE_LOGIN_DETAILS,
                    new String[]{
                            LoginDetailsAdapter.COLUMN_LOGIN_ID,
                            LoginDetailsAdapter.COLUMN_USERNAME,
                            LoginDetailsAdapter.COLUMN_PASSWORD,
                            LoginDetailsAdapter.COLUMN_ROLE_ID,
                            LoginDetailsAdapter.COLUMN_DISPLAY_NAME
                    }
                    , LoginDetailsAdapter.COLUMN_USERNAME + " = ? ",
                    new String[]{String.valueOf(username)},
                    null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                loginDetails.login(cursor.getString(1), cursor.getString(2), cursor.getString(4));
                loginDetails.setLoginId(cursor.getInt(0));
                loginDetails.setRoleId(cursor.getInt(3));

            }

        }
        catch (Exception e){

           loginDetails.setLoginId(-1);
        }

        close();

        return loginDetails;

    }

    @Override
    public HashMap getList() {

        //Contact object
        LoginDetails loginDetails = new LoginDetails();
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
                
                objectList.put("error","yes");
            }
            else {
                if (cursor.moveToFirst()) {

                    do {


                        loginDetails = new LoginDetails();
                        loginDetails.login(cursor.getString(2), cursor.getString(3), cursor.getString(4));
                        loginDetails.setLoginId(cursor.getInt(0));
                        loginDetails.setRoleId(cursor.getInt(1));

                        //Populate your hashMap
                        objectList.put(counter, loginDetails);

                        counter++;//Change key values for hashMap

                    } while (cursor.moveToNext());
                }
            }
        }
        catch(Exception e)
        {
            
            objectList.put("error","yes");
        }

        return objectList;

    }

}
