package com.fiki.n3.technology.electro.electrotechn3application.activities.pronybreak;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.math.lab.mathlab.domain.model.PronyBreakEfficiency;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.activities.ExerciseMainMenuActivity;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.DefaultCalculatorService;
import com.fiki.n3.technology.electro.electrotechn3application.services.prony.breakmethod.PronyBreakServerRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PronyBrakeExerciseActivity extends AppCompatActivity implements ServiceResultReceiver.Receiver{

        private DialogHandler dialogHandler = new DialogHandler(this);
        private PronyBreakDTO pronyBreakDTO;

        private Button bClear,bSubmit,bExist,formulaBtn,calculatorBtn;
        private TextView tvLength, tvVolts, tvCurrent, tvSpeed, tvWeights, tvTension,tvDisplay,tvFormula;

        private int currentIndex = -1;
        private UserDTO userDTO;

        private List objectList = new ArrayList<>();

        private EditText txtResult;

        public ServiceResultReceiver receiver;
        public double answer;

        public PronyBreakServerRunner serviceRunner = new PronyBreakServerRunner(PronyBrakeExerciseActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prony_brake_exercise_activity);

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

        pronyBreakDTO = new PronyBreakDTO.Builder()
                .build();

        //setItemArray();
        setInitUsableComponents();

        receiver = new ServiceResultReceiver(new Handler());
        receiver.setReceiver(this);

        serviceRunner.findAllService(pronyBreakDTO, receiver);

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
        setExit();

    }

    private void setExit() {
        bExist.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(PronyBrakeExerciseActivity.this,ExerciseMainMenuActivity.class);
              startActivity(i);
              finish();
          }
      }
        );
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
                equation.setFormula("Efficiency", "PronyBreak");
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
                service.setClass(PronyBrakeExerciseActivity.this, DefaultCalculatorService.class);
                startService(service);
            }
        });

    }

    private void setSubmitButton()
    {
        bSubmit.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

               if(txtResult.getText().toString() == null || txtResult.getText().toString().trim().equals(""))
               {
                   dialogHandler.outputMessage("Invalid", "Please enter in a value");
               }
               else
               {
                   PronyBreakDTO object = (PronyBreakDTO ) objectList.get(currentIndex);
                   double result = Double.parseDouble(txtResult.getText().toString());

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
        bClear = (Button)findViewById(R.id.btnClear_pronyExercise);
        bSubmit = (Button)findViewById(R.id.btnSubmit_pronyExercise);
        bExist  = (Button)findViewById(R.id.btnExit_pronyExercise);

        tvLength = (TextView)findViewById(R.id.text_length_pronyExercise);
        tvVolts = (TextView)findViewById(R.id.text_volts_pronyExercise);
        tvCurrent = (TextView)findViewById(R.id.text_current_pronyExercise);
        tvSpeed = (TextView)findViewById(R.id.text_speed_pronyExercise);
        tvDisplay = (TextView)findViewById(R.id.display_name_prony_exercise);

        tvWeights = (TextView)findViewById(R.id.text_weight_pronyExercise);
        tvTension = (TextView)findViewById(R.id.text_tension_pronyExercise);
        tvFormula = (TextView)findViewById(R.id.text_Formula_pronyExercise);
        formulaBtn = (Button)findViewById(R.id.btnFormula_pronyExercise);

        txtResult = (EditText)findViewById(R.id.txtResult_pronyExercise);

        calculatorBtn = (Button)findViewById(R.id.default_calculator_pronyExercise);
    }


    private void setRecordsToList(ResultDTO resultDTO) {

        if (resultDTO.getSResult().equals("-1")) {

        } else {
            if (resultDTO.getSResult().equals("-1")) {

            } else {
                HashMap objectMap = new HashMap();
                objectMap = resultDTO.getResult();

                objectMap = (HashMap) objectMap.get("result");

                //Object
                PronyBreakDTO newObject;
                PronyBreakEfficiency pronyBreakEfficiency = null;

                if (!objectMap.isEmpty()) {
                    // Get a set of the entries
                    Set set = objectMap.entrySet();

                    // Get an iterator
                    Iterator i = set.iterator();


                    while (i.hasNext()) {
                        Map.Entry me = (Map.Entry) i.next();

                        pronyBreakEfficiency = (PronyBreakEfficiency) me.getValue();

                        newObject = new PronyBreakDTO.Builder()
                                .pronyBreakEfficiency(pronyBreakEfficiency)
                                .tutorialId(pronyBreakEfficiency.getId())
                                .build();

                        objectList.add(newObject);
                    }

                }
            }
        }
    }

    private void setExercise()
    {

        currentIndex++;
        if(currentIndex < objectList.size()) {
            PronyBreakDTO object = (PronyBreakDTO) objectList.get(currentIndex);

            tvLength.setText(new String(new Double(object.getPronyBreakEfficiency().getArmsLength()).toString()));
            tvVolts.setText(new String(new Double(object.getPronyBreakEfficiency().getVolts()).toString()));
            tvCurrent.setText(new String(new Double(object.getPronyBreakEfficiency().getCurrent()).toString()));
            tvSpeed.setText(new String(new Double(object.getPronyBreakEfficiency().getSpeed()).toString()));
            tvWeights.setText(new String(new Double(object.getPronyBreakEfficiency().getWeight()).toString()));
            tvTension.setText(new String(new Double(object.getPronyBreakEfficiency().getTension()).toString()));

            answer = object.getPronyBreakEfficiency().result();
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
