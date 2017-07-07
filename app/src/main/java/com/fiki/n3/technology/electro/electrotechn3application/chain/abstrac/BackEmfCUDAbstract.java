package com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac;


import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public abstract class BackEmfCUDAbstract {
	
	 public BackEmfCUDAbstract nextValue;

    public void setNext(BackEmfCUDAbstract nextValue) {
        this.nextValue = nextValue;
    }
    
    //This function will be overidded to all subClasses
    public abstract String action(String request, BackEmfDTO backEmfDTO, ContextDTO context
    );
    
	
}
