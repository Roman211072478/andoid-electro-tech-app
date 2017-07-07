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
public abstract class EquationCalculator {
     EquationCalculator nextEquation;

    public void setNext(EquationCalculator nextEquation) {
        this.nextEquation = nextEquation;
    }
    public abstract String process(String type,String equationOuput);
  
   
}
