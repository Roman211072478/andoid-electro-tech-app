package com.fiki.n3.technology.electro.electrotechn3application.calculators;

/**
 * Created by Roman on 2016/05/28.
 */
public class MachineEmfCalculator {

    private double total;

    public MachineEmfCalculator()
    {

    }

    public Boolean isCorrect(double emf,double poles,double speed,double conductors,
                             double flux,double paths)
    {
        double nominator = 2 * poles * conductors * speed * flux;
        double denominator = paths * 60;
        total =  nominator / denominator;

        if(emf == total)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double calcutlateNewEmf(double emf,double poles,double speed,double conductors,
                                   double flux,double paths)
    {
        total = 0;
        double nominator = 2 * poles * conductors * speed * flux;
        double denominator = paths * 60;

        total =  nominator / denominator;

        return total;
    }
}
