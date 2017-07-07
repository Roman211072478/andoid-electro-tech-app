/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.PronyBreakEfficiency;
import com.fiki.math.lab.mathlab.factories.ProneBreakEfficiencyFactory;

/**
 *
 * @author Roman
 */
public class ProneBreakEfficiencyFactoryImpl implements ProneBreakEfficiencyFactory{

    private static ProneBreakEfficiencyFactoryImpl factory = null;

    private  ProneBreakEfficiencyFactoryImpl() {
    }
    
    public static ProneBreakEfficiencyFactoryImpl getInstance(){
        if(factory ==null)
            factory = new ProneBreakEfficiencyFactoryImpl();
        return factory;
    }

    @Override
    public PronyBreakEfficiency createProneBreakEfficiency(double speed, double volts,
            double current, double weight, double tension,
            double pronyBreakEfficiency, double armsLength) {

        PronyBreakEfficiency pronyBreakEfficiencyObject = new PronyBreakEfficiency.Builder()
                .armsLength(armsLength)
                .current(current)                
                .pronyBreakEfficiency(pronyBreakEfficiency)
                .speed(speed)
                .weight(weight)
                .tension(tension)
                .volts(volts)
                .build();
        
        return pronyBreakEfficiencyObject;
        
    }
    
}
