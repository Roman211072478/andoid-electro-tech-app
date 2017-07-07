package com.fiki.math.lab.mathlab.calculations.electrical.machineEmf;
import com.fiki.math.lab.mathlab.CalculatorFacade;
import com.fiki.math.lab.mathlab.domain.model.MachineEmf;
import com.fiki.math.lab.mathlab.functions.Division;
import com.fiki.math.lab.mathlab.functions.Multiplication;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Roman on 2016/04/17.
 */
public class CalculateGeneratedEmf {
    private double result;

    private List numberList = new ArrayList<Double>();

    private double denominator;
    private MachineEmf object;
    private final CalculatorFacade calculator = new CalculatorFacade();
    //private final CalculatorFacade divideCalc = new Division();

    //constructor
    public CalculateGeneratedEmf(MachineEmf value)
    {
        numberList.add(2d);
        numberList.add(value.getArmatureConductors());
        numberList.add(value.getspeedRevolution());
        numberList.add(value.getPolePairs());
        numberList.add(value.getFlux());

        this.object = value;
        calculateDenominator();
    }

    private void calculateDenominator()// make a functions in calculator class and make singleton
    {
        this.denominator =(object.getParrelPaths()*60);

    }
    private void calculateNumerator()
    {



    }

    private void calculateEmf()
    {
        this.result = calculator.division(calculator.multiply(numberList),this.denominator);

    }

    //the above should be the approperiate way to end this class
    public MachineEmf generateEmf()
    {
        calculateEmf();

        MachineEmf newObject = new MachineEmf
                .Builder()
                .copy(object)
                .emf(result)
                .build();

        return newObject;
    }

}
