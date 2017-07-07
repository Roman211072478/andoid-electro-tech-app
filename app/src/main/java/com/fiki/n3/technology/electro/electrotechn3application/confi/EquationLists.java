package com.fiki.n3.technology.electro.electrotechn3application.confi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 2016/05/16.
 */
public class EquationLists {



    private List<String> equationOutputMachineEmfList;



    private List<String> equationOutputEffiecncyList;
    private List<String> equationOutputDcMotorList;

    public EquationLists()
    {
        loadMachineEmfEquationOuputSpinner();
        loadEfficiencyEquationOuputSpinner();
        loadDcMotorEquationOuputSpinner();

    }

    public List<String> getEquationOutputEffiecncyList() {
        return equationOutputEffiecncyList;
    }

    public List<String> getEquationOutputDcMotorList() {
        return equationOutputDcMotorList;
    }

    public List<String> getEquationOutputMachineEmfList() {
        return equationOutputMachineEmfList;
    }
    private void loadMachineEmfEquationOuputSpinner()

    {
        equationOutputMachineEmfList = new ArrayList<>();
        equationOutputMachineEmfList.add("Emf");



    }
    private void  loadEfficiencyEquationOuputSpinner()
    {
        equationOutputEffiecncyList = new ArrayList<>();
        equationOutputEffiecncyList.add("Swine");
        equationOutputEffiecncyList.add("RopeBreak");
        equationOutputEffiecncyList.add("PronyBreak");


    }
    private void  loadDcMotorEquationOuputSpinner()
    {
        equationOutputDcMotorList = new ArrayList<>();
        equationOutputDcMotorList.add("Torque");
        equationOutputDcMotorList.add("BackEmf");


    }


}
