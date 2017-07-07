/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.chains;

import com.fiki.math.lab.mathlab.chains.EquationCalculator;

/**
 *
 * @author Roman
 */
public class GeneratorMachineFormula extends EquationCalculator{

    @Override
    public String process(String type, String equationOuput) {
         if("DcGenerator".equals(type)){
            
          if(("LineCurrent".equals(equationOuput)))
             {
                 return "IL = Power(in watts) / Volts";
             }
             else if("ShuntCurrent".equals(equationOuput))
             {
                 return "Ish= V / Rsh";
             }
             
             else if("ArmatureCurrent".equals(equationOuput))
             {
                 return "Ia = IL + Ish";
             }
          else if("Emf".equals(equationOuput))
             {
                 return "E = V + (Ia X Ra)";
             }
            return "invalid DcGenerator";  

            
        }else{
            if (nextEquation!=null) {
                return nextEquation.process( type,  equationOuput);
            }
            return "invalid";
        } 
        
    }
    
}
