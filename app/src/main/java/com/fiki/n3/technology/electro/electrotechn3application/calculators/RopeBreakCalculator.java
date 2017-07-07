/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fiki.n3.technology.electro.electrotechn3application.calculators;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roman
 */
public class RopeBreakCalculator {

    public RopeBreakCalculator()
    {}

    public double calculatEfficiency(double speed,double totalTension,
                                     double radius,double volts,double amps)
    {
        double outputPower = calculatOutputPower( speed, totalTension, radius);

        double inputPower = 60 * volts * amps;
        double efficiency = outputPower / inputPower;

        efficiency = efficiency * 100;

        //round of 2decimals
        efficiency = efficiency * 100;
        efficiency = Math.round(efficiency);
        efficiency = efficiency / 100;

        return efficiency ;//2 decimal places e.g (0.00)
    }

    public  double calculatOutputPower(double speed,double totalTension,
                                       double radius)
    {
        double outputPower = 2 * Math.PI * speed * totalTension * radius;
        return outputPower;
    }

    //Step 1
    //return choice for volts u can choose from to make actual senarios
    public int predictInputVolts( double outputPower)
    {
        double volts = 0;
        List<Double> voltsList = voltLists();

        double inputPower = 0;

        for(int i=0;i<voltsList.size();i++)
        {
            inputPower = voltsList.get(i) * 60 * 80; //Minimuim value
            
            /*if(inputPower < outputPower)
            {
                volts = voltsList.get(i);
                break;
            }else */if( outputPower < inputPower)
        {
            volts = voltsList.get(i);
            break;
        }
        }

        //return for menus frm volts
        if(volts == 110d)
        {
            return 1;
        }
        else if(volts == 220d)
        {
            return 2;
        }
        else if(volts == 350d)
        {
            return 3;
        }
        else if(volts == 450d)
        {
            return 4;
        }
        else if(volts == 0d)
        {
            return -1;
        }
        else{
            return 5;
        }
    }

    /*Step2
    //just to make sure the range from amps to be chosen comparing to chosen volts*/
    public  double predictAmps(double outputPower,double volts)
    {
        double amps = 0;
        double rhs = volts * 60;
        amps = outputPower  / rhs;

        amps = Math.ceil(amps);

        if(amps<5)
        {
            amps = 5;
        }

        return amps;
    }

    /*  List of possible volts */
    public  List<Double> voltLists()
    {
        List<Double> volts = new ArrayList<Double>();

        volts.add(110d);
        volts.add(220d);
        volts.add(350d);
        volts.add(450d);
        volts.add(550d);

        return volts;
    }
}
