/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fiki.validations.validate.datatypevalidation.validations;

/**
 *
 * @author Roman
 */
public class DoubleValidation {
    
    public DoubleValidation()
    {
        
    }
     public boolean isDouble(String sValue)
    {       
        try{
            double value = Double.parseDouble(sValue);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        catch(NullPointerException e)
        {
            return false;
        }
        return true;       
    }
    public double convertToDouble(String sValue)
    {
         double value;
        try{
             value = Double.parseDouble(sValue);
        }
        catch(NumberFormatException e)
        {
            return -1;
        }
        catch(NullPointerException e)
        {
            return -1;
        }
        return value;        
    }
}
