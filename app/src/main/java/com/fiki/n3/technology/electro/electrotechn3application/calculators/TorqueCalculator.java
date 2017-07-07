package com.fiki.n3.technology.electro.electrotechn3application.calculators;

/**
 * Created by Roman on 2016/06/04.
 */
public class TorqueCalculator {

    private double total;

    public TorqueCalculator()
    {

    }

    public Boolean isCorrect(double torque,double conductors,double pairs,double flux,double armatureCurrent, double parallelPaths)
    {
        double mechanicalOutput = 1 / Math.PI;

        //two decimals

        torque = torque * 100;
        torque = Math.round(torque);
        torque = torque / 100;


        double nominator = conductors * flux *pairs * armatureCurrent;
        double denominator = parallelPaths;
        total = mechanicalOutput *  (nominator / denominator);

        total = total * 100;
        total = Math.round(total);
        total = total / 100;

        if(torque == total)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double calculateNewTorque(double torque, double conductors, double pairs, double flux, double armatureCurrent, double parallelPaths)
    {
        total = 0;
        double mechanicalOutput = 1 / Math.PI;

        torque = torque * 100;
        torque = Math.round(torque);
        torque = torque / 100;

        double nominator = conductors * flux *pairs * armatureCurrent;
        double denominator = parallelPaths;
        total = mechanicalOutput *  (nominator / denominator);

        total = total * 100;
        total = Math.round(total);
        total = total / 100;

        return total;
    }
}
