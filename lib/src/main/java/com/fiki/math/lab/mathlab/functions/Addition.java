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
public class Addition {
    
    public Addition()
    {}
    public double add(double lhs, double rhs)
    {
        return lhs + rhs;
    }
    public double add(List<Double> numbers)
    {
        double result = 0;
         for(Double number :numbers)
        {
            result = result * number;
        }
        return result;
    }
}
