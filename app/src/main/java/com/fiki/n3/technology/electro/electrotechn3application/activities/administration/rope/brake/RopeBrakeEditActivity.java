package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.rope.brake;

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

import com.fiki.math.lab.mathlab.domain.model.RopeBreakMethod;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.calculators.RopeBreakCalculator;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RopeBreakDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.services.rope.breakmethod.RopeBreakServiceRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RopeBrakeEditActivity extends AppCompatActivity implements ServiceResultReceiver.Receiver {

    private RopeBreakCalculator ropeBreakCalculator = new RopeBreakCalculator();
    private DialogHandler dialogHandler = new DialogHandler(RopeBrakeEditActivity.this);
    private final RopeBreakServiceRunner service = new RopeBreakServiceRunner(this);

    private Button btnClear,btnDelete,btnBack;
    private EditText txtTutorialID, txtEfficiency, txtSpeed, txtRadius;
    private EditText  txtVolts, txtCurrent, txtWeight,txtTension;
    
    public ServiceResultReceiver receiver;

    private ListView lvRopeBreak;
    private List<RopeBreakDTO> ropeDTOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rope_brake_edit_activity);

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
            ropeDTOList = listPopulateHander(resultDTOFound);
        }else if(resultDTOFound.getRequest().equals("delete"))
        {
            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogHandler.outputMessage("delete", "Delete was unsuccessful");
            }
            else
            {
                Toast.makeText(RopeBrakeEditActivity.this, "success", Toast.LENGTH_SHORT).show();
                clearFields();
                populateService();//reload list view
            }
        }

    }

    //done
    @Nullable
    private List<RopeBreakDTO> listPopulateHander(ResultDTO resultDTO)
    {
        lvRopeBreak.setAdapter(null);

        if(resultDTO.getSResult().equals("-1"))
        {
            lvRopeBreak.setAdapter(null);
        }
        else {
            List<String> list = new ArrayList<String>();

            List<RopeBreakDTO> objectList = new ArrayList<RopeBreakDTO>();
            RopeBreakMethod object = null;

            HashMap myList = new HashMap();
            HashMap objectMap = new HashMap();
            objectMap = resultDTO.getResult();

            myList = (HashMap) objectMap.get("result");

            //Object
            RopeBreakDTO dto;

            if (!myList.isEmpty()) {
                // Get a set of the entries
                Set set = myList.entrySet();

                // Get an iterator
                Iterator i = set.iterator();

                list.clear();//Clear the list

                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();

                    object = (RopeBreakMethod) me.getValue();

                    dto = new RopeBreakDTO.Builder()
                            .ropeBreakMethod(object)
                            .tutorialId(object.getId())
                            .build();

                    objectList.add(dto);
                    list.add(object.getId() + " | " + object.result() + " | " +
                                    object.getSpeed()+ " | " +
                                    object.getRadius()+ " | " +
                                    object.getVolts()+ " | " +
                                    object.getCurrent()+ " | " +
                                    object.getWeight()+ " | " +
                                    object.getTension()
                    );//Add to string list
                }

                //Set Values to listview
                lvRopeBreak.setAdapter(new ArrayAdapter<String>(RopeBrakeEditActivity.this, android.R.layout.simple_list_item_1, list));
                return objectList;
            }
        }
        return null;
    }


    //done
    private void populateService() {
        RopeBreakDTO dto = new RopeBreakDTO.Builder()
                .build();

        service.findAllService(dto,receiver);
    }

    //done
    private void initComponents() {
        //buttons
        btnDelete = (Button) findViewById(R.id.rope_deleteBtn_edit_layout);
        btnClear = (Button) findViewById(R.id.rope_clearBtn_edit_layout);
        btnBack =  (Button) findViewById(R.id.rope_backBTN_edit_layout);

        //listview
        lvRopeBreak = (ListView)findViewById(R.id.rope_edit_lv);

        //inputs
        txtTutorialID = (EditText) findViewById(R.id.rope_tutID_edit_layout);
        txtVolts = (EditText) findViewById(R.id.rope_volts_edit_layout);
        txtRadius = (EditText) findViewById(R.id.rope_radius_edit_layout);
        txtEfficiency = (EditText) findViewById(R.id.rope_effieciency_edit_layout);
        txtSpeed = (EditText) findViewById(R.id.rope_speed_edit_layout);
        txtCurrent = (EditText) findViewById(R.id.rope__current_edit_layout);
        txtWeight = (EditText) findViewById(R.id.rope_weight_edit_layout);
        txtTension = (EditText) findViewById(R.id.rope_tension_edit_layout);
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
                Intent intent = new Intent(RopeBrakeEditActivity.this, RopeBrakeCrudActivity.class);
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
        lvRopeBreak.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);

                if (!(data.trim()).equals("")) {

                    txtTutorialID.setText(ropeDTOList.get(position).getTutorialId().toString());
                    txtEfficiency.setText(String.valueOf(ropeDTOList.get(position).getRopeBreakMethod().result()));
                    txtRadius.setText(String.valueOf(ropeDTOList.get(position).getRopeBreakMethod().getRadius()));
                    txtVolts.setText(String.valueOf(ropeDTOList.get(position).getRopeBreakMethod().getVolts()));
                    txtSpeed.setText(String.valueOf(ropeDTOList.get(position).getRopeBreakMethod().getSpeed()));
                    txtCurrent.setText(String.valueOf(ropeDTOList.get(position).getRopeBreakMethod().getCurrent()));
                    txtWeight.setText(String.valueOf(ropeDTOList.get(position).getRopeBreakMethod().getWeight()));
                    txtTension.setText(String.valueOf(ropeDTOList.get(position).getRopeBreakMethod().getTension()));
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

        RopeBreakMethod object = new RopeBreakMethod.Builder()
                .build();

        RopeBreakDTO dto = new RopeBreakDTO.Builder()
                .tutorialId(tutId)
                .ropeBreakMethod(object)
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
        txtRadius.setText("");
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
        }else if(txtRadius.getText().toString().trim().equals("") || txtVolts.getText().toString().trim().equals(""))
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
