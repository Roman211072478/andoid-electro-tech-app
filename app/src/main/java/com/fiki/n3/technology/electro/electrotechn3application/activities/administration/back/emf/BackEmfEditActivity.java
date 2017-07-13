package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.back.emf;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.calculators.BackEmfCalculator;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.services.back.emf.BackEmfServiceRunner;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Roman on 2016/05/16.
 */
public class BackEmfEditActivity extends Activity implements ServiceResultReceiver.Receiver{

    private BackEmfCalculator backEmfCalculator = new BackEmfCalculator();

    private DialogHandler dialogHandler = new DialogHandler(BackEmfEditActivity.this);

    private DialogHandler dialog = new DialogHandler(BackEmfEditActivity.this);
    private Button btnUpdate,btnClear,btnDelete;
    private EditText txtTutorialID, txtEmf, txtVolts,txtIa,txtRa;
    public ServiceResultReceiver receiver;
    private ListView lvBacKEmf;
    private List<BackEmfDTO> backEmfList;

    private final BackEmfServiceRunner service = new BackEmfServiceRunner(this);
    //private Button createBtn,editBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.back_emf_update_delete);

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
            backEmfList = listPopulateHander(resultDTOFound);
        }else if(resultDTOFound.getRequest().equals("delete"))
        {
            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogBox("delete", "Delete was unsuccessful");
            }
            else
            {
                Toast.makeText(BackEmfEditActivity.this, "success", Toast.LENGTH_SHORT).show();
                clearFields();
                populateService();//reload list view
            }
        }
        else if(resultDTOFound.getRequest().equals("update"))
        {
            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogBox("update","update was unsuccessful");

            }
            else
            {
                Toast.makeText(BackEmfEditActivity.this,"success",Toast.LENGTH_SHORT).show();
                clearFields();
                populateService();//reload list view
            }
        }

    }



    private List<BackEmfDTO> listPopulateHander(ResultDTO resultDTO)
    {

        lvBacKEmf.setAdapter(null);

        if(resultDTO.getSResult().equals("-1"))
        {
            lvBacKEmf.setAdapter(null);
        }
        else {
            List<String> list = new ArrayList<String>();

            List<BackEmfDTO> backEmfs = new ArrayList<BackEmfDTO>();

            HashMap myList = new HashMap();
            HashMap objectMap = new HashMap();
            objectMap = resultDTO.getResult();


            myList = (HashMap) objectMap.get("result");


            //Object
            BackEmfDTO backEmfDTO;

            if (!myList.isEmpty()) {
                // Get a set of the entries
                Set set = myList.entrySet();

                // Get an iterator
                Iterator i = set.iterator();

                list.clear();//Clear the list

                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();

                    backEmfDTO = (BackEmfDTO) me.getValue();

                    backEmfs.add(backEmfDTO);
                    list.add(backEmfDTO.getTutorialId() + " | " + backEmfDTO.getBackEmf().result() + " | " +
                            backEmfDTO.getBackEmf().getVolts()+ " | " +
                                    backEmfDTO.getBackEmf().getResistance()+ " | " +
                                    backEmfDTO.getBackEmf().getArmatureCurrent()
                            );//Add to string list
                }


                //Set Values to listview
                lvBacKEmf.setAdapter(new ArrayAdapter<String>(BackEmfEditActivity.this, android.R.layout.simple_list_item_1, list));
                return backEmfs;
            }
        }

        return null;
    }
    private void populateService() {
        BackEmfDTO dto = new BackEmfDTO.Builder()
                .build();

        service.findAllService(dto,receiver);
    }

    private void initComponents() {
        //buttons
        btnDelete = (Button) findViewById(R.id.back_emf_deleteBtn_edit_layout);
        btnUpdate = (Button) findViewById(R.id.back_emf_updateBtn_edit_layout);
        btnClear = (Button) findViewById(R.id.back_emf_clearBtn_edit_layout);

        //listview
        lvBacKEmf = (ListView)findViewById(R.id.back_emf_edit_lv);

        //inputs
        txtTutorialID = (EditText) findViewById(R.id.back_emf_tutID_edit_layout);
        txtRa = (EditText) findViewById(R.id.back_emf_RA_edit_layout);
        txtIa = (EditText) findViewById(R.id.back_emf_IA_edit_layout);
        txtEmf = (EditText) findViewById(R.id.back_emf_backEmf_edit_layout);
        txtVolts = (EditText) findViewById(R.id.back_emf_Volts_edit_layout);
    }

    private void setButtons() {
        clearButton();
        setOnItemClick();
        update();
        deleteButton();
    }
    private void clearButton()
    {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }
    private void setOnItemClick()
    {
        lvBacKEmf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String data = (String) parent.getItemAtPosition(position);


                if (!(data.trim()).equals("")) {

                    txtTutorialID.setText(backEmfList.get(position).getTutorialId().toString());
                    txtEmf.setText(String.valueOf(backEmfList.get(position).getBackEmf().result()));
                    txtIa.setText(String.valueOf(backEmfList.get(position).getBackEmf().getArmatureCurrent()));
                    txtRa.setText(String.valueOf(backEmfList.get(position).getBackEmf().getResistance()));
                    txtVolts.setText(String.valueOf(backEmfList.get(position).getBackEmf().getVolts()));
                }
            }
        });
    }

    private void update()
    {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean validate = validations();
                if (validate == false) {
                    dialogBox("edit", "Please insert all values");

                } else {

                    dialog.Confirm("Update", "Are you sure you want to update?",
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

    private void updateMethod()
    {

            //should consider putting a try and catch here for the casting
            Integer tutId = new Integer(txtTutorialID.getText().toString());
            double backEmf = Double.parseDouble(txtEmf.getText().toString());
            double volts = Double.parseDouble(txtVolts.getText().toString());
            double ia = Double.parseDouble(txtIa.getText().toString());
            double ra = Double.parseDouble(txtRa.getText().toString());

            BackEmf object = new BackEmf.Builder()
                    .backEmf(backEmf)
                    .volts(volts)
                    .armatureCurrent(ia)
                    .resistance(ra)
                    .build();

            BackEmfDTO dto = new BackEmfDTO.Builder()
                    .tutorialId(tutId)
                    .backEmf(object)
                    .build();


        Boolean correctInput = backEmfCalculator.isCorrect(backEmf, volts, ia, ra);


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
                dialogBox("Error", "Service error");
            }


    }
    private void generateEmf()
    {

        double backEmf = Double.parseDouble(txtEmf.getText().toString());
        double volts = Double.parseDouble(txtVolts.getText().toString());
        double ia = Double.parseDouble(txtIa.getText().toString());
        double ra = Double.parseDouble(txtRa.getText().toString());


        double generatedEmf = backEmfCalculator.calcutlateNewEmf(backEmf, volts, ia, ra);

        txtEmf.setText(String.valueOf(generatedEmf));
    }
    private void deleteButton()
    {

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean validate = validations();
                if (validate == false) {
                    dialog.outputMessage("delete", "Choose an item to delete");
                }
                else {
                    dialog.Confirm("Delete", "Are you sure you want to delete?",
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
    private void deleteMethod()
    {

            //should consider putting a try and catch here for the casting
            Integer tutId = new Integer(txtTutorialID.getText().toString());

            BackEmf object = new BackEmf.Builder()
                    .build();

            BackEmfDTO dto = new BackEmfDTO.Builder()
                    .tutorialId(tutId)
                    .backEmf(object)
                    .build();

            try {

                service.DeleteService(dto,receiver);

            }catch (Exception e) {
                dialogBox("Error", "Service error");
            }

    }

    private void clearFields()
    {
        txtTutorialID.setText("");
        txtRa.setText("");
        txtIa.setText("");
        txtEmf.setText("");
        txtVolts.setText("");
    }


   private void dialogBox(String title,String message)
      {
          Dialog d = new Dialog(BackEmfEditActivity.this);
          d.setTitle(title);
          TextView tv = new TextView(BackEmfEditActivity.this);

          tv.setText(message);
          d.setContentView(tv);
          d.show();
      }

    private boolean validations()
    {

        if(txtTutorialID.getText().toString().trim().equals("") || txtEmf.getText().toString().trim().equals(""))
        {
            return false;
        }else if(txtIa.getText().toString().trim().equals("") || txtRa.getText().toString().trim().equals(""))
        {
            return false;
        }
        else if(txtVolts.getText().toString().trim().equals(""))
        {
            return false;
        }
        else{
            return true;
        }

    }


}
