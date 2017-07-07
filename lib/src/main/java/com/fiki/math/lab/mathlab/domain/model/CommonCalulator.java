/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.domain.model;


import com.fiki.math.lab.mathlab.functions.Addition;
import com.fiki.math.lab.mathlab.functions.Division;
import com.fiki.math.lab.mathlab.functions.Multiplication;
import com.fiki.math.lab.mathlab.functions.Subtraction;

import java.util.List;

/**
 *
 * @author Roman
 */
public class CommonCalulator {
    private static CommonCalulator commonCalulator = null;
    //private CalculatorFacade calculator;
    private Division division;
    private Multiplication multiplication;
    private Subtraction subtract;
    private Addition addition;
    
    private CommonCalulator()
    {}
    
    public static CommonCalulator getInstance()
    {
        if(commonCalulator == null)
        {
            commonCalulator = new CommonCalulator();
        }
        return commonCalulator;
    }
    
    public double division(double numerator,double denominator)
    {
        return division.division(numerator,denominator);
    }
    
     public double multiply(double num1,double num2)
    {
        return multiplication.multiplication(num1, num2);
    }
    public double multiply(List<Double> numbers)
    {        
        return multiplication.multiplication(numbers);
    }
    public double subtraction(double lhs,double rhs)
    {
        return subtract.subtract(lhs, rhs);
    }
    
     public double Addition(double lhs,double rhs)
    {
        return addition.add(lhs, rhs);
    }
    public double Addition(List<Double> numbers)
    {        
        return addition.add(numbers);
    }
    
}

