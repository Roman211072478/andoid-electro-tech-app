package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.prony;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fiki.math.lab.mathlab.domain.model.PronyBreakEfficiency;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.services.prony.breakmethod.PronyBreakServerRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PronyBrakeEditActivity extends AppCompatActivity implements ServiceResultReceiver.Receiver {
    
    private DialogHandler dialogHandler = new DialogHandler(PronyBrakeEditActivity.this);
    private final PronyBreakServerRunner service = new PronyBreakServerRunner(this);

    private Button btnClear,btnDelete,btnBack;
    private EditText txtTutorialID, txtEfficiency, txtSpeed, txtLength;
    private EditText  txtVolts, txtCurrent, txtWeight,txtTension;

    public ServiceResultReceiver receiver;

    private ListView lvPronyBreak;
    private List<PronyBreakDTO> pronyBreakDTOs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prony_brake_edit_activity);

        receiver = new ServiceResultReceiver(new Handler());
        receiver.setReceiver(this);

        initComponents();//initiate all components for use
        setButtons();//set buttons

        populateService(); //populate list view
    }
    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        ResultDTO resultDTOFound = null;

        resultDTOFound = (ResultDTO)resultData.getSerializable("ServiceTag");

        if(resultDTOFound.getRequest().equals("find all"))
        {
            pronyBreakDTOs = listPopulateHander(resultDTOFound);
        }else if(resultDTOFound.getRequest().equals("delete"))
        {
            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogHandler.outputMessage("delete", "Delete was unsuccessful");
            }
            else
            {
                Toast.makeText(PronyBrakeEditActivity.this, "success", Toast.LENGTH_SHORT).show();
                clearFields();
                populateService();//reload list view
            }
        }

    }

    //done
    @Nullable
    private List<PronyBreakDTO> listPopulateHander(ResultDTO resultDTO)
    {
        lvPronyBreak.setAdapter(null);
        if(resultDTO.getSResult().equals("-1"))
        {
            lvPronyBreak.setAdapter(null);
        }
        else {
            List<String> list = new ArrayList<String>();

            List<PronyBreakDTO> objectList = new ArrayList<PronyBreakDTO>();
            PronyBreakEfficiency object = null;

            HashMap myList = new HashMap();
            HashMap objectMap = new HashMap();
            objectMap = resultDTO.getResult();

            myList = (HashMap) objectMap.get("result");

            //Object
            PronyBreakDTO dto;

            if (!myList.isEmpty()) {
                // Get a set of the entries
                Set set = myList.entrySet();

                // Get an iterator
                Iterator i = set.iterator();

                list.clear();//Clear the list

                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();

                    object = (PronyBreakEfficiency) me.getValue();

                    dto = new PronyBreakDTO.Builder()
                            .pronyBreakEfficiency(object)
                            .tutorialId(object.getId())
                            .build();

                    objectList.add(dto);
                    list.add(object.getId() + " | " + object.result() + " | " +
                                    object.getSpeed()+ " | " +
                                    object.getArmsLength()+ " | " +
                                    object.getVolts()+ " | " +
                                    object.getCurrent()+ " | " +
                                    object.getWeight()+ " | " +
                                    object.getTension()
                    );//Add to string list
                }
                //Set Values to listview
                lvPronyBreak.setAdapter(new ArrayAdapter<String>(PronyBrakeEditActivity.this, android.R.layout.simple_list_item_1, list));
                return objectList;
            }
        }
        return null;
    }
    //done
    private void populateService() {
        PronyBreakDTO dto = new PronyBreakDTO.Builder()
                .build();

        service.findAllService(dto,receiver);
    }

    //done
    private void initComponents() {
        //buttons
        btnDelete = (Button) findViewById(R.id.prony_deleteBtn_edit_layout);
        btnClear = (Button) findViewById(R.id.prony_clearBtn_edit_layout);
        btnBack =  (Button) findViewById(R.id.prony_backBTN_edit_layout);

        //listview
        lvPronyBreak = (ListView)findViewById(R.id.prony_edit_lv);

        //inputs
        txtTutorialID = (EditText) findViewById(R.id.prony_tutID_edit_layout);
        txtVolts = (EditText) findViewById(R.id.prony_volts_edit_layout);
        txtLength = (EditText) findViewById(R.id.prony_length_edit_layout);
        txtEfficiency = (EditText) findViewById(R.id.prony_effieciency_edit_layout);
        txtSpeed = (EditText) findViewById(R.id.prony_speed_edit_layout);
        txtCurrent = (EditText) findViewById(R.id.prony__current_edit_layout);
        txtWeight = (EditText) findViewById(R.id.prony_weight_edit_layout);
        txtTension = (EditText) findViewById(R.id.prony_tension_edit_layout);
    }

    //no need
    private void setButtons() {
        clearButton();
        setOnItemClick();
        deleteButton();
        backButton();
    }

    private void backButton() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PronyBrakeEditActivity.this, PronyBrakeCRUDActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //no need
    private void clearButton()
    {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    //done
    private void setOnItemClick()
    {
        lvPronyBreak.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);

                if (!(data.trim()).equals("")) {

                    txtTutorialID.setText(pronyBreakDTOs.get(position).getTutorialId().toString());
                    txtEfficiency.setText(String.valueOf(pronyBreakDTOs.get(position).getPronyBreakEfficiency().result()));
                    txtLength.setText(String.valueOf(pronyBreakDTOs.get(position).getPronyBreakEfficiency().getArmsLength()));
                    txtVolts.setText(String.valueOf(pronyBreakDTOs.get(position).getPronyBreakEfficiency().getVolts()));
                    txtSpeed.setText(String.valueOf(pronyBreakDTOs.get(position).getPronyBreakEfficiency().getSpeed()));
                    txtCurrent.setText(String.valueOf(pronyBreakDTOs.get(position).getPronyBreakEfficiency().getCurrent()));
                    txtWeight.setText(String.valueOf(pronyBreakDTOs.get(position).getPronyBreakEfficiency().getWeight()));
                    txtTension.setText(String.valueOf(pronyBreakDTOs.get(position).getPronyBreakEfficiency().getTension()));
                }
            }
        });
    }

    //done
    private void deleteButton()
    {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean validate = validations();
                if (validate == false) {
                    dialogHandler.outputMessage("delete", "Choose an item to delete");
                }
                else {
                    dialogHandler.Confirm("Delete", "Are you sure you want to delete?",
                            "No", "Yes",
                            new Runnable() { //coolest thing ever
                                @Override
                                public void run() {
                                    deleteMethod();
                                }
                            },
                            new Runnable() {
                                @Override
                                public void run() {

                                }

                            }
                    );
                }


            }});
    }

    //done
    private void deleteMethod()
    {
        //should consider putting a try and catch here for the casting
        Integer tutId = new Integer(txtTutorialID.getText().toString());

        PronyBreakEfficiency object = new PronyBreakEfficiency.Builder()
                .build();

        PronyBreakDTO dto = new PronyBreakDTO.Builder()
                .tutorialId(tutId)
                .pronyBreakEfficiency(object)
                .build();
        try {

            service.DeleteService(dto,receiver);

        }catch (Exception e) {
            dialogHandler.outputMessage("Error", "Service error");
        }
    }

    //done
    private void clearFields()
    {
        txtTutorialID.setText("");
        txtVolts.setText("");
        txtLength.setText("");
        txtEfficiency.setText("");
        txtSpeed.setText("");
        txtWeight.setText("");
        txtCurrent.setText("");
        txtTension.setText("");
    }

    //done
    private boolean validations()
    {

        if(txtTutorialID.getText().toString().trim().equals("") || txtEfficiency.getText().toString().trim().equals(""))
        {
            return false;
        }else if(txtLength.getText().toString().trim().equals("") || txtVolts.getText().toString().trim().equals(""))
        {
            return false;
        }else if(txtWeight.getText().toString().trim().equals("") || txtCurrent.getText().toString().trim().equals(""))
        {
            return false;
        }
        else if(txtSpeed.getText().toString().trim().equals("") || txtTension.getText().toString().trim().equals(""))
        {
            return false;
        }
        else{
            return true;
        }

    }
}
