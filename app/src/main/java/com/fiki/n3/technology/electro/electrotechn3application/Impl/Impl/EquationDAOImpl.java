package com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl;

/**
 * Created by Roman on 2016/04/15.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.EquationDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.dbAdapters.EquationAdapter;

import java.util.HashMap;

public class EquationDAOImpl implements EquationDAO {

    //Private variables
    private SQLiteDatabase database;
    private EquationAdapter dbHelper;



    //Constructor
    public EquationDAOImpl(Context context)
    {
        dbHelper = new EquationAdapter(context);
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
        String selectQuery = "SELECT * FROM "+EquationAdapter.TABLE_EQUATION;

        //open connection
        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor == null) return cursor;
        cursor.moveToFirst();
        close();
        return cursor;
    }


    @Override
    public Long create(Equation equation) {

        open();

        equation.setFormula(equation.getType(),equation.getOutput());

        ContentValues values = new ContentValues();

        //settings URL
        values.put(EquationAdapter.COLUMN_EQUATION_OUTPUT, equation.getOutput());
        values.put(EquationAdapter.COLUMN_EQUATION_TYPE, equation.getType());
        values.put(EquationAdapter.COLUMN_FORMULA, equation.getFormula());

        //Insert row
        Long result = database.insert(EquationAdapter.TABLE_EQUATION, (String)(null), values);
        //Close database

        close();//Close connection
		return result;

    }

    @Override
    public int update(Equation equation) {
        open();//open connection

        equation.setFormula(equation.getType(),equation.getOutput());
        ContentValues values = new ContentValues();

        //settings URL
        values.put(EquationAdapter.COLUMN_FORMULA, equation.getFormula());
        values.put(EquationAdapter.COLUMN_EQUATION_TYPE, equation.getType());
        // values.put(BackEmfAdapter.COLUMN_TUTORIAL_ID, tutorialId);
        values.put(EquationAdapter.COLUMN_EQUATION_OUTPUT, equation.getOutput());

      
            //updating row
            int result = database.update(EquationAdapter.TABLE_EQUATION, values, EquationAdapter.COLUMN_EQUATION_ID+
                    " = ?", new String[]{String.valueOf(equation.getEquationId() )} );
       
        //Close connection
        close();
		return result;

    }

    @Override
    public int delete(Integer equationId) {

        open();
        int result = database.delete(EquationAdapter.TABLE_EQUATION, EquationAdapter.COLUMN_EQUATION_ID + " = ?",
                new String[]{String.valueOf(equationId)});
	close();
				return result;
    }

    @Override
    public int deleteTable() {
        open();
        int result = database.delete(EquationAdapter.TABLE_EQUATION,null,null);

        close();
        return result;
    }

    @Override
    public Equation findById(Integer equationId) {

        open();

        Equation equation = new Equation();

        try {
            Cursor cursor = database.query(EquationAdapter.TABLE_EQUATION,
                    new String[]{

                            EquationAdapter.COLUMN_FORMULA,
                            EquationAdapter.COLUMN_EQUATION_TYPE,
                            EquationAdapter.COLUMN_EQUATION_OUTPUT

                    }
                    , EquationAdapter.COLUMN_EQUATION_ID + " = ? ",
                    new String[]{String.valueOf(equationId)},
                    null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                //Object


                equation.setFormula(cursor.getString(1), cursor.getString(2));
                equation.setEquationId(equationId);
            }



        }
        catch(Exception e)
        {
            equation.setEquationId(-1);
        }
            close();

        return equation;

    }

    @Override
    public HashMap getList() {

        //Contact object
        Equation equation =  new Equation();
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
            } else if (cursor.moveToFirst()) {

                String type = "";
                String output = "";
                int id = -1;
                do {

                    equation = new Equation();
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("equation_id"));
                    type = cursor.getString(cursor.getColumnIndexOrThrow("equation_type"));
                    output = cursor.getString(cursor.getColumnIndexOrThrow("equation_output"));

                    equation.setEquationId(id);

                    equation.setFormula(type,output);

                    //Populate your hashMap
                    objectList.put(counter, equation);

                    counter++;//Change key values for hashMap

                } while (cursor.moveToNext());
            }
             }
            catch(Exception e)
            {
                
                objectList.put("error", "yes");
            }
            close();
        

        return objectList;

    }
}
