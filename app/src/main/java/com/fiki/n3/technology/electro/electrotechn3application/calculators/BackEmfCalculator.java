package com.fiki.n3.technology.electro.electrotechn3application.calculators;

/**
 * Created by Roman on 2016/05/26.
 */
public class BackEmfCalculator {

    private double total;

    public BackEmfCalculator()
    {

    }

    public Boolean isCorrect(double emf,double volts,double ia,double ra)
    {
        total =  volts - ( ia * ra);

        if(emf == total)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double calcutlateNewEmf(double emf,double volts,double ia,double ra)
    {
        total = 0;
        total =  volts - ( ia * ra);

       return total;
    }
}
