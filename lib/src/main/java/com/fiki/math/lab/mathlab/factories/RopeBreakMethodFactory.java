/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.RopeBreakMethod;

/**
 *
 * @author Roman
 */
public interface RopeBreakMethodFactory {
    
    public RopeBreakMethod createRopeBreakMethod( double speed,
   
     double radius,
   
     double volts, 
     double current,  
     double weight, 
     double tension, 
     double ropeBreakEfficiency);
    
}
