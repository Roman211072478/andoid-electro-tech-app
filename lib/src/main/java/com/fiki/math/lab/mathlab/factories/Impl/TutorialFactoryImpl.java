/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.math.lab.mathlab.domain.model.Staff;
import com.fiki.math.lab.mathlab.domain.model.Tutorial;
import com.fiki.math.lab.mathlab.factories.TutorialFactory;

/**
 *
 * @author Roman
 */
public class TutorialFactoryImpl implements TutorialFactory{
    
    private static TutorialFactoryImpl factory = null;

    private  TutorialFactoryImpl() {
    }
    
    public static TutorialFactoryImpl getInstance(){
        if(factory ==null)
            factory = new TutorialFactoryImpl();
        return factory;
    }

    
    @Override
    public Tutorial createTutorial(Integer tutorialId, String equationOutput,
            String equationType, String tutorialType,String screenId,Staff staff) {
           
        Tutorial tutorial = new Tutorial.Builder()
                .tutorialId(tutorialId)
                .tutorialType(tutorialType)
                .equationOutput(equationOutput)               
                .equationType(equationType)
                .screenId(screenId)
                .staff(staff)
                .build();
        
        return tutorial;
    }


}
