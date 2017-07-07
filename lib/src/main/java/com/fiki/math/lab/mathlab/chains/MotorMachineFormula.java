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
public class MotorMachineFormula extends EquationCalculator{
 
     @Override
    public String process(String type,String equationOuput) {
         if("DcMotor".equals(type)){
            
          if(("Torque".equals(equationOuput)))
             {
                 return "T = (1/pie) x ((p X flux X conductors X Ia) / parallel paths)";
             }
             else if("BackEmf".equals(equationOuput))
             {
                 return "Back Emf = V - (Ia x Ra)";
             }
             
             else if("Starter".equals(equationOuput))
             {
                 return "Ia = (V-Emf) / Ra";
             }
            return "invalid Motor";  

            
        }else{
            if (nextEquation!=null) {
                return nextEquation.process( type,  equationOuput);
            }
            return "invalid";
        } 
    }
    
    
}
