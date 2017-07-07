package com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac;


import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/29.
 */
public abstract class PronyBreakFindAbstract {
	
	 public PronyBreakFindAbstract nextValue;

    public void setNext(PronyBreakFindAbstract nextValue) {
        this.nextValue = nextValue;
    }
    
    //This function will be overidded to all subClasses
    public abstract HashMap action(String request, PronyBreakDTO pronyBreakDTO, ContextDTO ctx);
    
	
}