/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.math.lab.mathlab.domain.model.Staff;
import com.fiki.math.lab.mathlab.domain.model.Tutorial;

/**
 *
 * @author Roman
 */
public interface TutorialFactory {
    Tutorial createTutorial( Integer tutorialId,String equationOutput,
                             String equationType,String tutorialType,
                             String screenId,Staff staff);
  
}
