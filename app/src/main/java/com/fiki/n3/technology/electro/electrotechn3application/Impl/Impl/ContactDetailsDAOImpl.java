package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.ContactDetails;
import com.fiki.math.lab.mathlab.factories.ContactDetailsFactory;
import com.fiki.math.lab.mathlab.factories.Impl.ContactDetailFactoryImpl;
import com.fiki.roman.andoirdmathlab.repository.Impl.ContactDetailsDAO;
import com.fiki.roman.andoirdmathlab.repository.Impl.dbAdapters.ContactDetailsAdapter;

import java.util.HashMap;


/**
 * Created by Roman on 2016/04/14.
 */
public class ContactDetailsDAOImpl implements ContactDetailsDAO {

    //Private variables
    private SQLiteDatabase database;
    private ContactDetailsAdapter dbHelper;
    private ContactDetailsFactory factory =  ContactDetailFactoryImpl.getInstance();


    //Constructor
    public ContactDetailsDAOImpl(Context context)
    {
        dbHelper = new ContactDetailsAdapter(context);
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
        String selectQuery = "SELECT * FROM "+ContactDetailsAdapter.TABLE_CONTACT_DETAILS;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }


    @Override
    public Long create(ContactDetails contactDetails,Integer staff_id) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(ContactDetailsAdapter.COLUMN_CELL_NUMBER, contactDetails.getCellNumber());
        values.put(ContactDetailsAdapter.COLUMN_EMAIL_ADDRES, contactDetails.getEmail());
        values.put(ContactDetailsAdapter.COLUMN_STAFF_ID, staff_id);

       
            //Insert row
           Long result = database.insert(ContactDetailsAdapter.TABLE_CONTACT_DETAILS, (String)(null), values);
       
        //Close database
        close();//Close connection
			
			return result;
    }

    @Override
    public int update(ContactDetails contactDetails,Integer staff_id) {
        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(ContactDetailsAdapter.COLUMN_EMAIL_ADDRES, contactDetails.getEmail());
        values.put(ContactDetailsAdapter.COLUMN_CELL_NUMBER, contactDetails.getCellNumber());

       
            //updating row
           int result =  database.update(ContactDetailsAdapter.TABLE_CONTACT_DETAILS, values, ContactDetailsAdapter.COLUMN_STAFF_ID+
                    " = ?", new String[]{String.valueOf(staff_id )} );
       
        //Close connection
        close();
		return result;
    }

    @Override
    public int delete(Integer StaffId) {

        open();
      

        int result = database.delete(ContactDetailsAdapter.TABLE_CONTACT_DETAILS, ContactDetailsAdapter.COLUMN_STAFF_ID + " = ?",
                new String[]{String.valueOf(StaffId)});
				close();
		return result;
    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(ContactDetailsAdapter.TABLE_CONTACT_DETAILS,null,null);
        close();
        return result;
    }

    @Override
    public ContactDetails findById(Integer staff_id) {

	ContactDetails contactDetails = null;
        //open connection first
        open();

		Cursor cursor = null;
		try{
         cursor = database.query(ContactDetailsAdapter.TABLE_CONTACT_DETAILS,
                new String[]{
                        ContactDetailsAdapter.COLUMN_CELL_NUMBER,
                        ContactDetailsAdapter.COLUMN_EMAIL_ADDRES
                }
                ,ContactDetailsAdapter.COLUMN_STAFF_ID + " = ? ",
                new String[]{String.valueOf(staff_id)},
                null,null,null,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
			 contactDetails =  factory.createContactDetails(cursor.getString(1)
                , cursor.getString(0));
        }


        //Object
        
		}
		catch(Exception e)
		{
			contactDetails =  factory.createContactDetails("-1"
                , "-1");
		}

        return contactDetails;

    }

    @Override
    public HashMap getList() {

        //Contact object

        ContactDetails backEmf;
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

            else{
            if(cursor.moveToFirst())
            {

                do{


                    backEmf = new ContactDetails(cursor.getString(1),cursor.getString(2));

                    //Populate your hashMap
                    objectList.put(counter,backEmf);

                    counter++;//Change key values for hashMap

                }while(cursor.moveToNext());
            }
			}
		 }catch(Exception e){
			 objectList.put("error","yes");
		 }
        
        return objectList;

    }

}
