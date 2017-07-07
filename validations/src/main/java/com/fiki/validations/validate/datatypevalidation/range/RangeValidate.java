/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fiki.validations.validate.datatypevalidation.range;

/**
 *
 * @author Roman
 */
public class RangeValidate {
    
    public RangeValidate()
    {}
    
    public boolean inRange(int min,int max,int value)
    {
        if(value >= min && value <= max)   
        {
            return true;
        }
        return false;
    }
    public boolean inRange(double min,double max,double value)
    {
         if(value >= min && value <= max)   
        {
            return true;
        }
        return false;
    }
}
