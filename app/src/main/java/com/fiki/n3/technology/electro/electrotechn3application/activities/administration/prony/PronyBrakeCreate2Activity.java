package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.prony;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.math.lab.mathlab.domain.model.PronyBreakEfficiency;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.calculators.PronyBrakeCalculator;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.services.equation.EquationServiceRunner;
import com.fiki.n3.technology.electro.electrotechn3application.services.prony.breakmethod.PronyBreakServerRunner;
import com.fiki.n3.technology.electro.electrotechn3application.services.tutorial.TutorialServiceRunner;
import com.fiki.validations.validate.datatypevalidation.range.RangeValidate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PronyBrakeCreate2Activity extends AppCompatActivity  implements ServiceResultReceiver.Receiver,AdapterView.OnItemSelectedListener {


    private PronyBrakeCalculator pronyBrakeCalculator = new PronyBrakeCalculator();
    
    private DialogHandler dialogHandler = new DialogHandler(PronyBrakeCreate2Activity.this);
    private ServiceResultReceiver receiver;
    private Spinner txtVolts;

    private EditText txtPronyBreakEfficiency,txtCurrent;

    private Equation equation;
    private EquationDTO equationDto;
    private Integer equationID;

    private TutorialDTO tutorialDTO;
    private String serviceRequest = "";
    private Button btnSubmit,btnClear,btnExit,btnGenerate;
    private TextView tvFormula,tvOuput,tvType;

    private TextView tvSpeedError,tvWeightError,tvCurrentError,tvRadiusError;
    private PronyBreakEfficiency pronyBreakMethod;
    private double ampChoice;
    private String[] mainVolts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prony_brake_create2_activity);

        receiver = new ServiceResultReceiver(new Handler());
        receiver.setReceiver(this);

        //initiate components
        initiateComponents();
        initButtons();

        Bundle bundle = getIntent().getExtras();
        try{
            pronyBreakMethod = (PronyBreakEfficiency) bundle.getSerializable("pronyBreakObject");
            getFormula();
        }
        catch(Exception e)
        {
            dialogHandler.outputMessage(" Input error"," No data was passed");
        }

    }
    /*_ _Methods_ _ */
    //done
    private void submitMethod()
    {
        //validation that things were chosen
        if (txtCurrent.getText().toString().trim().equals("")) {
            dialogHandler.outputMessage("on submit"," Please fill in values");
        }
        else {

            if(validateAmps())
            {//if true

                createTutorial(); //generate the efficiency
            }
            else
            {
                dialogHandler.outputMessage(" Current error", " Select values with in given range");
            }
        }
    }
    //done
    private boolean validateAmps()
    {
        RangeValidate object = new RangeValidate();

        double amps = Double.parseDouble(txtCurrent.getText().toString());
        return object.inRange(this.ampChoice,80,amps);
    }
    //done
    private void initiateComponents() {

        txtPronyBreakEfficiency = (EditText)findViewById(R.id.efficiency_prony_create2_layout);
        txtCurrent = (EditText)findViewById(R.id.current_prony_create2_layout);

        txtVolts =(Spinner)findViewById(R.id.volts_prony_create2_layout);

        btnSubmit = (Button)findViewById(R.id.submit_btn_prony_create2_layout);
        btnClear  = (Button)findViewById(R.id.clear_btn_prony_create2_layout);
        btnExit = (Button)findViewById(R.id.back_prony_create2_layout);
        btnGenerate = (Button)findViewById(R.id.generate_prony_create2_layout);

        tvFormula = (TextView)findViewById(R.id.formula_prony_create2_layout);
        tvOuput  = (TextView )findViewById(R.id.output_prony_create2_layout);
        tvType = (TextView )findViewById(R.id.type_prony_create2_layout);

        tvCurrentError = (TextView )findViewById(R.id.current_error_prony_brake_create2);
    }
    private void initButtons() {
        generateBtn();
        submitBtn();
        clearBtn();
        backBtn();
    }

    private void backBtn() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PronyBrakeCreate2Activity.this, PronyBrakeCreateActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void clearBtn() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

    }
    private void clear(){
        txtPronyBreakEfficiency.setText("");
        txtCurrent.setText("");
        txtCurrent.setEnabled(true);
        txtVolts.setEnabled(true);
        btnSubmit.setEnabled(false);
    }

    private void submitBtn() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     submitMethod();
                 }
             }
        );
    }
    /* Set Buttons*/
    //done
    private void generateBtn() {
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateEfficiency();
            }
        });
    }
    //done
    private void generateEfficiency()
    {
        //validate
        if(!txtCurrent.getText().toString().trim().equals(""))
        {
            if (validateAmps())
            {
                double current = Double.parseDouble(txtCurrent.getText().toString());

                pronyBreakMethod = new PronyBreakEfficiency.Builder()
                        .copy(pronyBreakMethod)
                        .current(current)
                        .build();
                double tensionTotal = pronyBreakMethod.getWeight() + pronyBreakMethod.getTension();
                //final step
                double efficiency = pronyBrakeCalculator.calculatEfficiency(pronyBreakMethod.getSpeed(), tensionTotal, pronyBreakMethod.getArmsLength(), pronyBreakMethod.getVolts(), pronyBreakMethod.getCurrent());

                pronyBreakMethod = new PronyBreakEfficiency.Builder()
                        .copy(pronyBreakMethod)
                        .pronyBreakEfficiency(efficiency)
                        .build();

                txtPronyBreakEfficiency.setText(String.valueOf(efficiency));
                btnSubmit.setEnabled(true);
                //disable current edit field
                txtCurrent.setEnabled(false);
                txtVolts.setEnabled(false);
                //volts
            }else
            {
                dialogHandler.outputMessage(" Current error", " Select values with in given range");
            }

        }
        else
        {
            dialogHandler.outputMessage(" Current error", " Select values with in given range");
        }
    }
    private void predictVoltsAndAmps()
    {
        double tensionTotal = pronyBreakMethod.getWeight() + pronyBreakMethod.getTension();

        //Step 1
        double outputPower = pronyBrakeCalculator.calculatOutputPower(pronyBreakMethod.getSpeed(), tensionTotal, pronyBreakMethod.getArmsLength());
        int choice = pronyBrakeCalculator.predictInputVolts(outputPower);

        List<Double> voltsList = pronyBrakeCalculator.voltLists();

        String[] volts;

        if(choice != -1)
        {
            List<Double> subList =  voltsList.subList(choice-1,voltsList.size());
            volts = new String[subList.size()];
            //set drop down box
            //for(int i= choice-1;i<voltsList.size();i++)
            for(int i= 0;i<subList.size();i++)
            {
                volts[i] = subList.get(i).toString();
                //System.out.println(voltsList.get(i));
            }

            //mainVolts = volts;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(PronyBrakeCreate2Activity.this,android.R.layout.simple_spinner_dropdown_item,volts);

            txtVolts.setAdapter(adapter);
            txtVolts.setOnItemSelectedListener(this);
        }

        //step 2
        double ampsChoice = pronyBrakeCalculator.predictAmps(outputPower, voltsList.get(choice - 1));
        tvCurrentError.setVisibility(View.VISIBLE);
        tvCurrentError.setText(ampsChoice + "A" + "-> 80A");

        this.ampChoice = ampsChoice;

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String sVolts = (String) txtVolts.getItemAtPosition(position);
        double volt = Double.parseDouble(sVolts);

        pronyBreakMethod = new PronyBreakEfficiency.Builder()
                .copy(pronyBreakMethod)
                .volts(volt)
                .build();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //done
    private void createTutorial() {
        TutorialDTO dto = new TutorialDTO.Builder()
                .equationId(equationDto.getEquationId())
                .screenId("ropeBrake")
                .staffId(0)
                .build();

        serviceRequest = "tutorial";
        TutorialServiceRunner service = new TutorialServiceRunner(this.getApplicationContext());
        service.createService(dto, receiver);
    }

    //done
    private List<Equation> getAllEquations(ResultDTO resultDTO) {

        if(resultDTO.getSResult().equals("-1"))
        {
            return null;
        }
        else {
            List<Equation> list = new ArrayList<Equation>();

            HashMap myList = new HashMap();
            HashMap objectMap = new HashMap();
            objectMap = resultDTO.getResult();

            myList = (HashMap) objectMap.get("result");

            //Object
            Equation equation;

            if (!myList.isEmpty()) {
                // Get a set of the entries
                Set set = myList.entrySet();

                // Get an iterator
                Iterator i = set.iterator();

                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();

                    equation = (Equation) me.getValue();

                    list.add(equation);
                }
                return list;
            }
        }
        return null;
    }

    //done
    private void getAllEquation()
    {
        serviceRequest = "equation";
        EquationServiceRunner service = new EquationServiceRunner(this.getApplicationContext());
        service.findAllService(equationDto, receiver);
    }
    //done
    private void getCreateEquation()
    {
        serviceRequest = "equation";
        EquationServiceRunner service = new EquationServiceRunner(this.getApplicationContext());
        service.createService(equationDto, receiver);
    }

    //done
    private void createRopeBreak()
    {
        PronyBreakDTO dto = new PronyBreakDTO.Builder()
                .tutorialId(tutorialDTO.getTutorialId())
                .pronyBreakEfficiency(pronyBreakMethod)
                .build();

        serviceRequest = "prony";
        PronyBreakServerRunner service = new PronyBreakServerRunner(this.getApplicationContext());
        service.createService(dto, receiver);
    }

    //done
    private void getFormula() {
        String equationsType = tvType.getText().toString();
        String equationOutput  = tvOuput.getText().toString();

        equation =  new Equation();

        equation.setFormula(equationsType, equationOutput);

        tvFormula.setText(equation.getFormula());

        equationDto = new EquationDTO.Builder()
                .equation(equation)
                .equationType(equationsType)
                .equationOutput(equationOutput)
                .build();

        getCreateEquation();
    }

    //done
    private void dialogBox(String title,String message)
    {
        Dialog d = new Dialog(PronyBrakeCreate2Activity.this);
        d.setTitle(title);
        TextView tv = new TextView(PronyBrakeCreate2Activity.this);

        tv.setText(message);
        d.setContentView(tv);
        d.show();
    }
    //done
    public Integer getEquationId(List<Equation> list) {

        String formula = tvFormula.getText().toString();
        Integer equationId = -1;

        for(int i= 0;i<list.size();i++)
        {
            if(list.get(i).getFormula().equals(formula))
            {
                equationId = list.get(i).getEquationId();
            }
        }


        return equationId;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
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

                    predictVoltsAndAmps();
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
                        // btnSubmit.setEnabled(true);

                        dialogBox("Equation Success", "" + equationDto.getEquationId());
                        predictVoltsAndAmps();
                    }
                    else
                    {
                        //btnSubmit.setEnabled(false);
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
                    createRopeBreak();
                }
            }
            else
            {
                dialogBox("Tutorial","table does not exist");
            }
        }
        else if(serviceRequest.equals("prony"))
        {
            serviceRequest = "";

            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogBox("create error", "insert problem");
            }
            else {


                clear();
                dialogBox("create", "Successful insert");
            }

        }
    }
}
