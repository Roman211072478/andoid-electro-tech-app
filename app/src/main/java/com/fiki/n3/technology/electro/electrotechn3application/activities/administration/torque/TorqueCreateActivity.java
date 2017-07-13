package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.torque;

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
import com.fiki.math.lab.mathlab.domain.model.Torque;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.calculators.TorqueCalculator;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TorqueDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.services.equation.EquationServiceRunner;
import com.fiki.n3.technology.electro.electrotechn3application.services.torque.TorqueServiceRunner;
import com.fiki.n3.technology.electro.electrotechn3application.services.tutorial.TutorialServiceRunner;
import com.fiki.validations.validate.datatypevalidation.validations.IntegerValidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TorqueCreateActivity extends Activity implements ServiceResultReceiver.Receiver {

    private TorqueCalculator torqueCalculator = new TorqueCalculator();
    private DialogHandler dialogHandler = new DialogHandler(TorqueCreateActivity.this);

    private ServiceResultReceiver receiver;

    private EditText txtTorque, txtConductors, txtFlux, txtPolePairs,txtIa,txtPaths;

    private Equation equation;
    private EquationDTO equationDto;
    private Integer equationID;
    private Torque torqueObject;

    private TutorialDTO tutorialDTO;

    private String serviceRequest = "";

    private Button btnSubmit,btnClear,btnExit;

    private TextView tvFormula,tvOuput,tvType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.torque_create_layout);

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
        else if(serviceRequest.equals("torque"))
        {
            serviceRequest = "";

            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogBox("Torque error", "insert problem");
            }
            else {

                txtTorque.setText("");
                txtConductors.setText("");
                txtFlux.setText("");
                txtPolePairs.setText("");
                txtIa.setText("");
                txtPaths.setText("");

                dialogBox("Torque", "Successful insert");
            }

        }

    }

    private void createTutorial() {
        TutorialDTO dto = new TutorialDTO.Builder()
                .equationId(equationDto.getEquationId())
                .screenId("Torque")
                .staffId(0)
                .build();

        serviceRequest = "tutorial";
        TutorialServiceRunner service = new TutorialServiceRunner(this.getApplicationContext());
        service.createService(dto, receiver);
    }

    private void setSubmitBtn()
    {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean conditionPassed;

                if (txtTorque.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtConductors.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtFlux.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtPolePairs.getText().toString().trim().equals("")) {
                    conditionPassed = false;
                } else if (txtIa.getText().toString().trim().equals("")) {
                    conditionPassed = false;
                } else if (txtPaths.getText().toString().trim().equals("")) {
                    conditionPassed = false;
                } else {
                    conditionPassed = true;
                }

                boolean pathsCheckOut = validateParallelPaths(txtPaths.getText().toString(), txtPolePairs.getText().toString());

                if (pathsCheckOut == true)
                {
                    if (conditionPassed == true) {

                        Double torque = Double.parseDouble(txtTorque.getText().toString());
                        Double conductors = Double.parseDouble(txtConductors.getText().toString());
                        Double ia = Double.parseDouble(txtIa.getText().toString());
                        Double polePairs = Double.parseDouble(txtPolePairs.getText().toString());
                        Double flux = Double.parseDouble(txtFlux.getText().toString());
                        Double paths = Double.parseDouble(txtPaths.getText().toString());

                        Boolean correctInput = torqueCalculator.isCorrect(torque, conductors, polePairs, flux, ia, paths);

                        if (correctInput == true) {
                            torqueObject = new Torque.Builder()
                                    .polePairs(polePairs)
                                    .armatureCurrent(ia)
                                    .armatureConductors(conductors)
                                    .torque(torque)
                                    .flux(flux)
                                    .parallelPaths(paths)
                                    .build();

                            createTutorial();
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


                    } else {

                        dialogBox("Info", "Please insert all values");
                    }
            }
            }
        });


    }
    private boolean validateParallelPaths(String Spaths ,String sPairs){
        IntegerValidation intValidation = new IntegerValidation();
        int paths = intValidation.convertToInteger(Spaths);
        int pairs = intValidation.convertToInteger(sPairs);

        int lapwound = pairs * 2;
        int waveWound = 2;

        if(paths == waveWound || paths == lapwound)
        {
            //pass
            return true;
        }
        else{
            //fail
            dialogHandler.outputMessage(" Parallel paths" , "You anser can be 2 or (pairs * 2)");
            return false;
        }

    }
    private void generateTorque()
    {
        Double torque = Double.parseDouble(txtTorque.getText().toString());
        Double conductors = Double.parseDouble(txtConductors.getText().toString());
        Double ia = Double.parseDouble(txtIa.getText().toString());
        Double polePairs = Double.parseDouble(txtPolePairs.getText().toString());
        Double flux = Double.parseDouble(txtFlux.getText().toString());
        Double paths = Double.parseDouble(txtPaths.getText().toString());

        double generatedEmf = torqueCalculator.calculateNewTorque(torque, conductors, polePairs, flux, ia, paths);

        txtTorque.setText(String.valueOf(generatedEmf));
    }

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


    private void backBtn() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TorqueCreateActivity.this, TorqueCrudActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearBtn() {

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtTorque.setText("");
                txtConductors.setText("");
                txtFlux.setText("");
                txtPolePairs.setText("");
                txtIa.setText("");
                txtPaths.setText("");

            }
        });

    }


    private void initiateComponents() {

        txtTorque = (EditText)findViewById(R.id.torque_torque_create_layout);
        txtConductors = (EditText)findViewById(R.id.conductors_torque_create_layout);
        txtFlux = (EditText)findViewById(R.id.flux_torque_create_layout);
        txtPolePairs =(EditText)findViewById(R.id.poles_torque_create_layout);

        txtIa=(EditText)findViewById(R.id.ia_torque_create_layout);
        txtPaths = (EditText)findViewById(R.id.paths_torque_create_layout);

        btnSubmit = (Button)findViewById(R.id.submit_btn_torque_create_layout);
        btnClear  = (Button)findViewById(R.id.clear_btn_torque_create_layout);
        btnExit = (Button)findViewById(R.id.back_btn_torque_create_layout);

        tvFormula = (TextView )findViewById(R.id.formula_create_torque);
        tvOuput  = (TextView )findViewById(R.id.output_create_torque);
        tvType = (TextView )findViewById(R.id.type_create_torque);
    }


    private void getAllEquation()
    {
        serviceRequest = "equation";

        EquationServiceRunner service = new EquationServiceRunner(this.getApplicationContext());

        service.findAllService(equationDto,receiver);

    }
    private void getCreateEquation()
    {
        serviceRequest = "equation";
        EquationServiceRunner service = new EquationServiceRunner(this.getApplicationContext());
        service.createService(equationDto, receiver);

    }

    private void createBackEmf()
    {
        TorqueDTO dto = new TorqueDTO.Builder()
                .tutorialId(tutorialDTO.getTutorialId())
                .torque(torqueObject)
                .build();

        serviceRequest = "torque";
        TorqueServiceRunner service = new TorqueServiceRunner(this.getApplicationContext());
        service.createService(dto,receiver);
    }
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

    private void dialogBox(String title,String message)
     {
        Dialog d = new Dialog(TorqueCreateActivity.this);
        d.setTitle(title);
        TextView tv = new TextView(TorqueCreateActivity.this);

        tv.setText(message);
        d.setContentView(tv);
        d.show();
    }


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
}
