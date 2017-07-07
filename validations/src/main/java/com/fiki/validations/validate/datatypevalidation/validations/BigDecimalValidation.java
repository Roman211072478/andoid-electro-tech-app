/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fiki.validations.validate.datatypevalidation.validations;

import java.math.BigDecimal;

/**
 *
 * @author Roman
 */
public class BigDecimalValidation {
    
    public BigDecimalValidation()
    {}        
      
    public boolean isBigDecimal(String sValue)
    {       
       try{
            BigDecimal value = new BigDecimal(sValue);
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
    public BigDecimal convertToBigDecimal(String sValue)
    {
        BigDecimal value;
        try{
             value = new BigDecimal(sValue);
        }
        catch(NumberFormatException e)
        {
            return new BigDecimal(-1);
        }
        catch(NullPointerException e)
        {
            return new BigDecimal(-1);
        }
        return value; 
    }
}
