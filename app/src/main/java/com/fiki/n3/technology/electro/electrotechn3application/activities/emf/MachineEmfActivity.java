package com.fiki.n3.technology.electro.electrotechn3application.activities.emf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.activities.ExerciseMainMenuActivity;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.dto.MachineEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.DefaultCalculatorService;
import com.fiki.n3.technology.electro.electrotechn3application.services.machine.emf.MachineEmfServiceRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


/**
 * Created by Roman on 2016/05/16.
 */
public class MachineEmfActivity extends Activity implements ServiceResultReceiver.Receiver {


    private DialogHandler dialogHandler = new DialogHandler(this);
    private MachineEmfDTO machineEmfDTO;

    private Button bClear,bSubmit,bExist,formulaBtn,calculatorBtn;
    private TextView tvEmf, tvPolePairs, tvConductors, tvSpeed,tvFlux,tvPaths,tvDisplay,tvFormula;



    private List itemArray = new ArrayList<String>();
    private int randomChooser;
    private int currentIndex = -1;
    private UserDTO userDTO;




    private List objectList = new ArrayList<>();

    private EditText txtResult;

    public ServiceResultReceiver receiver;

    public MachineEmfServiceRunner serviceRunner = new MachineEmfServiceRunner(MachineEmfActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_emf_exersice);

        Bundle bundle = getIntent().getExtras();
        try{

            if(bundle.containsKey("loggedInUser"))
            {
                userDTO = (UserDTO) bundle.getSerializable("loggedInUser");
                tvDisplay.setText(userDTO.getDisplayName().toString());

            }
            else
            {
                dialogHandler.outputMessage("Game", "This is a new game");
            }

        }
        catch(Exception e)
        {
            dialogHandler.outputMessage("", "no saved games");
        }
        //createBackEmfDTO();

        machineEmfDTO = new MachineEmfDTO.Builder()
                //.context(this)
                .build();

        //setItemArray();
        setInitUsableComponents();
        setItemArray();

        receiver = new ServiceResultReceiver(new Handler());
        receiver.setReceiver(this);

        serviceRunner.findAllService(machineEmfDTO, receiver);

        setButtons();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_emf_exercise_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.BackEmfBackItem:
                //startActivity(new Intent(this, About.class));
                startActivity(new Intent(this, ExerciseMainMenuActivity.class));


                return true;
            case R.id.help:
                //startActivity(new Intent(this, Help.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    /*  */

    private void setButtons() {
        setSubmitButton();
        setFormulaBtn();
        defaultCalculatorCaller();
        setClearBtn();

    }

    private void setClearBtn() {
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearField();
            }
        });
    }

    private void clearField()
    {
        txtResult.setText("");
    }

    private void setFormulaBtn() {


        formulaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Equation equation =new Equation();
                equation.setFormula("MachineEmf", "Emf");

                tvFormula.setText(equation.getFormula());
            }
        });

    }

    private void defaultCalculatorCaller()
    {
        this.calculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service;
                service = new Intent();
                service.setClass(MachineEmfActivity.this, DefaultCalculatorService.class);
                startService(service);
            }
        });

    }

    private void setItemArray()
    {
        itemArray.add("Emf");
        itemArray.add("PolePairs");
        itemArray.add("Conductors");
        itemArray.add("speed");
        itemArray.add("flux");
        itemArray.add("paths");

    }
    private void setSubmitButton()
    {
        bSubmit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(txtResult.getText().toString() == null || txtResult.getText().toString().trim().equals(""))
               {

               }
               else
               {
                   MachineEmfDTO object = (MachineEmfDTO) objectList.get(currentIndex);
                   double result = Double.parseDouble(txtResult.getText().toString());

                   double emmbedAnswer = 0;

                   switch(randomChooser)
                   {
                       case 0: emmbedAnswer = object.getMachineEmf().result();
                           break;
                       case 1: emmbedAnswer = object.getMachineEmf().getPolePairs();
                           break;
                       case 2: emmbedAnswer = object.getMachineEmf().getArmatureConductors();
                           break;
                       case 3: emmbedAnswer = object.getMachineEmf().getspeedRevolution();
                           break;
                       case 4: emmbedAnswer = object.getMachineEmf().getFlux();
                           break;
                       case 5: emmbedAnswer = object.getMachineEmf().getParrelPaths();
                           break;
                       default: ;
                   }

                   if(result == emmbedAnswer)
                   {

                       if(currentIndex < objectList.size())
                       {

                           dialogHandler.outputMessage("Congradulations", "YOU WON!!!!");

                           setExercise();
                           txtResult.setText("");
                       }else {


                       }

                   }
                   else
                   {
                       dialogHandler.outputMessage("Wrong", "WRONG ANSWER!!!!");
                   }

               }
           }
       }

        );
    }
    private void setInitUsableComponents()
    {
        bClear = (Button)findViewById(R.id.btnClear_machineEmfExercise);
        bSubmit = (Button)findViewById(R.id.btnSubmit_machineEmfExercise);
        bExist  = (Button)findViewById(R.id.btnExit_machineEmfExercise);

        tvEmf = (TextView)findViewById(R.id.text_emf_machineEmfExercise);
        tvPolePairs = (TextView)findViewById(R.id.text_poles_machineEmfExercise);
        tvConductors = (TextView)findViewById(R.id.text_conductors_machineEmfExercise);
        tvSpeed = (TextView)findViewById(R.id.text_speed_machineEmfExercise);
        tvDisplay = (TextView)findViewById(R.id.display_name_machine_emf_exercise);

        tvFlux = (TextView)findViewById(R.id.text_flux_machineEmfExercise);
        tvPaths = (TextView)findViewById(R.id.text_paths_MachineEmfExercise);
        tvFormula = (TextView)findViewById(R.id.text_Formula_machineEmfExercise);
        formulaBtn = (Button)findViewById(R.id.btnFormula_machineEmfExercise);

        txtResult = (EditText)findViewById(R.id.txtResult_machineEmfExercise);

        calculatorBtn = (Button)findViewById(R.id.default_calculator_machineEmfExercise);

    }

    private void createBackEmfDTO()
    {
        machineEmfDTO = new MachineEmfDTO.Builder()
                //.context(BackEmfExerciseLayout.this)
                .build();
        //MainActivity
    }

    private  void getAllListTest()
    {
        //get all rows
        HashMap valueReturned = new HashMap();
        getFindService("find all", machineEmfDTO);
    }


    private void getFindService(String sRequest, MachineEmfDTO objectDTO)
    {
        serviceRunner.findAllService(objectDTO, receiver);
    }
    private void setRecordsToList(ResultDTO resultDTO)
    {

        if(resultDTO.getSResult().equals("-1")){

        }
        else{
            HashMap objectMap = new HashMap();
            objectMap = resultDTO.getResult();

            objectMap = (HashMap)objectMap.get("result");

            //Object
            MachineEmfDTO newObject;

            if(!objectMap.isEmpty())
            {
                // Get a set of the entries
                Set set = objectMap.entrySet();

                // Get an iterator
                Iterator i = set.iterator();


                while(i.hasNext())
                {
                    Map.Entry me = (Map.Entry)i.next();

                    newObject = (MachineEmfDTO)me.getValue();
                    objectList.add(newObject);
                }

            }}
    }

    private void setExercise()
    {

        currentIndex++;
        if(currentIndex < objectList.size()) {
            Random random = new Random();

            int myResult = random.nextInt(6);

            randomChooser = myResult;

            itemArray.get(myResult);//not being used

            MachineEmfDTO object = (MachineEmfDTO) objectList.get(currentIndex);

            tvEmf.setText(new String(new Double(object.getMachineEmf().result()).toString()));
            tvPolePairs.setText(new String(new Double(object.getMachineEmf().getPolePairs()).toString()));
            tvConductors.setText(new String(new Double(object.getMachineEmf().getArmatureConductors()).toString()));
            tvSpeed.setText(new String(new Double(object.getMachineEmf().getspeedRevolution()).toString()));
            tvFlux.setText(new String(new Double(object.getMachineEmf().getFlux()).toString()));
            tvPaths.setText(new String(new Double(object.getMachineEmf().getParrelPaths()).toString()));

            switch (myResult) {
                case 0:
                    tvEmf.setText("???");
                    break;
                case 1:
                    tvPolePairs.setText("???");
                    break;
                case 2:
                    tvConductors.setText("???");
                    break;
                case 3:
                    tvSpeed.setText("???");
                    break;
                case 4:
                    tvFlux.setText("???");
                    break;
                case 5:
                    tvPaths.setText("???");
                    break;
                default:
                    ;
            }
        }
        else{

            dialogHandler.outputMessage("Completed", "no more exercises");

            bSubmit.setEnabled(false);
        }
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO)resultData.getSerializable("ServiceTag");
        setRecordsToList(resultDTOFound);
        setExercise();
       /* ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO)resultData.getSerializable("ServiceTag");

        setRecordsToList(resultDTOFound);
        setExercise();*/
    }
}
