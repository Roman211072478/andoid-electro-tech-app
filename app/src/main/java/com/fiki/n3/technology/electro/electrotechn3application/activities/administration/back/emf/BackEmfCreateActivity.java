package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.back.emf;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;
import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.calculators.BackEmfCalculator;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;
import com.fiki.n3.technology.electro.electrotechn3application.services.back.emf.BackEmfServiceRunner;
import com.fiki.n3.technology.electro.electrotechn3application.services.equation.EquationServiceRunner;
import com.fiki.n3.technology.electro.electrotechn3application.services.tutorial.TutorialServiceRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Roman on 2016/05/16.
 */
public class BackEmfCreateActivity extends Activity implements ServiceResultReceiver.Receiver {

    private BackEmfCalculator backEmfCalculator = new BackEmfCalculator();
    private DialogHandler dialogHandler = new DialogHandler(BackEmfCreateActivity.this);

    private ServiceResultReceiver receiver;
    private EditText txtEmf, txtVolts,txtIa,txtARa;
    private Equation equation;
    private EquationDTO equationDto;
    private Integer equationID;
    private BackEmf backEmfObject;

    private TutorialDTO tutorialDTO;

    private String serviceRequest = "";

    private Button btnSubmit,btnClear,btnExit;

    private TextView tvFormula,tvOuput,tvType;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_emf_create_layout);

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
                       // btnSubmit.setEnabled(false);
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
        else if(serviceRequest.equals("back emf"))
        {
            serviceRequest = "";

            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogBox("Back emf error", "insert problem");
            }
            else {

                txtEmf.setText("");
                txtVolts.setText("");
                txtIa.setText("");
                txtARa.setText("");

                dialogBox("Back emf","Successful insert");
            }

        }

    }

    private void createTutorial() {
        TutorialDTO dto = new TutorialDTO.Builder()
                .equationId(equationDto.getEquationId())
                .screenId("BackEmf")
                .staffId(0)
                .build();

        serviceRequest = "tutorial";
        TutorialServiceRunner service = new TutorialServiceRunner(this.getApplicationContext());
        service.createService(dto,receiver);
    }

    private void setSubmitBtn()
    {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean conditionPassed;


                if (txtEmf.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtVolts.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtIa.getText().toString().trim().equals("")) {
                    conditionPassed = false;

                } else if (txtARa.getText().toString().trim().equals("")) {
                    conditionPassed = false;
                } else {
                    conditionPassed = true;
                }

                if (conditionPassed == true) {

                    Double emf = Double.parseDouble(txtEmf.getText().toString());
                    Double volts = Double.parseDouble(txtVolts.getText().toString());
                    Double ia = Double.parseDouble(txtIa.getText().toString());
                    Double ra = Double.parseDouble(txtARa.getText().toString());

                    Boolean correctInput = backEmfCalculator.isCorrect(emf, volts, ia, ra);

                    if (correctInput == true) {
                        backEmfObject = new BackEmf.Builder()
                                .resistance(ra)
                                .armatureCurrent(ia)
                                .volts(volts)
                                .backEmf(emf)
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

                    dialogBox("Info", "Please insert all values");
                }


            }
        });

        //createBackEmf(); go in receiver
    }
    private void generateEmf()
    {
        Double emf = Double.parseDouble(txtEmf.getText().toString());
        Double volts = Double.parseDouble(txtVolts.getText().toString());
        Double ia = Double.parseDouble(txtIa.getText().toString());
        Double ra = Double.parseDouble(txtARa.getText().toString());

        double generatedEmf = backEmfCalculator.calcutlateNewEmf(emf,volts,ia,ra);

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


    private void backBtn() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackEmfCreateActivity.this, BackEmfCrudMenuAcitivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void clearBtn() {

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtEmf.setText("");
                txtVolts.setText("");
                txtIa.setText("");
                txtARa.setText("");

            }
        });

    }


    private void initiateComponents() {

        txtEmf = (EditText)findViewById(R.id.emf_back_emf_create_layout);
        txtVolts = (EditText)findViewById(R.id.volts_back_emf_create_layout);
        txtIa = (EditText)findViewById(R.id.ia_back_emf_create_layout);
        txtARa =(EditText)findViewById(R.id.ra_back_emf_create_layout);

        btnSubmit = (Button)findViewById(R.id.submit_btn_back_emf_create_layout);
        btnClear  = (Button)findViewById(R.id.clear_btn_back_emf_create_layout);
        btnExit = (Button)findViewById(R.id.back_btn_back_emf_create_layout);


        tvFormula = (TextView )findViewById(R.id.formula_create_BackEmf);
        tvOuput  = (TextView )findViewById(R.id.output_create_back_emf);
        tvType = (TextView )findViewById(R.id.type_create_back_emf);


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
        BackEmfDTO dto = new BackEmfDTO.Builder()
                .tutorialId(tutorialDTO.getTutorialId())
                .backEmf(backEmfObject)
                .build();

        serviceRequest = "back emf";
        BackEmfServiceRunner service = new BackEmfServiceRunner(this.getApplicationContext());
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
             Dialog d = new Dialog(BackEmfCreateActivity.this);
             d.setTitle(title);
             TextView tv = new TextView(BackEmfCreateActivity.this);

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
