/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.functions;


import java.util.List;

/**
 *
 * @author Roman
 */
public class Multiplication {
    
    public Multiplication()
    {
        //constructor
    }
    
    public double multiplication(double num1,double num2)
    {
        return num1 * num2;
    }
    public double multiplication(List<Double> numbers)
    {
        double result = 1;
        
        for(Double number :numbers)
        {
            result = result * number;
        }
       
        return result;
    }
    
}
