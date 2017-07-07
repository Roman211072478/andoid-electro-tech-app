package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.roman.andoirdmathlab.dto.UserDTO;
import com.fiki.roman.andoirdmathlab.repository.Impl.UserDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.dbAdapters.UserAdapter;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public class UserDAOImpl implements UserDAO {

    //Private variables
    private SQLiteDatabase database;
    private UserAdapter dbHelper;


    //Constructor
    public UserDAOImpl(Context context)
    {
        dbHelper = new UserAdapter(context);
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
        String selectQuery = "SELECT * FROM "+UserAdapter.TABLE_USER;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }

    @Override
    public Long create(UserDTO userDTO) {

         /*
        before performing this action the id of loginId and roleId must first
        exist and tutorial or tutorial can be null
        * */
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(UserAdapter.COLUMN_ROLE_ID, userDTO.getRoleId());
        values.put(UserAdapter.COLUMN_LOGIN_ID, userDTO.getLoginId());
        values.put(UserAdapter.COLUMN_CURRENT_TUTORIAL_ID, userDTO.getTutorialId());

        
            //Insert row
           Long result = database.insert(UserAdapter.TABLE_USER, (String)(null), values);
        
        //Close database
        close();//Close connection
return result;
    }

    @Override
    public int update(UserDTO userDTO) {

        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(UserAdapter.COLUMN_ROLE_ID, userDTO.getRoleId());
        values.put(UserAdapter.COLUMN_LOGIN_ID, userDTO.getLoginId());
        values.put(UserAdapter.COLUMN_CURRENT_TUTORIAL_ID, userDTO.getTutorialId());

        
            //updating row
        int result = database.update(UserAdapter.TABLE_USER, values, UserAdapter.COLUMN_USER_ID +
                    " = ?", new String[]{String.valueOf(userDTO.getUserId())});
       
        //Close connection
        close();
		return result;

    }

    @Override
    public int delete(Integer userId) {
        open();

        int result = database.delete(UserAdapter.TABLE_USER, UserAdapter.COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(userId)});
        //int result = database.delete(UserAdapter.TABLE_USER, null, null);//return 1 if successful
        close();
return result;
    }

    @Override
    public int deleteTable() {
open();
        database.delete(UserAdapter.TABLE_USER, null, null);//return 1 if successful
        close();
        return 0;
    }

    @Override
    public UserDTO findByIdUserId(Integer userId) {

        Integer roleId;
        Integer loginId;
        Integer tutorialId;

        UserDTO userDTO = new UserDTO.Builder()
                .build();
        //open connection first
        open();
        try{

            Cursor cursor = database.query(UserAdapter.TABLE_USER,
                    new String[]{
                            UserAdapter.COLUMN_ROLE_ID,
                            UserAdapter.COLUMN_LOGIN_ID,
                            UserAdapter.COLUMN_CURRENT_TUTORIAL_ID
                    }
                    , UserAdapter.COLUMN_USER_ID + " = ? ",
                    new String[]{String.valueOf(userId)},
                    null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                roleId = cursor.getInt(0);
                loginId = cursor.getInt(1);
                tutorialId = cursor.getInt(2);

                userDTO = new UserDTO.Builder()
                        .userId(userId)
                        .loginId(loginId)
                        .roleId(roleId)
                        .tutorialId(tutorialId)
                        .build();
             }
        }
        catch(Exception e)
        {
            userDTO = new UserDTO.Builder()
            .userId(-1)
            .build();

        }

        close();
        return userDTO;
    }

    @Override
    public UserDTO findByLoginId(Integer loginId) {

        Integer roleId;
        Integer userId;
        Integer tutorialId;

        UserDTO userDTO = new UserDTO.Builder()
                .build();
        //open connection first
        open();

        try {
            Cursor cursor = database.query(UserAdapter.TABLE_USER,
                    new String[]{
                            UserAdapter.COLUMN_USER_ID,
                            UserAdapter.COLUMN_ROLE_ID,
                            UserAdapter.COLUMN_CURRENT_TUTORIAL_ID
                    }
                    , UserAdapter.COLUMN_LOGIN_ID + " = ? ",
                    new String[]{String.valueOf(loginId)},
                    null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                userId = cursor.getInt(0);
                roleId = cursor.getInt(1);
                tutorialId = cursor.getInt(2);


                userDTO = new UserDTO.Builder()
                        .userId(userId)
                        .loginId(loginId)
                        .roleId(roleId)
                        .tutorialId(tutorialId)
                        .build();
            }
        }
        catch(Exception ex)
        {
            userDTO = new UserDTO.Builder()
                    .userId(-1)
                    .build();
        }

        close();
        return userDTO;
    }

    @Override
    public HashMap getList() {
        HashMap items = new HashMap();



        //cursor used to get rows from database
        Cursor cursor;

        UserDTO userDTO;
        //using hashMap because List of objects dont want to work
        HashMap objectList = new HashMap();

        int counter = 0;//used for the keys

        try {
            cursor = getRows();//get data using method from this class

            int size = cursor.getCount();
            if(cursor == null  || size == 0)
            {
                objectList.put("error","yes");
            }else {
                if (cursor.moveToFirst()) {

                    do {
                         userDTO = new UserDTO.Builder()
                                .userId(cursor.getInt(0))
                                .loginId(cursor.getInt(2))
                                .roleId(cursor.getInt(1))
                                .tutorialId(cursor.getInt(3))
                                .build();

                        //Populate your hashMap
                        objectList.put(counter, new UserDTO.Builder().copy(userDTO).build());

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
