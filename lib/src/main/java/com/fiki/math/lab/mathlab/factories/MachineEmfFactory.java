/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.MachineEmf;

/**
 *
 * @author Roman
 */
public interface MachineEmfFactory {
    
    MachineEmf createMachineEmf(double armatureConductors,
            double flux, double parallelPaths, double polePairs,
            double speedRevolution,double emf);
    
}
