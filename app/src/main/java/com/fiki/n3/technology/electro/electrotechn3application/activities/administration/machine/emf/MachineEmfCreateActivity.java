package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.machine.emf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.math.lab.mathlab.domain.model.MachineEmf;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.calculators.MachineEmfCalculator;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.MachineEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.services.equation.EquationServiceRunner;
import com.fiki.n3.technology.electro.electrotechn3application.services.machine.emf.MachineEmfServiceRunner;
import com.fiki.n3.technology.electro.electrotechn3application.services.tutorial.TutorialServiceRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MachineEmfCreateActivity extends Activity implements ServiceResultReceiver.Receiver {

    private MachineEmfCalculator machineEmfCalculator = new MachineEmfCalculator();
    private DialogHandler dialogHandler = new DialogHandler(MachineEmfCreateActivity.this);

    private ServiceResultReceiver receiver;
    private EditText txtEmf, txtPole, txtSpeed, txtConductors,txtFlux,txtPaths;
    private Equation equation;
    private EquationDTO equationDto;
    private Integer equationID;
    private MachineEmf machineEmfObject;

    private TutorialDTO tutorialDTO;

    private String serviceRequest = "";

    private Button btnSubmit,btnClear,btnExit;

    private TextView tvFormula,tvOuput,tvType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mach_ine_emf_create);
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

                    dialogHandler.outputMessage("Equation error", "Equation exists");
                    getAllEquation();
                }
                else{

                    this.equationID = new Integer(resultDTOFound.getSResult());

                    equationDto = new EquationDTO.Builder()
                            .copy(equationDto)
                            .equationId(new Integer(resultDTOFound.getSResult()))
                            .build();
                    dialogHandler.outputMessage("Equation Success", "created " + equationDto.getEquationId());

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


                        dialogHandler.outputMessage("Equation Success", "" + equationDto.getEquationId());


                    }
                    else
                    {

                        dialogHandler.outputMessage("Equation error", "the equation table doesnt exist");
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
                    dialogHandler.outputMessage("Tutorial error", "Please insert values");
                }
                else{

                    tutorialDTO = new TutorialDTO.Builder()
                            .TutorialId(new Integer(resultDTOFound.getSResult()))
                            .build();

                    dialogHandler.outputMessage("tutorial Success", "created " + tutorialDTO.getTutorialId() + " " + equationDto.getEquationId());

                    createMachineEmf();
                }
            }
            else
            {
                dialogHandler.outputMessage("Tutorial", "table does not exist");
            }
        }
        else if(serviceRequest.equals("machine emf"))
        {
            serviceRequest = "";

            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogHandler.outputMessage("Machine emf error", "insert problem");
            }
            else {
                clearFields();
                dialogHandler.outputMessage("Machine emf", "Successful insert");
            }

        }

    }

    //this done
    private void createTutorial() {
        TutorialDTO dto = new TutorialDTO.Builder()
                .equationId(equationDto.getEquationId())
                .screenId("MachineEmf")
                .staffId(0)
                .build();

        serviceRequest = "tutorial";
        TutorialServiceRunner service = new TutorialServiceRunner(this.getApplicationContext());
        service.createService(dto, receiver);
    }

    //done
    private void setSubmitBtn()
    {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean conditionPassed;

                if (txtEmf.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtPole.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtSpeed.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtConductors.getText().toString().trim().equals("")) {
                    conditionPassed = false;
                } else if (txtFlux.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtPaths.getText().toString().trim().equals("")) {
                    conditionPassed = false;
                } else {
                    conditionPassed = true;
                }

                if (conditionPassed == true) {

                    Double emf = Double.parseDouble(txtEmf.getText().toString());
                    Double poles = Double.parseDouble(txtPole.getText().toString());
                    Double speed = Double.parseDouble(txtSpeed.getText().toString());
                    Double conductors = Double.parseDouble(txtConductors.getText().toString());
                    Double flux = Double.parseDouble(txtFlux.getText().toString());
                    Double paths = Double.parseDouble(txtPaths.getText().toString());

                    Boolean correctInput = machineEmfCalculator.isCorrect(emf, poles, speed, conductors, flux, paths);

                    if (correctInput == true) {

                        machineEmfObject = new MachineEmf.Builder()
                                .emf(emf)
                                .armatureConductors(conductors)
                                .flux(flux)
                                .parallelPaths(paths)
                                .polePairs(poles)
                                .speedRevolution(speed)
                                .build();

                        createTutorial();
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


                } else {

                    dialogHandler.outputMessage("Info", "Please insert all values");
                }


            }
        });

        //createMachineEmf(); go in receiver
    }
    //done
    private void generateEmf()
    {
        Double emf = Double.parseDouble(txtEmf.getText().toString());
        Double poles = Double.parseDouble(txtPole.getText().toString());
        Double speed = Double.parseDouble(txtSpeed.getText().toString());
        Double conductors = Double.parseDouble(txtConductors.getText().toString());
        Double flux = Double.parseDouble(txtFlux.getText().toString());
        Double paths = Double.parseDouble(txtPaths.getText().toString());

        double generatedEmf = machineEmfCalculator.calcutlateNewEmf(emf, poles, speed, conductors, flux, paths);

        txtEmf.setText(String.valueOf(generatedEmf));
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

 //done
    private void backBtn() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MachineEmfCreateActivity.this, MachineEmfCrud.class);
                startActivity(intent);
                finish();
            }
        });



    }

    //done
    private void clearBtn() {

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();

            }
        });

    }

    //done
    private void clearFields()
    {
        txtEmf.setText("");
        txtPole.setText("");
        txtSpeed.setText("");
        txtConductors.setText("");
        txtFlux.setText("");
        txtPaths.setText("");
    }

//done
    private void initiateComponents() {

        txtEmf = (EditText)findViewById(R.id.emf_machine_emf_create_layout);
        txtPole = (EditText)findViewById(R.id.poles_machine_emf_create_layout);
        txtSpeed = (EditText)findViewById(R.id.speed_machine_emf_create_layout);
        txtConductors =(EditText)findViewById(R.id.conductors_machine_emf_create_layout);
        txtFlux =(EditText)findViewById(R.id.flux_machine_emf_create_layout);
        txtPaths =(EditText)findViewById(R.id.paths_machine_emf_create_layout);

        btnSubmit = (Button)findViewById(R.id.submit_btn_machine_emf_create_layout);
        btnClear  = (Button)findViewById(R.id.clear_btn_machine_emf_create_layout);
        btnExit = (Button)findViewById(R.id.back_btn_machine_emf_create_layout);


        tvFormula = (TextView )findViewById(R.id.formula_create_MachineEmf);
        tvOuput  = (TextView )findViewById(R.id.output_create_Machine_emf);
        tvType = (TextView )findViewById(R.id.type_create_Machine_emf);


    }

//done
    private void getAllEquation()
    {
        serviceRequest = "equation";

        EquationServiceRunner service = new EquationServiceRunner(this.getApplicationContext());

        service.findAllService(equationDto,receiver);

    }
    //done
    private void getCreateEquation()
    {
        serviceRequest = "equation";
        EquationServiceRunner service = new EquationServiceRunner(this.getApplicationContext());
        service.createService(equationDto, receiver);
    }
//done
    private void createMachineEmf()
    {
        MachineEmfDTO dto = new MachineEmfDTO.Builder()
                .tutorialId(tutorialDTO.getTutorialId())
                .machineEmf(machineEmfObject)
                .build();

        serviceRequest = "machine emf";
        MachineEmfServiceRunner service = new MachineEmfServiceRunner(this.getApplicationContext());
        service.createService(dto,receiver);
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
