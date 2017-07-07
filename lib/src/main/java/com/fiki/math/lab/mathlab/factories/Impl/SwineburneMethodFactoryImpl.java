/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.SwinburneMethod;
import com.fiki.math.lab.mathlab.factories.SwinburneMethodFactory;

/**
 *
 * @author Roman
 */
public class SwineburneMethodFactoryImpl implements SwinburneMethodFactory{

    private static SwineburneMethodFactoryImpl factory = null;

    private SwineburneMethodFactoryImpl() {
    }
    
    public static SwineburneMethodFactoryImpl getInstance(){
        if(factory ==null)
            factory = new SwineburneMethodFactoryImpl();
        return factory;
    }

    
    @Override
    public SwinburneMethod createSwinBurneMethod(double noLoadLoss,
            double shuntFieldLoss, double fullLoadAmatureLoss, double output, double input, double efficiency)
    {
        
        SwinburneMethod swinburneMethod = new SwinburneMethod.Builder()
                .efficiency(efficiency)
                .fullLoadArmatureLoss(fullLoadAmatureLoss)
                .input(input)
                .noLoad(noLoadLoss)
                .output(output)
                .shuntFieldLoss(shuntFieldLoss)
                .build();
                
        return swinburneMethod;
    }
    
}
