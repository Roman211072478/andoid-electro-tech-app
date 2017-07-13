package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.math.lab.mathlab.factories.Impl.RoleFactoryImpl;
import com.fiki.math.lab.mathlab.factories.RoleFactory;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RoleDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.RoleAdapter;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public class RoleDAOImpl implements RoleDAO {

    //Private variables
    private SQLiteDatabase database;
    private RoleAdapter dbHelper;
    private RoleFactory factory = RoleFactoryImpl.getInstance();


    //Constructor
    public RoleDAOImpl(Context context)
    {
        dbHelper = new RoleAdapter(context);
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
        String selectQuery = "SELECT * FROM "+RoleAdapter.TABLE_ROLE;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }

    @Override
    public Long create(Role role) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(RoleAdapter.COLUMN_ROLE, role.getRole());
        values.put(RoleAdapter.COLUMN_DESCRIPTION, role.getDescription());

       
            //Insert row
           Long result = database.insert(RoleAdapter.TABLE_ROLE, (String)(null), values);
       
        //Close database
        close();//Close connection
	return result;

    }

    @Override
    public int update(Role role) {
        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(RoleAdapter.COLUMN_DESCRIPTION, role.getDescription());
        values.put(RoleAdapter.COLUMN_ROLE, role.getRole());
        values.put(RoleAdapter.COLUMN_ROLE_ID, role.getRoleId());

        
            //updating row
            int result = database.update(RoleAdapter.TABLE_ROLE, values, RoleAdapter.COLUMN_ROLE_ID+
                    " = ?", new String[]{String.valueOf(role.getRoleId() )} );
      
        //Close connection
        close();
return result;

    }

    @Override
    public int delete(Integer roleId) {
        open();
        int result = database.delete(RoleAdapter.TABLE_ROLE, RoleAdapter.COLUMN_ROLE_ID + " = ?",
                new String[]{String.valueOf(roleId)});
        close();
		return result;
    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(RoleAdapter.TABLE_ROLE,null,null);
        close();
        return result;
    }

    @Override
    public Role findById(Integer roleId) {

        //Integer roleId;
        String role;
        String description;

        Role roleObject = new Role();

        open();
        Cursor cursor = null;

        try {
            cursor = database.query(RoleAdapter.TABLE_ROLE,
                    new String[]{
                            RoleAdapter.COLUMN_ROLE,
                            RoleAdapter.COLUMN_DESCRIPTION
                    }
                    , RoleAdapter.COLUMN_ROLE_ID + " = ? ",
                    new String[]{String.valueOf(roleId)},
                    null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                role = cursor.getString(0);
                description = cursor.getString(1);

                roleObject =  factory.createRole(role, description, roleId);
                cursor.close();
            }


        }
        catch(Exception e){
            roleObject =  factory.createRole("", "",-1 );
        }

        //Object


        close();
        return roleObject;
    }

    @Override
    public HashMap getList() {
        //Contact object
        Role roleObject = new Role();
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
                roleObject.setRoleId(-1);
                objectList.put("error","yes");
            }

            else{
                if (cursor.moveToFirst()) {

                    do {

                        roleObject = new Role(cursor.getString(1), cursor.getString(2));
                        roleObject.setRoleId(cursor.getInt(0));

                        //Populate your hashMap  maybe instead of the counter use the tutorial id=> cursor.getString(0)
                        objectList.put(counter, roleObject);

                        counter++;//Change key values for hashMap

                    } while (cursor.moveToNext());
                }
            }


        }
        catch(Exception e)
        {
            roleObject.setRoleId(-1);
            objectList.put("error","yes");
        }
       

        return objectList;
    }
}
