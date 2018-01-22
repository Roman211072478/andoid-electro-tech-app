package com.fiki.n3.technology.electro.electrotechn3application.fiki.test;

import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.calculators.BackEmfCalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by root on 2017/07/21.
 */

public class BackEmfCalcTest {

    private BackEmfCalculator backEmfCalculator = new BackEmfCalculator();

    @Test
    public void addition_isCorrect() throws Exception {
        generateEmf();
        assertEquals(4, 2 + 2);

    }

    private void generateEmf()
    {
        Double emf = Double.parseDouble("219");
        Double volts = Double.parseDouble("220");
        Double ia = Double.parseDouble("4");
        Double ra = Double.parseDouble("0.1");
        double generatedEmf = backEmfCalculator.calcutlateNewEmf(emf,volts,ia,ra);

        System.out.println(generatedEmf);
    }
}
