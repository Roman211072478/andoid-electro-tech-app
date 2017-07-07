/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.services.Impl;

import com.fiki.math.lab.mathlab.domain.model.MachineEmf;
import com.fiki.math.lab.mathlab.services.MachineEmfService;

/**
 *
 * @author Roman
 */
public class MachineEmfServiceImp implements MachineEmfService{
    /*
        just to create the an object
    */
   
    
    public MachineEmf getMachineEmf(double armatureConductors,
            double flux, double parallelPaths, double polePairs,
            double speedRevolution) {
        
       MachineEmf object = new MachineEmf
                .Builder()
                .armatureConductors(armatureConductors)
                .flux(flux)
                .parallelPaths(parallelPaths)
                .polePairs(polePairs)
                .speedRevolution(speedRevolution)                
                .build();
       return object;
    }

  /*  public MachineEmf updateMachineEmf(MachineEmf ObjectValue) {
        
        MachineEmf object = new MachineEmf
                .Builder()
                .copy(ObjectValue)
                .
        
        
        return object;
                
    }*/
   
    
    
    
    
   
}
