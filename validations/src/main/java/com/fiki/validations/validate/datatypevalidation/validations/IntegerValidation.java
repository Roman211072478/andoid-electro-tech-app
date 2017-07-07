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
public class IntegerValidation {
    
    public IntegerValidation()
    {                
    }
    public boolean isInteger(String sValue)
    {       
       try{
            Integer value = Integer.parseInt(sValue);
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
    public Integer convertToInteger(String sValue)
    {
        Integer value;
        try{
             value = Integer.parseInt(sValue);
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
