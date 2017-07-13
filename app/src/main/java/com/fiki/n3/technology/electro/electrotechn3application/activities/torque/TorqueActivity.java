package com.fiki.n3.technology.electro.electrotechn3application.activities.torque;

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
import com.fiki.math.lab.mathlab.domain.model.Torque;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.activities.ExerciseMainMenuActivity;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TorqueDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.DefaultCalculatorService;
import com.fiki.n3.technology.electro.electrotechn3application.services.torque.TorqueServiceRunner;

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
public class TorqueActivity extends Activity implements ServiceResultReceiver.Receiver {


    private DialogHandler dialogHandler = new DialogHandler(this);
    private TorqueDTO torqueDTO;

    private Button bClear,bSubmit,bExist,formulaBtn,calculatorBtn;
    private TextView tvTorque, tvPolePairs, tvConductors, tvIa,tvFlux,tvPaths,tvDisplay,tvFormula;

    private List itemArray = new ArrayList<String>();//not being used
    private int randomChooser;
    private int currentIndex = -1;
    private UserDTO userDTO;

    private List objectList = new ArrayList<>();

    private EditText txtResult;

    public ServiceResultReceiver receiver;

    public TorqueServiceRunner serviceRunner = new TorqueServiceRunner(TorqueActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.torque_exercise_activity);

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
        catch(Exception e) {
            dialogHandler.outputMessage("", "no saved games");
        }
        //createBackEmfDTO();
        torqueDTO = new TorqueDTO.Builder()
                .build();

        //setItemArray();
        setInitUsableComponents();
        setItemArray();//not being used

        receiver = new ServiceResultReceiver(new Handler());
        receiver.setReceiver(this);

        serviceRunner.findAllService(torqueDTO, receiver);

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
                equation.setFormula("DcMotor", "Torque");
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
                service.setClass(TorqueActivity.this, DefaultCalculatorService.class);
                startService(service);
            }
        });
    }

    private void setItemArray()//not being used
    {
        itemArray.add("Torque");
        itemArray.add("PolePairs");
        itemArray.add("Conductors");
        itemArray.add("Ia");
        itemArray.add("Flux");
        itemArray.add("Paths");
    }
    private void setSubmitButton()
    {
        bSubmit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v)
               {
                   if(txtResult.getText().toString() == null || txtResult.getText().toString().trim().equals(""))
                   {

                   }
                   else
                   {
                       TorqueDTO object = (TorqueDTO) objectList.get(currentIndex);
                       double result = Double.parseDouble(txtResult.getText().toString());

                       double answer = 0;

                       switch(randomChooser)
                       {
                           case 0: answer = object.getTorque().result();
                               break;
                           case 1: answer = object.getTorque().getPolePairs();
                               break;
                           case 2: answer = object.getTorque().getArmatureConductors();
                               break;
                           case 3: answer = object.getTorque().getArmatureCurrent();
                               break;
                           case 4: answer = object.getTorque().getFlux();
                               break;
                           case 5: answer = object.getTorque().getParallelPaths();
                               break;
                           default: ;
                       }

                       if(result == answer)
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
        bClear = (Button)findViewById(R.id.btnClear_torque_exercise);
        bSubmit = (Button)findViewById(R.id.btnSubmit_torque_exercise);
        bExist  = (Button)findViewById(R.id.btnExit_torque_exercise);

        tvTorque = (TextView)findViewById(R.id.text_torque_torqueExercise);
        tvPolePairs = (TextView)findViewById(R.id.text_poles_torque_exercise);
        tvConductors = (TextView)findViewById(R.id.text_conductors_torque_exercise);
        tvIa = (TextView)findViewById(R.id.text_ia_torque_exercise);
        tvDisplay = (TextView)findViewById(R.id.display_name_torque_exercise);

        tvFlux = (TextView)findViewById(R.id.text_flux_torque_exercise);
        tvPaths = (TextView)findViewById(R.id.text_paths_torque_exercise);
        tvFormula = (TextView)findViewById(R.id.text_Formula_torque_exercise);
        formulaBtn = (Button)findViewById(R.id.btnFormula_torque_exercise);

        txtResult = (EditText)findViewById(R.id.txtResult_torque_exercise);

        calculatorBtn = (Button)findViewById(R.id.default_calculator_torque_exercise);
    }

    private  void getAllListTest()//not used
    {
        //get all rows
        HashMap valueReturned = new HashMap();
        getFindService("find all", torqueDTO);
    }


    private void getFindService(String sRequest, TorqueDTO objectDTO)
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
            TorqueDTO newObject;
            Torque torque=null;

            if(!objectMap.isEmpty())
            {
                // Get a set of the entries
                Set set = objectMap.entrySet();

                // Get an iterator
                Iterator i = set.iterator();


                while(i.hasNext())
                {
                    Map.Entry me = (Map.Entry)i.next();

                    torque = (Torque)me.getValue();

                    newObject = new TorqueDTO.Builder()
                            .torque(torque)
                            .tutorialId(torque.getId())
                            .build();

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

            TorqueDTO object = (TorqueDTO) objectList.get(currentIndex);

            tvTorque.setText(String.valueOf(object.getTorque().result()));
            tvPolePairs.setText(String.valueOf(object.getTorque().getPolePairs()));
            tvConductors.setText(String.valueOf(object.getTorque().getArmatureConductors()));
            tvIa.setText(String.valueOf(object.getTorque().getArmatureCurrent()));
            tvFlux.setText(String.valueOf(object.getTorque().getFlux()));
            tvPaths.setText(String.valueOf(object.getTorque().getParallelPaths()));

            switch (myResult) {
                case 0:
                    tvTorque.setText("???");
                    break;
                case 1:
                    tvPolePairs.setText("???");
                    break;
                case 2:
                    tvConductors.setText("???");
                    break;
                case 3:
                    tvIa.setText("???");
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
    }
}
