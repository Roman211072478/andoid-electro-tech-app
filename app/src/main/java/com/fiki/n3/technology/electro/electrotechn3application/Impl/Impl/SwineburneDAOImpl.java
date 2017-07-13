package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.SwinburneMethod;
import com.fiki.math.lab.mathlab.factories.Impl.SwineburneMethodFactoryImpl;
import com.fiki.math.lab.mathlab.factories.SwinburneMethodFactory;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.SwineburneDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.SwineburneAdapter;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public class SwineburneDAOImpl implements SwineburneDAO {
    //Private variables
    private SQLiteDatabase database;
    private SwineburneAdapter dbHelper;
    private SwinburneMethodFactory factory = SwineburneMethodFactoryImpl.getInstance();


    //Constructor
    public SwineburneDAOImpl(Context context)
    {
        dbHelper = new SwineburneAdapter(context);
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
        String selectQuery = "SELECT * FROM "+SwineburneAdapter.TABLE_SWINEBURN;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }

    @Override
    public Long create(SwinburneMethod swinburneMethod, Integer tutorialId) {
        open();

        ContentValues values = new ContentValues();

        //settings URL
        values.put(SwineburneAdapter.COLUMN_NO_LOAD_LOSS, swinburneMethod.getNoLoadLoss());
        values.put(SwineburneAdapter.COLUMN_SHUNT_FIELD_LOSS, swinburneMethod.getShuntFieldLoss());
        values.put(SwineburneAdapter.COLUMN_FULL_LOAD_LOSS, swinburneMethod.getFullLoadAmatureLoss());
        values.put(SwineburneAdapter.COLUMN_TOTAL_LOSS, swinburneMethod.getTotalLoss());
        values.put(SwineburneAdapter.COLUMN_OUTPUT, swinburneMethod.getOutput());
        values.put(SwineburneAdapter.COLUMN_INPUT, swinburneMethod.getInput());
        values.put(SwineburneAdapter.COLUMN_EFFICIENCY, swinburneMethod.getEfficiency());
        values.put(SwineburneAdapter.COLUMN_TUTORIAL_ID, tutorialId);

       
            //Insert row
         Long result =  database.insert(SwineburneAdapter.TABLE_SWINEBURN, (String)(null), values);
        
        //Close database
        close();//Close connection
return result;

    }

    @Override
    public int update(SwinburneMethod swinburneMethod, Integer tutorialId) {
        open();//open connection

        ContentValues values = new ContentValues();

        //settings URL
        values.put(SwineburneAdapter.COLUMN_NO_LOAD_LOSS, swinburneMethod.getNoLoadLoss());
        values.put(SwineburneAdapter.COLUMN_SHUNT_FIELD_LOSS, swinburneMethod.getShuntFieldLoss());
        values.put(SwineburneAdapter.COLUMN_FULL_LOAD_LOSS, swinburneMethod.getFullLoadAmatureLoss());
        values.put(SwineburneAdapter.COLUMN_TOTAL_LOSS, swinburneMethod.getTotalLoss());
        values.put(SwineburneAdapter.COLUMN_OUTPUT, swinburneMethod.getOutput());
        values.put(SwineburneAdapter.COLUMN_INPUT, swinburneMethod.getInput());
        values.put(SwineburneAdapter.COLUMN_EFFICIENCY, swinburneMethod.getEfficiency());
        values.put(SwineburneAdapter.COLUMN_TUTORIAL_ID, tutorialId);

         //updating row
          int result = database.update(SwineburneAdapter.TABLE_SWINEBURN, values, SwineburneAdapter.COLUMN_TUTORIAL_ID+
                    " = ?", new String[]{String.valueOf(tutorialId )} );
       
        //Close connection
        close();
return result;

    }

    @Override
    public int delete(Integer tutorialId) {
        open();
        int result = database.delete(SwineburneAdapter.TABLE_SWINEBURN, SwineburneAdapter.COLUMN_TUTORIAL_ID + " = ?",
                new String[]{String.valueOf(tutorialId)});
        close();
return result;
    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(SwineburneAdapter.TABLE_SWINEBURN,null,null);

        close();
        return result;
    }

    @Override
    public SwinburneMethod findByIdTutorialId(Integer tutorialId) {

        double noLoadLoss;
        double shuntFieldLoss;
        double fullLoadLoss;
        double totalLoss;
        double output;
        double input;
        double efficiency;
		
		SwinburneMethod machineEmf = null;
        //open connection first
        open();
		
		try{

        Cursor cursor = database.query(SwineburneAdapter.TABLE_SWINEBURN,
                new String[]{
                        SwineburneAdapter.COLUMN_NO_LOAD_LOSS,
                        SwineburneAdapter.COLUMN_SHUNT_FIELD_LOSS,
                        SwineburneAdapter.COLUMN_FULL_LOAD_LOSS,
                        SwineburneAdapter.COLUMN_TOTAL_LOSS,
                        SwineburneAdapter.COLUMN_OUTPUT,
                        SwineburneAdapter.COLUMN_INPUT,
                        SwineburneAdapter.COLUMN_EFFICIENCY
                }
                ,SwineburneAdapter.COLUMN_TUTORIAL_ID + " = ? ",
                new String[]{String.valueOf(tutorialId)},
                null,null,null,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
			 noLoadLoss = Double.parseDouble(cursor.getString(0));
			shuntFieldLoss  = Double.parseDouble(cursor.getString(1));
			fullLoadLoss  = Double.parseDouble(cursor.getString(2));
			//totalLoss  = Double.parseDouble(cursor.getString(3)) ;
			output  = Double.parseDouble(cursor.getString(4));
			input  = Double.parseDouble(cursor.getString(5)) ;
			efficiency  = Double.parseDouble(cursor.getString(6)) ;
			
			 //Object
         machineEmf =  factory.createSwinBurneMethod(
                noLoadLoss,
                shuntFieldLoss,
                fullLoadLoss,
                output,
                input,
                efficiency);
        }

       


        
       
		}
		catch(Exception e)
		{
			 machineEmf =  factory.createSwinBurneMethod(
                -1,
                 -1,
                 -1,
                 -1,
                 -1,
                 -1);
			
		}

		close();

        return machineEmf;
    }

    @Override
    public HashMap getList() {
        //Contact object
        SwinburneMethod swinburneMethod;
        //cursor used to get rows from database
        Cursor cursor;

        //using hashMap because List of objects dont want to work
        HashMap objectList = new HashMap();

        int counter = 0;//used for the keys
       
            cursor = getRows();//get data using method from this class

            if(cursor.moveToFirst())
            {

                do{


                    swinburneMethod = new SwinburneMethod.Builder()
                            .noLoad(Double.parseDouble(cursor.getString(1)))
                            .shuntFieldLoss(Double.parseDouble(cursor.getString(2)))
                            .fullLoadArmatureLoss(Double.parseDouble(cursor.getString(3)))
                            .output(Double.parseDouble(cursor.getString(5)))
                            .input(Double.parseDouble(cursor.getString(6)))
                            .efficiency(Double.parseDouble(cursor.getString(7)))
                            .build();


                    //Populate your hashMap  maybe instead of the counter use the tutorial id=> cursor.getString(0)
                    objectList.put(counter/*cursor.getString(0)*/,new SwinburneMethod.Builder().copy(swinburneMethod).build());

                    counter++;//Change key values for hashMap

                }while(cursor.moveToNext());
            }
       

        return objectList;
    }
}
