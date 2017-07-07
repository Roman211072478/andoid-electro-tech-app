package com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac;

import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.SwineburneDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public abstract class SwineburneCUDAbstract {
	
	 public SwineburneCUDAbstract nextValue;

    public void setNext(SwineburneCUDAbstract nextValue) {
        this.nextValue = nextValue;
    }
    
    //This function will be overidded to all subClasses
    public abstract String action(String request, SwineburneDTO swineburneDTO, ContextDTO ctx);
    
	
}