/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.RopeBreakMethod;
import com.fiki.math.lab.mathlab.factories.RopeBreakMethodFactory;

/**
 *
 * @author Roman
 */
public class RopeBreakMethodFactoryImpl implements RopeBreakMethodFactory
{
    private static RopeBreakMethodFactoryImpl factory = null;

    private  RopeBreakMethodFactoryImpl() {
    }
    
    public static RopeBreakMethodFactoryImpl getInstance(){
        if(factory ==null)
            factory = new RopeBreakMethodFactoryImpl();
        return factory;
    }

    @Override
    public RopeBreakMethod createRopeBreakMethod(double speed, double radius, double volts,
                                                 double current, double weight, double tension,
                                                 double ropeBreakEfficiency) {
        RopeBreakMethod object = new RopeBreakMethod.Builder()
                .RopeBreakEfficiency(ropeBreakEfficiency)
                .current(current)
                .radius(radius)
                .speed(speed)
                .tension(tension)
                .weight(weight)
                .volts(volts)
                .build();
        
        return object;
    }
    
}
