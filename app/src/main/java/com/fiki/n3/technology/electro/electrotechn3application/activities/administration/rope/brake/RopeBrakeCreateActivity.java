package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.rope.brake;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.math.lab.mathlab.domain.model.RopeBreakMethod;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;


public class RopeBrakeCreateActivity extends Activity implements ServiceResultReceiver.Receiver {

    private ServiceResultReceiver receiver;

    private EditText txtSpeed, txtRadius,txtWeight, txtTension;

    private Equation equation;

    private RopeBreakMethod ropeBreakMethod;

    private Button btnSubmit,btnClear,btnExit;
    private TextView tvFormula,tvOuput,tvType;

    private TextView tvSpeedError,tvWeightError,tvRadiusError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rope_brake_create);

        receiver = new ServiceResultReceiver(new Handler());
        receiver.setReceiver(this);

        //initiate components
        initiateComponents();

        initButtons();

       getFormula();
    }

    private void initButtons() {
        // createBtn();
        clearBtn();
        setSubmitBtn();
        backBtn();
    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
/*
        ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO) resultData.getSerializable("ServiceTag");

        if(serviceRequest.equals("equation"))
        {
            serviceRequest = "";
            if(resultDTOFound.getRequest().equals("create"))
            {
                if(resultDTOFound.getSResult().equals("-1"))
                {
                    dialogBox("Equation error","Equation exists");
                    getAllEquation();
                }
                else{

                    this.equationID = new Integer(resultDTOFound.getSResult());

                    equationDto = new EquationDTO.Builder()
                            .copy(equationDto)
                            .equationId(new Integer(resultDTOFound.getSResult()))
                            .build();
                    dialogBox("Equation Success","created "+equationDto.getEquationId());

                }
            }
            else if(resultDTOFound.getRequest().equals("find all"))
            {
                if(!resultDTOFound.getSResult().equals("-1")) {
                    List<Equation> equationList;

                    equationList = getAllEquations(resultDTOFound);
                    Integer equationId = getEquationId(equationList);

                    if(equationId != -1)
                    {
                        this.equationID = equationId;
                        equationDto = new EquationDTO.Builder()
                                .equationId(equationId)
                                .build();
                        btnSubmit.setEnabled(true);

                        dialogBox("Equation Success", "" + equationDto.getEquationId());


                    }
                    else
                    {
                        btnSubmit.setEnabled(false);
                        dialogBox("Equation error","the equation table doesnt exist");
                    }

                }
            }

        }
        else  if(serviceRequest.equals("tutorial"))
        {
            serviceRequest = "";
            if(resultDTOFound.getRequest().equals("create"))
            {
                if(resultDTOFound.getSResult().equals("-1"))
                {
                    dialogBox("Tutorial error", "Please insert values");
                }
                else{

                    tutorialDTO = new TutorialDTO.Builder()
                            .TutorialId(new Integer(resultDTOFound.getSResult()))
                            .build();

                    dialogBox("tutorial Success", "created " + tutorialDTO.getTutorialId() + " " + equationDto.getEquationId());

                    createBackEmf();
                }
            }
            else
            {
                dialogBox("Tutorial","table does not exist");
            }
        }
        else if(serviceRequest.equals("rope"))
        {
            serviceRequest = "";

            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogBox("rope error", "insert problem");
            }
            else {

                txtRopeBreakEfficiency.setText("");
                txtSpeed.setText("");
                txtRadius.setText("");
                txtVolts.setText("");
                txtWeight.setText("");
                txtTension.setText("");
                txtCurrent.setText("");

                dialogBox("rope", "Successful insert");
            }

        }*/

    }


    private void setSubmitBtn()
    {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean conditionPassed;
                int errors = 0;

              if (txtSpeed.getText().toString().trim().equals("")) {
                  errors++;
                } else if (txtRadius.getText().toString().trim().equals("")) {
                  errors++;
                } else if (txtWeight.getText().toString().trim().equals("")) {
                  errors++;
                } else if (txtTension.getText().toString().trim().equals("")) {
                  errors++;
                } else {
                  errors = 0;
                }

                if (errors == 0) {
                    double speed = Double.parseDouble(txtSpeed.getText().toString());
                    double weight = Double.parseDouble(txtWeight.getText().toString());
                    double radius = Double.parseDouble(txtRadius.getText().toString());
                    double tension = Double.parseDouble(txtTension.getText().toString());

                    boolean procced = validate(weight, tension, speed, radius);

                    if(procced)
                    {
                        /*go to next screen*/

                        //Boolean correctInput = ropeBreakCalculator.isCorrect(torque, conductors, polePairs, flux, ia, paths);

                   // if (correctInput == true) {
                        ropeBreakMethod = new RopeBreakMethod.Builder()
                                .radius(radius)
                                .speed(speed)
                                .tension(tension)
                                .weight(weight)
                                .build();

                        Intent intent = new Intent(RopeBrakeCreateActivity.this,RopeBrakeCreate2Activity.class);
                        intent.putExtra("ropeBrakeObject",ropeBreakMethod);
                        startActivity(intent);

                        //createTutorial();


                }
                } else {

                    dialogBox("Info", "Please insert all values");
                }
            }
        });
    }
/* Validations */
    private boolean validate(double weight,double tension,double speed,
                             double radius)
    {
        boolean proceed = true;

        int sum = validateSpeed(speed);

        sum = sum + weightValidate(weight,tension);//true = 2
        sum = sum + validateRadius(radius);//true = 3

       if(sum==3)
       {
           return true;
       }
        else{
           return false;
       }

    }
    private int weightValidate(double weight ,double tension)
    {
        if(weight > tension && weight <= 400)
        {
            tvWeightError.setVisibility(View.INVISIBLE);
            return 1;//true
        }
        else{
            tvWeightError.setVisibility(View.VISIBLE);
        }
        return 0;//false
    }
    private int validateSpeed(double speed)
    {
        if(speed >= 100 && speed <= 1000)
        {
            tvSpeedError.setVisibility(View.INVISIBLE);
            return 1;
        }
        else{
            tvSpeedError.setVisibility(View.VISIBLE);
        }
        return 0;//not met
    }
    private int validateRadius(double radius)
    {
        if(radius >= 0d && radius <= 1d)
        {
            tvRadiusError.setVisibility(View.INVISIBLE);
            return 1;//true
        }
        else{
            tvRadiusError.setVisibility(View.VISIBLE);
        }
        return 0;//false
    }
    private void backBtn() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RopeBrakeCreateActivity.this, RopeBrakeCrudActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearBtn() {

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txtSpeed.setText("");
                txtRadius.setText("");

                txtWeight.setText("");
                txtTension.setText("");

            }
        });

    }


    private void initiateComponents() {


        txtSpeed = (EditText)findViewById(R.id.speed_rope_create_layout);
        txtRadius = (EditText)findViewById(R.id.radius_rope_create_layout);

        txtWeight =(EditText)findViewById(R.id.weight_rope_create_layout);
        txtTension = (EditText)findViewById(R.id.tention_rope_create_layout);

        btnSubmit = (Button)findViewById(R.id.submit_btn_rope_create_layout);
        btnClear  = (Button)findViewById(R.id.clear_btn_rope_create_layout);
        btnExit = (Button)findViewById(R.id.back_rope_create_layout);

        tvFormula = (TextView)findViewById(R.id.formula_rope_create_layout);
        tvOuput  = (TextView )findViewById(R.id.output_rope_create_layout);
        tvType = (TextView )findViewById(R.id.type_rope_create_layout);

        tvSpeedError = (TextView )findViewById(R.id.speed_error_rope_brake_create);
        tvWeightError = (TextView )findViewById(R.id.weight_error_rope_brake_create);

        tvRadiusError = (TextView )findViewById(R.id.radius_error_rope_brake_create);
    }

    private void getFormula() {
        String equationsType = tvType.getText().toString();
        String equationOutput  = tvOuput.getText().toString();

        equation =  new Equation();

        equation.setFormula(equationsType, equationOutput);

        tvFormula.setText(equation.getFormula());

    }

    private void dialogBox(String title,String message)
    {
        Dialog d = new Dialog(RopeBrakeCreateActivity.this);
        d.setTitle(title);
        TextView tv = new TextView(RopeBrakeCreateActivity.this);

        tv.setText(message);
        d.setContentView(tv);
        d.show();
    }


}
