/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.SwinburneMethod;

/**
 *
 * @author Roman
 */
public interface SwinburneMethodFactory {
    public SwinburneMethod createSwinBurneMethod(
            double noLoadLoss,
            double shuntFieldLoss,
            double fullLoadAmatureLoss,

            double output,
            double input,
            double efficiency);
    
    
}
