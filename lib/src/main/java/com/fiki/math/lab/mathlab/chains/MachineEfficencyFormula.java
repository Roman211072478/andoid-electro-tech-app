/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.chains;

/**
 *
 * @author Roman
 */
public class MachineEfficencyFormula extends EquationCalculator{

    @Override
    public String process(String type, String equationOuput) {
         if( "Efficiency".equals(type)){
             
             if(("Swine".equals(equationOuput)))
             {
                 return "Efficiency = output/input x 100%";
             }
             else if("RopeBreak".equals(equationOuput))
             {
                 return "Efficiency = (((2 x pie x N) x (W-S)r) / 60 x VI) x100";
             }
             
             else if("PronyBreak".equals(equationOuput))
             {
                 return "Efficiency = (((2 x pie x N) x (W+S)L) / 60 x VI) x100";
             }
            return "invalid Efficiency";
        }else{
            if (nextEquation!=null) {
                return nextEquation.process(type, equationOuput);
            }
            return "Invalid";
        }
    }
    
}
