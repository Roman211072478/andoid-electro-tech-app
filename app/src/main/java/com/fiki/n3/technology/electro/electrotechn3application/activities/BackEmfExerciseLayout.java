package com.fiki.n3.technology.electro.electrotechn3application.activities;

import android.app.Activity;
import android.app.Dialog;
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
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.DefaultCalculatorService;
import com.fiki.n3.technology.electro.electrotechn3application.services.back.emf.BacEmfFindService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by Roman on 2016/05/06.
 */
public class BackEmfExerciseLayout extends Activity implements ServiceResultReceiver.Receiver {

    private BackEmfDTO backEmfDTO;

    private Button bClear,bSubmit,bExist,formulaBtn,calculatorBtn;
    private TextView tvBackEmf,tvIa,tvVolts,tvRa,tvDisplay,tvFormula;



    private List itemArray = new ArrayList<String>();
    private int randomChooser;
    private int currentIndex = -1;
    private UserDTO userDTO;




    private List objectList = new ArrayList<>();

    private EditText txtResult;

    public ServiceResultReceiver receiver;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_emf_exercise_landing);

        Bundle bundle = getIntent().getExtras();
        try{

            if(bundle.containsKey("loggedInUser"))
            {
                userDTO = (UserDTO) bundle.getSerializable("loggedInUser");
                tvDisplay.setText(userDTO.getDisplayName().toString());
            }
            else
            {
                Dialog d = new Dialog(BackEmfExerciseLayout.this);
                d.setTitle("Game ");
                TextView tv = new TextView(BackEmfExerciseLayout.this);

                tv.setText("This is a new game");
                d.setContentView(tv);
                d.show();
            }

        }
        catch(Exception e)
        {
            Dialog d = new Dialog(BackEmfExerciseLayout.this);
            d.setTitle(" ");
            TextView tv = new TextView(BackEmfExerciseLayout.this);

            tv.setText("no saved games");
            d.setContentView(tv);
            d.show();

        }
        //createBackEmfDTO();

        backEmfDTO = new BackEmfDTO.Builder()
                //.context(this)
                .build();

        //setItemArray();
        setInitUsableComponents();
        setItemArray();


        receiver = new ServiceResultReceiver(new Handler());
        receiver.setReceiver(this);


        Intent service;
        service = new Intent(BackEmfExerciseLayout.this, BacEmfFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", "find all");
        service.putExtra("dtoTag", backEmfDTO);
        service.putExtra("receiverTag", receiver);

        startService(service);


        setButtons();

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

                Equation equation = new Equation();
                equation.setFormula("DcMotor", "BackEmf");

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
                service.setClass(BackEmfExerciseLayout.this, DefaultCalculatorService.class);
                startService(service);
            }
        });

    }

    private void setItemArray()
    {
        itemArray.add("BackEmf");
        itemArray.add("Volts");
        itemArray.add("Ia");
        itemArray.add("Ra");

    }
    private void setSubmitButton()
    {
        bSubmit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

       if(txtResult.getText().toString() == null || txtResult.getText().toString().equals(""))
       {

       }
       else
       {
           BackEmfDTO object = (BackEmfDTO) objectList.get(currentIndex);
           double result = Double.parseDouble(txtResult.getText().toString());

           double emmbedAnswer = 0;

           switch(randomChooser)
           {
               case 0: emmbedAnswer = object.getBackEmf().result();
                   break;
               case 1: emmbedAnswer = object.getBackEmf().getVolts();
                   break;
               case 2: emmbedAnswer = object.getBackEmf().getArmatureCurrent();
                   break;
               case 3: emmbedAnswer = object.getBackEmf().getResistance();
                   break;
               default: ;


           }
            if(result == emmbedAnswer)
            {

                if(currentIndex < objectList.size())
                {

                    Dialog d = new Dialog(BackEmfExerciseLayout.this);
                    d.setTitle("title U won");
                    TextView tv = new TextView(BackEmfExerciseLayout.this);

                    tv.setText("success");
                    d.setContentView(tv);
                    d.show();


                    setExercise();
                    txtResult.setText("");
                }else {


                }

            }

       }
               }
           }

        );
    }
    private void setInitUsableComponents()
    {
        bClear = (Button)findViewById(R.id.btnClearBackEmfLanding);
        bSubmit = (Button)findViewById(R.id.btnSubmitBackEmfLanding);
        bExist  = (Button)findViewById(R.id.btnExitBackEmfLanding);

        tvBackEmf = (TextView)findViewById(R.id.textBackEmfBackEmfLanding);
        tvIa = (TextView)findViewById(R.id.textArmatureCurrentBackEmfLanding);
        tvVolts = (TextView)findViewById(R.id.textVoltsBackEmfLanding);
        tvRa = (TextView)findViewById(R.id.textArmatureResistBackEmfLanding);
        tvDisplay = (TextView)findViewById(R.id.display_name_back_emf_exercise);

        tvFormula = (TextView)findViewById(R.id.textFormulaBackEmfLanding);
        formulaBtn = (Button)findViewById(R.id.btnFormulaBackEmfLanding);

        txtResult = (EditText)findViewById(R.id.txtResultBackEmfLanding);

        calculatorBtn = (Button)findViewById(R.id.back_emf_exercise_default_calculator);

    }

    private void createBackEmfDTO()
    {
        backEmfDTO = new BackEmfDTO.Builder()
                //.context(BackEmfExerciseLayout.this)
                .build();
        //MainActivity
    }
    private  void getAllListTest()
    {
        //get all rows
        HashMap valueReturned = new HashMap();
        getFindService("find all", backEmfDTO);
    }


    private void getFindService(String sRequest, BackEmfDTO objectDTO)
    {

        Intent service;
        service = new Intent();
        service.setClass(BackEmfExerciseLayout.this,BacEmfFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", sRequest);
        service.putExtra("dtoTag", objectDTO);
        service.putExtra("receiverTag", receiver);

        startService(service);

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
        BackEmfDTO newObject;


        if(!objectMap.isEmpty())
        {
            // Get a set of the entries
            Set set = objectMap.entrySet();

            // Get an iterator
            Iterator i = set.iterator();


            while(i.hasNext())
            {
                Map.Entry me = (Map.Entry)i.next();

                newObject = (BackEmfDTO)me.getValue();
                objectList.add(newObject);
            }

        }}
    }

    private void setExercise()
    {

        currentIndex++;
        if(currentIndex < objectList.size()) {
            Random random = new Random();

            int myResult = random.nextInt(4);

            randomChooser = myResult;

            itemArray.get(myResult);//not being used

            BackEmfDTO object = (BackEmfDTO) objectList.get(currentIndex);

            tvBackEmf.setText(new String(new Double(object.getBackEmf().result()).toString()));
            tvIa.setText(new String(new Double(object.getBackEmf().getArmatureCurrent()).toString()));
            tvVolts.setText(new String(new Double(object.getBackEmf().getVolts()).toString()));
            tvRa.setText(new String(new Double(object.getBackEmf().getResistance()).toString()));

            switch (myResult) {
                case 0:
                    tvBackEmf.setText("???");
                    break;
                case 1:
                    tvVolts.setText("???");
                    break;
                case 2:
                    tvIa.setText("???");
                    break;
                case 3:
                    tvRa.setText("???");
                    break;
                default:
                    ;


            }
        }
        else{

            Dialog d = new Dialog(BackEmfExerciseLayout.this);
            d.setTitle("title U won");
            TextView tv = new TextView(BackEmfExerciseLayout.this);

            tv.setText("success no more exercises");
            d.setContentView(tv);
            d.show();

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
