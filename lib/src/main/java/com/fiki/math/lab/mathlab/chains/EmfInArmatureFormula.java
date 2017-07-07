/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.chains;

import com.fiki.math.lab.mathlab.domain.model.MachineEmf;
//import com.fiki.math.lab.mathlab.domain.model.chains.EquationCalculator;

/**
 *
 * @author Roman
 */
public class EmfInArmatureFormula extends EquationCalculator{

    @Override
    public String process(String type, String equationOuput) {
       if("MachineEmf".equals(type)){
            
          if(("Emf".equals(equationOuput)))
          {
             MachineEmf emfObject = new MachineEmf.Builder().build();
             
             return emfObject.getFormula();
          
//                return "emf = (2*Conductors*speed*polePairs*flux)  / (parallel paths*60sec)";
             }
          else
          {
              return "invalid Emf";
          }
          
        }else{
            if (nextEquation!=null) {
                return nextEquation.process( type,  equationOuput);
            }
            return "invalid";
        } 
        
    }
    }
    

