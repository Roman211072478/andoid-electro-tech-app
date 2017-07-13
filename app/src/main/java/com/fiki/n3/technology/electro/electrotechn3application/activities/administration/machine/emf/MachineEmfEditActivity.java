package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.machine.emf;

import android.app.Activity;
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

import com.fiki.math.lab.mathlab.domain.model.MachineEmf;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.calculators.MachineEmfCalculator;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.dto.MachineEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.machine.emf.MachineEmfServiceRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MachineEmfEditActivity extends Activity implements ServiceResultReceiver.Receiver {

    private MachineEmfCalculator machineEmfCalculator = new MachineEmfCalculator();
    private DialogHandler dialogHandler = new DialogHandler(MachineEmfEditActivity.this);

    private Button btnUpdate,btnClear,btnDelete;
    private EditText txtTutorialID, txtEmf, txtPole, txtSpeed, txtConductors,txtFlux,txtPaths;
    public ServiceResultReceiver receiver;
    private ListView lvMachineEmf;
    private List<MachineEmfDTO> machineEmfList;

    private final MachineEmfServiceRunner service = new MachineEmfServiceRunner(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_emf_edit);

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
        //Geting the listView

        if(resultDTOFound.getRequest().equals("find all"))
        {
            machineEmfList = listPopulateHander(resultDTOFound);
        }else if(resultDTOFound.getRequest().equals("delete"))
        {
            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogHandler.outputMessage("delete", "Delete was unsuccessful");
            }
            else
            {
                Toast.makeText(MachineEmfEditActivity.this, "success", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MachineEmfEditActivity.this,"success",Toast.LENGTH_SHORT).show();
                clearFields();
                populateService();//reload list view
            }
        }

    }



    //done
    @Nullable
    private List<MachineEmfDTO> listPopulateHander(ResultDTO resultDTO)
    {

        lvMachineEmf.setAdapter(null);

        if(resultDTO.getSResult().equals("-1"))
        {
            lvMachineEmf.setAdapter(null);
        }
        else {
            List<String> list = new ArrayList<String>();

            List<MachineEmfDTO> backEmfs = new ArrayList<MachineEmfDTO>();

            HashMap myList = new HashMap();
            HashMap objectMap = new HashMap();
            objectMap = resultDTO.getResult();

            myList = (HashMap) objectMap.get("result");


            //Object
            MachineEmfDTO machineEmfDTO;

            if (!myList.isEmpty()) {
                // Get a set of the entries
                Set set = myList.entrySet();

                // Get an iterator
                Iterator i = set.iterator();

                list.clear();//Clear the list

                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();

                    machineEmfDTO = (MachineEmfDTO) me.getValue();

                    backEmfs.add(machineEmfDTO);
                    list.add(machineEmfDTO.getTutorialId() + " | " + machineEmfDTO.getMachineEmf().result() + " | " +
                                    machineEmfDTO.getMachineEmf().getArmatureConductors()+ " | " +
                                    machineEmfDTO.getMachineEmf().getFlux()+ " | " +
                                    machineEmfDTO.getMachineEmf().getParrelPaths()+ " | " +
                                    machineEmfDTO.getMachineEmf().getspeedRevolution()+ " | " +
                                    machineEmfDTO.getMachineEmf().getPolePairs()
                    );//Add to string list
                }


                //Set Values to listview
                lvMachineEmf.setAdapter(new ArrayAdapter<String>(MachineEmfEditActivity.this, android.R.layout.simple_list_item_1, list));
                return backEmfs;
            }
        }

        return null;
    }


    //done
    private void populateService() {
        MachineEmfDTO dto = new MachineEmfDTO.Builder()
                .build();

        service.findAllService(dto,receiver);
    }

    //done
    private void initComponents() {
        //buttons
        btnDelete = (Button) findViewById(R.id.machine_emf_deleteBtn_edit_layout);
        btnUpdate = (Button) findViewById(R.id.machine_emf_updateBtn_edit_layout);
        btnClear = (Button) findViewById(R.id.machine_emf_clearBtn_edit_layout);

        //listview
        lvMachineEmf= (ListView)findViewById(R.id.machine_emf_edit_lv);

        //inputs
        txtTutorialID = (EditText) findViewById(R.id.machine_emf_tutID_edit_layout);
        txtConductors = (EditText) findViewById(R.id.machine_emf_conductors_edit_layout);
        txtSpeed = (EditText) findViewById(R.id.machine_emf_speed_edit_layout);
        txtEmf = (EditText) findViewById(R.id.machine_emf_Emf_edit_layout);
        txtPole = (EditText) findViewById(R.id.machine_emf_pairs_edit_layout);
        txtFlux = (EditText) findViewById(R.id.machine_emf_flux_edit_layout);
        txtPaths = (EditText) findViewById(R.id.machine_emf_paths_edit_layout);
    }

    //no need
    private void setButtons() {
        clearButton();
        setOnItemClick();
        update();
        deleteButton();
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
        lvMachineEmf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String data = (String) parent.getItemAtPosition(position);


                if (!(data.trim()).equals("")) {

                    txtTutorialID.setText(machineEmfList.get(position).getTutorialId().toString());
                    txtEmf.setText(String.valueOf(machineEmfList.get(position).getMachineEmf().result()));
                    txtSpeed.setText(String.valueOf(machineEmfList.get(position).getMachineEmf().getspeedRevolution()));
                    txtConductors.setText(String.valueOf(machineEmfList.get(position).getMachineEmf().getArmatureConductors()));
                    txtPole.setText(String.valueOf(machineEmfList.get(position).getMachineEmf().getPolePairs()));
                    txtFlux.setText(String.valueOf(machineEmfList.get(position).getMachineEmf().getFlux()));
                    txtPaths.setText(String.valueOf(machineEmfList.get(position).getMachineEmf().getParrelPaths()));
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
        double emf = Double.parseDouble(txtEmf.getText().toString());
        double polePairs = Double.parseDouble(txtPole.getText().toString());
        double speed = Double.parseDouble(txtSpeed.getText().toString());
        double conductors = Double.parseDouble(txtConductors.getText().toString());
        double flux = Double.parseDouble(txtFlux.getText().toString());
        double paths = Double.parseDouble(txtPaths.getText().toString());

        MachineEmf object = new MachineEmf.Builder()
                .emf(emf)
                .polePairs(polePairs)
                .speedRevolution(speed)
                .armatureConductors(conductors)
                .flux(flux)
                .parallelPaths(paths)
                .build();

        MachineEmfDTO dto = new MachineEmfDTO.Builder()
                .tutorialId(tutId)
                .machineEmf(object)
                .build();



        Boolean correctInput = machineEmfCalculator.isCorrect(emf, polePairs, speed, conductors, flux, paths);

        try {

            if (correctInput == true) {
                service.updateService(dto, receiver);

            } else if (correctInput == false) {

                dialogHandler.Confirm("Input wrong", "none of your values add up(formula),would you like to generated an answer from your input?",
                        "No", "Yes",
                        new Runnable() { //coolest thing ever
                            @Override
                            public void run() {
                                generateEmf();
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
    private void generateEmf()
    {

        double emf = Double.parseDouble(txtEmf.getText().toString());
        double polePairs = Double.parseDouble(txtPole.getText().toString());
        double speed = Double.parseDouble(txtSpeed.getText().toString());
        double conductors = Double.parseDouble(txtConductors.getText().toString());
        double flux = Double.parseDouble(txtFlux.getText().toString());
        double paths = Double.parseDouble(txtPaths.getText().toString());

        double generatedEmf = machineEmfCalculator.calcutlateNewEmf(emf, polePairs, speed, conductors, flux, paths);

        txtEmf.setText(String.valueOf(generatedEmf));
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

        MachineEmf object = new MachineEmf.Builder()
                .build();

        MachineEmfDTO dto = new MachineEmfDTO.Builder()
                .tutorialId(tutId)
                .machineEmf(object)
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
        txtSpeed.setText("");
        txtEmf.setText("");
        txtPole.setText("");
        txtPaths.setText("");
        txtFlux.setText("");
    }

//done
    private boolean validations()
    {

        if(txtTutorialID.getText().toString().trim().equals("") || txtEmf.getText().toString().trim().equals(""))
        {
            return false;
        }else if(txtSpeed.getText().toString().trim().equals("") || txtConductors.getText().toString().trim().equals(""))
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
