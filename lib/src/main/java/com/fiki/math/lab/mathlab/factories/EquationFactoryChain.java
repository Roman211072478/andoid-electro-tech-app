/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.chains.EmfInArmatureFormula;
import com.fiki.math.lab.mathlab.chains.EquationCalculator;
import com.fiki.math.lab.mathlab.chains.GeneratorMachineFormula;
import com.fiki.math.lab.mathlab.chains.MotorMachineFormula;
import com.fiki.math.lab.mathlab.chains.MachineEfficencyFormula;

/**
 *
 * @author Roman
 */
public class EquationFactoryChain {
    
     public static String getEquation(String value,String value2){
        EquationCalculator chain = setUpChain();
        return chain.process(value,value2);
    }

    public static EquationCalculator setUpChain(){
        EquationCalculator a = new MotorMachineFormula();
        EquationCalculator b = new MachineEfficencyFormula();
        EquationCalculator c = new GeneratorMachineFormula();
        EquationCalculator d = new EmfInArmatureFormula();
      
        a.setNext(b);
        b.setNext(c);
        c.setNext(d);
        return a;
    }
}
