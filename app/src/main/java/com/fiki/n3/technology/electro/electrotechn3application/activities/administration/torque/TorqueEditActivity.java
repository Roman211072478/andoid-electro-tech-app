package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.torque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fiki.math.lab.mathlab.domain.model.Torque;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.calculators.TorqueCalculator;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TorqueDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.services.torque.TorqueServiceRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TorqueEditActivity extends Activity  implements ServiceResultReceiver.Receiver  {

    private TorqueCalculator torqueCalculator = new TorqueCalculator();
    private DialogHandler dialogHandler = new DialogHandler(TorqueEditActivity.this);
    private final TorqueServiceRunner service = new TorqueServiceRunner(this);

    private Button btnUpdate,btnClear,btnDelete,btnBack;
    private EditText txtTutorialID, txtTorque, txtPole, txtIa, txtConductors,txtFlux,txtPaths;

    public ServiceResultReceiver receiver;

    private ListView lvTorque;
    private List<TorqueDTO> torqueDTOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.torque_edit_activity);

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
            torqueDTOList = listPopulateHander(resultDTOFound);
        }else if(resultDTOFound.getRequest().equals("delete"))
        {
            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogHandler.outputMessage("delete", "Delete was unsuccessful");
            }
            else
            {
                Toast.makeText(TorqueEditActivity.this, "success", Toast.LENGTH_SHORT).show();
                clearFields();
                populateService();//reload list view
            }
        }
        else if(resultDTOFound.getRequest().equals("update"))
        {
            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogHandler.outputMessage("update", "update was unsuccessful");
            }
            else
            {
                Toast.makeText(TorqueEditActivity.this,"success",Toast.LENGTH_SHORT).show();
                clearFields();
                populateService();//reload list view
            }
        }

    }



    //done
    @Nullable
    private List<TorqueDTO> listPopulateHander(ResultDTO resultDTO)
    {

        lvTorque.setAdapter(null);

        if(resultDTO.getSResult().equals("-1"))
        {
            lvTorque.setAdapter(null);
        }
        else {
            List<String> list = new ArrayList<String>();

            List<TorqueDTO> objectList = new ArrayList<TorqueDTO>();
            Torque object = null;

            HashMap myList = new HashMap();
            HashMap objectMap = new HashMap();
            objectMap = resultDTO.getResult();

            myList = (HashMap) objectMap.get("result");

            //Object
            TorqueDTO torqueDTO;

            if (!myList.isEmpty()) {
                // Get a set of the entries
                Set set = myList.entrySet();

                // Get an iterator
                Iterator i = set.iterator();

                list.clear();//Clear the list

                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();

                    object = (Torque) me.getValue();

                    torqueDTO = new TorqueDTO.Builder()
                            .torque(object)
                            .tutorialId(object.getId())
                            .build();

                    objectList.add(torqueDTO);
                    list.add(torqueDTO.getTutorialId() + " | " + torqueDTO.getTorque().result() + " | " +
                                    torqueDTO.getTorque().getArmatureConductors()+ " | " +
                                    torqueDTO.getTorque().getFlux()+ " | " +
                                    torqueDTO.getTorque().getParallelPaths()+ " | " +
                                    torqueDTO.getTorque().getArmatureCurrent()+ " | " +
                                    torqueDTO.getTorque().getPolePairs()
                    );//Add to string list
                }

                //Set Values to listview
                lvTorque.setAdapter(new ArrayAdapter<String>(TorqueEditActivity.this, android.R.layout.simple_list_item_1, list));
                return objectList;
            }
        }

        return null;
    }


    //done
    private void populateService() {
        TorqueDTO dto = new TorqueDTO.Builder()
                .build();

        service.findAllService(dto,receiver);
    }

    //done
    private void initComponents() {
        //buttons
        btnDelete = (Button) findViewById(R.id.torque_deleteBtn_edit_layout);
        btnUpdate = (Button) findViewById(R.id.torque_updateBtn_edit_layout);
        btnClear = (Button) findViewById(R.id.torque_clearBtn_edit_layout);
        btnBack =  (Button) findViewById(R.id.torque_backBTN_edit_layout);

        //listview
        lvTorque = (ListView)findViewById(R.id.torque_edit_lv);

        //inputs
        txtTutorialID = (EditText) findViewById(R.id.torque_tutID_edit_layout);
        txtConductors = (EditText) findViewById(R.id.torque_conductors_edit_layout);
        txtIa = (EditText) findViewById(R.id.torque_ia_edit_layout);
        txtTorque = (EditText) findViewById(R.id.torque_torque_edit_layout);
        txtPole = (EditText) findViewById(R.id.torque_poles_edit_layout);
        txtFlux = (EditText) findViewById(R.id.torque__flux_edit_layout);
        txtPaths = (EditText) findViewById(R.id.torque_paths_edit_layout);
    }

    //no need
    private void setButtons() {
        clearButton();
        setOnItemClick();
        update();
        deleteButton();
        backButton();
    }

    private void backButton() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TorqueEditActivity.this,TorqueCrudActivity.class);
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
        lvTorque.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String data = (String) parent.getItemAtPosition(position);


                if (!(data.trim()).equals("")) {

                    txtTutorialID.setText(torqueDTOList.get(position).getTutorialId().toString());
                    txtTorque.setText(String.valueOf(torqueDTOList.get(position).getTorque().result()));
                    txtIa.setText(String.valueOf(torqueDTOList.get(position).getTorque().getArmatureCurrent()));
                    txtConductors.setText(String.valueOf(torqueDTOList.get(position).getTorque().getArmatureConductors()));
                    txtPole.setText(String.valueOf(torqueDTOList.get(position).getTorque().getPolePairs()));
                    txtFlux.setText(String.valueOf(torqueDTOList.get(position).getTorque().getFlux()));
                    txtPaths.setText(String.valueOf(torqueDTOList.get(position).getTorque().getParallelPaths()));
                }
            }
        });
    }

    //done
    private void update()
    {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean validate = validations();
                if (validate == false) {
                    dialogHandler.outputMessage("edit", "Please insert all values");

                } else {

                    dialogHandler.Confirm("Update", "Are you sure you want to update?",
                            "No", "Yes",
                            new Runnable() { //coolest thing ever
                                @Override
                                public void run() {
                                    updateMethod();
                                }
                            },
                            new Runnable() {
                                @Override
                                public void run() {

                                }
                            }
                    );
                }
            }
        });
    }

    //done
    private void updateMethod()
    {

        //should consider putting a try and catch here for the casting
        Integer tutId = new Integer(txtTutorialID.getText().toString());
        double torque = Double.parseDouble(txtTorque.getText().toString());
        double polePairs = Double.parseDouble(txtPole.getText().toString());
        double ia = Double.parseDouble(txtIa.getText().toString());
        double conductors = Double.parseDouble(txtConductors.getText().toString());
        double flux = Double.parseDouble(txtFlux.getText().toString());
        double paths = Double.parseDouble(txtPaths.getText().toString());

        Torque object = new Torque.Builder()
                .torque(torque)
                .polePairs(polePairs)
                .armatureCurrent(ia)
                .armatureConductors(conductors)
                .flux(flux)
                .parallelPaths(paths)
                .build();

        TorqueDTO dto = new TorqueDTO.Builder()
                .tutorialId(tutId)
                .torque(object)
                .build();

        Boolean correctInput = torqueCalculator.isCorrect(torque,conductors,polePairs, flux,ia, paths);

        try {

            if (correctInput == true) {
                service.updateService(dto, receiver);

            } else if (correctInput == false) {

                dialogHandler.Confirm("Input wrong", "none of your values add up(formula),would you like to generated an answer from your input?",
                        "No", "Yes",
                        new Runnable() { //coolest thing ever
                            @Override
                            public void run() {
                                generateTorque();
                            }
                        },
                        new Runnable() {
                            @Override
                            public void run() {

                            }

                        }
                );
            }



        } catch (Exception e) {
            dialogHandler.outputMessage("Error", "Service error");
        }

    }
    //done
    private void generateTorque()
    {

        double torque = Double.parseDouble(txtTorque.getText().toString());
        double polePairs = Double.parseDouble(txtPole.getText().toString());
        double ia = Double.parseDouble(txtIa.getText().toString());
        double conductors = Double.parseDouble(txtConductors.getText().toString());
        double flux = Double.parseDouble(txtFlux.getText().toString());
        double paths = Double.parseDouble(txtPaths.getText().toString());

        double generatedResult = torqueCalculator.calculateNewTorque(torque, conductors, polePairs, flux, ia, paths);

        txtTorque.setText(String.valueOf(generatedResult));
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

        Torque object = new Torque.Builder()
                .build();

        TorqueDTO dto = new TorqueDTO.Builder()
                .tutorialId(tutId)
                .torque(object)
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
        txtConductors.setText("");
        txtIa.setText("");
        txtTorque.setText("");
        txtPole.setText("");
        txtPaths.setText("");
        txtFlux.setText("");
    }

    //done
    private boolean validations()
    {

        if(txtTutorialID.getText().toString().trim().equals("") || txtTorque.getText().toString().trim().equals(""))
        {
            return false;
        }else if(txtIa.getText().toString().trim().equals("") || txtConductors.getText().toString().trim().equals(""))
        {
            return false;
        }else if(txtPaths.getText().toString().trim().equals("") || txtFlux.getText().toString().trim().equals(""))
        {
            return false;
        }
        else if(txtPole.getText().toString().trim().equals(""))
        {
            return false;
        }
        else{
            return true;
        }

    }
}
