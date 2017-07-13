package com.fiki.n3.technology.electro.electrotechn3application.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;
import com.fiki.math.lab.mathlab.factories.BackEmfFactory;
import com.fiki.math.lab.mathlab.factories.Impl.BackEmfFactoryImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.BackEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.BackEmfDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.back.emf.BacEmfFindService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Roman on 2016/04/17.
 */
public class ResultVew extends Activity implements OnItemClickListener,ServiceResultReceiver.Receiver {

    final BackEmfDAO dao = new BackEmfDAOImpl(this);

    private BackEmfFactory factory = BackEmfFactoryImpl.getInstance();
    private TextView tv;
    private ServiceResultReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);


        try {

            usingVoid();

        }
        catch(Exception e)
        {
            Dialog d = new Dialog(this);
            d.setTitle("title error");
            TextView tv = new TextView(this);
            String error = e.toString();
            tv.setText("Erorr "+ error);
            d.setContentView(tv);
            d.show();


        }
        finally
        {
            Dialog d = new Dialog(ResultVew.this);
            d.setTitle("title Succes");
            TextView tv = new TextView(ResultVew.this);

            tv.setText("success");
            d.setContentView(tv);
            d.show();


        }




    }

    private void usingVoid()
    {
       //The strings for


        BackEmfDTO backEmfdto = new BackEmfDTO.Builder()
                //.context(ResultVew.this)
                .build();
        Intent service;
        service = new Intent(ResultVew.this,BacEmfFindService.class);


        receiver = new ServiceResultReceiver(new Handler());

        receiver.setReceiver(this);
        service.putExtra("requestTag", "find all");
        service.putExtra("dtoTag", backEmfdto );
        service.putExtra("receiverTag", receiver);


        startService(service);

       // myList = dao.getList();//get the list


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String data = (String)parent.getItemAtPosition(position);

		 /* passContact.clear();
		  passContact = null;*/

        if(!(data.trim()).equals(""))
        {
            String str = data.substring((data.indexOf("|")+1));

            str = str.trim();

            /*Intent myIntent = new Intent(ResultView.this);

            myIntent.putExtra(KEY_VALUE, str);

            //startActivity
            startActivity(myIntent);*/
        }
    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        List<String> list = new ArrayList<String>();


        HashMap myList = new HashMap();

        ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO)resultData.getSerializable("ServiceTag");
        //Geting the listView

        HashMap objectMap = new HashMap();
        objectMap = resultDTOFound.getResult();

        myList = (HashMap)objectMap.get("result");
        ListView listView = (ListView)findViewById(R.id.lvResults);

        //Object
        BackEmf newObject;

        BackEmfDTO dto;

        if(!myList.isEmpty())
        {
            // Get a set of the entries
            Set set = myList.entrySet();

            // Get an iterator
            Iterator i = set.iterator();

            list.clear();//Clear the list

            while(i.hasNext())
            {
                Map.Entry me = (Map.Entry)i.next();

                dto = (BackEmfDTO)me.getValue();

                newObject = dto.getBackEmf();
                list.add( dto.getTutorialId()+ " | " +newObject.getArmatureCurrent() + " | " + newObject.getResistance()
                        + " | " + newObject.getVolts()
                        + " | " + newObject.result());//Add to string list
            }


            //Set Values to listview
            listView.setAdapter(new ArrayAdapter<String>(ResultVew.this,android.R.layout.simple_list_item_1,list));
            listView.setOnItemClickListener(this);
        }

    }
}
