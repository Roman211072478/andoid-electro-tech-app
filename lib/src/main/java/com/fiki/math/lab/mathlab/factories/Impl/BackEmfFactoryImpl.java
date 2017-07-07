/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;
import com.fiki.math.lab.mathlab.factories.BackEmfFactory;

/**
 *
 * @author Roman
 */
public class BackEmfFactoryImpl implements BackEmfFactory{

      private static BackEmfFactoryImpl factory = null;

    private  BackEmfFactoryImpl() {
    }
    
    public static BackEmfFactoryImpl getInstance(){
        if(factory ==null)
            factory = new BackEmfFactoryImpl();
        return factory;
    }
    
    @Override
    public BackEmf createBackEmf(double armatureCurrent, double resistance, double volts, double backEmf) {
             
        BackEmf backEmfObject = new BackEmf.Builder()
                .armatureCurrent(armatureCurrent)
                .backEmf(backEmf)
                .resistance(resistance)
                .volts(volts)
                .build();
        
        return backEmfObject;
    }
    
    
}
