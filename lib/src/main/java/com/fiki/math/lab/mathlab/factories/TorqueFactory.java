/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.Torque;

/**
 *
 * @author Roman
 */
public interface TorqueFactory {
    public Torque createTorque(  double armatureConductors,
     double polePairs,
     double flux, 
     double armatureCurrent,  
     double parallelPaths,
     double torque);
            
}
