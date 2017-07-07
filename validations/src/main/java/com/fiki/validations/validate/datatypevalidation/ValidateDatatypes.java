/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fiki.validations.validate.datatypevalidation;


import com.fiki.validations.validate.datatypevalidation.validations.BigDecimalValidation;
import com.fiki.validations.validate.datatypevalidation.validations.DoubleValidation;
import com.fiki.validations.validate.datatypevalidation.validations.IntegerValidation;

import java.math.BigDecimal;


/**
 *
 * @author Roman
 */
public class ValidateDatatypes {
    
    public ValidateDatatypes()
    {  /*Default constructor*/      }
    /*Double*/
    public boolean isDouble(String sValue)
    {       
       DoubleValidation object = new DoubleValidation();
       return object.isDouble(sValue);
    }
    public double convertToDouble(String sValue)
    {
        DoubleValidation object = new DoubleValidation();     
        return object.convertToDouble(sValue);
    }
    /*Integer*/
    public boolean isInteger(String sValue)
    {       
       IntegerValidation object = new IntegerValidation();
       return object.isInteger(sValue);
    }
    public Integer convertToInteger(String sValue)
    {
        IntegerValidation object = new IntegerValidation();     
        return object.convertToInteger(sValue);
    }
    /*Big Decimal*/
    public boolean isBigDecimal(String sValue)
    {       
       BigDecimalValidation object = new BigDecimalValidation();
       return object.isBigDecimal(sValue);
    }
    public BigDecimal convertToBigDecimal(String sValue)
    {
        BigDecimalValidation object = new BigDecimalValidation();     
        return object.convertToBigDecimal(sValue);
    }    
}
