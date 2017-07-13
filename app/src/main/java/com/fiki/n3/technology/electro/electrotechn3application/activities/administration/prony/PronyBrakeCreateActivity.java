package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.prony;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.math.lab.mathlab.domain.model.PronyBreakEfficiency;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.validations.validate.datatypevalidation.range.RangeValidate;

public class PronyBrakeCreateActivity extends AppCompatActivity implements ServiceResultReceiver.Receiver {

    private ServiceResultReceiver receiver;

    private EditText txtSpeed, txtArmLength,txtWeight, txtTension;

    private Equation equation;

    private PronyBreakEfficiency pronyBreakMethod;

    private Button btnSubmit,btnClear,btnExit;
    private TextView tvFormula,tvOuput,tvType;

    private TextView tvSpeedError,tvWeightError,tvRadiusError,tvTensionError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prony_brake_create_activity);

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
    }


    private void setSubmitBtn()
    {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Boolean conditionPassed;
                int errors = 0;

                if (txtSpeed.getText().toString().trim().equals("")) {
                    errors++;
                } else if (txtArmLength.getText().toString().trim().equals("")) {
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
                    double radius = Double.parseDouble(txtArmLength.getText().toString());
                    double tension = Double.parseDouble(txtTension.getText().toString());

                    boolean procced = validate(weight, tension, speed, radius);

                    if(procced)
                    {
                        pronyBreakMethod = new PronyBreakEfficiency.Builder()
                                .armsLength(radius)
                                .speed(speed)
                                .tension(tension)
                                .weight(weight)
                                .build();

                        Intent intent = new Intent(PronyBrakeCreateActivity.this,PronyBrakeCreate2Activity.class);
                        intent.putExtra("pronyBreakObject", pronyBreakMethod);
                        startActivity(intent);
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
        sum = sum + validateLength(radius);//true = 3
        sum = sum + validateTension(tension);//true = 3

        if(sum==4)
        {
            return true;
        }
        else{
            return false;
        }

    }

    private int validateTension(double tension) {
        RangeValidate object = new RangeValidate();

        if(object.inRange(0,200,tension) == true)
        {
            tvTensionError.setVisibility(View.INVISIBLE);
            return 1;
        }
        else{
            tvTensionError.setVisibility(View.VISIBLE);
        }

        return 0;
    }

    private int weightValidate(double weight ,double tension)
    {
        if( weight <= 200)
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
    private int validateLength(double radius)
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
                Intent intent = new Intent(PronyBrakeCreateActivity.this, PronyBrakeCRUDActivity.class);
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
                txtArmLength.setText("");
                txtWeight.setText("");
                txtTension.setText("");
            }
        });

    }
    private void initiateComponents() {

        txtSpeed = (EditText)findViewById(R.id.speed_prony_create_layout);
        txtArmLength = (EditText)findViewById(R.id.radius_prony_create_layout);

        txtWeight =(EditText)findViewById(R.id.weight_prony_create_layout);
        txtTension = (EditText)findViewById(R.id.tention_prony_create_layout);

        btnSubmit = (Button)findViewById(R.id.submit_btn_prony_create_layout);
        btnClear  = (Button)findViewById(R.id.clear_btn_prony_create_layout);
        btnExit = (Button)findViewById(R.id.back_prony_create_layout);

        tvFormula = (TextView)findViewById(R.id.formula_prony_create_layout);
        tvOuput  = (TextView )findViewById(R.id.output_prony_create_layout);
        tvType = (TextView )findViewById(R.id.type_prony_create_layout);

        tvSpeedError = (TextView )findViewById(R.id.speed_error_prony_brake_create);
        tvWeightError = (TextView )findViewById(R.id.weight_error_prony_brake_create);
        tvTensionError= (TextView )findViewById(R.id.tension_error_prony_brake_create);

        tvRadiusError = (TextView )findViewById(R.id.radius_error_prony_brake_create);
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
        Dialog d = new Dialog(PronyBrakeCreateActivity.this);
        d.setTitle(title);
        TextView tv = new TextView(PronyBrakeCreateActivity.this);

        tv.setText(message);
        d.setContentView(tv);
        d.show();
    }
}
