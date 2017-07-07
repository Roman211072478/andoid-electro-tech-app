package com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac;


import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/29.
 */
public abstract class TutorialFindAbstract {
	
	 public TutorialFindAbstract nextValue;

    public void setNext(TutorialFindAbstract nextValue) {
        this.nextValue = nextValue;
    }
    
    //This function will be overidded to all subClasses
    public abstract HashMap action(String request, TutorialDTO tutorialDTO, ContextDTO ctx);
    
	
}