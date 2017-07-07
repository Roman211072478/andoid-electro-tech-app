/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.MachineEmf;
import com.fiki.math.lab.mathlab.factories.MachineEmfFactory;

/**
 *
 * @author Roman
 */
public class MachineEmfFactoryImpl implements MachineEmfFactory{
  private static MachineEmfFactoryImpl factory = null;

    private  MachineEmfFactoryImpl() {
    }
    
    public static MachineEmfFactoryImpl getInstance(){
        if(factory ==null)
            factory = new MachineEmfFactoryImpl();
        return factory;
    }

    @Override
    public MachineEmf createMachineEmf(double armatureConductors, double flux,
                                       double parallelPaths, double polePairs,
                                       double speedRevolution,double emf) {
                 MachineEmf object = new MachineEmf
                .Builder()
                .armatureConductors(armatureConductors)
                .emf(emf)
                .flux(flux)
                .parallelPaths(parallelPaths)
                .polePairs(polePairs)
                .speedRevolution(speedRevolution)                
                .build();
       return object;
    }
    
}
