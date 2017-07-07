/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;

/**
 *
 * @author Roman
 */
public interface BackEmfFactory {
    BackEmf createBackEmf(double armatureCurrent,double resistance,double volts,
            double backEmf);
}
