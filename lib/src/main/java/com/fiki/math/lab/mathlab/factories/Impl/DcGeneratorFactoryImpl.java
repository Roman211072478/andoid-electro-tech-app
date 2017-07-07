/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.DcGenerator;
import com.fiki.math.lab.mathlab.factories.DcGeneratorFactory;

/**
 *
 * @author Roman
 */
public class DcGeneratorFactoryImpl implements DcGeneratorFactory{

    private static DcGeneratorFactoryImpl factory = null;

    private  DcGeneratorFactoryImpl() {
    }
    
    public static DcGeneratorFactoryImpl getInstance(){
        if(factory ==null)
            factory = new DcGeneratorFactoryImpl();
        return factory;
    }
    
    @Override
    public DcGenerator createDcGenerator() {
        DcGenerator dcGenerator= DcGenerator.getInstance();
        return dcGenerator;
    }
    
}
