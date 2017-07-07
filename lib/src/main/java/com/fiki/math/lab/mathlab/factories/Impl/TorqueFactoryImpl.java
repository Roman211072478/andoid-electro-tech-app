/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.Torque;
import com.fiki.math.lab.mathlab.factories.TorqueFactory;

/**
 *
 * @author Roman
 */
public class TorqueFactoryImpl implements TorqueFactory{

    private static TorqueFactoryImpl factory = null;

    private  TorqueFactoryImpl() {
    }
    
    public static TorqueFactoryImpl getInstance(){
        if(factory ==null)
            factory = new TorqueFactoryImpl();
        return factory;
    }
    
    @Override
    public Torque createTorque(double armatureConductors, double polePairs,
                               double flux, double armatureCurrent, double parallelPaths,
                               double torque) {

        Torque torqueObject = new Torque.Builder()
                .armatureConductors(armatureConductors)
                .armatureCurrent(armatureCurrent)
                .flux(flux)
                .parallelPaths(parallelPaths)
                .polePairs(polePairs)
                .torque(torque)
                .build();
        
        return  torqueObject;
    }
    
}
